package com.tourism_org.com.tourismapp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.tourism_org.com.tourismapp.dao.ReservePackageDao;
import com.tourism_org.com.tourismapp.model.ReservePackage;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("reserve_package")
public class ReservePackageResource {
	
		private Gson gson = new Gson();
		
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public Response getReservePackages() {
			ReservePackageDao reservePackageDao = new ReservePackageDao();
			List<ReservePackage> packageList = reservePackageDao.getAll();
			
			String jsonString = gson.toJson(packageList);
			
			return Response
					.status(200)
					.entity(jsonString)
					.header("Access-Control-Allow-Origin", "*")
					.build();
		}
		
		@Path("{reserve_id}")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public Response getAnItem(@PathParam("reserve_id") int reserve_id) {
			ReservePackageDao reservePackageDao = new ReservePackageDao();
			ReservePackage reservePackage = reservePackageDao.getAReservePackage(reserve_id);
			if(reservePackage != null) {
				String jsonString = gson.toJson(reservePackage);
				return Response
						.status(200)
						.entity(jsonString)
						.header("Access-Control-Allow-Origin", "*")
						.build();
			} else {
				Map<String, String> errorMsg = new HashMap<>();
				errorMsg.put("ERROR", "Inavlid Reserve Package");
				
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
		public Response addReservePackage(String jsonData) {
		
			Gson gson = new Gson();
			ReservePackage customer = gson.fromJson(jsonData, ReservePackage.class); // converting json string to java class.
			
			ReservePackageDao reservePackageDao = new ReservePackageDao();
			int res = reservePackageDao.addReservePackage(customer);
			
			if(res > 0) {
				Map<String, String> msg = new HashMap<>();
				msg.put("SUCCESS", "You Have Reserved The Package");
				String jsonString = gson.toJson(msg);
				
				return Response
						.status(200)
						.entity(jsonString)
						.header("Access-Control-Allow-Origin", "*")
						.build();
			} else {
				Map<String, String> msg = new HashMap<>();
				msg.put("WARNNING", "Reservation Package Invalid!");
				String jsonString = gson.toJson(msg);
				
				return Response
						.status(400)
						.entity(jsonString)
						.build();
			}
		}
		
		

		
		
		@DELETE
		@Path("/{reserve_id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response deleteReservePackage (@PathParam("reserve_id") int reserve_id) {

			ReservePackageDao reservePackageDao = new ReservePackageDao();
			reservePackageDao.getAReservePackage(reserve_id);	
			
			if (reservePackageDao.getAReservePackage(reserve_id) != null) {
				
				reservePackageDao.deleteReservePackage(reserve_id);
				Map<String, String> msg = new HashMap<>();
				
				msg.put("Success", "Reservation Package Cancelled");
				String jsonString = gson.toJson(msg);
			
				return Response  
						.status(200)
						.entity(jsonString)
						.header("Access-Control-Allow-Origin", "*")
						.build();
			
			} else {
				Map<String, String> msg = new HashMap<>();
				msg.put("Error"," YOU HAVE ALREADY CANCELLED RESERVATION!!!");
				String jsonString = gson.toJson(msg);
				return Response  
						.status(401)
						.entity(jsonString)
						.build(); 
			}
		}
		

}