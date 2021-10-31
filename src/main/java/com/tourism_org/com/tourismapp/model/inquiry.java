package com.tourism_org.com.tourismapp.model;

public class inquiry {

	private int inquiry_id;
	private String email;
	private String desc;
	
	public inquiry () {
		
	}
	
	public inquiry(int inquiry_id, String email, String desc) {
		super();
		this.inquiry_id = inquiry_id;
		this.email = email;
		this.desc = desc;
	}


	public int getInquiry_id() {
		return inquiry_id;
	}


	public void setInquiry_id(int inquiry_id) {
		this.inquiry_id = inquiry_id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
}


