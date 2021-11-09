package com.tourism_org.com.tourismapp.model;

public class User {
 
	private int id;
	private String fname;
	private String lname;
	private int phone;
	private String email;
	private boolean srilankan;
	private String address;
	private String country;
	private String nationality;
	private String passport;
	private String password;
	

	public User () {
		
	}

	public User(int id, String fname, String lname, int phone, String address, String country,
			String nationality, boolean srilankan, String email, String passport, String password) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.phone = phone;
		this.address = address;
		this.setNationality(nationality);
		this.srilankan = srilankan;
		this.country = country;
		this.email = email;
		this.passport = passport;
		this.password = password;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean isSrilankan() {
		return srilankan;
	}

	public void setSrilankan(boolean srilankan) {
		this.srilankan = srilankan;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoginid() {
		// TODO Auto-generated method stub
		return null;
	}

	public void put(int id2, User user) {
		// TODO Auto-generated method stub
		
	}

//	public int getCardType() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	public int getCardNo() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	public int getCvv() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	public String getExp_date() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public String getPaymentdate() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public float getAmountpaid() {
//		// TODO Auto-generated method stub
//		return 0;
//	}




}
	