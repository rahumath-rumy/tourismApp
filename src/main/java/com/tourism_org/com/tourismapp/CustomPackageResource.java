package com.tourism_org.com.tourismapp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.tourism_org.com.tourismapp.dao.AdminDao;
import com.tourism_org.com.tourismapp.dao.UserDao;
import com.tourism_org.com.tourismapp.dao.custompackage;
import com.tourism_org.com.tourismapp.dao.inquiryDao;
import com.tourism_org.com.tourismapp.model.CustomPackage;
import com.tourism_org.com.tourismapp.model.User;
import com.tourism_org.com.tourismapp.model.admin;
import com.tourism_org.com.tourismapp.model.inquiry;

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

@Path("customize")
public class CustomPackageResource {

	private Gson gson = new Gson();
		
	/**
	 * Get all the records
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCustomPackagesFromDb()  {
		custompackage cpDao = new custompackage();
		List<CustomPackage> cpList = custompackage.getCustomPackageFromDB();
		
		String jsonString = gson.toJson(cpList);
		
		return Response
				.status(200)
				.entity(jsonString)
				.header("Access-Control-Allow-Origin", "*")
				.build();
	}
		
	/**
	 * get all custom package based on id
	 * @param cp_id
	 * @return
	 */
	@Path("/{custompackage_id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getaCustomPackage(@PathParam("custompackage_id") int cp_id) {
		custompackage cpDao = new custompackage();
		CustomPackage customPackages = cpDao.getaCustomPackage(cp_id);
		
		if(customPackages != null) {
			String jsonString = gson.toJson(customPackages);
			return Response
					.status(200)
					.entity(jsonString)
					.header("Access-Control-Allow-Origin", "*")
					.build();
			
		} else {
			Map<String, String> errorMsg = new HashMap<>();
			errorMsg.put("ERROR", "No customized package.");
			
			String errorString = gson.toJson(errorMsg);
			return Response
					.status(400)
					.entity(errorString)
					.build();
		}
	}
	
	/**
	 * insert data to the db
	 * @param country
	 * @param country_location
	 * @param hotel1
	 * @param hotel2
	 * @param activity1
	 * @param activity2
	 * @param number_of_people
	 * @param start_date
	 * @param end_date
	 * @param number_of_nights
	 * @return
	 */

	@POST 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces (MediaType.APPLICATION_JSON)
	public Response addCustomPackage(@FormParam("country") String country,
							@FormParam("country_location") String country_location,
							@FormParam("hotel1") String hotel1,
							@FormParam("hotel2") String hotel2,
							@FormParam("activity1") String activity1,
							@FormParam("activity2") String activity2,
							@FormParam("number_of_people") int number_of_people,
							@FormParam("start_date") String start_date,
							@FormParam("end_date") String end_date,
							@FormParam ("number_of_nights") int number_of_nights) {
		
		Gson gson = new Gson();
		
		CustomPackage customPackages = new CustomPackage();
		customPackages.setCountry(country);
		customPackages.setCountry_location(country_location);
		customPackages.setHotel1(hotel1);
		customPackages.setHotel2(hotel2);
		customPackages.setActivity1(activity1);
		customPackages.setActivity2(activity2);
		customPackages.setNumber_of_people(number_of_people);
		customPackages.setStart_date(start_date);
		customPackages.setEnd_date(end_date);
		customPackages.setNumber_of_nights(number_of_nights);
		
		
		custompackage cpDao = new custompackage();
		int Res = cpDao.addCustomPackage(customPackages);
		
		Map<String, String> Msg = new HashMap<>();
		
		if (Res > 0) {
			Msg.put("Success", "Your customized package has been received. We will get back to you within 2 working days of the approval status!");
			String jsonString = gson.toJson(Msg);
			
			//if u want to redirect to a different page
			return Response  
					.status(200)
					.entity(jsonString)
					.header("Access-Control-Allow-Origin", "*")
					.build();
		
		} else {
			Msg.put("Error","An error has occured. Please try again!");
			String jsonString = gson.toJson(Msg);
			return Response  
					.status(401)
					.entity(jsonString)
					.build(); 
		}
	}
	
	/**
	 * update record
	 * @param jsonData
	 * @return
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response updateCustomPackage(String jsonData) {
	
		Gson gson = new Gson();
		CustomPackage customPackage = gson.fromJson(jsonData, CustomPackage.class);
		
		custompackage cpDao = new custompackage();
		int CPD = cpDao.UpdateCustomPackage(customPackage);
			
			if(CPD > 0) {
				Map<String, String> msg = new HashMap<>();
				msg.put("SUCCESS", " Your customized package has been updated successfully!");
				String jsonString = gson.toJson(msg);
				
				return Response
						.status(200)
						.entity(jsonString)
						.header("Access-Control-Allow-Origin", "*")
						.build();
			} else {
				Map<String, String> msg = new HashMap<>();
				msg.put("ERROR", "Sorry. Your customized package could not be updated! ");
				String jsonString = gson.toJson(msg);
				
				return Response
						.status(400)
						.entity(jsonString)
						.build();
			}

		}
		
	/**
	 * delete record
	 * @param cp_id
	 * @return
	 */
	@DELETE
	@Path("/{custompackage_id}")
	public Response delcustompackage (@PathParam("custompackage_id") int cp_id) {

		custompackage cpDao = new custompackage();
		CustomPackage customPackage = cpDao.getaCustomPackage(cp_id);	
		
		if (cpDao.getaCustomPackage(cp_id) != null) {
			
			cpDao.delCustomPackage(cp_id);
			Map<String, String> msg = new HashMap<>();
			
			msg.put("Success", "Your customised package has been cancelled.");
			String jsonString = gson.toJson(msg);
		
			return Response  
					.status(200)
					.entity(jsonString)
					.header("Access-Control-Allow-Origin", "*")
					.build();
		
		} else {
			Map<String, String> msg = new HashMap<>();
			msg.put("Error"," Could not cancel your customized package!");
			String jsonString = gson.toJson(msg);
			return Response  
					.status(401)
					.entity(jsonString)
					.build(); 
		}
		
	}
	
	/**
	 * 
	 * @param jsonData
	 * @return
	 */
	@Path ("/status")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response statusCustomPackage(String jsonData) {
	
		Gson gson = new Gson();
		CustomPackage customPackage = gson.fromJson(jsonData, CustomPackage.class);
		
		custompackage cpDao = new custompackage();
		int CPD = cpDao.StatusCustomPackage(customPackage);
			
			if(CPD > 0) {
				Map<String, String> msg = new HashMap<>();
				msg.put("SUCCESS", " Status of the package has been updated!");
				String jsonString = gson.toJson(msg);
				
				return Response
						.status(200)
						.entity(jsonString)
						.header("Access-Control-Allow-Origin", "*")
						.build();
			} else {
				Map<String, String> msg = new HashMap<>();
				msg.put("ERROR", "Sorry. Could not update status of the package! ");
				String jsonString = gson.toJson(msg);
				
				return Response
						.status(400)
						.entity(jsonString)
						.build();
			}

		}
		
}

