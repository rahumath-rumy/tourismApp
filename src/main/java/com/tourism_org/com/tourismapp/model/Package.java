package com.tourism_org.com.tourismapp.model;

public class Package {

	private int package_code;
	private String package_name;
	private String country;
	private String country_area1;
	private String country_area2;
	private String number_of_nights;
	private String weekly_schedule;
	private String start_date;
	private String end_date;
	private String hotel1;
	private String hotel2;
	private String activity1;
	private String activity2;
	private String description;
	private String price_per_person;
	public Package() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Package(int package_code, String package_name, String country, String country_area1, String country_area2,
			String number_of_nights, String weekly_schedule, String start_date, String end_date, String hotel1,
			String hotel2, String activity1, String activity2, String description, String price_per_person) {
		super();
		this.package_code = package_code;
		this.package_name = package_name;
		this.country = country;
		this.country_area1 = country_area1;
		this.country_area2 = country_area2;
		this.number_of_nights = number_of_nights;
		this.weekly_schedule = weekly_schedule;
		this.start_date = start_date;
		this.end_date = end_date;
		this.hotel1 = hotel1;
		this.hotel2 = hotel2;
		this.activity1 = activity1;
		this.activity2 = activity2;
		this.description = description;
		this.price_per_person = price_per_person;
	}
	public int getPackage_code() {
		return package_code;
	}
	public void setPackage_code(int package_code) {
		this.package_code = package_code;
	}
	public String getPackage_name() {
		return package_name;
	}
	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCountry_area1() {
		return country_area1;
	}
	public void setCountry_area1(String country_area1) {
		this.country_area1 = country_area1;
	}
	public String getCountry_area2() {
		return country_area2;
	}
	public void setCountry_area2(String country_area2) {
		this.country_area2 = country_area2;
	}
	public String getNumber_of_nights() {
		return number_of_nights;
	}
	public void setNumber_of_nights(String number_of_nights) {
		this.number_of_nights = number_of_nights;
	}
	public String getWeekly_schedule() {
		return weekly_schedule;
	}
	public void setWeekly_schedule(String weekly_schedule) {
		this.weekly_schedule = weekly_schedule;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPrice_per_person() {
		return price_per_person;
	}
	public void setPrice_per_person(String price_per_person) {
		this.price_per_person = price_per_person;
	}
	
}
	