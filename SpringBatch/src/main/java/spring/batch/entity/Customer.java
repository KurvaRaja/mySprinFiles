package spring.batch.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Customer")
public class Customer
{
	@Id
	private long customerId;
	private String gender;
	private int age;
	private double annual_Income;
	private int spending_Score;
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getAnnual_Income() {
		return annual_Income;
	}
	public void setAnnual_Income(double annual_Income) {
		this.annual_Income = annual_Income;
	}
	public int getSpending_Score() {
		return spending_Score;
	}
	public void setSpending_Score(int spending_Score) {
		this.spending_Score = spending_Score;
	}
	public Customer(long customerId, String gender, int age, double annual_Income, int spending_Score) {
		super();
		this.customerId = customerId;
		this.gender = gender;
		this.age = age;
		this.annual_Income = annual_Income;
		this.spending_Score = spending_Score;
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", gender=" + gender + ", age=" + age + ", annual_Income="
				+ annual_Income + ", spending_Score=" + spending_Score + "]";
	}
	public Customer() {
		super();
	}
	

}
