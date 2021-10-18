package com.tourism_org.com.tourismapp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.Media;

import com.google.gson.Gson;
import com.tourism_org.com.tourismapp.dao.AdminDao;
import com.tourism_org.com.tourismapp.model.admin;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("admin")
public class AdminResource {
	
	private Gson gson = new Gson();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAdminFromDb() {
		AdminDao adminDao = new AdminDao();
		List<admin> adminList = adminDao.getAdminFromDb();
		
		String jsonString = gson.toJson(adminList);
		
		return Response
				.status(200)
				.entity(jsonString)
				.build();
	}
		
	@Path("{admin_id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAnItem(@PathParam("admin_id") int admin_id) {
		AdminDao adminDao = new AdminDao();
		admin Admin = adminDao.getaAdmin(admin_id);
		
		if(Admin != null) {
			String jsonString = gson.toJson(Admin);
			return Response
					.status(200)
					.entity(jsonString)
					.build();
		} else {
			Map<String, String> errorMsg = new HashMap<>();
			errorMsg.put("ERROR", "Invalid admin id");
			
			String errorString = gson.toJson(errorMsg);
			return Response
					.status(400)
					.entity(errorString)
					.build();
		}
	}
	
	@POST // to insert we use post
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces (MediaType.APPLICATION_JSON)
	public Response addAdmin(@FormParam("fname") String fname,
							@FormParam("lname") String lname,
							@FormParam("email") String email,
							@FormParam("mobile") int mobile,
							@FormParam ("password") String password) {
		
		//declare gson
		Gson gson = new Gson();
		
		admin admin = new admin();
		admin.setFname(fname);
		admin.setLname(lname);
		admin.setEmail(email);
		admin.setMobile(mobile);
		admin.setPassword(password);
		
		
		AdminDao adminDao = new AdminDao();
		int Res = adminDao.addAdmin(admin);
		
		Map<String, String> Msg = new HashMap<>();
		
		if (Res > 0) {
			Msg.put("Success", "user record added sucesfully");
			String jsonString = gson.toJson(Msg);
			
			//if u want to redirect to a different page
			return Response  
					.status(200)
					.entity(jsonString)
					.build();
		
		} else {
			Msg.put("Error"," add valid info");
			String jsonString = gson.toJson(Msg);
			return Response  
					.status(401)
					.entity(jsonString)
					.build(); 
		}
	}
}
