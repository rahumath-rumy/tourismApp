package com.tourism_org.com.tourismapp.test;
//import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.tourism_org.com.tourismapp.dao.PackageDao;
import com.tourism_org.com.tourismapp.model.Package;

public class PackageTest {
	
	//Arrange
	int unexpected = 0;
	
	@Test
	public void testUserDbCall() {
		PackageDao packageDao = new PackageDao();
		List<Package> packages = packageDao.getAll();
		int actual = packages.size();
		
		assertNotEquals(unexpected, actual);
	}
	
	//Arrange
	int packageId = 257;
	
	@Test
	public void testAPackage() {
		PackageDao packageDao = new PackageDao();
		Package package1 = packageDao.getAPackage(packageId);
		
		assertNotNull(package1);
	}
	
	
/**	//Arrange
	Package package1 = new Package("0128", "BBB", "Sri Lanka", "Kandy", "2", "2", "29/10/2021", "Imperial Hotel", "AQS", "4500");
	int expected = 1;
	
	public void testAddPackage() {
		PackageDao packageDao = new PackageDao();
		int actual = packageDao.addPackage(package1);
		
		assertEquals(expected, actual);
	}
	
*/

}
