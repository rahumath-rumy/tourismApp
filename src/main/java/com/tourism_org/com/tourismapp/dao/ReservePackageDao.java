package com.tourism_org.com.tourismapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tourism_org.com.tourismapp.config.DbConnection;
import com.tourism_org.com.tourismapp.model.ReservePackage;

public class ReservePackageDao {

private Logger logger = LogManager.getLogger(PackageDao.class);
	
	private List<ReservePackage> reservepackageList = new ArrayList<>();
	
	/**
	 * Get all the Packages.
	 * @return
	 */
	public List<ReservePackage> getAll(){
		
		
		List<ReservePackage> reservePackages = getReservePackageFromDb();
		
		return reservePackages;
	}
	
	/**
	 * Get a package by package_code.
	 * @param String
	 * @return
	 */
	public ReservePackage getAReservePackage(int reserve_id) {
		
		List<ReservePackage> reservePackages = getReservePackageFromDb();
		
		for (ReservePackage reservePackage : reservePackages) {
			if (reservePackage.getReserve_id() == reserve_id) {
				return reservePackage;
			}
		}
		return null;
	}
	
	/**
	 * Add Package into the system.
	 * @param reserve
	 * @return
	 */
	public int addReservePackage(ReservePackage reserve1) {
		
		Connection connection = DbConnection.getInstance().getConnection();
		
		try {		
			
			//Prepare SQL query.
			String sql = "INSERT INTO `reserve_packages` (`reserve_id`, `package_code`, `customer_id`)"
					+ "VALUES (?, ?, ?);";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, reserve1.getReserve_id());
			stmt.setInt(2, reserve1.getPackage_code());
			stmt.setInt(3, reserve1.getCustomer_id());
			

			
			
			int response = stmt.executeUpdate();
			connection.close();
			return response;
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("SQL ERROR :  COULD NOT INSERT DATA - "+e.getMessage());
			return -1;
		}
	}
	
	/**
	 * Delete a package
	 * @param package_code
	 * @return
	 */
	public ReservePackage deleteReservePackage(int reserve_id) {
		
		 Connection connection = DbConnection.getInstance().getConnection();
			
		 try {		
		  Class.forName("com.mysql.cj.jdbc.Driver");
	      String sql ="delete from reserve_packages where reserve_id = ?";
	      PreparedStatement stmt = connection.prepareStatement(sql);
	      stmt.setInt(1, reserve_id);
	      
	     stmt.executeUpdate();
	    	  
	    
		return null;
		
		}
			catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		 
	}
	
	
	public List<ReservePackage> getReservePackageFromDb(){
	
		List<ReservePackage> reservepackageList = new ArrayList<>();
		
		//Create connection instance.
		Connection conn = DbConnection.getInstance().getConnection();
		
		//Prepare the query.
		String sql = "SELECT * FROM `reserve_packages`;";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet resultSet = stmt.executeQuery();
						
			while(resultSet.next()) {
				ReservePackage customer = new ReservePackage();
				customer.setReserve_id(resultSet.getInt("reserve_id"));
				customer.setPackage_code(resultSet.getInt("package_code"));
				customer.setCustomer_id(resultSet.getInt("customer_id"));

				reservepackageList.add(customer);
			}
			conn.close();
			return reservepackageList;
			
		} catch (SQLException e) {
			logger.error("DB ERROR :  COULD NOT ACCESS DATA - "+e.getMessage());
			return null;
		}
	}
	
	

	private List<ReservePackage> LogFiles() {
	//Log
	logger.fatal("This is a FATAL log");
	logger.error("This is a ERROR log");
	logger.warn("This is a WARN log");
	logger.info("This is a INFO log");
	logger.debug("This is a DEBUG log");
	logger.trace("This is a TRACE log");
	
	return reservepackageList;
	}
}