package spring.login.logout.entity;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface  EmployeeRepository  extends JpaRepository<Employee, Integer>{

	public Employee findByEmail(String email);
}
