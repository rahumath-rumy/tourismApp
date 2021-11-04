package com.tourism_org.com.tourismapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tourism_org.com.tourismapp.config.DbConnection;
import com.tourism_org.com.tourismapp.model.inquiry;

public class inquiryDao {

	private Logger logger = LogManager.getLogger(inquiryDao.class);
	
	
	public int addInquiry(inquiry inquiry) {
		
		Connection connection = DbConnection.getInstance().getConnection();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

		    
		    //Prepare SQL query.
			String sql = "INSERT INTO `inquiry` ( `email`,`inquiry_desc`)"
					+ "VALUES (?, ?);";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, inquiry.getEmail());
			stmt.setString(2, inquiry.getDesc());
		
			int res = stmt.executeUpdate();
			
			return res;
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("SQL ERROR :  Could not add your inquiry. - "+e.getMessage());
			return -1;
		}
	}

	
}
