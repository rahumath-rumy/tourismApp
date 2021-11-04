package com.tourism_org.com.tourismapp.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.tourism_org.com.tourismapp.dao.UserDao;
import com.tourism_org.com.tourismapp.dao.inquiryDao;
import com.tourism_org.com.tourismapp.model.User;
import com.tourism_org.com.tourismapp.model.inquiry;


public class InquiryTest {

	@Test
	public void testInquiry() {
		
		String email ="rah@gmail.com";
		String desc ="I want to inquire about the latest packages you'll have.";
		
		
		inquiryDao inquiryDao = new inquiryDao();
		inquiry inquiry;
	    String actual =inquiryDao.addInquiry(email, desc);
		
		assertNotNull(actual);
	}
	
}
