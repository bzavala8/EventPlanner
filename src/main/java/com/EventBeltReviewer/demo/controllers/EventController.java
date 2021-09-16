package com.EventBeltReviewer.demo.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.EventBeltReviewer.demo.services.EventService;
import com.EventBeltReviewer.demo.services.UserService;
import com.EventBeltReviewer.demo.models.User;


@SpringBootApplication
@Controller
@RequestMapping("/events")
public class EventController {
	private final EventService service;
	private final UserService userService;
	
	public EventController(EventService service, UserService userService) {
		this.service = service;
		this.userService = userService;
	}
	
	private User getUserFromSession(HttpSession session) {
		Long id = (Long)session.getAttribute("userId");
		
		if( id == null ) return null;
		
		return this.userService.findById(id);
	}
	
	@GetMapping("/dashboard")
	public String viewDashboard(Model model, HttpSession session) {
		User user = this.getUserFromSession(session);
		model.addAttribute("localEvents", this.service.eventsInState(user.getState()));
		model.addAttribute("otherEvents", this.service.eventsNotInState(user.getState()));
		return "index.jsp";
	}
	
	@PostMapping("/dashboard")
	public String newEvent(@RequestParam(value="name") String name) {
		return "random";
	}

}
