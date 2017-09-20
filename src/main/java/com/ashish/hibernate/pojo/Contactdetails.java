package com.ashish.hibernate.pojo;

import javax.persistence.Embeddable;

@Embeddable
public class Contactdetails {
	
	private String phoneNumber;
	private String mobileNumber;

	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	@Override
	public String toString() {
		return "Contactdetails [phoneNumber=" + phoneNumber + ", mobileNumber=" + mobileNumber + "]";
	}
}
