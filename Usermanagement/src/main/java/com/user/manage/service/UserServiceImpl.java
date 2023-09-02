package com.user.manage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.user.manage.entities.User;
import com.user.manage.repository.UserRepository;

import jakarta.servlet.http.HttpSession;
@Service
public class UserServiceImpl  implements UserService
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	

	@Override
	public User saveUser(User user) 
	{
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		user.setRole("ROLE_USER");
		return userRepository.save(user);
	}



	@Override
	public boolean checkEmail(String email) 
	{
		
		return userRepository.existsByEmail(email);
	}



	@Override
	public void removeSessionMessage() 
	{
		HttpSession session = ( (ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		session.removeAttribute("msg");
	}
	
	
      
}
