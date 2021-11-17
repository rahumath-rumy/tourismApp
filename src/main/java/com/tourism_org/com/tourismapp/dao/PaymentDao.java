package com.tourism_org.com.tourismapp.dao;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tourism_org.com.tourismapp.config.DbConnection;
import com.tourism_org.com.tourismapp.model.Payment;


public class PaymentDao {
	
	private Logger logger = LogManager.getLogger(PackageDao.class);
	
	private List<Payment> paymentList = new ArrayList<>();
	
	/**
	 * Get all the Payments.
	 * @return
	 */
	public List<Payment> getAll(){
		
		
		List<Payment> payments = getPaymentFromDb();
		
		return payments;
	}
	
	/**
	 * Get a package by package_code.
	 * @param String
	 * @return
	 */
	public Payment getAPayment(int payment_id) {
		
		List<Payment> payments = getPaymentFromDb();
		
		for (Payment payment : payments) {
			if (payment.getPayment_id() == payment_id) {
				return payment;
			}
		}
		return null;
	}

public int addPayment (Payment payment) { 
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/tourismapp","root","Root@1234");
		
		String sql = "INSERT INTO `payment` (`Payment_id`, `cardType`, `cardNo`, `CVV`, `expDate`, `paymentDate`,`amountPaid`)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?);";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, payment.getPayment_id());
		stmt.setString(2, payment.getCardType());
		stmt.setString(3, payment.getCardNo());
		stmt.setInt(4, payment.getCVV());
		stmt.setString(5, payment.getExpDate());
		stmt.setTimestamp(6, payment.getPaymentDate());
		stmt.setFloat(7, payment.getAmountPaid());
		

int response = stmt.executeUpdate();
		return response;
		
     	} catch (Exception e) {
	    	e.printStackTrace();
	    logger.error("SQL ERROR :  Could not insert data - "+e.getMessage());
		    return -1; 
	}
}
	
	
public List<Payment> getPaymentFromDb(){
	
	List<Payment> paymentList = new ArrayList<>();
	
	//Create connection instance.
	Connection conn = DbConnection.getInstance().getConnection();
	
	//Prepare the query.
	String sql = "SELECT * FROM `payment`;";
	
	try {
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		ResultSet resultSet = stmt.executeQuery();
					
		while(resultSet.next()) {
			Payment user = new Payment();
			user.setPayment_id(resultSet.getInt("Payment_id"));
			user.setCardType(resultSet.getString("cardType"));
			user.setCardNo(resultSet.getString("cardNo"));
			user.setCVV(resultSet.getInt("CVV"));
			user.setExpDate(resultSet.getString("expDate"));
			user.setPaymentDate(resultSet.getTimestamp("paymentDate"));
			user.setAmountPaid(resultSet.getFloat("amountPaid"));
			
			paymentList.add(user);
		}
		conn.close();
		return paymentList;
		
	} catch (SQLException e) {
		logger.error("DB ERROR :  COULD NOT ACCESS DATA - "+e.getMessage());
		return null;
	}
}
	
	
	
	
	
	
	
	
	

	public int payment (Payment payment) { 
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/tourismapp","root","Root@1234");
			
			String sql = "INSERT INTO `payment` (`Payment_id`, `cardType`, `cardNo`, `cvv`,  `expDate`,  `paymentDate`, `amountPaid`) "
					+ "VALUES ( ?, ?, ?, ?, ?, ?, ?);";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, payment.getPayment_id());
			stmt.setString(2, payment.getCardType());
			stmt.setString(3, payment.getCardNo());
			stmt.setInt(4, payment.getCVV());
			stmt.setString(5, payment.getExpDate());
			stmt.setTimestamp(6, payment.getPaymentDate());
			stmt.setFloat(7, payment.getAmountPaid());
			
			
	
	int response = stmt.executeUpdate();
			return response;
			
	     	} catch (Exception e) {
		    	e.printStackTrace();
		    logger.error("SQL ERROR :  Could not insert data - "+e.getMessage());
			    return -1; 
		}
	}
	
	private List<Payment> LogFiles() {
	//Log
	logger.fatal("This is a FATAL log");
	logger.error("This is a ERROR log");
	logger.warn("This is a WARN log");
	logger.info("This is a INFO log");
	logger.debug("This is a DEBUG log");
	logger.trace("This is a TRACE log");
	
	return paymentList;
	}
}
