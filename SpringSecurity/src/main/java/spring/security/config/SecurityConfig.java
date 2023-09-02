package spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled=true)
@EnableWebSecurity
public class SecurityConfig
{
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	@Bean
     public UserDetailsService userDetailsService()
     {
    	 UserDetails user = User.withUsername("user").password(passwordEncoder().encode("123")).roles("USER").build();
    	 UserDetails admin = User.withUsername("admin").password(passwordEncoder().encode("123")).roles("ADMIN").build();
    	 return new InMemoryUserDetailsManager(user,admin);
     }
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		
		http.csrf().disable()
		
		.authorizeHttpRequests()
		.requestMatchers("/about")
		.permitAll()
		.requestMatchers("/**")
		.authenticated().and()
		.formLogin();
		return http.build();
	}
}
