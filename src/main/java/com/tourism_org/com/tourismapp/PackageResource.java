package com.tourism_org.com.tourismapp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import com.tourism_org.com.tourismapp.dao.PackageDao;
import com.tourism_org.com.tourismapp.dao.UserDao;
import com.tourism_org.com.tourismapp.model.Package;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
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
	
	@Path("/{package_code}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAPackage(@PathParam("package_code") int package_code) {
		PackageDao packageDao = new PackageDao();
		Package package1 = packageDao.getAPackage(package_code);
		
		if(package1 != null) {
			String jsonString = gson.toJson(package1);
			return Response
					.status(200)
					.entity(jsonString)
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
	@Consumes(MediaType.APPLICATION_JSON) // request data type
	@Produces(MediaType.APPLICATION_JSON)
	public Response addItem(String jsonData) {
	
		Gson gson = new Gson();
		Package user = gson.fromJson(jsonData, Package.class); // converting json string to java class.
		
		PackageDao packageDao = new PackageDao();
		int res = packageDao.addPackage(user);
		
		if(res > 0) {
			Map<String, String> msg = new HashMap<>();
			msg.put("SUCCESS", "New Package Record Has Been Added Successfully!");
			String jsonString = gson.toJson(msg);
			
			return Response
					.status(200)
					.entity(jsonString)
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
					.build();
		
		} else {
			Map<String, String> msg = new HashMap<>();
			msg.put("Error"," Could not delete package");
			String jsonString = gson.toJson(msg);
			return Response  
					.status(401)
					.entity(jsonString)
					.build(); 
		}
}

	

}
