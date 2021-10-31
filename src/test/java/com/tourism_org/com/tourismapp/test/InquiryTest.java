package com.tourism_org.com.tourismapp.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.tourism_org.com.tourismapp.dao.inquiryDao;
import com.tourism_org.com.tourismapp.model.inquiry;


public class InquiryTest {

	@Test
	public void testInquiry() {
		
		inquiryDao inquiryDao = new inquiryDao();
		inquiry actual =inquiryDao.addInquiry(email, desc);
		
		assertNotNull(actual);
	}
	
}
