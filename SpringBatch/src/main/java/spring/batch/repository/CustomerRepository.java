package spring.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.batch.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
