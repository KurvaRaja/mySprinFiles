package com.user.manage.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.user.manage.entities.User;
import com.user.manage.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController
{
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
	@GetMapping("/profile")
     public String profile()
     {
    	 return "profile";
     }
}
