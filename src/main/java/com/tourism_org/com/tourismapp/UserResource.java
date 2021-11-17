    package com.tourism_org.com.tourismapp;
	
	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;
	import com.google.gson.Gson;
	import com.tourism_org.com.tourismapp.dao.UserDao;
	import com.tourism_org.com.tourismapp.model.User;

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


	@Path("customer")
	//annotate produces and consumers here. and elete from the rest
	public class UserResource {
		
		private Gson gson = new Gson();
		
		/**
		 * get all the user record from the Db
		 * @return
		 */
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public Response getUsersFromDb()  {
			UserDao userDao = new UserDao();
			List<User> userList = UserDao.getUserFromDB();
			
			String jsonString = gson.toJson(userList);
			
			return Response
					.status(200)
					.entity(jsonString)
					.header("Access-Control-Allow-Origin", "*")
					.build();
		}
		
		/**
		 * get user record from Id
		 * @param id
		 * @return
		 */
			
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
						.header("Access-Control-Allow-Origin", "*")
						.build();
			}
		}
		
		/**
		 * insert user into the Db
		 * @param fname
		 * @param lname
		 * @param phone
		 * @param email
		 * @param address
		 * @param srilankan
		 * @param country
		 * @param nationality
		 * @param passport
		 * @param password
		 * @return
		 */
		
		@POST // to insert data we use post
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces (MediaType.APPLICATION_JSON)
		public Response addUser(@FormParam("Fname") String fname,
								@FormParam("Lname") String lname,
								@FormParam("Phone") int phone,
								@FormParam("Email") String email,
								@FormParam("Address") String address,
								@FormParam("Srilankan") boolean srilankan,
								@FormParam("Country") String country,
								@FormParam("Nationality") String nationality,
								@FormParam("PassportOrNIC") String passport,
								@FormParam ("password") String password) {
			
		
			Gson gson = new Gson();
			
			User user = new User();
			user.setFname(fname);
			user.setLname(lname);
			user.setPhone(phone);
			user.setEmail(email);
			user.setAddress(address);
			user.setSrilankan(srilankan);
			user.setCountry(country);
			user.setNationality(nationality);
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
						.header("Access-Control-Allow-Origin", "*")
						.build();
			
			} else {
				Msg.put("Error","Incorrect details have been entered. Please check the details and try again!");
				String jsonString = gson.toJson(Msg);
				return Response  
						.status(401)
						.entity(jsonString)
						.build(); 
			}
		}
		
		/**
		 * user logins to their account
		 * @param data
		 * @return
		 */
		
		
		@Path ("/userlogin")
		@GET
		@Consumes (MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response login(String data) {
			Gson gson = new Gson();
			User enter_user = gson.fromJson(data, User.class);
			
			UserDao userDao = new UserDao();
			User user = userDao.userAuth(enter_user .getEmail(), enter_user.getPassword());
			
			if (user != null) {
				Map  <String, String> msg = new HashMap<>();
				
				msg.put("Success", "You have logged in!");
				String jsonString = gson.toJson(msg);
			
				return Response  
						.status(200)
						.entity(jsonString)
						.header("Access-Control-Allow-Origin", "*")
						.build();
			
			} else {
				Map<String, String> msg = new HashMap<>();
				msg.put("Error"," Invalid login. Please try again!");
				String jsonString = gson.toJson(msg);
				return Response  
						.status(401)
						.entity(jsonString)
						.build(); 
			}
		}
		

		
		/**
		 * user forgets password
		 * @param email
		 * @return
		 */
	
		@Path ("/forgotpassword")
		@POST
		@Consumes (MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response forgotpassword(String email) {
			Gson gson = new Gson();
			User enter_email = gson.fromJson(email, User.class);
			
			UserDao userDao = new UserDao();
			User user = userDao.forgotpassword(enter_email.getEmail());
			
			if (user != null) {
				Map  <String, String> msg = new HashMap<>();
				
				msg.put("Success", "An email has been sent! Kindly click on the link sent to your email to change your password.");
				String jsonString = gson.toJson(msg);
			
				return Response  
						.status(200)
						.entity(jsonString)
						.header("Access-Control-Allow-Origin", "*")
						.build();
			
			} else {
				Map<String, String> msg = new HashMap<>();
				msg.put("Error"," Incorrect Email Address! Please try again!");
				String jsonString = gson.toJson(msg);
				return Response  
						.status(401)
						.entity(jsonString)
						.build(); 
			}
		}
		
		/**
		 * update customer details
		 * @param jsonData
		 * @return
		 */
		@PUT
		@Consumes(MediaType.APPLICATION_JSON) 
		@Produces(MediaType.APPLICATION_JSON)
		
		public Response updateUser(String jsonData) {
		
		Gson gson = new Gson();
		User User = gson.fromJson(jsonData, User.class);
		
		UserDao userDao = new UserDao();
		int user = userDao.UpdateUser(User);
			
			
			if(user > 0) {
				Map<String, String> msg = new HashMap<>();
				msg.put("SUCCESS", " Your Record has been updated successfully!");
				String jsonString = gson.toJson(msg);
				
				return Response
						.status(200)
						.entity(jsonString)
						.header("Access-Control-Allow-Origin", "*")
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
		
		/**
		 * delete customer
		 * @param id
		 * @return
		 */
		
		@DELETE
		@Path("/{Id}")
		public Response deluser (@PathParam("Id") int id) {

			UserDao userDao = new UserDao();
			User user = userDao.getaUser(id);	
			
			if (userDao.getaUser(id) != null) {
				
				userDao.deluser(id);
				Map<String, String> msg = new HashMap<>();
				
				msg.put("Success", "Your account has been deleted permanently");
				String jsonString = gson.toJson(msg);
			
				return Response  
						.status(200)
						.entity(jsonString)
						.header("Access-Control-Allow-Origin", "*")
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
