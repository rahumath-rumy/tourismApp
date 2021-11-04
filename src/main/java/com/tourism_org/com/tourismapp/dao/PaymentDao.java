package com.tourism_org.com.tourismapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tourism_org.com.tourismapp.model.Payment;
import com.tourism_org.com.tourismapp.model.User;

public class PaymentDao {

	private Logger logger = LogManager.getLogger(PaymentDao.class);
	
	public int payment (Payment payment) { 
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/tourismapp","root","12345");
			
			String sql = "INSERT INTO `payment` (`Package_Id`, `CardType`, `card_no`, `CVV`,  `exp_date`, `Amount_paid`) "
					+ "VALUES ( ?, ?, ?, ?, ?, ?);";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, payment.getPackage_Id());
			stmt.setString(2, payment.getCardType());
			stmt.setInt(3, payment.getCardNo());
			stmt.setInt(4, payment.getCvv());
			stmt.setString(5, payment.getExpDate());
			//stmt.set(6, payment.getPaymentDate());
			stmt.setFloat(6, payment.getAmountPaid());
			
			int response = stmt.executeUpdate();
			return response;
			
	     	} catch (Exception e) {
		    	e.printStackTrace();
			    logger.error("SQL ERROR :  Could not insert data - "+e.getMessage());
			    return -1; 
		}
	}
}

