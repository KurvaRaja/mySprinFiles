package spring.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController

{
	@GetMapping("/home")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
   public String home()
   {
	   return"Home";
   }
	@GetMapping("/index")
	@PreAuthorize("hasAuthority('ROLE_USER')")
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
