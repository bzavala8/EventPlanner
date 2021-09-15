package com.EventBeltReviewer.demo.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.EventBeltReviewer.demo.models.User;
import com.EventBeltReviewer.demo.services.UserService;

@SpringBootApplication
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping("/login")
	public String getLoginForm(@ModelAttribute("user") User user) {
		return "loginForm";
	}
	
	@PostMapping("/login")
	public String Login(
			@Valid User user, BindingResult result,
			RedirectAttributes redirectAttributes,
			HttpSession session
			) {
		
		if (result.hasErrors()) return "loginForm";
		
		user = this.service.login(user);
		
		if ( user == null ) {
			redirectAttributes.addFlashAttribute("errors", new ArrayList<String>(Arrays.asList("invalid credentials")));
			return "redirect:/user/login";
		}
		
		session.setAttribute("userId", user.getId());
		
		redirectAttributes.addFlashAttribute("messages", new ArrayList<String>(Arrays.asList("Welcome Back!")));
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String Logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("/register")
	public String getRegistrationForm(@ModelAttribute("user") User user) {
		return "registrationForm";
	}
	
	@PostMapping("/register")
	public String register(
			@Valid User user, BindingResult result,
			RedirectAttributes redirectAttributes
			) {
		
		if (result.hasErrors()) return "registrationForm";
		
		user = this.service.register(user);
		
		if ( user == null ) {
			redirectAttributes.addFlashAttribute("errors", new ArrayList<String>(Arrays.asList("email addres is not available")));
			return "redirect:/user/register";
		}
		
		
		// create session
		
		return "redirect:/login";
	}
	
//	@RequestMapping("/add")
//	public String getAddForm(@ModelAttribute("user") User user) {
//		return "addCategoryForm";
//	}
//	
//	@RequestMapping(path="/add", method=RequestMethod.POST)
//	public String add(
//			@Valid User user, BindingResult result,
//			RedirectAttributes redirectAttributes
//			) {
//		
//		if (result.hasErrors()) return "addCategoryForm";
//		
//		//this.service.create(category);
//		
//		redirectAttributes.addFlashAttribute("messages", new ArrayList<String>(Arrays.asList(String.format("%s has been added", user.getEmail()))));
//		
//		return "redirect:/category/add";
//	}

}
