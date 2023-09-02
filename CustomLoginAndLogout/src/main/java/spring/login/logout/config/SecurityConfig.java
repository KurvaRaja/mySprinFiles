package spring.login.logout.config;

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
   @Bean
   public BCryptPasswordEncoder bCryptPasswordEncoder()
   {
	   return new BCryptPasswordEncoder();
   }
   @Bean
   public UserDetailsService getDetailsService()
   {
	   return new CustomeUserDetailService();
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
		http.csrf()
		.disable()
		.authorizeHttpRequests()
		.requestMatchers("/register","/signin","/saveuser")
		.permitAll()
		.requestMatchers("/profile")
		.authenticated()
		.and()
		.formLogin()
		.loginPage("/signin")
		.loginProcessingUrl("/userLogin")
	
		.defaultSuccessUrl("/home")
		.permitAll()
		//.failureUrl("/invalid")
		.and()
		.logout()
		.logoutSuccessUrl("/userlogout")
		.permitAll();
		
		
		 return http.build();
	}
}
