package com.tourism_org.com.tourismapp.model;

//import java.sql.Date;

public class Package {

	private int package_id;
	private String package_name; 
	private String country;
	private String country_area;
	private String number_of_nights;
	private String weekly_schedule;
	private String date;
	private String hotels;
	private String activities;
	private String price_per_person;
	
	public Package() {
		
	}

	public Package(int package_id, String package_name, String country, String country_area, String number_of_nights,
			String weekly_schedule, String date, String hotels, String activities, String price_per_person) {
		super();
		this.package_id = package_id;
		this.package_name = package_name;
		this.country = country;
		this.country_area = country_area;
		this.number_of_nights = number_of_nights;
		this.weekly_schedule = weekly_schedule;
		this.date = date;
		this.hotels = hotels;
		this.activities = activities;
		this.price_per_person = price_per_person;
	}

	public int getPackage_id() {
		return package_id;
	}

	public void setPackage_id(int package_id) {
		this.package_id = package_id;
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

	public String getCountry_area() {
		return country_area;
	}

	public void setCountry_area(String country_area) {
		this.country_area = country_area;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHotels() {
		return hotels;
	}

	public void setHotels(String hotels) {
		this.hotels = hotels;
	}

	public String getActivities() {
		return activities;
	}

	public void setActivites(String activities) {
		this.activities = activities;
	}

	public String getPrice_per_person() {
		return price_per_person;
	}

	public void setPrice_per_person(String price_per_person) {
		this.price_per_person = price_per_person;
	}
		
		
			
			
		
	

}

