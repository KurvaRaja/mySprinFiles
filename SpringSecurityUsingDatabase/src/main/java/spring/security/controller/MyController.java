package spring.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.security.service.EmployeeService;

@Controller
public class MyController

{
	@Autowired
	public EmployeeService service;
	
	@GetMapping("/save")
	@ResponseBody
	public void save()
	{
		service.newUser();
	}
	@GetMapping("/home")
	@PreAuthorize("hasAuthority('ROLE_USER')")
   public String home()
   {
	   return"Home";
   }
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/index")
	   public String index()
	   {
		   return"index";
	   }
	
	@GetMapping("/about")
	
	   public String about()
	   {
		   return"about";
	   }
}
