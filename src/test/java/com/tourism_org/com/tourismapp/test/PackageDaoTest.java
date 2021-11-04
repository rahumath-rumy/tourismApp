package com.tourism_org.com.tourismapp.test;

import com.tourism_org.com.tourismapp.dao.PackageDao;
import com.tourism_org.com.tourismapp.model.Package;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;

public class PackageDaoTest {

	
	private int expected = 7;
	@Test
	public void GetPackages() {
		PackageDao packageDao = new PackageDao();
		List<Package> packages = packageDao.getPackageFromDb();
		int actual = packages.size();
		
		assertEquals(expected, actual);
	}
	
	// PUT Annotation Test when you need to add a Package
	
	@Test
	public void AddPackage() {
		
		int expected = 1;
		
		Package package1 = new Package();
		//package1.setPackage_id("401");
		package1.setPackage_name("QAS");
		package1.setCountry("Sri Lanka");
		package1.setCountry_area("Colombo");
		package1.setNumber_of_nights("2");
		package1.setWeekly_schedule("1");
		package1.setDate("2021-11-01");
		package1.setHotels("Shios Hotel");
		package1.setActivites("ASCV");
		package1.setPrice_per_person("5000");
		

		PackageDao packageDao = new PackageDao();
		int actual = packageDao.addPackage(package1);
				
		assertEquals(expected, actual);
	
	}
	
}

