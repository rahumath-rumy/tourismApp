package com.tourism_org.com.tourismapp.dao;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tourism_org.com.tourismapp.config.DbConnection;
import com.tourism_org.com.tourismapp.dao.AdminDao;
import com.tourism_org.com.tourismapp.model.admin;

import jakarta.ws.rs.PathParam;

public class AdminDao {
	
	private Logger logger = LogManager.getLogger(AdminDao.class);
	
	private List <admin> adminList = new ArrayList<>();
	
	/**
	 * get all the Admins details
	 * @return
	 */
	public List<admin> getAll(){
		
		List<admin> admin = getAdminFromDb();
		
		return admin;
	}

 
	/**
	 * add admin into the db
	 * @param Admin
	 * @return
	 */
	public int addAdmin(admin Admin) {
	
	Connection connection = DbConnection.getInstance().getConnection();
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
	    String password = Admin.getPassword();
	    String encryptedPassword =  Sha1Encrypt (password);
	    
		String sql = "INSERT INTO `admin` (`admin_id`,`admin_fname`,`admin_lname`, `email`,`mobile`,`address`,`admin_control`, `admin_password`)"
				+ "VALUES (?, ?, ?, ?, ?,?,?,?);";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, Admin.getAdmin_id());
		stmt.setString(2, Admin.getFname());
		stmt.setString(3, Admin.getLname());
		stmt.setString(4, Admin.getEmail());
		stmt.setInt(5, Admin.getMobile());
		stmt.setString(6, Admin.getAddress());
		stmt.setBoolean(7, Admin.isAdmin_control());
		stmt.setString(8, encryptedPassword);
		
		int res = stmt.executeUpdate();
		
		return res;
		
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("SQL ERROR :  Could not insert data - "+e.getMessage());
		return -1;
	}
}
	
	/**
	 * encrypt password
	 * @param tobeEncrpyted
	 * @return
	 */

	public String Sha1Encrypt (String tobeEncrpyted) {
   
		try {
			byte[] passwordArr = tobeEncrpyted.getBytes();
    
			MessageDigest sha1Encrypt = MessageDigest.getInstance("SHA-1");
			byte[] encryptPassword = sha1Encrypt.digest (passwordArr);
 
    
			String s = Base64.getEncoder().encodeToString(encryptPassword);
    	
			return s;
	
	} 	catch (Exception e) { 
		e.printStackTrace();
		return null;
	}
   
	
}

	/**
	 * get Admin details by the ID
	 * @param admin_id
	 * @return
	 */
	public admin getaAdmin(int admin_id) {
		
		List<admin> admins = getAdminFromDb();

		for (admin admin : admins) {
			if (admin.getAdmin_id() == admin_id) {
				return admin;
			}
		}
		 logger.info("Invalid ID");
		return null;
}


	/**
	 * login authorisation for admins.
	 * @param email
	 * @param password
	 * @return
	 */
	public admin adminAuth(String email, String password) {

	Connection connection = DbConnection.getInstance().getConnection();
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
   
      String encryptedPassword = Sha1Encrypt (password);
      
      String sql ="Select * from `admin` where `email` = ? and `admin_password`=?";
      PreparedStatement stmt = connection.prepareStatement(sql);
      stmt.setString(1,email);
      stmt.setString (2, encryptedPassword);
      
      ResultSet resultSet = stmt.executeQuery();
      
      int rows =0;
      admin Admin = new admin();
      while (resultSet.next()) {
    	  
    	    rows ++;
    	
			Admin.setAdmin_id(resultSet.getInt("admin_id"));
			Admin.setFname(resultSet.getString("admin_fname"));
			Admin.setLname(resultSet.getString("admin_lname"));
			Admin.setEmail(resultSet.getString("email"));
			Admin.setMobile(resultSet.getInt("mobile"));
			Admin.setAddress(resultSet.getString("address"));
			Admin.setAdmin_control(resultSet.getBoolean("admin_control"));
			Admin.setPassword(resultSet.getString("admin_password"));
      }
      
      if (rows == 1) {
    	  
    	  Connection connection1 = DbConnection.getInstance().getConnection(); 
    	  Class.forName("com.mysql.cj.jdbc.Driver");
        
    	 
    	 String sql1 = "INSERT INTO `admin_login` (`email`, `admin_password`)"
  				+ "VALUES ( ?, ?);";
  		
  		PreparedStatement stmt1 = connection1.prepareStatement(sql1);
  		stmt1.setString(1, Admin.getEmail());
  		stmt1.setString(2, Admin.getPassword());

  		int res1 = stmt1.executeUpdate();
  		
    	 return Admin;
    	  
      } else {
    	  return null;
      }
      
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * Admin forgets password
	 * @param email
	 * @return
	 */
	public admin forgotpassword(String email) {
		
		Connection connection = DbConnection.getInstance().getConnection();
		
		try {
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  
	      String sql ="Select * from `admin` where `email` = ?";
	      PreparedStatement stmt = connection.prepareStatement(sql);
	      stmt.setString(1,email);
	      
	      ResultSet resultSet = stmt.executeQuery();
	      
	      int rows =0;
	      admin Admin = new admin();
	      while (resultSet.next()) {
	    	  
	    	    rows ++;
	    	
				Admin.setAdmin_id(resultSet.getInt("admin_id"));
				Admin.setFname(resultSet.getString("admin_fname"));
				Admin.setLname(resultSet.getString("admin_lname"));
				Admin.setEmail(resultSet.getString("email"));
				Admin.setMobile(resultSet.getInt("mobile"));
				Admin.setAddress(resultSet.getString("address"));
				Admin.setAdmin_control(resultSet.getBoolean("admin_control"));
				Admin.setPassword(resultSet.getString("admin_password"));
	    	  
	      }  if (rows == 1) {
	    	 return Admin;
	    	  
	      } else {
	    	  return null;
	      }
	      
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
}
	
	/**
	 * get all admins from the database
	 * @return
	 */

	public List<admin> getAdminFromDb(){
	
	Connection connection = DbConnection.getInstance().getConnection();
	List<admin> adminList = new ArrayList<>();
	
	String sql = "SELECT * FROM `admin`;";
	
	try {
	  Class.forName("com.mysql.cj.jdbc.Driver");
    
	  PreparedStatement stmt = connection.prepareStatement(sql);
	  ResultSet resultSet = stmt.executeQuery();
					
		while(resultSet.next()) {
			admin Admin = new admin();
			Admin.setAdmin_id(resultSet.getInt("admin_id"));
			Admin.setFname(resultSet.getString("admin_fname"));
			Admin.setLname(resultSet.getString("admin_lname"));
			Admin.setEmail(resultSet.getString("email"));
			Admin.setMobile(resultSet.getInt("mobile"));
			Admin.setAddress(resultSet.getString("address"));
			Admin.setAdmin_control(resultSet.getBoolean("admin_control"));
			Admin.setPassword(resultSet.getString("admin_password"));
			
		adminList.add(Admin);
		}
		
		return adminList;
		
		} catch (Exception e) {
			logger.error("DB ERROR :  Could not access data - "+e.getMessage());
			return null;
	}

}
	
	/**
	 * delete admin from the Database
	 */
	public admin deladmin(int admin_id) {

		 Connection connection = DbConnection.getInstance().getConnection();	
		try {		
		  Class.forName("com.mysql.cj.jdbc.Driver");
				
	      
	      String sql ="delete from admin where admin_id = ?";
	      PreparedStatement stmt = connection.prepareStatement(sql);
	      stmt.setInt(1,admin_id);
	      
	     stmt.executeUpdate();
	     return null;
		
		}
			catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * update admin details
	 * @param package1
	 * @return
	 */
	public int UpdateAdmin(admin admin) {
		
		 try {
			 
			String password = admin.getPassword();
			String encryptedPassword =  Sha1Encrypt (password);
			    
	        String sql = "UPDATE admin SET `admin_fname` = ?, `admin_lname` = ?, `email` = ?, "
	        		+ "`mobile` = ?, `address` = ?, `admin_control` =?, `admin_password` =?"
	        		+ " WHERE `admin_id` = ?";
	        
	        Connection conn = DbConnection.getInstance().getConnection();
	        
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        
			stmt.setInt(8, admin.getAdmin_id());
			stmt.setString(1, admin.getFname());
			stmt.setString(2, admin.getLname());
			stmt.setString(3, admin.getEmail());
			stmt.setInt(4, admin.getMobile());
			stmt.setString(5, admin.getAddress());
			stmt.setBoolean(6, admin.isAdmin_control());
			stmt.setString(7, encryptedPassword);
			
			int response = stmt.executeUpdate();
			conn.close();
			return response;
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("SQL ERROR :  COULD NOT UPDATE ADMIN DATA - "+e.getMessage());
			return -1;
		}
	}
	
}