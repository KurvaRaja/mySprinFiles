package spring.batch.config;

import org.springframework.batch.item.ItemProcessor;

import spring.batch.entity.Customer;

public class CustomerProcessor implements ItemProcessor<Customer, Customer> {

	@Override
	public Customer process(Customer item) throws Exception {
		// TODO Auto-generated method stub
		if(item.getGender().startsWith("M"))
		{
		return item;
		}
		return null;
	}

}
