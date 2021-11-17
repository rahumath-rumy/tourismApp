package com.tourism_org.com.tourismapp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import com.tourism_org.com.tourismapp.dao.PackageDao;
import com.tourism_org.com.tourismapp.model.Package;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("package")
public class PackageResource {

	private Gson gson = new Gson();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPackages() {
		PackageDao packageDao = new PackageDao();
		List<Package> packageList = packageDao.getAll();
		
		String jsonString = gson.toJson(packageList);
		
		return Response
				.status(200)
				.entity(jsonString)
				.header("Access-Control-Allow-Origin", "*")
				.build();
	}
	
	@Path("{package_code}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAnItem(@PathParam("package_code") int package_code) {
		PackageDao packageDao = new PackageDao();
		Package package1 = packageDao.getAPackage(package_code);
		if(package1 != null) {
			String jsonString = gson.toJson(package1);
			return Response
					.status(200)
					.entity(jsonString)
					.header("Access-Control-Allow-Origin", "*")
					.build();
		} else {
			Map<String, String> errorMsg = new HashMap<>();
			errorMsg.put("ERROR", "Invalid Package Code");
			
			String errorString = gson.toJson(errorMsg);
			return Response
					.status(400)
					.entity(errorString)
					.build();
		}
	}
	
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.APPLICATION_JSON)
	public Response addItem(String jsonData) {
	
		Gson gson = new Gson();
		Package user = gson.fromJson(jsonData, Package.class); 
		
		PackageDao packageDao = new PackageDao();
		int res = packageDao.addPackage(user);
		
		if(res > 0) {
			Map<String, String> msg = new HashMap<>();
			msg.put("SUCCESS", "New Package Record Has Been Added Successfully!");
			String jsonString = gson.toJson(msg);
			
			return Response
					.status(200)
					.entity(jsonString)
					.header("Access-Control-Allow-Origin", "*")
					.build();
		} else {
			Map<String, String> msg = new HashMap<>();
			msg.put("WARNNING", "Package is already added!");
			String jsonString = gson.toJson(msg);
			
			return Response
					.status(400)
					.entity(jsonString)
					.build();
		}
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON) // request data type
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response updatePackage(String jsonData) {
	
	Gson gson = new Gson();
	Package package1 = gson.fromJson(jsonData, Package.class);
	
		PackageDao packageDao = new PackageDao();
		int Pack = packageDao.UpdatePackage(package1);
		
		if(Pack > 0) {
			Map<String, String> msg = new HashMap<>();
			msg.put("SUCCESS", " Package Record Has Been Updated Successfully!");
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
	
	
	@DELETE
	@Path("/{package_code}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePackage (@PathParam("package_code") int package_code) {

		PackageDao packageDao = new PackageDao();
		packageDao.getAPackage(package_code);	
		
		if (packageDao.getAPackage(package_code) != null) {
			
			packageDao.deletePackage(package_code);
			Map<String, String> msg = new HashMap<>();
			
			msg.put("Success", "Package has been deleted permanently");
			String jsonString = gson.toJson(msg);
		
			return Response  
					.status(200)
					.entity(jsonString)
					.header("Access-Control-Allow-Origin", "*")
					.build();
		
		} else {
			Map<String, String> msg = new HashMap<>();
			msg.put("Error"," Package is already deleted..!");
			String jsonString = gson.toJson(msg);
			return Response  
					.status(401)
					.entity(jsonString)
					.build(); 
		}
	}
	
//	@Path ("/search")
//	@GET
//	@Consumes(MediaType.APPLICATION_JSON) // request data type
//	@Produces(MediaType.APPLICATION_JSON)
//	
//	public Response SearchPackage(String jsonData) {
//	
//	Gson gson = new Gson();
//	Package package1 = gson.fromJson(jsonData, Package.class);
//	
////	PackageDao packageDao = new PackageDao();
////	Package package1 = packageDao.searchPackage(country, number_of_nights);
//	
//	
//		PackageDao packageDao = new PackageDao();
//		Package package1 = packageDao.searchPackage(package1);
//		int Pack = packageDao.SearchPackage(package1);
//		
////		if(Pack > 0) {
////			Map<String, String> msg = new HashMap<>();
////			msg.put("SUCCESS", " Package Record Has Been Updated Successfully!");
////			String jsonString = gson.toJson(msg);
//		
//		if(package1 != null) {
//			String jsonString = gson.toJson(package1);
//			return Response
//					.status(200)
//					.entity(jsonString)
//					.build();
////			return Response
////					.status(200)
////					.entity(jsonString)
////					.build();
//		} else {
//			Map<String, String> msg = new HashMap<>();
//			msg.put("ERROR", "Please Enter Valid Information?");
//			String jsonString = gson.toJson(msg);
//			
//			return Response
//					.status(400)
//					.entity(jsonString)
//					.build();
//		}
//
//	}
//	
//	
//	
	
//	@Path("{country}/{number_of_nights}")
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response searchpackage(@PathParam("country") String country,
//			@PathParam("number_of_nights") String number_of_nights) {
//		PackageDao packageDao = new PackageDao();
//		Package package1 = packageDao.searchPackage(country, number_of_nights);
//		if(package1 != null) {
//			String jsonString = gson.toJson(package1);
//			return Response
//					.status(200)
//					.entity(jsonString)
//					.build();
//		} else {
//			Map<String, String> errorMsg = new HashMap<>();
//			errorMsg.put("ERROR", "No search results found");
//			
//			String errorString = gson.toJson(errorMsg);
//			return Response
//					.status(400)
//					.entity(errorString)
//					.build();
//		}
//	}
	

}