package com.razz.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.razz.pojo.UserAccountDtl;
import com.razz.service.UserManagementService;

@Controller
public class UserManagementController {

	UserManagementService userservice;

	public UserManagementController(UserManagementService userservice) {
		super();
		this.userservice = userservice;
	}

	@GetMapping(value = { "/", "/index" })
	public String loadform(Model model) {
		model.addAttribute("user", new UserAccountDtl());
		Map<Integer, String> allcountries = userservice.getAllCountry();
		model.addAttribute("allcountries", allcountries);
		return "register";

	}

	@GetMapping("/checkduplicateemail")
	@ResponseBody
	private Boolean checkDuplicateEmail(@RequestParam("emailId") String emailId) {
		//System.out.println(emailId);
		Boolean duplicateEmail = userservice.checkDuplicateEmail(emailId);
		if (duplicateEmail!=false) {
			return true;
		}
		return false;

	}

	@GetMapping("/getstates")
	@ResponseBody
	private Map<Integer, String> getStates(@RequestParam("cid") Integer countryId) {
		// Integer countryId =2;
		System.out.println(countryId);
		Map<Integer, String> states = userservice.getStateByCountryId(countryId);

		/*
		 * for (Map.Entry<Integer, String> entry : states.entrySet()) {
		 * System.out.print(entry.getKey()); System.out.println(entry.getValue()); }
		 */
		return states;

	}

	@GetMapping("/getcities")
	@ResponseBody
	private Map<Integer, String> getCities(@RequestParam("sid") Integer stateId) {
		// Integer stateId = 3;
		Map<Integer, String> cities = userservice.getCityByStateId(stateId);

		/*
		 * for (Map.Entry<Integer, String> entry : cities.entrySet()) {
		 * System.out.print(entry.getKey()); System.out.println(entry.getValue()); }
		 */ return cities;

	}

	@PostMapping("/registration")
	public String handleSubmitBtn(@ModelAttribute("user") UserAccountDtl user, RedirectAttributes attribute) {
		UserAccountDtl saveduser = userservice.save(user);
		if (saveduser != null) {
			attribute.addFlashAttribute("msg", "Congratulation!!!Email has been sent to unlock your account");
		} else {
			attribute.addFlashAttribute("msg", "Somthing went wrong");
		}

		return "redirect:/";

	}

}
