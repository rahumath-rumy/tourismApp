package com.tourism_org.com.tourismapp.config;

import java.lang.System.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;

import com.mysql.jdbc.Driver;

public class DbConnection {

	private static Logger logger = (Logger) LogManager.getLogger();
	private static final String DB_URL="jdbc:mysql://localhost:3306/tourismapp";
	private static final String DB_USER="root";
	private static final String DB_PASSWORD ="12345";

private static DbConnection newInstance = null;

private DbConnection() {

}

public static DbConnection getInstance() {
	if (newInstance == null) {
		newInstance = new DbConnection();
	}
	return newInstance;
}

public Connection getConnection() {
  	try {
  		Class.forName ("com.mysql.cj.jdbc.driver");
  		Connection conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
  		//((org.apache.logging.log4j.Logger) logger).info( "DB Success : Connection succesful- ");
  				
  			
  				//Driver driver =new Driver();
  				//DriverManager.registerDriver(driver);
  				//Connection conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
  				//((org.apache.logging.log4j.Logger) logger).info("DB Sucess : Connection succesful");
  		return conn;
  		
  	} catch (SQLException | ClassNotFoundException e ){
  		//	} catch (SQLException e ){
  				e.printStackTrace();
  				((org.apache.logging.log4j.Logger) logger).error(e.hashCode()+ "DB ERROR : Connection Failed -" +e.getMessage());
  				return null;
  			}
  		}
  	}

