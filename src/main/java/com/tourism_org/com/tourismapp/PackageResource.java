package com.tourism_org.com.tourismapp;
import com.tourism_org.com.tourismapp.dao.AdminDao;
import com.tourism_org.com.tourismapp.dao.PackageDao;
import com.tourism_org.com.tourismapp.model.Package;
import com.tourism_org.com.tourismapp.model.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
				.build();
	}
	
	@Path("/{package_id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAnItem(@PathParam("package_id") int id) {
		PackageDao packageDao = new PackageDao();
		Package package1 = packageDao.getAPackage(id);
		if(package1 != null) {
			String jsonString = gson.toJson(package1);
			return Response
					.status(200)
					.entity(jsonString)
					.build();
		} else {
			Map<String, String> errorMsg = new HashMap<>();
			errorMsg.put("ERROR", "Inavlid Package id");
			
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
	public Response addItem(String jsonData) {
	
		Gson gson = new Gson();
		Package user = gson.fromJson(jsonData, Package.class); // converting json string to java class.
		
		PackageDao packageDao = new PackageDao();
		int res = packageDao.addPackage(user);
		
		if(res > 0) {
			Map<String, String> msg = new HashMap<>();
			msg.put("SUCCESS", "Package Has Been Added Successfully");
			String jsonString = gson.toJson(msg);
			
			return Response
					.status(200)
					.entity(jsonString)
					.build();
		} else {
			Map<String, String> msg = new HashMap<>();
			msg.put("ERROR", "Please Enter Valid Information");
			String jsonString = gson.toJson(msg);
			
			return Response
					.status(400)
					.entity(jsonString)
					.build();
		}
	}
	
//	@Path ("/search")
//	@GET
//	@Consumes (MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response Search(String data) {
//		Gson gson = new Gson();
//		Package enter_search =gson.fromJson(data, Package.class);
//		
//		PackageDao packageDao = new PackageDao();
//		Package PackageDao =packageDao.search(enter_search.getCountry(), enter_search.getNumber_of_nights(), enter_search.getWeekly_schedule());
//		
//		if (PackageDao != null) {
//			Map<String, String> msg = new HashMap<>();
//			
//			msg.put("Success", "Your Search results!");
//			String jsonString = gson.toJson(msg);
//		
//			return Response  
//					.status(200)
//					.entity(jsonString)
//					.build();
//		
//		} else {
//			Map<String, String> msg = new HashMap<>();
//			msg.put("Error"," Sorry. No results were found!");
//			String jsonString = gson.toJson(msg);
//			return Response  
//					.status(401)
//					.entity(jsonString)
//					.build(); 
//		}
//	}


//@Path("/{country}")
//@GET
//@Produces(MediaType.APPLICATION_JSON)
//public Response getSearch(@PathParam("country") String country,
//		@PathParam("number_of_nights") String number_of_nights,
//		@PathParam("weekly_schedule") String weekly_schedule) {
//	
//	PackageDao packageDao = new PackageDao();
//	Package package1 = packageDao.getSearch(country,number_of_nights,weekly_schedule);
//	if(package1 != null) {
//		String jsonString = gson.toJson(package1);
//		return Response
//				.status(200)
//				.entity(jsonString)
//				.build();
//	} else {
//		Map<String, String> errorMsg = new HashMap<>();
//		errorMsg.put("ERROR", "Invalid Package id");
//		
//		String errorString = gson.toJson(errorMsg);
//		return Response
//				.status(400)
//				.entity(errorString)
//				.build();
//	}
//}
}