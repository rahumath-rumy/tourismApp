package com.tourism_org.com.tourismapp.model;

public class admin {

	private int admin_id;
	private String fname;
	private String lname;
	private String email;
	private int mobile;
	private String address;
	private boolean admin_control;
	private String password;

	public admin () {
		
	}

	public admin(int admin_id, String fname, String lname, String email, int mobile, String address, boolean admin_control, String password) {
		super();
		this.admin_id = admin_id;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.mobile = mobile;
		this.setAddress(address);
		this.setAdmin_control(admin_control);
		this.password = password;
	}

	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getAloginid() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isAdmin_control() {
		return admin_control;
	}

	public void setAdmin_control(boolean admin_control) {
		this.admin_control = admin_control;
	}

	
	
}
