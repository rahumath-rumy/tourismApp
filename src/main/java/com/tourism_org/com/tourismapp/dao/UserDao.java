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
import com.tourism_org.com.tourismapp.model.User;
import com.tourism_org.com.tourismapp.model.admin;
import com.tourism_org.com.tourismapp.dao.UserDao;

	
    public class UserDao {

    	private static Logger logger = LogManager.getLogger(UserDao.class);
		//private org.apache.logging.log4j.Logger logger =  LogManager.getLogger(UserDao.class);
		
		private List <User> userList = new ArrayList<>();//empty list which accepts only the users
		
		/**
		 * Get all the users.
		 * @return
		 */
	

		
//		public List<User> getUserFromDb() {
//			// TODO Auto-generated method stub
//			return null;
//		}

		/**
		 * Get a user by user id.
		 * @param id
		 * @return
		 */
		
		//get all the users
		
		public List <User> getAlluser() { // if I need the list of users
			List<User> user = getUserFromDB(); // if I need the list of users
		
			return user;
		}
		
		
		public User getaUser (int id) { //to get a user

			List <User> users =  getUserFromDB();
			 
			 for(User user:users) {
				 if (user.getId() == id) {
					 return user;
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
			
			//Connection connection = DbConnection.getInstance().getConnection();
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/tourismapp","root","12345");
				//Prepare SQL query.
				

				String password = user.getPassword();
			    String encryptedPassword =  Sha1Encrypt1 (password);
				
				//Prepare SQL query.
				String sql = "INSERT INTO `customer` (`Fname`, `Lname`, `Phone`, `Address`,  `City`,  `State`,  `PostalCode`, `Country`,`Email`,`PassportNumber`, `password`) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
				
				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setString(1, user.getFname());
				stmt.setString(2, user.getLname());
				stmt.setInt(3, user.getPhone());
				stmt.setString(4, user.getAddress());
				stmt.setString(5, user.getCity());
				stmt.setString(6, user.getState());
				stmt.setString(7, user.getPostalcode());
				stmt.setString(8, user.getCountry());
				stmt.setString(9, user.getEmail());
				stmt.setString(10, user.getPassport());
				stmt.setString(11, encryptedPassword);
		
				int response = stmt.executeUpdate();
				//connection.close();
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
		 * @throws ClassNotFoundException 
		 */
		
		public User userAuth(String email, String password) {
			
			try {
			  Class.forName("com.mysql.cj.jdbc.Driver");
		      Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tourismapp","root","12345");
		    
		      String encryptedPassword = Sha1Encrypt (password);
		      
		      String sql ="Select * from `customer` where `Email` = ? and `password`=?";
		      PreparedStatement stmt = conn.prepareStatement(sql);
		      stmt.setString(1,email);
		      stmt.setString (2, encryptedPassword);
		      
		      ResultSet resultSet = stmt.executeQuery();
		      
		      int rows =0;
		      User User = new User();
		      while (resultSet.next()) {
		    	  
		    	    rows ++;
		    	
		    	    User user = new User();
					user.setId(resultSet.getInt("Id"));
					user.setFname(resultSet.getString("Fname"));
					user.setLname(resultSet.getString("Lname"));
					user.setPhone(resultSet.getInt("Phone"));
					user.setAddress(resultSet.getString("Address"));
					user.setCity(resultSet.getString("City"));
					user.setState(resultSet.getString("State"));
					user.setPostalcode(resultSet.getString("PostalCode"));
					user.setCountry(resultSet.getString("Country"));
					user.setEmail(resultSet.getString("Email"));
					user.setPassport(resultSet.getString("PassportNumber"));
					user.setPassword(resultSet.getString("password"));
		    	  
		      }
		      
		      if (rows == 1) {
		        	  
		    	 Class.forName("com.mysql.cj.jdbc.Driver");
		         Connection conn1=DriverManager.getConnection("jdbc:mysql://localhost:3306/tourismapp","root","12345");
		    	    	 
		         String sql1 = "INSERT INTO `customer_login` (`Email`, `password`)"
		    	  				+ "VALUES (?,?);";
		    	  		
		  		PreparedStatement stmt1 = conn1.prepareStatement(sql1);
	   	  		//stmt1.setString(1, User.getLoginid());
	   	  		stmt1.setString(1, User.getEmail());
		  		stmt1.setString(2, User.getPassword());

		    	int res1 = stmt1.executeUpdate();
		    	  		
		    	 //return res1;
		    	 return User;
		  
		      } else {
		    	  return null;
		      }
		      
			} catch (Exception e) {
				e.printStackTrace();
				return null;
				
			}
			
		}

		public String Sha1Encrypt1 (String tobeEncrpyted) {
		   
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
		
		
//		public int updateUser(User user) {
//			return 1; //have to with a db pause for now
//			
//		}
//		
//		public int deleteUser(User user) {
//			try {
//				if (user != null) {
//					userList.remove(user);
//					return 1;
//				} else {
//					return 0;
//				}
//				
//			} catch (Exception e) { //if something goes wrong, informs here
//				e.printStackTrace(); //error details in the console
//				return -1; //if something goes wrong
//			}
//			
//		}
		
		public static List<User> getUserFromDB()   {
			
			List <User> userList = new ArrayList<>();
			//create connection instance
			
			//Connection conn = DbConnection.getInstance().getConnection();
			//select, delete, update, insert - wont change the structure (CRUD can be done with those queries)
			
			//prepare query
			String sql = "Select * from `customer`;" ;
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tourismapp","root","12345");
					
				PreparedStatement stmt = conn.prepareStatement(sql);
				
				ResultSet resultSet = stmt.executeQuery();
							
				while(resultSet.next()) {
					User user = new User();
					user.setId(resultSet.getInt("id"));
					user.setFname(resultSet.getString("Fname"));
					user.setLname(resultSet.getString("Lname"));
					user.setPhone(resultSet.getInt("Phone"));
					user.setAddress(resultSet.getString("Address"));
					user.setCity(resultSet.getString("City"));
					user.setState(resultSet.getString("State"));
					user.setPostalcode(resultSet.getString("PostalCode"));
					user.setCountry(resultSet.getString("Country"));
					user.setEmail(resultSet.getString("Email"));
					user.setPassport(resultSet.getString("PassportNumber"));
					user.setPassword(resultSet.getString("password"));
			
					userList.add(user);
				}
					//connection.close();
					return userList;
				
			} catch (Exception e) {
				logger.error("DB ERROR : Could not access data - "+e.getMessage());
				return null;
			}
		}
			//PreparedStatement stmt = conn.prepareStatement(sql);
		
		//}
		
//		private List<user> fakeDbCall(){ //fakedb is name of db
			//database access happens here
//		
		//implement log here 
//		logger.fatal("This is a fatal log")	;
//		logger.error("This is an error!");
//		logger.warn("This is a warn");
//		//logger.info("This is info");
//		//logger.debug("This is debug");
//		logger.trace("This is a trace log");
//
//		}

		
//		/**
//		 * SHA-1 encryption method
//		 * @param tobeEncrypted
//		 * @return
//		 */
		
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
    }


