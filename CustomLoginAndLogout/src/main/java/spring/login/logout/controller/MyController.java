package spring.login.logout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import spring.login.logout.entity.EmpService;
import spring.login.logout.entity.Employee;

@Controller
public class MyController {
	
	@Autowired
	private EmpService empservice;
	
	@GetMapping("/home")
	public String home()
	{
		return "Home";
	}
	
	@GetMapping("/profile")
	public String profile()
	{
		return "Home";
	}
	
	
	@GetMapping("/register")
	public void register()
	{
		empservice.saveEmployee();
		
	}
	@PostMapping("/saveuser")
	public String save(@ModelAttribute("employee") Employee emp)
	{
		empservice.saveEmployee();
		return "redirect:/login";
	}
	
	
	@GetMapping("/signin")
	public String login()
	{
		return "login";
	}
	@GetMapping("/invalid")
	public String invalid()
	{
		return "invalid";
	}
	@PostMapping("/loginuser")
	public String verifyuser(@ModelAttribute("employee") Employee emp)
	{
		empservice.saveEmployee();
		return "Home";
	}
	
	@GetMapping("/userlogout")
	public String logout()
	{
		return "logout";
	}

}
