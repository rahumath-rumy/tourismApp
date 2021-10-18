package com.tourism_org.com.tourismapp.model;

public class User {
 
	private int customer_id;
	private String fname;
	private String lname;
	private String email;
	private String country;
	private String passport;
	private int mobile;
	private String address; 
	private String password;

	public User () {
		
	}

	public User(int customer_id, String email, String country, String passport, int mobile,
			String address, String fname, String lname, String password) {
		super();
		this.customer_id = customer_id;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.country = country;
		this.passport = passport;
		this.mobile = mobile;
		this.address = address;
		this.password = password;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public int getMobile() {
		return mobile;
	}

	public void setMobile(int mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	
}

