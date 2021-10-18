package com.tourism_org.com.tourismapp.dao;
import java.lang.System.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;

import com.tourism_org.com.tourismapp.config.DbConnection;
import com.tourism_org.com.tourismapp.model.User;
	
    public class UserDao {

		private  org.apache.logging.log4j.Logger logger =  LogManager.getLogger(UserDao.class);
		
		private List<User> userlist = new ArrayList<>();//empty list which accepts only the users
		
		/**
		 * Get all the users.
		 * @return
		 */

		public List <User> getAll(){ // if I need the list of users
			List<User> user = getUsersFromDb(); // if I need the list of users
		
			return user;
		}
		
		/**
		 * Get a user by user id.
		 * @param id
		 * @return
		 */
		public User getaUser (int id) { //to get a user
			 List <User> user =  getUsersFromDb();
			 
			 for(User users:user) {
				 if (users.getCustomer_id()== id) {
					 return users;
				 }
			 }
			 logger.error("Invalid ID");
			 return null; //if there is no such id
		}
		
		/**
		 * Add user into the system.
		 * @param user
		 * @return
		 */

		public int addUser (User user) { //if more than 2 condition we can go with boolean
			//try catch block is to do complicated coding
			//error handling can be easily done using try catch
			Connection connection = DbConnection.getInstance().getConnection();
			
			try {
				
				String password =  user.getPassword();
				String encryptedPassword = Sha1Encrpt(password);
				
				//Prepare SQL query.
				String sql = "INSERT INTO `customer` (`firstname`, `lastname`, `mobile`, `address`, `country`,`email`,`passport`, `password`) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
				
				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setString(1, user.getFname());
				stmt.setString(2, user.getLname());
				stmt.setInt(3, user.getMobile());
				stmt.setString(4, user.getAddress());
				stmt.setString(5, user.getCountry());
				stmt.setString(6, user.getEmail());
				stmt.setString(7, user.getPassport());
				stmt.setString(8, encryptedPassword);
		
				int response = stmt.executeUpdate();
				connection.close();
				return response;
				
		     	} catch (Exception e) {
			    	e.printStackTrace();
				    logger.error("SQL ERROR :  Could not insert data - "+e.getMessage());
				    return -1; //if something goes wrong
			}
		}

		/**
		 * Login checker method.
		 * @param email
		 * @param password
		 * @return
		 */
		public User userAuth(String email, String password) {
			Connection connection = DbConnection.getInstance().getConnection();
			
			try {
				
				String encryptedPassword = Sha1Encrpt(password);
				
				//Prepare SQL query.
				String sql = "SELECT * FROM `customer` WHERE `email`=? AND `password`=?";
				
				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setString(1, email);
				stmt.setString(2, encryptedPassword);
				
				ResultSet rs = stmt.executeQuery();
				
				int rows = 0;
				User user = new User();
				while(rs.next()) {
					rows++;
					user.setCustomer_id(rs.getInt("id"));
					user.setFname(rs.getString("fname"));
					user.setLname(rs.getString("lname"));
					user.setMobile(rs.getInt("mobile"));
					user.setAddress(rs.getString("address"));
					user.setEmail(rs.getString("email"));
					user.setPassword(rs.getString("password"));
				}
				
				if(rows == 1) {
					return user;
				} else {
					return null;
				}
				
			    } catch (Exception e) {
				    e.printStackTrace();
				    return null;
			}
			
		}


		public int updateUser(User user) {
			return 1; //have to with a db pause for now
			
		}
		
		public int deleteUser(User user) {
			try {
				if (user != null) {
					userlist.remove(user);
					return 1;
				} else {
					return 0;
				}
				
			} catch (Exception e) { //if something goes wrong, informs here
				e.printStackTrace(); //error details in the console
				return -1; //if something goes wrong
			}
			
		}
		
		public List<User> getUsersFromDb() throws SQLException{
			
			List <User> userList = newArrayList<>();
			//create connection instance
			
			Connection conn = DbConnection.getInstance().getConnection();
			//select, delete, update, insert - wont change the structure (CRUD can be done with those queries)
			
			//prepare query
			String sql = "Select * from `customer`" ;
			
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				
				ResultSet resultSet = stmt.executeQuery();
							
				while(resultSet.next()) {
					User user = new User();
					user.setCustomer_id(resultSet.getInt("id"));
					user.setFname(resultSet.getString("fname"));
					user.setLname(resultSet.getString("lname"));
					user.setMobile(resultSet.getInt("mobile"));
					user.setAddress(resultSet.getString("address"));
					user.setEmail(resultSet.getString("email"));
					user.setPassport(resultSet.getString("passport"));
					user.setPassword(resultSet.getString("password"));
			
					userList.add(user);
				}
					conn.close();
					return userList;
				
			} catch (SQLException e) {
				logger.error("DB ERROR :  Could not access data - "+e.getMessage());
				return null;

			//PreparedStatement stmt = conn.prepareStatement(sql);
		
		//}
		
		//private List<user> fakeDbCall(){ //fakedb is name of db
			//database access happens here
		
		//implement log here 
		//logger.fatal("This is a fatal log")	;
		logger.error("This is an error!");
		logger.warn("This is a warn");
		//logger.info("This is info");
		//logger.debug("This is debug");
		logger.trace("This is a trace log");

			
		userlist.add(new User(1,"Rahmath" , "Rumy", 011892119, "dehiwala", "rah@gmail.com","9899","123"));
		userlist.add(new user(2,"zai@gmail.com","zai","456","staff"));
		userlist.add(new user(3,"thisu@gmail.com","thisuni","113","admin"));
		userlist.add(new user(4,"saf@gmail.com","safra","ar3","admin"));	
		userlist.add(new user(5,"anon@gmail.com","anon","rt3","customer"));
		//can have different tables for different kinds of user
		
		return userlist;
		
		}
		
		/**
		 * SHA-1 encryption method
		 * @param tobeEncrypted
		 * @return
		 */
		public String Sha1Encrpt(String tobeEncrypted) {
			try {
				byte[] passwordArr = tobeEncrypted.getBytes(StandardCharsets.UTF_8);
				MessageDigest sha1Encrypt = MessageDigest.getInstance("SHA-1");
				byte[] encrptPassword = sha1Encrypt.digest(passwordArr);
				
				String encryptedString = Base64.getEncoder().encodeToString(encrptPassword);
				
				return encryptedString;
				
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		

	}

}
