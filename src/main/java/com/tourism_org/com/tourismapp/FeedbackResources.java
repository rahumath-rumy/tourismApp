package com.tourism_org.com.tourismapp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.tourism_org.com.tourismapp.dao.FeedbackDao;
import com.tourism_org.com.tourismapp.model.Feedback;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("feedback")
public class FeedbackResources {

	private Gson gson = new Gson();
		
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public Response getfeedback() {
			FeedbackDao feedbackDao = new FeedbackDao();
			List<Feedback> feedbackList = feedbackDao.getAll();
			
			String jsonString = gson.toJson(feedbackList);
			
			return Response
					.status(200)
					.entity(jsonString)
					.header("Access-Control-Allow-Origin", "*")
					.build();
		}
		

		@POST
		@Consumes(MediaType.APPLICATION_JSON) // request data type
		@Produces(MediaType.APPLICATION_JSON)
		public Response addfeedback(String jsonData) {
		
			Gson gson = new Gson();
			Feedback user = gson.fromJson(jsonData, Feedback.class); // converting json string to java class.
			
			FeedbackDao feedbackDao = new FeedbackDao();
			int res = feedbackDao.addFeedback(user);
			
			if(res > 0) {
				Map<String, String> msg = new HashMap<>();
				msg.put("SUCCESS", "Feedback Delivered Successfully!");
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
