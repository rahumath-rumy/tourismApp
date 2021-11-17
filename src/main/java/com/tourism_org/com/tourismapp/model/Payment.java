package com.tourism_org.com.tourismapp.model;

import java.sql.Timestamp;

public class Payment {

	private int Payment_id;
	private String cardType;
	private String cardNo;
	private int CVV;
	private String expDate;
	private Timestamp paymentDate;
	private Float amountPaid;
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Payment(int payment_id, String cardType, String cardNo, int cVV, String expDate, Timestamp paymentDate,
			Float amountPaid) {
		super();
		Payment_id = payment_id;
		this.cardType = cardType;
		this.cardNo = cardNo;
		CVV = cVV;
		this.expDate = expDate;
		this.paymentDate = paymentDate;
		this.amountPaid = amountPaid;
	}
	public int getPayment_id() {
		return Payment_id;
	}
	public void setPayment_id(int payment_id) {
		Payment_id = payment_id;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public int getCVV() {
		return CVV;
	}
	public void setCVV(int cVV) {
		CVV = cVV;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	public Timestamp getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}
	public Float getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(Float amountPaid) {
		this.amountPaid = amountPaid;
	}
	
}