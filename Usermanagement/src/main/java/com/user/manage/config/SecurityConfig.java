package com.user.manage.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig 
{
	@Autowired
	public CustomAuthSuccesHandler hanlder;
   @Bean
   public BCryptPasswordEncoder bCryptPasswordEncoder()
   {
	   return new BCryptPasswordEncoder();
   }
   @Bean
   public UserDetailsService getDetailsService()
   {
	   return new CustomeUserDetailsService();
   }
   
   @Bean
   public DaoAuthenticationProvider getAuthenticationProvider()
   {
	   DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
	   authenticationProvider.setUserDetailsService(getDetailsService());
	   authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
	   return authenticationProvider;
   }
	
	@Bean
	public SecurityFilterChain filter(HttpSecurity http) throws Exception
	{
//		http.csrf()
//		.disable()
//		.authorizeHttpRequests()
//		.requestMatchers("/","/register","/signin","/saveUser")
//		.permitAll()
//		.requestMatchers("/user/**")
//		.authenticated()
//		.and()
//		.formLogin()
//		.loginPage("/signin")
//		.loginProcessingUrl("/userLogin")
//	
//		.defaultSuccessUrl("/user/profile").permitAll();
		http.csrf()
		.disable()
		.authorizeHttpRequests()
		.requestMatchers("/user/**").hasRole("USER")
		.requestMatchers("/admin/**").hasRole("ADMIN")
		.requestMatchers("/**")
		.permitAll()
		.and()
		.formLogin()
		.loginPage("/signin")
		.loginProcessingUrl("/userLogin")
		.successHandler(hanlder)
		.and()
		.logout()
		.permitAll();
		
		
		
		 return http.build();
	}
}
