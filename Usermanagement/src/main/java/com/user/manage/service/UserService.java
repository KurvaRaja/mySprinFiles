package com.user.manage.service;

import com.user.manage.entities.User;


public interface UserService
{
	public User saveUser(User user);
	public boolean checkEmail(String email);
	public void removeSessionMessage();

}
