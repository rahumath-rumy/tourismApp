package com.tourism_org.com.tourismapp.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.tourism_org.com.tourismapp.dao.inquiryDao;

import com.tourism_org.com.tourismapp.model.inquiry;


public class InquiryTest {

	@Test
	public void testInquiry() {
		
		int expected = 1;
		inquiry Inquiry = new inquiry();
		
		Inquiry.setEmail("rayyaan@gmail.com");
		Inquiry.setDesc("Sample Inquiry");

		inquiryDao InquiryDao = new inquiryDao();
		int actual = InquiryDao .addInquiry(Inquiry);

		assertNotNull(actual);
			
	}
	
}
