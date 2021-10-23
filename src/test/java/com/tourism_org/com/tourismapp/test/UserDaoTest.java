package com.tourism_org.com.tourismapp.test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.tourism_org.com.tourismapp.dao.UserDao;
import com.tourism_org.com.tourismapp.model.User;

	public class UserDaoTest {
		
		private int expected =5;
		
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
			
			String email ="olive@yahoo.com";
			String password ="olive";
			
			UserDao userDao = new UserDao();
			User actual =userDao.userAuth(email, password);
			
			assertNotNull(actual);
		}
		
		@Test
		public void testForgotPassword() {
			
			String email ="olive@yahoo.com";
			
			UserDao userDao = new UserDao();
			User actual =userDao.forgotpassword(email);
			
			assertNotNull(actual);
		}
	}
	 

