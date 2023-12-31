package spring.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.security.entity.Employee;

@Repository
public interface  EmployeeRepository  extends JpaRepository<Employee, Integer>{

	public Employee findByEmail(String email);
}
