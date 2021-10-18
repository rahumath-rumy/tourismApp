package com.tourism_org.com.tourismapp.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.tourism_org.com.tourismapp.dao.AdminDao;
import com.tourism_org.com.tourismapp.model.admin;

public class AdminDaoTest {
	
	private int expected =2;
	
	@Test
	public void testGetAdmins() {
		AdminDao adminDao = new AdminDao ();
		List <admin> admins =  adminDao.getAdminFromDb();
	    int actual = admins.size();
	    assertEquals(expected, actual);
	
	}
}
 