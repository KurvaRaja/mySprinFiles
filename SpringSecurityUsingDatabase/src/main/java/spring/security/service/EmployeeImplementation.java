package spring.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import spring.security.entity.Employee;
import spring.security.repository.EmployeeRepository;

@Service
public class EmployeeImplementation  implements EmployeeService
{

	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private EmployeeRepository repo;
	@Override
	public Employee newUser() 
	{
		Employee emp =new Employee();
		emp.setEmail("arun@gmail.com");
		emp.setDepartment("cse");
		emp.setName("arun");
		emp.setPassword(encoder.encode("arun"));
		emp.setRole("ROLE_ADMIN");
		
		repo.save(emp);
		return null;
	}

}
