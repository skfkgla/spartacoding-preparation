package com.sparta.springauth.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sparta.springauth.security.UserDetailsImpl;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		model.addAttribute("username", userDetails.getUsername());
		return "index";
	}
}