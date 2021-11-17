package com.tourism_org.com.tourismapp.model;

public class Feedback {

	private int feedback_id;
	private String duration;
	private String weekly_schedule;
	private String activites;
	private String package_price;
	private String description;
	private String feedback;
	public Feedback() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Feedback(int feedback_id, String duration, String weekly_schedule, String activites, String package_price,
			String description, String feedback) {
		super();
		this.feedback_id = feedback_id;
		this.duration = duration;
		this.weekly_schedule = weekly_schedule;
		this.activites = activites;
		this.package_price = package_price;
		this.description = description;
		this.feedback = feedback;
	}
	public int getFeedback_id() {
		return feedback_id;
	}
	public void setFeedback_id(int feedback_id) {
		this.feedback_id = feedback_id;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getWeekly_schedule() {
		return weekly_schedule;
	}
	public void setWeekly_schedule(String weekly_schedule) {
		this.weekly_schedule = weekly_schedule;
	}
	public String getActivites() {
		return activites;
	}
	public void setActivites(String activites) {
		this.activites = activites;
	}
	public String getPackage_price() {
		return package_price;
	}
	public void setPackage_price(String package_price) {
		this.package_price = package_price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
}