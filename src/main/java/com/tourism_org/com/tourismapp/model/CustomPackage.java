package com.tourism_org.com.tourismapp.model;

public class CustomPackage {

	
	private int cp_id;
	private String cp_code;
	private String country;
	private String hotel1;
	private String hotel2;
	private String activity1;
	private String activity2;
	private String number_of_people;
	private String start_date;
	private String end_date;
	private int number_of_nights;
	private boolean status;
	
	public CustomPackage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public CustomPackage(int cp_id, String cp_code, String country, String hotel1, String hotel2, String activity1,
			String activity2, String number_of_people, String start_date, String end_date, int number_of_nights) {
		super();
		this.cp_id = cp_id;
		this.cp_code = cp_code;
		this.country = country;
		this.hotel1 = hotel1;
		this.hotel2 = hotel2;
		this.activity1 = activity1;
		this.activity2 = activity2;
		this.number_of_people = number_of_people;
		this.start_date = start_date;
		this.end_date = end_date;
		this.number_of_nights = number_of_nights;
	}
	public int getCp_id() {
		return cp_id;
	}
	public void setCp_id(int cp_id) {
		this.cp_id = cp_id;
	}
	public String getCp_code() {
		return cp_code;
	}
	public void setCp_code(String cp_code) {
		this.cp_code = cp_code;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getHotel1() {
		return hotel1;
	}
	public void setHotel1(String hotel1) {
		this.hotel1 = hotel1;
	}
	public String getHotel2() {
		return hotel2;
	}
	public void setHotel2(String hotel2) {
		this.hotel2 = hotel2;
	}
	public String getActivity1() {
		return activity1;
	}
	public void setActivity1(String activity1) {
		this.activity1 = activity1;
	}
	public String getActivity2() {
		return activity2;
	}
	public void setActivity2(String activity2) {
		this.activity2 = activity2;
	}
	public String getNumber_of_people() {
		return number_of_people;
	}
	public void setNumber_of_people(String number_of_people) {
		this.number_of_people = number_of_people;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public int getNumber_of_nights() {
		return number_of_nights;
	}
	public void setNumber_of_nights(int number_of_nights) {
		this.number_of_nights = number_of_nights;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}

	
	
	
	

}
