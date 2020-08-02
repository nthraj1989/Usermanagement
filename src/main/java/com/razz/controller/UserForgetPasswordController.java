package com.razz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.razz.pojo.UserForgetPasswordDtls;
import com.razz.service.UserManagementService;

@Controller
public class UserForgetPasswordController {

	@Autowired
	UserManagementService usermanagementservice;

	@GetMapping("/forgetpwd")
	public String loadForgetForm(Model model) {
		model.addAttribute("forgetpassword", new UserForgetPasswordDtls());
		return "forget";
	}

	@PostMapping("/sendforgetpsswd")
	public String handleForgetPwdSubmitBtn(@ModelAttribute("forgetpassword") UserForgetPasswordDtls forgetpassword,
			Model model) {
		Boolean returnedpassword = usermanagementservice.forgetPassword(forgetpassword.getEmail());
		if (returnedpassword != false) {
			model.addAttribute("successmsg", "Password has been to to your mobile and email");
			return "forget";
		} else {
			model.addAttribute("errormsg", "Something went wrong!! Try again");
			return "forget";
		}

	}

}
