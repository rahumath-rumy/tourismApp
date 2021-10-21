    package com.tourism_org.com.tourismapp;
	import java.sql.SQLException;
	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;
	import com.google.gson.Gson;
	import com.tourism_org.com.tourismapp.dao.UserDao;
	import com.tourism_org.com.tourismapp.model.User;
	
	import jakarta.ws.rs.Consumes;
	import jakarta.ws.rs.FormParam;
	import jakarta.ws.rs.GET;
	import jakarta.ws.rs.POST;
	import jakarta.ws.rs.Path;
	import jakarta.ws.rs.PathParam;
	import jakarta.ws.rs.Produces;
	import jakarta.ws.rs.core.MediaType;
	import jakarta.ws.rs.core.Response;


	@Path("customer")
	public class UserResource {
		
		
		private Gson gson = new Gson();
		
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public Response getUsersFromDb()  {
			UserDao userDao = new UserDao();
			List<User> userList = UserDao.getUserFromDB();
			
			String jsonString = gson.toJson(userList);
			
			return Response
					.status(200)
					.entity(jsonString)
					.build();
		}
			
		@Path("{Id}")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public Response getaUser(@PathParam("Id") int id) {
			UserDao userDao = new UserDao();
			User user = userDao.getaUser(id);
			
			if(user != null) {
				String jsonString = gson.toJson(user);
				return Response
						.status(200)
						.entity(jsonString)
						.build();
				
			} else {
				Map<String, String> errorMsg = new HashMap<>();
				errorMsg.put("ERROR", "Invalid Customer ID");
				
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
		public Response addUser(@FormParam("Fname") String fname,
								@FormParam("Lname") String lname,
								@FormParam("Phone") int phone,
								@FormParam("Address") String address,
								@FormParam("City") String city,
								@FormParam("State") String state,
								@FormParam("PostalCode") String postalcode,
								@FormParam("Country") String country,
								@FormParam("Email") String email,
								@FormParam("PassportNumber") String passport,
								@FormParam ("password") String password) {
			
			//declare gson
			Gson gson = new Gson();
			
			User user = new User();
			user.setFname(fname);
			user.setLname(lname);
			user.setPhone(phone);
			user.setAddress(address);
			user.setCity(city);
			user.setState(state);
			user.setPostalcode(postalcode);
			user.setCountry(country);
			user.setEmail(email);
			user.setPassport(passport);
			user.setPassword(password);
			
			
			UserDao userDao = new UserDao();
			int Res = userDao.addUser(user);
			
			Map<String, String> Msg = new HashMap<>();
			
			if (Res > 0) {
				Msg.put("Success", "Customer record has been added successfully");
				String jsonString = gson.toJson(Msg);
				
				//if u want to redirect to a different page
				return Response  
						.status(200)
						.entity(jsonString)
						.build();
			
			} else {
				Msg.put("Error","please add valid info");
				String jsonString = gson.toJson(Msg);
				return Response  
						.status(401)
						.entity(jsonString)
						.build(); 
			}
		}
		
//		public int updateAdmin(admin Admin) {
//			return 1;
//		}
//		
//		public int deleteUser(admin Admin) {
//			try {
//				if (user != null) {
//					userList.remove(user);
//					return 1;
//				} else {
//					return 0;
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//				return -1;
//			}
//		}
//		
//	}

}