package com.tourism_org.com.tourismapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import javax.print.attribute.standard.Media;
import com.google.gson.Gson;
import com.tourism_org.com.tourismapp.config.DbConnection;
import com.tourism_org.com.tourismapp.dao.AdminDao;
import com.tourism_org.com.tourismapp.dao.PackageDao;
import com.tourism_org.com.tourismapp.dao.UserDao;
import com.tourism_org.com.tourismapp.model.Package;
import com.tourism_org.com.tourismapp.model.User;
import com.tourism_org.com.tourismapp.model.admin;

import jakarta.validation.ReportAsSingleViolation;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
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
	
	@POST // to insert data we use post
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces (MediaType.APPLICATION_JSON)
	public Response addAdmin(@FormParam("admin_id") int admin_id,
							@FormParam("admin_fname") String fname,
							@FormParam("admin_lname") String lname,
							@FormParam("email") String email,
							@FormParam("mobile") int mobile,
							@FormParam("address") String address,
							@FormParam("admin_control") boolean admin_control,
							@FormParam ("admin_password") String password) {
		
		//declare gson
		Gson gson = new Gson();
		
		admin admin = new admin();
		admin.setAdmin_id(admin_id);
		admin.setFname(fname);
		admin.setLname(lname);
		admin.setEmail(email);
		admin.setMobile(mobile);
		admin.setAddress(address);
		admin.setAdmin_control(admin_control);
		admin.setPassword(password);
		
		
		AdminDao adminDao = new AdminDao();
		int Res = adminDao.addAdmin(admin);
		
		Map<String, String> Msg = new HashMap<>();
		
		if (Res > 0) {
			Msg.put("Success", "Staff record added sucesfully");
			String jsonString = gson.toJson(Msg);
			
			//if u want to redirect to a different page
			return Response  
					.status(200)
					.entity(jsonString)
					.build();
		
		} else {
			Msg.put("Error"," Please add valid info");
			String jsonString = gson.toJson(Msg);
			return Response  
					.status(401)
					.entity(jsonString)
					.build(); 
		}
	}
	
	@Path ("/login")
	@POST
	@Consumes (MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(String data) {
		Gson gson = new Gson();
		admin enter_admin =gson.fromJson(data, admin.class);
		
		AdminDao adminDao = new AdminDao();
		admin admin = adminDao.adminAuth(enter_admin.getEmail(), enter_admin.getPassword());
		
		if (admin != null) {
			Map<String, String> msg = new HashMap<>();
			
			msg.put("Success", "You have logged in!");
			String jsonString = gson.toJson(msg);
		
			return Response  
					.status(200)
					.entity(jsonString)
					.build();
		
		} else {
			Map<String, String> msg = new HashMap<>();
			msg.put("Error"," Invalid Admin Login. Please try again!");
			String jsonString = gson.toJson(msg);
			return Response  
					.status(401)
					.entity(jsonString)
					.build(); 
		}
	}
	
	@Path ("/forgotpassword")
	@POST
	@Consumes (MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response forgotpassword(String email) {
		Gson gson = new Gson();
		admin enter_email = gson.fromJson(email, admin.class);
		
		AdminDao adminDao = new AdminDao();
		admin admin = adminDao.forgotpassword(enter_email.getEmail());
		
		if (admin != null) {
			Map<String, String> msg = new HashMap<>();
			
			msg.put("Success", "A link has been sent to your email! Kindly click on the link to change password.");
			String jsonString = gson.toJson(msg);
		
			return Response  
					.status(200)
					.entity(jsonString)
					.build();
		
		} else {
			Map<String, String> msg = new HashMap<>();
			msg.put("Error"," Invalid email address. Please try again!");
			String jsonString = gson.toJson(msg);
			return Response  
					.status(401)
					.entity(jsonString)
					.build(); 
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON) // request data type
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response updateAdmin(String jsonData) {
	
	Gson gson = new Gson();
	admin admin = gson.fromJson(jsonData, admin.class);
	
	AdminDao adminDao = new AdminDao();
	int Admin = adminDao.UpdateAdmin(admin);
		
		
		if(Admin > 0) {
			Map<String, String> msg = new HashMap<>();
			msg.put("SUCCESS", " Your Record has been updated successfully!");
			String jsonString = gson.toJson(msg);
			
			return Response
					.status(200)
					.entity(jsonString)
					.build();
		} else {
			Map<String, String> msg = new HashMap<>();
			msg.put("ERROR", "I am sorry. Your record has not been updated! ");
			String jsonString = gson.toJson(msg);
			
			return Response
					.status(400)
					.entity(jsonString)
					.build();
		}

	}
	
	
	@DELETE
	@Path("/{admin_id}")
	public Response deladmin (@PathParam("admin_id") int admin_id) {

		AdminDao adminDao = new AdminDao();
		admin admin = adminDao.getaAdmin(admin_id);	
		
		if (adminDao.getaAdmin(admin_id) != null) {
			
			adminDao.deladmin(admin_id);
			Map<String, String> msg = new HashMap<>();
			
			msg.put("Success", "Your account has been deleted permanently");
			String jsonString = gson.toJson(msg);
		
			return Response  
					.status(200)
					.entity(jsonString)
					.build();
		
		} else {
			Map<String, String> msg = new HashMap<>();
			msg.put("Error"," Could not delete account");
			String jsonString = gson.toJson(msg);
			return Response  
					.status(401)
					.entity(jsonString)
					.build(); 
		}
}
}


