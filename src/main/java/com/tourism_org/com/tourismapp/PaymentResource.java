package com.tourism_org.com.tourismapp;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.tourism_org.com.tourismapp.dao.PaymentDao;
import com.tourism_org.com.tourismapp.model.Payment;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("payment")
public class PaymentResource {
	

private Gson gson = new Gson();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPayments() {
		PaymentDao paymentDao = new PaymentDao();
		List<Payment> paymentList = paymentDao.getAll();
		
		String jsonString = gson.toJson(paymentList);
		
		return Response
				.status(200)
				.entity(jsonString)
				.header("Access-Control-Allow-Origin", "*")
				.build();
	}
	
	@Path("{payment_id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAnItem(@PathParam("payment_id") int payment_id) {
		PaymentDao paymentDao = new PaymentDao();
		Payment payment = paymentDao.getAPayment(payment_id);
		if(payment != null) {
			String jsonString = gson.toJson(payment);
			return Response
					.status(200)
					.entity(jsonString)
					.header("Access-Control-Allow-Origin", "*")
					.build();
		} else {
			Map<String, String> errorMsg = new HashMap<>();
			errorMsg.put("ERROR", "Inavlid Package Code");
			
			String errorString = gson.toJson(errorMsg);
			return Response
					.status(400)
					.entity(errorString)
					.build();
		}
	}
	
	
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON) // request data type
	@Produces(MediaType.APPLICATION_JSON)
	public Response addpayment(String jsonData) {
	
		Gson gson = new Gson();
		Payment user = gson.fromJson(jsonData, Payment.class); // converting json string to java class.
		
		PaymentDao paymentDao = new PaymentDao();
		int res = paymentDao.addPayment(user);
		
		if(res > 0) {
			Map<String, String> msg = new HashMap<>();
			msg.put("SUCCESS", "Payment Received Successfully!");
			String jsonString = gson.toJson(msg);
			
			return Response
					.status(200)
					.entity(jsonString)
					.header("Access-Control-Allow-Origin", "*")
					.build();
		} else {
			Map<String, String> msg = new HashMap<>();
			msg.put("ERROR", "Please Enter Valid Information?");
			String jsonString = gson.toJson(msg);
			
			return Response
					.status(400)
					.entity(jsonString)
					.build();
		}
	}
}