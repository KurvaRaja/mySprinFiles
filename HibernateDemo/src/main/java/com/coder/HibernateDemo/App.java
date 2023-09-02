package com.coder.HibernateDemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        

SessionFactory factory =new Configuration()
        		           .configure()
        		           .addAnnotatedClass(Employee.class)
        		           .buildSessionFactory();
        Session session = factory.openSession();
        try
        {
        	Transaction transaction = session.beginTransaction();
        	session.save(new Employee(1,"raja","arunaja@gmail.com","ndjnf","IT","ADMIn"));
        	
        	
        	
        
      
        
        	
        	Employee employee = session.get(Employee.class, 1);
        	System.out.println(employee.getEmail());
        	
        	
//        	Employee employee2 = session.get(Employee.class, 8);
//        	session.delete(employee2);
//        	System.out.println(employee2.getEmail());
        	
        	
        	Employee employee3 = session.get(Employee.class, 1);
        	employee3.setName("pavan");
        	employee3.setRole("User");
        	session.update(employee3);
        	
        	transaction.commit();
        }
        finally {
        	factory.close();
		}
       
        
    }
}
