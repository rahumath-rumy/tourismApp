package com.tourism_org.com.tourismapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import com.cohort4.cohort4jaxrsapp.model.User;
//import com.tourism_org.com.tourismapp.config.DbConnection;
import com.tourism_org.com.tourismapp.dao.AdminDao;
import com.tourism_org.com.tourismapp.model.admin;

public class AdminDao {
	
	private Logger logger = LogManager.getLogger(AdminDao.class);
	
	 private List <admin> adminList = new ArrayList<>();
	
	public List<admin> getAll(){
		
		List<admin> admin = getAdminFromDb();
		
		return admin;
	}
	
//	public static void main (String[] args) {
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tourismapp","root","12345");
//		    String s="insert into admin values('2','Watson','John','johnwatson@gmail.com',0778978907,'passcode1542')";
//			PreparedStatement st=con.prepareStatement(s);
//		st.execute();
//		con.close();
//		}
//	catch (Exception e)
//		{
//		System.out.println (e);
//		}
//	}


public int addAdmin(admin Admin) {
	
	//Connection connection = DbConnection.getInstance().getConnection();
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	    Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/tourismapp","root","12345");
		//Prepare SQL query.
		String sql = "INSERT INTO `admin` (`admin_id`,`fname`,`lname`, `email`,`mobile`, `password`)"
				+ "VALUES (?, ?, ?, ?, ?);";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, Admin.getAdmin_id());
		stmt.setString(2, Admin.getFname());
		stmt.setString(3, Admin.getLname());
		stmt.setString(4, Admin.getEmail());
		stmt.setInt(5, Admin.getMobile());
		stmt.setString(6, Admin.getPassword());
		
		int response = stmt.executeUpdate();
		
		return response;
		
	} catch (Exception e) {
		e.printStackTrace();
		logger.error("SQL ERROR :  Could not insert data - "+e.getMessage());
		return -1;
	}
	}

	
public admin getaAdmin(int id) {
		
		List<admin> admins = getAdminFromDb();
		
		for (admin admin : admins) {
			if (admin.getAdmin_id() == id) {
				return admin;
			}
		}
		return null;
	}
	
public List<admin> getAdminFromDb(){
	
	List<admin> adminList = new ArrayList<>();
	
	//Create connection instance.
	//Connection conn = DbConnection.getInstance().getConnection();
	
	//Prepare the query.
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