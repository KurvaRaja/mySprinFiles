package spring.login.logout.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import spring.login.logout.entity.Employee;
import spring.login.logout.entity.EmployeeRepository;
@Component
public class CustomeUserDetailService implements UserDetailsService 
{
  @Autowired
	private EmployeeRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee emp = repo.findByEmail(username);
	
		if(emp==null)
		{
			throw new UsernameNotFoundException("Username Not Found Exception");
		}
		System.out.println(emp.getEmail());
		return  new CustomeUser(emp);
	}
}
