package com.ashish.hibernate.pojo;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name="Employee")
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Employee {
	
	@Id
	private int EmployeeId;
	private String FirstName;
	@Column(name="LastName") //create column with this name
	private String LastName;
	//@Transient	//skip this
	private String Address;
	private int Salary;
	private Contactdetails contactDetails;
	
	public int getEmployeeId() {
		return EmployeeId;
	}
	public void setEmployeeId(int employeeId) {
		EmployeeId = employeeId;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public int getSalary() {
		return Salary;
	}
	public void setSalary(int salary) {
		Salary = salary;
	}	
	public Contactdetails getContactDetails() {
		return contactDetails;
	}
	public void setContactDetails(Contactdetails contactDetails) {
		this.contactDetails = contactDetails;
	}
	@Override
	public String toString() {
		return "Employee [EmployeeId=" + EmployeeId + ", FirstName=" + FirstName + ", LastName=" + LastName
				+ ", Address=" + Address + ", Salary=" + Salary + ", contactDetails=" + contactDetails + "]";
	}	
	
}
