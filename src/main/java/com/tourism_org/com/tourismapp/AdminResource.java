package com.tourism_org.com.tourismapp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.Media;

import com.google.gson.Gson;
import com.tourism_org.com.tourismapp.dao.AdminDao;
import com.tourism_org.com.tourismapp.model.admin;

import jakarta.ws.rs.GET;
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
			errorMsg.put("ERROR", "Inavlid admin id");
			
			String errorString = gson.toJson(errorMsg);
			return Response
					.status(400)
					.entity(errorString)
					.build();
		}
	}
}
