package com.tourism_org.com.tourismapp.test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import org.junit.jupiter.api.Test;
import com.tourism_org.com.tourismapp.dao.AdminDao;
import com.tourism_org.com.tourismapp.model.admin;

public class AdminDaoTest {
	
	private int expected =1;
	
	@Test
	public void testGetAdmins() {
		AdminDao adminDao = new AdminDao ();
		List <admin> admins =  adminDao.getAdminFromDb();
	    int actual = admins.size();
	    assertEquals(expected, actual);
	
	}
	
	@Test
	public void testSha1Encrypt() {
		String input = "sample";
		AdminDao adminDao = new AdminDao();
		String actual = adminDao.Sha1Encrypt(input);
		System.out.println (actual);
		
		assertNotNull(actual);
	}
	
	@Test
	public void testLogin() {
		
		String email ="henryj@gmail.com";
		String password ="henrydanger";
		
		
		AdminDao adminDao = new AdminDao();
		admin actual =adminDao.adminAuth(email, password);
		
		assertNotNull(actual);
	}
	
}
 