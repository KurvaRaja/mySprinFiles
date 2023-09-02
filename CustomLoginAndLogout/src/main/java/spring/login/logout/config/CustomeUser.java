package spring.login.logout.config;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import spring.login.logout.entity.Employee;

public class CustomeUser  implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private Employee emp;
	
	public CustomeUser(Employee emp) {
		
		super();
		this.emp = emp;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		SimpleGrantedAuthority authority=new SimpleGrantedAuthority(emp.getRole());
		
		return Arrays.asList(authority);
	}

	@Override
	public String getPassword() 
	{
		return emp.getPassword();
	}

	@Override
	public String getUsername() {
		
		return emp.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
