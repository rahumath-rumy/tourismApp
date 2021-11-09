package com.tourism_org.com.tourismapp.dao;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tourism_org.com.tourismapp.config.DbConnection;
import com.tourism_org.com.tourismapp.model.Package; 


public class PackageDao {
	
	private Logger logger = LogManager.getLogger(PackageDao.class);
	
	//private List<Package> packageList = new ArrayList<>();
	
	/**
	 * Get all the Packages.
	 * @return
	 */
	public List<Package> getAll(){
		
		List<Package> packages = getPackageFromDb();
		
		return packages;
	}
	
	/**
	 * Get a package by package_code.
	 * @param String
	 * @return
	 */
	public Package getAPackage(int package_code) {
		
		List <Package> packages = getPackageFromDb();
		
		for (Package Package1 : packages) {
			if (Package1.getPackage_code() == package_code) {
				return Package1;
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
			String sql = "INSERT INTO `package` (`package_code`, `package_name`, `country`, `country_area1`, `country_area2`,`number_of_nights`,"
					+ " `weekly_schedule`, `start_date`, `end_date`, `hotel1`, `hotel2`, `activity1`, `activity2`, `price_per_person`)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, user.getPackage_code());
			stmt.setString(2, user.getPackage_name());
			stmt.setString(3, user.getCountry());
			stmt.setString(4, user.getCountry_area1());
			stmt.setString(5, user.getCountry_area2());
			stmt.setString(6, user.getNumber_of_nights());
			stmt.setString(7, user.getWeekly_schedule());
			stmt.setString(8, user.getStart_date());
			stmt.setString(9, user.getEnd_date());
			stmt.setString(10, user.getHotel1());
			stmt.setString(11, user.getHotel2());
			stmt.setString(12, user.getActivity1());
			stmt.setString(13, user.getActivity2());
			stmt.setString(14, user.getPrice_per_person());
			
			
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
	
	public Package deletePackage(int package_code) {
		
		 Connection connection = DbConnection.getInstance().getConnection();
			
		 try {		
		  Class.forName("com.mysql.cj.jdbc.Driver");
	      String sql ="delete from package where package_code = ?";
	      PreparedStatement stmt = connection.prepareStatement(sql);
	      stmt.setInt(1, package_code);
	      
	     stmt.executeUpdate();
	    	  
	    
		return null;
		
		}
			catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		 
	/**	try {
			if (package_code != null) {
				packageList.remove(package_code);
				return "1";
			} else {
				return "0";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "-1";
		} */
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
				user.setPackage_code(resultSet.getInt("package_code"));
				user.setPackage_name(resultSet.getString("package_name"));
				user.setCountry(resultSet.getString("country"));
				user.setCountry_area1(resultSet.getString("country_area1"));
				user.setCountry_area2(resultSet.getString("country_area2"));
				user.setNumber_of_nights(resultSet.getString("number_of_nights"));
				user.setWeekly_schedule(resultSet.getString("weekly_schedule"));
				user.setStart_date(resultSet.getString("start_date"));
				user.setEnd_date(resultSet.getString("end_date"));
				user.setHotel1(resultSet.getString("hotel1"));
				user.setHotel2(resultSet.getString("hotel2"));
				user.setActivity1(resultSet.getString("activity1"));
				user.setActivity2(resultSet.getString("activity2"));
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
	
	 public int UpdatePackage(Package package1) throws SQLException {
		 
		 try {
	        String sql = "UPDATE package SET package_name = ?, country = ?, country_area1 = ?, country_area2 = ?, number_of_nights =?, weekly_schedule =?,"
	        		+ " start_date =?,  end_date =?, hotel1 =?, hotel2 =?, activity1 =?, activity2 =?, price_per_person =?";
	        sql += " WHERE package_code = ?";
	        
	        Connection conn = DbConnection.getInstance().getConnection();
	        
	        PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, package1.getPackage_code());
			stmt.setString(2, package1.getPackage_name());
			stmt.setString(3, package1.getCountry());
			stmt.setString(4, package1.getCountry_area1());
			stmt.setString(5, package1.getCountry_area2());
			stmt.setString(6, package1.getNumber_of_nights());
			stmt.setString(7, package1.getWeekly_schedule());
			stmt.setString(8, package1.getStart_date());
			stmt.setString(9, package1.getEnd_date());
			stmt.setString(10, package1.getHotel1());
			stmt.setString(11, package1.getHotel2());
			stmt.setString(12, package1.getActivity1());
			stmt.setString(13, package1.getActivity2());
			stmt.setString(14, package1.getPrice_per_person());
			
			
			int response = stmt.executeUpdate();
			conn.close();
			return response;
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("SQL ERROR :  COULD NOT UPDATE DATA - "+e.getMessage());
			return -1;
		}
	}
	        
	       // connect();
	         
	      //  PreparedStatement statement = jdbcConnection.prepareStatement(sql);
	        
	 /**       
	        
	        statement.setString(1, book.getTitle());
	        statement.setString(2, book.getAuthor());
	        statement.setFloat(3, book.getPrice());
	        statement.setInt(4, book.getId());
	         
	        boolean rowUpdated = statement.executeUpdate() > 0;
	        statement.close();
	        disconnect();
	        return rowUpdated;     
	    }
	*/
	
//	private List<Package> LogFiles() {
//	//Log
//	logger.fatal("This is a FATAL log");
//	logger.error("This is a ERROR log");
//	logger.warn("This is a WARN log");
//	logger.info("This is a INFO log");
//	logger.debug("This is a DEBUG log");
//	logger.trace("This is a TRACE log");
//	
//	return packageList;
	}

