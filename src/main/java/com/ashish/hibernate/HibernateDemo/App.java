package com.ashish.hibernate.HibernateDemo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

import com.ashish.hibernate.pojo.Contactdetails;
import com.ashish.hibernate.pojo.Employee;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
    	Employee employee =  new Employee();
    	Contactdetails contactDetails = new Contactdetails();
    	contactDetails.setMobileNumber("+91-1234567890"); //Embeddable concept
    	contactDetails.setPhoneNumber("0522-1234567");//Embeddable concept
    	employee.setEmployeeId(3);
    	employee.setFirstName("Ashish");
    	employee.setLastName("Shukla");
    	employee.setAddress("Lucknow");
    	employee.setSalary(46000);
    	employee.setContactDetails(contactDetails);
    	
    	
    	Employee employeeUpdate =  new Employee();
    	Contactdetails contactDetailsUpdate = new Contactdetails();
    	contactDetailsUpdate.setMobileNumber("+911234567890");
    	contactDetailsUpdate.setPhoneNumber("05227654321");
    	employeeUpdate.setEmployeeId(3);
    	employeeUpdate.setFirstName("Ashish1Updated");
    	employeeUpdate.setLastName("Shukla1Updated");
    	employeeUpdate.setAddress("Thakurganj Lucknow Updated");
    	employeeUpdate.setSalary(51000);
    	employeeUpdate.setContactDetails(contactDetailsUpdate);  	
    	
    	Configuration con = new Configuration().configure().addAnnotatedClass(Employee.class);
    	ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
    	SessionFactory sf = con.buildSessionFactory(reg);
    	Session session =  sf.openSession();
    	Transaction transaction1 = session.beginTransaction();
    	
    	//1)Pushing data to database
    	//session.save(employee);	//--> commented for cache example
    	
    	//2)how to get values from database based on primary key ID
    	Employee ref = (Employee)session.get(Employee.class, 2);
    	System.out.println(ref);
    	
    	
    	//3)Updating database
    	session.update(employeeUpdate);
    	
    	//4)Cache
    	//Level 1 cache is enabled by default in session    	
    	Employee empObj = (Employee)session.get(Employee.class, 2);
    	System.out.println(empObj);
    	transaction1.commit();
    	session.close();
    	//we see there is only 1 select query being fired per session for same data being retrived
    	
    	//Level 2 Cache
    	Session session2 =  sf.openSession();
    	Transaction transaction2 = session2.beginTransaction();
    	Employee empObjCache = (Employee)session2.get(Employee.class, 2);
    	System.out.println(empObjCache); 	
    	transaction2.commit();
    	session2.close();    	
    	//here we see only 1 query is being fired for 2 different sessions
    	
    	
    	//5)HQL
    	Session session3 =  sf.openSession();
    	Transaction transaction3 = session3.beginTransaction();
    	Query query1 = session3.createQuery("from Employee");
    	List<Employee> employees = query1.list();
    	System.out.println("HQL Demo for list of employees");
    	for(Employee e : employees){
    		System.out.println(e);
    	}
    	
    	System.out.println("HQL Demo for just 1 employee");
    	Query query2 = session3.createQuery("from Employee where Salary=51000");
    	Employee employeeSingle  = (Employee)query2.uniqueResult();
    	System.out.println(employeeSingle);
    	
    	
    	//SQL
    	SQLQuery sqlQuery =  session3.createSQLQuery("select * from employee");
    	sqlQuery.addEntity(Employee.class);
    	List<Employee> employeesResult = sqlQuery.list();
    	
    	System.out.println("SQL Demo");
    	for(Employee e: employeesResult){
    		System.out.println(e);    		
    	}
    	
    	transaction3.commit();
    	session3.close();
    	
    	
    	//Criteria
    	Session session4 =  sf.openSession();
    	Transaction transaction4 = session4.beginTransaction();
    	
    	Criteria criteria1 =  session4.createCriteria(Employee.class);
    	criteria1.add(Restrictions.eq("FirstName", "Monu"));
    	
    	List<Employee> criteriaExample = (List<Employee>)criteria1.list();
    	System.out.println("criteria example");
    	for(Employee e: criteriaExample){
    		System.out.println(e);    		
    	}
    	
    	transaction4.commit();
    	session4.close();
    }
}
