package com.tourism_org.com.tourismapp.test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.tourism_org.com.tourismapp.dao.custompackage;
import com.tourism_org.com.tourismapp.model.CustomPackage;


public class CustomPackageTest {
	
	//Arrange
	int expected = 6;
	
	@Test
	public void testCustomPackageFromDb() {
		custompackage cpDao = new custompackage();
		List<CustomPackage> cpList = custompackage.getCustomPackageFromDB();
	
		int actual = cpList.size();
		
		assertNotEquals(expected, actual);
	}
	
	
	@Test
	public void addCustomPackage() {
		
		int expected = 1;
		
		CustomPackage customPackage= new CustomPackage();
		
		customPackage.setCountry("Dubai");
		customPackage.setHotel1("LEVA hotel");
		customPackage.setHotel2(" ");
		customPackage.setActivity1("Aquaventure Waterpark");
		customPackage.setActivity2("LegoLand");
		customPackage.setNumber_of_people(3);
		customPackage.setStart_date("2021-12-05");
		customPackage.setEnd_date("2021-12-10");
		customPackage.setNumber_of_nights(4);
		
		custompackage cpDao= new custompackage();
		int actual = cpDao.addCustomPackage(customPackage);
				
		assertEquals(expected, actual);
	
	}
	
	@Test
	public void testdelCustomPackage() {
		
		int cp_id =325;
		
		custompackage cpDao= new custompackage();
		CustomPackage actual = cpDao.delCustomPackage(cp_id);
		
		assertNotNull(actual);
	}
}
