package spring.login.logout.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmpImpl  implements EmpService{

	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private EmployeeRepository repo;
	@Override
	public void saveEmployee() 
	{
		
		Employee emp =new Employee();
		emp.setEmail("karan@gmail.com");
		emp.setDepartment("cse");
		emp.setName("arun");
		emp.setPassword(encoder.encode("karan"));
		emp.setRole("ROLE_ADMIN");
		repo.save(emp);
		
	}
	

}
