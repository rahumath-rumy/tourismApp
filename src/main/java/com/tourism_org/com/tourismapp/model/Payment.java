package com.tourism_org.com.tourismapp.model;

public class Payment {

	private int payment_id;
	private int package_id;
	private String cardType;
	private int cardNo;
	private int cvv;
	private String expDate;
	private String paymentDate;
	private Float amountPaid;
	
	public Payment(int payment_id, int package_id, String cardType, int cardNo, int cvv, String expDate, String paymentDate,
			Float amountPaid) {
		super();
		this.payment_id = payment_id;
		this.package_id = package_id;
		this.cardType = cardType;
		this.cardNo = cardNo;
		this.cvv = cvv;
		this.expDate = expDate;
		this.paymentDate = paymentDate;
		this.amountPaid = amountPaid;
	}

	public int getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}
	
	public int getPackage_Id() {
		return package_id;
	}
	
	public void setPackage_id(int package_id) {
		this.package_id = package_id;
	}
	

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public int getCardNo() {
		return cardNo;
	}

	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Float getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(Float amountPaid) {
		this.amountPaid = amountPaid;
	}

	
	
}
