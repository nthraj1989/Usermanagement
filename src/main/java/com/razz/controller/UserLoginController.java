package com.razz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.razz.pojo.UserLoginDtls;
import com.razz.service.UserManagementService;

@Controller
public class UserLoginController {
	
	@Autowired
	UserManagementService Usermanagementservice;

	@GetMapping("/login")
	public String loadloginform(Model model) {
		model.addAttribute("userlogindtls", new UserLoginDtls());
		return "login";

	}
	
	@PostMapping("/loginacc")
	public String handleloginSbtBtn(@ModelAttribute("userlogindtls") UserLoginDtls userlogindtls,Model model) {
		UserLoginDtls logineduser = Usermanagementservice.login(userlogindtls);
		if(logineduser != null) {
			model.addAttribute("user", logineduser.getEmail());
			return "dashboard";
		}
		return "error";
		
	}

}
