package com.tourism_org.com.tourismapp.test;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.tourism_org.com.tourismapp.dao.UserDao;
import com.tourism_org.com.tourismapp.model.User;

	public class UserDaoTest {
		
		private int expected =1;
		
		@Test
		public void testGetUser()  {
			UserDao UserDao = new UserDao ();
			List<User> users =  UserDao.getUserFromDB();
		    int actual = users.size();
		    assertEquals(expected, actual);
		
		}
	}
	 

