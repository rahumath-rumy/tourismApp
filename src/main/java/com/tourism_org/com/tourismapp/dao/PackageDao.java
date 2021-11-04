package com.tourism_org.com.tourismapp.dao;

import com.tourism_org.com.tourismapp.config.DbConnection;

import com.tourism_org.com.tourismapp.model.Package;
import com.tourism_org.com.tourismapp.model.User;
import com.tourism_org.com.tourismapp.model.admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PackageDao {
	
	private Logger logger = LogManager.getLogger(PackageDao.class);
	
	private List<Package> packageList = new ArrayList<>();
	
	/**
	 * Get all the Packages.
	 * @return
	 */
	public List<Package> getAll(){
		
		
		List<Package> packages = getPackageFromDb();
		
		return packages;
	}
	
	/**
	 * Get a package by package_id.
	 * @param id
	 * @return
	 */
	public Package getAPackage(int package_id) {
		
		List<Package> packages = getPackageFromDb();
		
		for (Package package1 : packages) {
			if (package1.getPackage_id() == package_id ) {
				return package1;
			}
		}
		return null;
	}
	
	/**
	 * Add Package into the system.
	 * @param user
	 * @return
	 */
	public int addPackage(Package user) {
		
		Connection connection = DbConnection.getInstance().getConnection();
		
		try {
			
			//String password =  user.getPassword();
		//	String encryptedPassword = Sha1Encrpt(password);
			
			
			//Prepare SQL query.
			String sql = "INSERT INTO `package` (`package_id`, `package_name`, `country`, `country_area`,`number_of_nights`,"
					+ " `weekly_schedule`, `date`, `hotels`, `activities`, `price_per_person`) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt   (1, user.getPackage_id());
			stmt.setString(2, user.getPackage_name());
			stmt.setString(3, user.getCountry());
			stmt.setString(4, user.getCountry_area());
			stmt.setString(5, user.getNumber_of_nights());
			stmt.setString(6, user.getWeekly_schedule());
			stmt.setString  (7, user.getDate());
			stmt.setString(8, user.getHotels());
			stmt.setString(9, user.getActivities());
			stmt.setString(10, user.getPrice_per_person());
			
			
			int response = stmt.executeUpdate();
			connection.close();
			return response;
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("SQL ERROR :  COULD NOT INSERT DATA - "+e.getMessage());
			return -1;
		}
	}
	
	public int updateUser(Package user) {
		return 1;
	}
	
	public int deleteUser(Package user) {
		try {
			if (user != null) {
				packageList.remove(user);
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	
	public List<Package> getPackageFromDb(){
	
		List<Package> packageList = new ArrayList<>();
		
		//Create connection instance.
		Connection conn = DbConnection.getInstance().getConnection();
		
		//Prepare the query.
		String sql = "SELECT * FROM `package`;";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet resultSet = stmt.executeQuery();
						
			while(resultSet.next()) {
				Package user = new Package();
				user.setPackage_id(resultSet.getInt("package_id"));
				user.setPackage_name(resultSet.getString("package_name"));
				user.setCountry(resultSet.getString("country"));
				user.setCountry_area(resultSet.getString("country_area"));
				user.setNumber_of_nights(resultSet.getString("number_of_nights"));
				user.setWeekly_schedule(resultSet.getString("weekly_schedule"));
				user.setDate(resultSet.getString("date"));
				user.setHotels(resultSet.getString("hotels"));
				user.setActivites(resultSet.getString("activities"));
				user.setPrice_per_person(resultSet.getString("price_per_person"));
				packageList.add(user);
			}
			conn.close();
			return packageList;
			
		} catch (SQLException e) {
			logger.error("DB ERROR :  COULD NOT ACCESS DATA - "+e.getMessage());
			return null;
		}
	}
	
//	public Package searchpackage(String country, String number_of_nights, String weekly_schedule) {
//
//		Connection connection = DbConnection.getInstance().getConnection();
//		
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//	  
//	      
//	      String sql ="Select * from `package` where `country` = ? and  `number_of_nights` =? and `weekly_schedule`=?;";
//	      PreparedStatement stmt = connection.prepareStatement(sql);
//	      stmt.setString(1,country);
//	      stmt.setString (2, number_of_nights);
//	      stmt.setString (3, weekly_schedule);
//	      
//	      ResultSet resultSet = stmt.executeQuery();
//	      
//	    	  int rows =0;
//		      Package user = new Package();
//		      while (resultSet.next()) {
//		   
//	    	    rows ++;
//	    	
//				user.setPackage_id(resultSet.getInt("package_id"));
//				user.setPackage_name(resultSet.getString("package_name"));
//				user.setCountry(resultSet.getString("country"));
//				user.setCountry_area(resultSet.getString("country_area"));
//				user.setNumber_of_nights(resultSet.getString("number_of_nights"));
//				user.setWeekly_schedule(resultSet.getString("weekly_schedule"));
//				user.setDate(resultSet.getString("date"));
//				user.setHotels(resultSet.getString("hotels"));
//				user.setActivites(resultSet.getString("activities"));
//				user.setPrice_per_person(resultSet.getString("price_per_person"));	
//	      }
//	      
//	      if (rows == 1) {
//	    	  
//	    	  Connection connection1 = DbConnection.getInstance().getConnection(); 
//	    	  Class.forName("com.mysql.cj.jdbc.Driver");
//	        
//	    	 
//	    	 String sql1 = "INSERT INTO `admin_login` (`email`, `admin_password`)"
//	  				+ "VALUES ( ?, ?);";
//	  		
//	  		PreparedStatement stmt1 = connection1.prepareStatement(sql1);
//	  //		stmt1.setString(1, Admin.getEmail());
//	  	//	stmt1.setString(2, Admin.getPassword());
//
//	  		int res1 = stmt1.executeUpdate();
//	  		
//	  		//return res1;
//
//	    	// return Admin;
//	    	  
//	      } else {
//	    	  return null;
//	      }
//	      
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//		}	
//	
	
	private List<Package> LogFiles() {
	//Log
	logger.fatal("This is a FATAL log");
	logger.error("This is a ERROR log");
	logger.warn("This is a WARN log");
	logger.info("This is a INFO log");
	logger.debug("This is a DEBUG log");
	logger.trace("This is a TRACE log");
	
	return packageList;
	}
}
	
