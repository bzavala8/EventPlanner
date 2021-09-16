package com.EventBeltReviewer.demo.controllers;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		return "/user/register";
	}
}
