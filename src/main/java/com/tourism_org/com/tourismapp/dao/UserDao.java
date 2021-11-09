package com.tourism_org.com.tourismapp.dao;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.tourism_org.com.tourismapp.config.DbConnection;
import com.tourism_org.com.tourismapp.model.User;

import jakarta.ws.rs.core.Response;

import com.tourism_org.com.tourismapp.dao.UserDao;

	
    public class UserDao {
    
    	private static Logger logger = LogManager.getLogger(UserDao.class);

	
    	//get a user
		public User getaUser (int id) {

			List <User> users =  getUserFromDB();
			 
			 for(User user:users) {
				 if (user.getId() == id) {
					 return user;
				 }
			 }
			 
			 logger.info("Invalid ID");
			 return null; 
		}
		
		
		//addauser
		public int addUser (User user) { 
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/tourismapp","root","12345");
				

				String password = user.getPassword();
			    String encryptedPassword =  Sha1Encrypt1 (password);
				
				
				String sql = "INSERT INTO `customer` (`Fname`, `Lname`, `Phone`, `Email`, `Address`,  `Srilankan`,  `Country`, `Nationality`,`PassportOrNIC`, `password`) "
						+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
				
				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setString(1, user.getFname());
				stmt.setString(2, user.getLname());
				stmt.setInt(3, user.getPhone());
				stmt.setString(4, user.getEmail());
				stmt.setString(5, user.getAddress());
				stmt.setBoolean(6, user.isSrilankan());
				stmt.setString(7, user.getCountry());
				stmt.setString(8, user.getNationality());
				stmt.setString(9, user.getPassport());
				stmt.setString(10, encryptedPassword);
		
				int response = stmt.executeUpdate();
				return response;
				
		     	} catch (Exception e) {
			    	e.printStackTrace();
				    logger.info("SQL ERROR :  Could not insert data - "+e.getMessage());
				    return -1; 
			}
		}
		
		//login to account
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
		    	
		    	    //User user = new User();
					User.setId(resultSet.getInt("Id"));
					User.setFname(resultSet.getString("Fname"));
					User.setLname(resultSet.getString("Lname"));
					User.setPhone(resultSet.getInt("Phone"));
					User.setAddress(resultSet.getString("Address"));
					User.setNationality(resultSet.getString("Nationality"));
					User.setSrilankan(resultSet.getBoolean("Srilankan"));
					User.setCountry(resultSet.getString("Country"));
					User.setEmail(resultSet.getString("Email"));
					User.setPassport(resultSet.getString("PassportOrNIC"));
					User.setPassword(resultSet.getString("password"));
		    	  
		      }
		      
	      if (rows == 1) {
		        	  
		    	 Class.forName("com.mysql.cj.jdbc.Driver");
		         Connection conn1=DriverManager.getConnection("jdbc:mysql://localhost:3306/tourismapp","root","12345");
		    	    	 
		         String sql1 = "INSERT INTO `customer_login` (`loginid`,`Email`, `password`)"
		    	  				+ "VALUES (?,?,?);";
		    	  		
		  		PreparedStatement stmt1 = conn1.prepareStatement(sql1);
	   	 
				stmt1.setString(1, User.getLoginid());
	   	  		stmt1.setString(2, User.getEmail());
		  		stmt1.setString(3, User.getPassword());

		    	int resultSet1 = stmt1.executeUpdate();		    	  		
	    	  	return User;
		  
	      } else {
	    	  return null;
	      }
	      
		} catch (Exception e) {
			e.printStackTrace();
			 logger.info("SQL ERROR :  Invalid data for login - "+e.getMessage());
			return null;
		}

	}
		
//		public User updateUser (User user) {
//
//			if(user.getId() <= 0) {
//				return null;
//			}
//			
//			user.put(user.getId(),user);
//				return user;
//			}
		
		
//		//update error
		 public User updateUser(int id)  {
			 
			 try {
				 
		     Connection connection = DbConnection.getInstance().getConnection(); 
			 
			 String sql =  "UPDATE customer SET Fname = ?, Lname=?, Phone=?, Email=?, Address=?, Srilankan=?, Country=?, "
		        + "Nationality=?, PassportOrNIC=?, password=? WHERE Id=?";
			
			 PreparedStatement stmt = connection.prepareStatement(sql);
			 
			 User user = null;
			 
			 String password = user.getPassword();
			 String encryptedPassword = Sha1Encrypt (password);
			 
				
				stmt.setString(1, user.getFname());
				stmt.setString(2, user.getLname());
				stmt.setInt(3, user.getPhone());
				stmt.setString(4, user.getEmail());
				stmt.setString(5, user.getAddress());
				stmt.setBoolean(6, user.isSrilankan());
				stmt.setString(7, user.getCountry());
				stmt.setString(8, user.getNationality());
				stmt.setString(9, user.getPassport());
				stmt.setString(10, encryptedPassword);

			  
			 int rowsUpdated = stmt.executeUpdate();
			 if (rowsUpdated > 0) {
			     System.out.println("An existing user was updated successfully!");
			 
			 } connection.close();
			 }catch (SQLException ex){
				 ex.printStackTrace();
			 }
		 }
			 
			 
				        
		          
//		        try {
//		        	Class.forName("com.mysql.cj.jdbc.Driver");
//		        	
//		        	PreparedStatement stmt = connection.prepareStatement(sql);
//		        	
//		        	String password = id.getPassword();
//		        	 String encryptedPassword = Sha1Encrypt (password);
//		    
//		    
//		        	stmt.setString(1, id.getFname());
//					stmt.setString(2, id.getLname());
//					stmt.setInt(3, id.getPhone());
//					stmt.setString(4, id.getEmail());
//					stmt.setString(5, id.getAddress());
//					stmt.setBoolean(6, id.isSrilankan());
//					stmt.setString(7, id.getCountry());
//					stmt.setString(8, id.getNationality());
//					stmt.setString(9, id.getPassport());
//					stmt.setString(10, encryptedPassword);
//					stmt.setInt(11, id.getId());
//		            stmt.executeUpdate();
//		       
//			  return null;
//					
//				}
//					catch (Exception e) {
//					e.printStackTrace();
//					return null;
//				}
//			}	
		 
		 //delete a user
		public User deluser(int id) {

			 Connection connection = DbConnection.getInstance().getConnection();	
			
			 try {		
			  Class.forName("com.mysql.cj.jdbc.Driver");
		      String sql ="delete from customer where Id = ?";
		      PreparedStatement stmt = connection.prepareStatement(sql);
		      stmt.setInt(1,id);
		      
		     stmt.executeUpdate();
		    	  
		    
			return null;
			
			}
				catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}	
		
		//get all the users from db
		public static List<User> getUserFromDB()   {
			
			List <User> userList = new ArrayList<>();
		
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
					user.setEmail(resultSet.getString("Email"));
					user.setAddress(resultSet.getString("Address"));
					user.setSrilankan(resultSet.getBoolean("Srilankan"));
					user.setCountry(resultSet.getString("Country"));
					user.setNationality(resultSet.getString("Nationality"));
					user.setPassport(resultSet.getString("PassportOrNIC"));
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

		
		//user forgot password
		public User forgotpassword(String email) {
			try {
				  Class.forName("com.mysql.cj.jdbc.Driver");
			      Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tourismapp","root","12345");
			      
			      String sql ="Select * from `customer` where `Email` = ?";
			     
			      PreparedStatement stmt = conn.prepareStatement(sql);
			      stmt.setString(1,email);
			      
			      ResultSet resultSet = stmt.executeQuery();
			      
			      int rows =0;
			      User User = new User();
			      while (resultSet.next()) {
			    	  
			    	    rows ++;
			    	
						User.setId(resultSet.getInt("Id"));
						User.setFname(resultSet.getString("Fname"));
						User.setLname(resultSet.getString("Lname"));
						User.setPhone(resultSet.getInt("Phone"));
						User.setEmail(resultSet.getString("Email"));
						User.setAddress(resultSet.getString("Address"));
						User.setSrilankan(resultSet.getBoolean("Srilankan"));
						User.setCountry(resultSet.getString("Country"));
						User.setNationality(resultSet.getString("Nationality"));
						User.setPassport(resultSet.getString("PassportOrNIC"));
						User.setPassword(resultSet.getString("password"));
			    	  
			      }
			      
		      if (rows == 1) {
			   	return User;
			  
		      } else {
		    	return null;
		      }
		      
			} catch (Exception e) {
				e.printStackTrace();
				return null;	
			}

		}
    
		
		/**
		 * SHA-1 encryption method
		 * @param tobeEncrypted
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
		
		public User Payment (String email) {
			
			Connection connection = DbConnection.getInstance().getConnection();
			
			try {
				  Class.forName("com.mysql.cj.jdbc.Driver");
			      String sql ="Select * from `customer` where `Email` = ?";
			     
			      PreparedStatement stmt = connection.prepareStatement(sql);
			      stmt.setString(1,email);
			      
			      ResultSet resultSet = stmt.executeQuery();
			      
			      int rows =0;
			      User User = new User();
			      while (resultSet.next()) {
			    	  
			    	    rows ++;
			    	
						User.setId(resultSet.getInt("Id"));
						User.setFname(resultSet.getString("Fname"));
						User.setLname(resultSet.getString("Lname"));
						User.setPhone(resultSet.getInt("Phone"));
						User.setEmail(resultSet.getString("Email"));
						User.setAddress(resultSet.getString("Address"));
						User.setSrilankan(resultSet.getBoolean("Srilankan"));
						User.setCountry(resultSet.getString("Country"));
						User.setNationality(resultSet.getString("Nationality"));
						User.setPassport(resultSet.getString("PassportOrNIC"));
						User.setPassword(resultSet.getString("password"));
			    	  
			      }
			      
		      if (rows == 1) {
			   	return User;
			  
		      } else {
		    	return null;
		      }
		      
			} catch (Exception e) {
				e.printStackTrace();
				return null;	
			}

		}
		
		public int payment (User user) { 
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/tourismapp","root","12345");
				
				String sql = "INSERT INTO `payment` (`Id`, `Package_Id`, `CardType`, `card_no`, `CVV`,  `exp_date`,  `Payment_date`, `Amount_paid`) "
						+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?);";
				
				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setInt(1, user.getId());
				//stmt.setString(2, user.packageID());
				stmt.setInt(3, user.getCardType());
				stmt.setInt(4, user.getCardNo());
				stmt.setInt(5, user.getCvv());
				stmt.setString(6, user.getExp_date());
				stmt.setString(7, user.getPaymentdate());
				stmt.setFloat(8, user.getAmountpaid());
				
		
				int response = stmt.executeUpdate();
				return response;
				
		     	} catch (Exception e) {
			    	e.printStackTrace();
				    logger.error("SQL ERROR :  Could not insert data - "+e.getMessage());
				    return -1; 
			}
		}

		
//		public User getpaymentuser (int id) {
//
//			List <User> users =  getUserFromDB();
//			 
//			 for(User user:users) {
//				 if (user.getId() == id) {
//					 
//					 try {
//							Class.forName("com.mysql.cj.jdbc.Driver");
//							Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/tourismapp","root","12345");
//							
//							String sql = "INSERT INTO `payment` (`Id`, `Package_Id`, `CardType`, `card_no`, `CVV`,  `exp_date`,  `Payment_date`, `Amount_paid`) "
//									+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?);";
//							
//							PreparedStatement stmt = connection.prepareStatement(sql);
//							stmt.setInt(1, user.getId());
//							//stmt.setString(2, user.packageID());
//							stmt.setInt(3, user.getCardType());
//							stmt.setInt(4, user.getCardNo());
//							stmt.setInt(5, user.getCvv());
//							stmt.setString(6, user.getExp_date());
//							stmt.setString(7, user.getPaymentdate());
//							stmt.setFloat(8, user.getAmountpaid());
//							
//					
//							int response = stmt.executeUpdate();
//							return user;
//							
//				 } catch (Exception e) {
//				    	e.printStackTrace();
//					    logger.error("SQL ERROR :  Could not insert data - "+e.getMessage());
//					    return null;
//					    	
//		}
				 
		}	
   