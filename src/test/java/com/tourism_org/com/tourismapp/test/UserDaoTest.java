package com.tourism_org.com.tourismapp.test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import org.junit.jupiter.api.Test;

import com.tourism_org.com.tourismapp.dao.AdminDao;
import com.tourism_org.com.tourismapp.dao.UserDao;
import com.tourism_org.com.tourismapp.model.User;
import com.tourism_org.com.tourismapp.model.admin;

	public class UserDaoTest {
		
		private int expected =4;
		
		@Test
		public void testGetUser()  {
			UserDao UserDao = new UserDao ();
			List<User> users =  com.tourism_org.com.tourismapp.dao.UserDao.getUserFromDB();
		    int actual = users.size();
		    assertEquals(expected, actual);
		}
		
		@Test
		public void testSha1Encrypt() {
			String input = "sampleuser";
			UserDao UserDao = new UserDao ();
			String actual = UserDao.Sha1Encrypt(input);
			System.out.println (actual);
			
			assertNotNull(actual);
		}

		@Test
		public void testLogin() {
			
			String email ="harith@yahoo.com";
			String password ="harith123";
			
			UserDao userDao = new UserDao();
			User actual =userDao.userAuth(email, password);
			
			assertNotNull(actual);
		}
		
		@Test
		public void testForgotPassword() {
			
			String email ="harith@yahoo.com";
			
			UserDao userDao = new UserDao();
			User actual =userDao.forgotpassword(email);
			
			assertNotNull(actual);
		}
		
		@Test
		public void AddUser() {
			
			int expected = 1;
			
			User User= new User();
		
			//User.setId("");
			User.setFname("Sophie");
			User.setLname("James");
			User.setPhone(778965423);
			User.setAddress("22/4B, Mary Apartment, Houston");
			User.setNationality("American");
			User.setSrilankan(false);
			User.setCountry("USA");
			User.setEmail("sophie98@gmail.com");
			User.setPassport("311456717");
			User.setPassword("sophie98");			
			
			UserDao userDao = new UserDao();
			int actual = userDao.addUser(User);
					
			assertEquals(expected, actual);
		
		}
	}
	 

