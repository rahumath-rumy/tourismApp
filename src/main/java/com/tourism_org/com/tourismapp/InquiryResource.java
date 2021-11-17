package com.tourism_org.com.tourismapp;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.tourism_org.com.tourismapp.dao.inquiryDao;
import com.tourism_org.com.tourismapp.model.inquiry;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("inquiries")
public class InquiryResource {
	
	/**
	 * insert inquiry
	 * @param email
	 * @param desc
	 * @return
	 */
	@POST 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces (MediaType.APPLICATION_JSON)
	public Response addInquiry(
							@FormParam("email") String email,
							@FormParam ("inquiry_desc") String desc) {
		
		Gson gson = new Gson();
		
		inquiry inquiry = new inquiry();
		inquiry.setEmail(email);
		inquiry.setDesc(desc);
	
		
		inquiryDao InquiryDao = new inquiryDao();
		int Res = InquiryDao.addInquiry(inquiry);
		
		Map<String, String> Msg = new HashMap<>();
		
		if (Res > 0) {
			Msg.put("Success", "We have received your inquiry. We will get back to you shortly.");
			String jsonString = gson.toJson(Msg);
			
			return Response  
					.status(200)
					.entity(jsonString)
					.header("Access-Control-Allow-Origin", "*")
					.build();
		
		} else {
			Msg.put("Error","We have not received your inquiry. Please try again!");
			String jsonString = gson.toJson(Msg);
			return Response  
					.status(401)
					.entity(jsonString)
					.build(); 
		}
	}
	
}
