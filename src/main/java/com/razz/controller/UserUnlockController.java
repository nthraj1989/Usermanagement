package com.razz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.razz.pojo.UserUnlockDtls;
import com.razz.service.UserManagementService;

@Controller
public class UserUnlockController {

	@Autowired
	UserManagementService userservice;

	@GetMapping("/unlockAcc")
	public String loadUnLockForm(@RequestParam("email") String email, Model model) {
		model.addAttribute("email", email);
		UserUnlockDtls unlockdtls = new UserUnlockDtls();
		model.addAttribute("unlockdtls", unlockdtls);
		return "UnlockAccount";

	}

	@PostMapping("/unlockaccount")
	public String unlockaccount(@ModelAttribute() UserUnlockDtls userunlockdetails, Model model) {
		boolean unlockAccount = userservice.unlockAccount(userunlockdetails);
		if (unlockAccount != true) {
			model.addAttribute("msg", "Something went wrong!!! Try again");
			return "error";
		} else {
			model.addAttribute("msg", "Congrats!!!Account has been unlocked.");
			return "success";
		}

	}

}
