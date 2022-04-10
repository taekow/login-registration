package com.codingdojo.authentication.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.authentication.Services.UserService;
import com.codingdojo.authentication.models.LoginUser;
import com.codingdojo.authentication.models.User;

@Controller
public class MainController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String index(@ModelAttribute("newUser") User user,
			@ModelAttribute("newLogin") LoginUser loginUser) {

		return "index.jsp";
	}

	// Register a user
	@PostMapping("/registration")
	public String registerUser(@Valid @ModelAttribute("newUser") User user, 
			BindingResult result, Model model, HttpSession session, 
			@ModelAttribute("newLogin") LoginUser loginUser) {

		// Validate user
		userService.validate(user, result);
		if (result.hasErrors()) {

			return "index.jsp";
		}

		// Register user
		userService.registerUser(user);

		// Put user in session
		session.setAttribute("loggedInUser", user);

		return "redirect:/welcome";
	}

	// Loing a user
	@PostMapping("/login")
	public String loginUser(
			@Valid @ModelAttribute("newLogin") LoginUser loginUser,
			BindingResult results, 
			HttpSession session,
			@ModelAttribute("newUser") User user) {
		
		// Authenticate a user
		userService.authenticateUser(loginUser, results);
		if(results.hasErrors()) {
			
			return "index.jsp";
		}
		
		// Put in Session
		User loggedInUser = userService.findUserByEmai(loginUser.getEmail());
		session.setAttribute("loggedInUser", loggedInUser);
		
		return "redirect:/welcome";
	}
	
	// Logout a user
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
	
	@GetMapping("/welcome")
	public String dashboard(HttpSession session) {
		if (session.getAttribute("loggedInUser") != null) {
			return "welcome.jsp";
		}
		
		return "redirect:/";
	}
	
}