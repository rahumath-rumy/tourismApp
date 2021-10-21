package com.tourism_org.com.tourismapp.dao;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import com.tourism_org.com.tourismapp.config.DbConnection;
import com.tourism_org.com.tourismapp.dao.AdminDao;
import com.tourism_org.com.tourismapp.model.admin;

import jakarta.ws.rs.PathParam;

public class AdminDao {
	
	private Logger logger = LogManager.getLogger(AdminDao.class);
	
	private List <admin> adminList = new ArrayList<>();
	
	
//	//get all the users
	public List<admin> getAll(){
		
		List<admin> admin = getAdminFromDb();
		
		return admin;
	}
//	
//	public static void main (String[] args) {
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tourismapp","root","12345");
//		    String s="insert into admin values('1','Watson','John','johnwatson@gmail.com',0778978907,'passcode1542')";
//			PreparedStatement st=con.prepareStatement(s);
//		st.execute();
//		con.close();
//		}
//	catch (Exception e)
//		{ 
//		System.out.println (e);
//		}
//	}}
 
//post request
public int addAdmin(admin Admin) {
	
	//Connection connection = DbConnection.getInstance().getConnection();
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	    Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/tourismapp","root","12345");
		
	    String password = Admin.getPassword();
	    String encryptedPassword =  Sha1Encrypt (password);
	    
	    
	    //Prepare SQL query.
		String sql = "INSERT INTO `admin` (`admin_fname`,`admin_lname`, `email`,`mobile`, `admin_password`)"
				+ "VALUES (?, ?, ?, ?, ?);";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, Admin.getFname());
		stmt.setString(2, Admin.getLname());
		stmt.setString(3, Admin.getEmail());
		stmt.setInt(4, Admin.getMobile());
		stmt.setString(5, encryptedPassword);
		
		int res = stmt.executeUpdate();
		
		return res;
		
	} catch (Exception e) {
		e.printStackTrace();
		logger.error("SQL ERROR :  Could not insert data - "+e.getMessage());
		return -1;
	}
}


public String Sha1Encrypt (String tobeEncrpyted) {
	//String tobeEncrpyted = Admin.getPassword();
   
	try {
	byte[] passwordArr = tobeEncrpyted.getBytes();
    
    MessageDigest sha1Encrypt = MessageDigest.getInstance("SHA-1");
    byte[] encryptPassword = sha1Encrypt.digest (passwordArr);
    
    //StringBuilder sbEncryptPassword = new StringBuilder();
    
//    for (byte b : encryptPassword) {
//    	sbEncryptPassword.append(b);
//    }
    
    String s = Base64.getEncoder().encodeToString(encryptPassword);
    	
    
    return s;
	
	} 	catch (Exception e) { 
		e.printStackTrace();
		return null;
	}
   
	
}

    public int DelAdmin(@PathParam("admin_id") int admin_id) {
    return DelAdmin(admin_id);
}



//get an admin by admin id 

public admin getaAdmin(int admin_id) {
		
		List<admin> admins = getAdminFromDb();

		for (admin admin : admins) {
			if (admin.getAdmin_id() == admin_id) {
				return admin;
			}
		}
		return null;
}

public admin adminAuth(String email, String password) {
	
	try {
	  Class.forName("com.mysql.cj.jdbc.Driver");
      Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tourismapp","root","12345");
    
      String encryptedPassword = Sha1Encrypt (password);
      
      String sql ="Select * from `admin` where `email` = ? and `admin_password`=?";
      PreparedStatement stmt = conn.prepareStatement(sql);
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
			Admin.setPassword(resultSet.getString("admin_password"));
    	  
      }
      if (rows == 1) {
    	  return Admin;
    	  
      } else {
    	  return null;
      }
      
	} catch (Exception e) {
		e.printStackTrace();
		return null;
		
	}
	
}
	
public List<admin> getAdminFromDb(){
	
	List<admin> adminList = new ArrayList<>();
	
//	Create connection instance.
//	Connection conn = DbConnection.getInstance().getConnection();
//	Prepare the query.
	
	String sql = "SELECT * FROM `admin`;";
	
	try {
	  Class.forName("com.mysql.cj.jdbc.Driver");
      Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tourismapp","root","12345");
	  PreparedStatement stmt = conn.prepareStatement(sql);
	  ResultSet resultSet = stmt.executeQuery();
					
		while(resultSet.next()) {
			admin Admin = new admin();
			Admin.setAdmin_id(resultSet.getInt("admin_id"));
			Admin.setFname(resultSet.getString("admin_fname"));
			Admin.setLname(resultSet.getString("admin_lname"));
			Admin.setEmail(resultSet.getString("email"));
			Admin.setMobile(resultSet.getInt("mobile"));
			Admin.setPassword(resultSet.getString("admin_password"));
			
		adminList.add(Admin);
		}
		
		return adminList;
		
		} catch (Exception e) {
			logger.error("DB ERROR :  Could not access data - "+e.getMessage());
			return null;
	}
	
}

}