package com.user.manage.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.user.manage.entities.User;
import com.user.manage.repository.UserRepository;
import com.user.manage.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController 
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository repository;
	
	@ModelAttribute
	public void commonUser(Principal principle,Model m)
	{
		if(principle!=null)
		{
		String email = principle.getName();
		User user = repository.findByEmail(email);
		m.addAttribute("user", user);
		}
		
	}
	@GetMapping("/")
	public String index()
	{
         return "index";
	}
	@GetMapping("/register")
	public String register()
	{
		 

         return "register";
	}
	
	@PostMapping("/saveUser")
	public String createUser(@ModelAttribute("user") User user,HttpSession session)
	{
		boolean checkEmail = userService.checkEmail(user.getEmail());
		if(checkEmail)
		{
			session.setAttribute("msg","Email already Exists");
			
		}
		else
		{
		    User saveUser = userService.saveUser(user);
		    if(saveUser!=null)
		    {
		    	session.setAttribute("msg", "Register Succesfully");
		    }
		    else
		    {
		    	session.setAttribute("msg", "something wrong on Server");
		    }
		    
		}
		return "redirect:/register";
	}
	@GetMapping("/signin")
	public String login()
	{
         return "login";
	}
	
//	@GetMapping("/user/profile")
//	public String profile(Principal principle,Model m)
//	{
//		String email = principle.getName();
//		//System.out.println(email);
//		User user = repository.findByEmail(email);
//		m.addAttribute("user", user);
//         return "profile";
//	}
//	@GetMapping("/user/home")
//	public String home()
//	{
//         return "home";
//	}

}
