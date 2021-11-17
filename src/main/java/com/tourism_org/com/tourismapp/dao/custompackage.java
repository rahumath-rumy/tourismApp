package com.tourism_org.com.tourismapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tourism_org.com.tourismapp.config.DbConnection;
import com.tourism_org.com.tourismapp.model.CustomPackage;

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
		
	/**
	 * get all the customised packages
	 * @return
	 */
	
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
					customPackages.setCountry(resultSet.getString("country"));
					customPackages.setCountry_location(resultSet.getString("country_location"));
					customPackages.setHotel1(resultSet.getString("hotel1"));
					customPackages.setHotel2(resultSet.getString("hotel2"));
					customPackages.setActivity1(resultSet.getString("activity1"));
					customPackages.setActivity2(resultSet.getString("activity2"));
					customPackages.setNumber_of_people(resultSet.getInt("number_of_people"));
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

		
		/**
		 * Add the customised package into the Db.
		 * @param user
		 * @return
		 */
	public int addCustomPackage (CustomPackage customPackages) { 
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/tourismapp","root","12345");
			
			String sql = "INSERT INTO `custompackage` (`country`,  `country_location`, `hotel1`, `hotel2`, `activity1`, `activity2` ,`number_of_people`,"
				+ " `start_date`, `end_date`,  `number_of_nights`)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			//stmt.setInt(1, customPackages.getCp_id());
			stmt.setString(1, customPackages.getCountry());
			stmt.setString(2, customPackages.getCountry_location());
			stmt.setString(3, customPackages.getHotel1());
			stmt.setString(4, customPackages.getHotel2());
			stmt.setString(5, customPackages.getActivity1());
			stmt.setString(6, customPackages.getActivity2());
			stmt.setInt(7, customPackages.getNumber_of_people());
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
	
	/**
	 * update status of customised packages
	 * @param customPackage
	 * @return
	 */
//	public int UpdateStatusCustomPackage(CustomPackage customPackage) {
//
//		try {		
//			String sql = "UPDATE custompackage SET `status`=? WHERE `cp_code`=?";
//        
//        Connection conn = DbConnection.getInstance().getConnection();
//        
//        PreparedStatement stmt = conn.prepareStatement(sql);
//        
//        stmt.setString(2, customPackage.getCp_code());
//		stmt.setString(1, customPackage.getStatus());
//		
//	
//		int response = stmt.executeUpdate();
//		conn.close();
//		return response;
//		
//	 } catch (Exception e) {
//		e.printStackTrace();
//		logger.error("SQL ERROR :  COULD NOT UPDATE THE CUSTOMIZED PACKAGE! - "+e.getMessage());
//		return -1;
//	}
//}

	
		/**
		 * edit customised packages
		 * @param customPackage
		 * @return
		 */
		public int UpdateCustomPackage(CustomPackage customPackage) {
	
			try {		
				String sql = "UPDATE custompackage SET `country`=?,  `country_location`=?, `hotel1`=?, `hotel2`=?, "
				+ "`activity1`=?, `activity2`=? ,`number_of_people`=?, `start_date`=?, `end_date`=?, "
				+ " `number_of_nights` =? WHERE `custompackage_id`=?";
	        
	        Connection conn = DbConnection.getInstance().getConnection();
	        
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        
	        stmt.setInt(11, customPackage.getCp_id());
	        stmt.setString(1, customPackage.getCountry());
			stmt.setString(2, customPackage.getCountry_location());
			stmt.setString(3, customPackage.getHotel1());
			stmt.setString(4, customPackage.getHotel2());
			stmt.setString(5, customPackage.getActivity1());
			stmt.setString(6, customPackage.getActivity2());
			stmt.setInt(7, customPackage.getNumber_of_people());
			stmt.setString(8, customPackage.getStart_date());
			stmt.setString(9, customPackage.getEnd_date());
			stmt.setInt(10, customPackage.getNumber_of_nights());
//			stmt.setString(11, customPackage.getStatus());
			
		
			int response = stmt.executeUpdate();
			conn.close();
			return response;
			
		 } catch (Exception e) {
			e.printStackTrace();
			logger.error("SQL ERROR :  COULD NOT UPDATE THE CUSTOMIZED PACKAGE! - "+e.getMessage());
			return -1;
		}
	}


		/**
		 * delete customised package from DB
		 */
		public CustomPackage delCustomPackage(int cp_id) {

			 Connection connection = DbConnection.getInstance().getConnection();	
			
			 try {		
			  Class.forName("com.mysql.cj.jdbc.Driver");
				 
		      String sql ="Delete from custompackage where `custompackage_id` = ?";
		      PreparedStatement stmt = connection.prepareStatement(sql);
		      stmt.setInt(1,cp_id);
		      
		     stmt.executeUpdate();
		    
			return null;
			
			}
				catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		
	
//		public int CustomPackageStatus(CustomPackage customPackage) {
//			
//			try {		
//				String sql = "UPDATE custompackage_appproval SET `status`=? WHERE `cp_code`=?";
//	        
//	        Connection conn = DbConnection.getInstance().getConnection();
//	        
//	        PreparedStatement stmt = conn.prepareStatement(sql);
//	        
//
//	        stmt.setString(1, customPackage.getStatus());
//			stmt.setString(2, customPackage.getCp_code());
//			
//			int response = stmt.executeUpdate();
//			conn.close();
//			return response;
//			
//		 } catch (Exception e) {
//			e.printStackTrace();
//			logger.error("SQL ERROR :  COULD NOT APPROVE THE CUSTOMIZED PACKAGE! - "+e.getMessage());
//			return -1;
//		}
//	}

		
	
		public int StatusCustomPackage(CustomPackage customPackage) {
			
			try {		
				String sql = "UPDATE custompackage SET `country`=?,  `country_location`=?, `hotel1`=?, `hotel2`=?, "
				+ "`activity1`=?, `activity2`=? ,`number_of_people`=?, `start_date`=?, `end_date`=?, "
				+ " `number_of_nights` =?, `status` =? WHERE `custompackage_id`=?";
	        
	        Connection conn = DbConnection.getInstance().getConnection();
	        
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        
	        stmt.setInt(12, customPackage.getCp_id());
	        stmt.setString(1, customPackage.getCountry());
			stmt.setString(2, customPackage.getCountry_location());
			stmt.setString(3, customPackage.getHotel1());
			stmt.setString(4, customPackage.getHotel2());
			stmt.setString(5, customPackage.getActivity1());
			stmt.setString(6, customPackage.getActivity2());
			stmt.setInt(7, customPackage.getNumber_of_people());
			stmt.setString(8, customPackage.getStart_date());
			stmt.setString(9, customPackage.getEnd_date());
			stmt.setInt(10, customPackage.getNumber_of_nights());
			stmt.setString(11, customPackage.getStatus());

			int response = stmt.executeUpdate();
			conn.close();
			return response;
			
		 } catch (Exception e) {
			e.printStackTrace();
			logger.error("SQL ERROR :  COULD NOT UPDATE THE STATUS OF THE CUSTOMIZED PACKAGE! - "+e.getMessage());
			return -1;
		}
	}

}
		