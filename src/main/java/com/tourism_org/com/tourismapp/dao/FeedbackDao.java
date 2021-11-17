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
import com.tourism_org.com.tourismapp.model.Feedback;


public class FeedbackDao {

	private Logger logger = LogManager.getLogger(PackageDao.class);
	
	private List<Feedback> feedbackList = new ArrayList<>();
	
	/**
	 * Get all the Payments.
	 * @return
	 */
	public List<Feedback> getAll(){
		
		
		List<Feedback> feedbacks = getFeedbackFromDb();
		
		return feedbacks;
	}
	
	/**
	 * Get a package by package_code.
	 * @param String
	 * @return
	 */
	public Feedback getAFeedback(int feedback_id) {
		
		List<Feedback> feedbacks = getFeedbackFromDb();
		
		for (Feedback feedback : feedbacks) {
			if (feedback.getFeedback_id() == feedback_id) {
				return feedback;
			}
		}
		return null;
	}
	
	
	

	public int addFeedback (Feedback feedback) { 
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/tourismapp","root","Root@1234");
			
			String sql = "INSERT INTO `feedback` (`feedback_id`, `duration`, `weekly_schedule`, `activites`,  `package_price`,  `description`, `feedback`) "
					+ "VALUES ( ?, ?, ?, ?, ?, ?, ?);";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, feedback.getFeedback_id());
			stmt.setString(2, feedback.getDuration());
			stmt.setString(3, feedback.getWeekly_schedule());
			stmt.setString(4, feedback.getActivites());
			stmt.setString(5, feedback.getPackage_price());
			stmt.setString(6, feedback.getDescription());
			stmt.setString(7, feedback.getFeedback());
			
	
	int response = stmt.executeUpdate();
			return response;
			
	     	} catch (Exception e) {
		    	e.printStackTrace();
		    logger.error("SQL ERROR :  Could not insert data - "+e.getMessage());
			    return -1; 
		}
	}
	
public List<Feedback> getFeedbackFromDb(){
	
	List<Feedback> feedbackList = new ArrayList<>();
	
	//Create connection instance.
	Connection conn = DbConnection.getInstance().getConnection();
	
	//Prepare the query.
	String sql = "SELECT * FROM `feedback`;";
	
	try {
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		ResultSet resultSet = stmt.executeQuery();
					
		while(resultSet.next()) {
			Feedback user = new Feedback();
			user.setFeedback_id(resultSet.getInt("feedback_id"));
			user.setDuration(resultSet.getString("duration"));
			user.setWeekly_schedule(resultSet.getString("weekly_schedule"));
			user.setActivites(resultSet.getString("activites"));
			user.setPackage_price(resultSet.getString("package_price"));
			user.setDescription(resultSet.getString("description"));
			user.setFeedback(resultSet.getString("feedback"));
			
			feedbackList.add(user);
		}
		conn.close();
		return feedbackList;
		
	} catch (SQLException e) {
		logger.error("DB ERROR :  COULD NOT ACCESS DATA - "+e.getMessage());
		return null;
	}
}
	
	
	private List<Feedback> LogFiles() {
	//Log
	logger.fatal("This is a FATAL log");
	logger.error("This is a ERROR log");
	logger.warn("This is a WARN log");
	logger.info("This is a INFO log");
	logger.debug("This is a DEBUG log");
	logger.trace("This is a TRACE log");
	
	return feedbackList;
	}
	
}