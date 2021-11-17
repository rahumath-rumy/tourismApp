package com.tourism_org.com.tourismapp.model;

public class ReservePackage {

	private int reserve_id;
	private int package_code;
	private int customer_id;
	public ReservePackage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReservePackage(int reserve_id, int package_code, int customer_id) {
		super();
		this.reserve_id = reserve_id;
		this.package_code = package_code;
		this.customer_id = customer_id;
	}
	public int getReserve_id() {
		return reserve_id;
	}
	public void setReserve_id(int reserve_id) {
		this.reserve_id = reserve_id;
	}
	public int getPackage_code() {
		return package_code;
	}
	public void setPackage_code(int package_code) {
		this.package_code = package_code;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	
}