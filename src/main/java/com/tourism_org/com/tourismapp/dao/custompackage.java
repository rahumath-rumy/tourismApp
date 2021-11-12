package com.tourism_org.com.tourismapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tourism_org.com.tourismapp.config.DbConnection;
import com.tourism_org.com.tourismapp.model.CustomPackage;
import com.tourism_org.com.tourismapp.model.User;


public class custompackage {
	
	private static Logger logger = LogManager.getLogger(PackageDao.class);
	
	/**
	 * Get a custom package by custom package id.
	 * @param String
	 * @return
	 */


	public CustomPackage getaCustomPackage (int cp_id) {

		List<CustomPackage> customPackages =  getCustomPackageFromDB();
		 
		for (CustomPackage customPackage : customPackages) {
			if (customPackage.getCp_id() == cp_id) {
				return customPackage;
			 }
		 }
		 
		 logger.info("Invalid ID");
		 return null; 
	}
		
	
	public static List<CustomPackage> getCustomPackageFromDB()   {
		
		List <CustomPackage> cpList = new ArrayList<>();
	
		String sql = "Select * from `custompackage`;" ;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tourismapp","root","12345");
				
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet resultSet = stmt.executeQuery();
						
				while(resultSet.next()) {
					CustomPackage customPackages = new CustomPackage();
					customPackages.setCp_id (resultSet.getInt("custompackage_id"));
					customPackages.setCp_code(resultSet.getString("cp_code"));
					customPackages.setCountry(resultSet.getString("country"));
					customPackages.setHotel1(resultSet.getString("hotel1"));
					customPackages.setHotel2(resultSet.getString("hotel2"));
					customPackages.setActivity1(resultSet.getString("activity1"));
					customPackages.setActivity2(resultSet.getString("activity2"));
					customPackages.setNumber_of_people(resultSet.getString("number_of_people"));
					customPackages.setStart_date(resultSet.getString("start_date"));
					customPackages.setEnd_date(resultSet.getString("end_date"));
					customPackages.setNumber_of_nights(resultSet.getInt("number_of_nights"));
					cpList.add(customPackages);
				}
				conn.close();
				return cpList;
		
			
		} catch (Exception e) {
			
			logger.error("DB ERROR : Could not access data - "+e.getMessage());
			return null;
		}
	}

		
//				
//		/**
//		 * Get all the Customized Packages.
//		 * @return
//		 */
//		
//
//		
//		
//		
//
//		/**
//		 * Add the custom Package into the system.
//		 * @param user
//		 * @return
//		 */
	public int addCustomPackage (CustomPackage customPackages) { 
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/tourismapp","root","12345");
			
			String sql = "INSERT INTO `custompackage` (`cp_code`,  `country`, `hotel1`, `hotel2`, `activity1`, `activity2` ,`number_of_people`,"
				+ " `start_date`, `end_date`,  `number_of_nights`)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			//stmt.setInt(1, customPackages.getCp_id());
			stmt.setString(1, customPackages.getCp_code());
			stmt.setString(2, customPackages.getCountry());
			stmt.setString(3, customPackages.getHotel1());
			stmt.setString(4, customPackages.getHotel2());
			stmt.setString(5, customPackages.getActivity1());
			stmt.setString(6, customPackages.getActivity2());
			stmt.setString(7, customPackages.getNumber_of_people());
			stmt.setString(8, customPackages.getStart_date());
			stmt.setString(9, customPackages.getEnd_date());
			stmt.setInt(10, customPackages.getNumber_of_nights());
	
			int response = stmt.executeUpdate();
			return response;
			
	     	} catch (Exception e) {
		    	e.printStackTrace();
			    logger.info("SQL ERROR :  Could not insert data - "+e.getMessage());
			    return -1; 
		}
	}
	
//		public int addCustomPackage(CustomPackage user) {
//			
//			Connection connection = DbConnection.getInstance().getConnection();
//			
//			try {		
//				
//				//Prepare SQL query.
//				String sql = "INSERT INTO `custompackage` (`cp_code`,  `country`, `hotel1`, `hotel2`, `activity1`, `activity2` ,`number_of_people`,"
//						+ " `start_date`, `end_date`,  `number_of_nights`)"
//						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
//				
//				PreparedStatement stmt = connection.prepareStatement(sql);
//				stmt.setInt(1, user.getCp_id());
//				stmt.setString(2, user.getCp_code());
//				stmt.setString(3, user.getCountry());
//				stmt.setString(4, user.getCountry());
//				stmt.setString(5, user.getHotel1());
//				stmt.setString(6, user.getHotel2());
//				stmt.setString(7, user.getActivity1());
//				stmt.setString(8, user.getActivity2());
//				stmt.setString(9, user.getNumber_of_people());
//				stmt.setString(10, user.getStart_date());
//				stmt.setString(11, user.getEnd_date());
//				stmt.setString(12, user.getNumber_of_nights());
//				
//				
//				int response = stmt.executeUpdate();
//				connection.close();
//				return response;
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//				logger.error("SQL ERROR :  COULD NOT INSERT DATA - "+e.getMessage());
//				return -1;
//			}
//		}
//		
//		/**
//		 * Update a package
//		 * @param package1
//		 * @return
//		 */
//		public Package updatePackage(Package package1){
//			return null;
//		}
//		
//		/**
//		 * Delete a package
//		 * @param package_code
//		 * @return
//		 */
//		public CustomPackage deleteCustomPackage(int package_code) {
//			
//			 Connection connection = DbConnection.getInstance().getConnection();
//				
//			 try {		
//			  Class.forName("com.mysql.cj.jdbc.Driver");
//		      String sql ="delete from package where package_code = ?";
//		      PreparedStatement stmt = connection.prepareStatement(sql);
//		      stmt.setInt(1, package_code);
//		      
//		     stmt.executeUpdate();
//		    	  
//		    
//			return null;
//			
//			}
//				catch (Exception e) {
//				e.printStackTrace();
//				return null;
//			}
//			 
//		/**	try {
//				if (package_code != null) {
//					packageList.remove(package_code);
//					return "1";
//				} else {
//					return "0";
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//				return "-1";
//			} */
//		}
//		

	public CustomPackage customapproval(String cp_code, Boolean status) {
		
		try {
		  Class.forName("com.mysql.cj.jdbc.Driver");
	      Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tourismapp","root","12345");
	    
	      
	      String sql ="Select * from `custompackage` where `cp_code` = ?";
	      PreparedStatement stmt = conn.prepareStatement(sql);
	      stmt.setString(1,cp_code);
	      
	      
	      ResultSet resultSet = stmt.executeQuery();
	      
	      int rows =0;
	      CustomPackage customPackages = new CustomPackage();
	      while (resultSet.next()) {
	    	  
	    	    rows ++;
	    	   
				customPackages.setCp_id (resultSet.getInt("custompackage_id"));
				customPackages.setCp_code(resultSet.getString("cp_code"));
				customPackages.setCountry(resultSet.getString("country"));
				customPackages.setHotel1(resultSet.getString("hotel1"));
				customPackages.setHotel2(resultSet.getString("hotel2"));
				customPackages.setActivity1(resultSet.getString("activity1"));
				customPackages.setActivity2(resultSet.getString("activity2"));
				customPackages.setNumber_of_people(resultSet.getString("number_of_people"));
				customPackages.setStart_date(resultSet.getString("start_date"));
				customPackages.setEnd_date(resultSet.getString("end_date"));
				customPackages.setNumber_of_nights(resultSet.getInt("number_of_nights"));
				
				
	    	  
	      }
	      
      if (rows == 1) {
	        	  
	    	 Class.forName("com.mysql.cj.jdbc.Driver");
	         Connection conn1=DriverManager.getConnection("jdbc:mysql://localhost:3306/tourismapp","root","12345");
	    	    	 
	         String sql1 = "INSERT INTO `custompackage_approval` (`cp_code`, `status`)"
	    	  				+ "VALUES (?,?);";
	    	  		
	  		PreparedStatement stmt1 = conn1.prepareStatement(sql1);
   	 
			stmt1.setString(1, customPackages.getCp_code());
	  		stmt1.setBoolean(2, customPackages.isStatus());

	    	int resultSet1 = stmt1.executeUpdate();		    	  		
    	  	return customPackages;
	  
      } else {
    	  return null;
      }
      
	} catch (Exception e) {
		e.printStackTrace();
		 logger.info("SQL ERROR :  The customize package code you have entered does not exist.  - "+e.getMessage());
		return null;
	}

}
//		
//		private List<Package> LogFiles() {
//		//Log
//		logger.fatal("This is a FATAL log");
//		logger.error("This is a ERROR log");
//		logger.warn("This is a WARN log");
//		logger.info("This is a INFO log");
//		logger.debug("This is a DEBUG log");
//		logger.trace("This is a TRACE log");
//		
//		return packageList;
//		}


		


	}
		

