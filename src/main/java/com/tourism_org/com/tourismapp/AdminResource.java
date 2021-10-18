package com.tourism_org.com.tourismapp;

import java.util.List;

//import javax.print.attribute.standard.Media;

import com.google.gson.Gson;
import com.tourism_org.com.tourismapp.dao.AdminDao;
import com.tourism_org.com.tourismapp.model.admin;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
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
		
}
