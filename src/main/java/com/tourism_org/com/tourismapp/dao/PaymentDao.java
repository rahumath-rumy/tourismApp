package com.tourism_org.com.tourismapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.tourism_org.com.tourismapp.model.Payment;
import com.tourism_org.com.tourismapp.model.User;

public class PaymentDao {

	public int payment (Payment payment) { 
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/tourismapp","root","12345");
			
			String sql = "INSERT INTO `payment` (`Id`, `Package_Id`, `CardType`, `card_no`, `CVV`,  `exp_date`,  `Payment_date`, `Amount_paid`) "
					+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?);";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, user.getId());
			stmt.setString(2, user.getLname());
			stmt.setInt(3, payment.getCardType());
			stmt.setString(4, user.getEmail());
			stmt.setString(5, user.getAddress());
			stmt.setBoolean(6, user.isSrilankan());
			stmt.setString(7, user.getCountry());
			stmt.setString(8, user.getNationality());
			stmt.setString(9, user.getPassport());
			stmt.setString(10, encryptedPassword);
	
			int response = stmt.executeUpdate();
			return response;
			
	     	} catch (Exception e) {
		    	e.printStackTrace();
			    logger.error("SQL ERROR :  Could not insert data - "+e.getMessage());
			    return -1; 
		}
	}
}
}
