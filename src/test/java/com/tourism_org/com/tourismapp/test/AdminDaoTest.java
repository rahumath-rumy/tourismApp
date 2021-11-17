package com.tourism_org.com.tourismapp.test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import org.junit.jupiter.api.Test;
import com.tourism_org.com.tourismapp.dao.AdminDao;
import com.tourism_org.com.tourismapp.dao.PackageDao;
import com.tourism_org.com.tourismapp.model.Package;
import com.tourism_org.com.tourismapp.model.admin;

public class AdminDaoTest {
	
	private int expected =5;
	
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
		
		String email ="kumar.varun@gmail.com";
		String password ="kumar12345";
		
		AdminDao adminDao = new AdminDao();
		admin actual =adminDao.adminAuth(email, password);
		
		assertNotNull(actual);
	}
	
	@Test
	public void testForgotpassword() {
		
		String email ="kumar.varun@gmail.com";
		
		AdminDao adminDao = new AdminDao();
		admin actual =adminDao.forgotpassword(email);
		
		assertNotNull(actual);
	}
	
	@Test
	public void testdeladmin() {
		int admin_id =101;
		
		AdminDao adminDao = new AdminDao();
		admin actual =adminDao.deladmin(admin_id);
		assertNotNull(actual);
	}
	
	@Test
	public void AddAdmin() {
		
		int expected = 1;
		
		admin Admin = new admin();
		
		Admin.setFname("Joseph");
		Admin.setLname("Faux");
		Admin.setEmail("joseph.faux@gmail.com");
		Admin.setMobile(778987654);
		Admin.setAddress("N0 18, Keppitipola Road, Kolannawa");
		Admin.setAdmin_control(true);
		Admin.setPassword("joseph");

		AdminDao adminDao = new AdminDao();
		int actual = adminDao.addAdmin(Admin);
				
		assertEquals(expected, actual);
	
	}
}
 