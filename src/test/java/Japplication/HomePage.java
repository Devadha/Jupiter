package Japplication;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import pageObjects.ContactPage;

import resources.base;

public class HomePage extends base {
	public WebDriver driver;
	public ContactPage cp;
	public static Logger log = LogManager.getLogger(base.class.getName());

	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
		driver.get(prop.getProperty("url"));
	}

	@Test
	public void basePageNavigat() throws IOException {
		ContactPage cp = new ContactPage(driver);
		cp.clickContact();
		log.info("Launched Contact Page");
		System.out.println("Launched Contact Page");
		cp.clicksubmit();
		log.info("Clicked submit");
		System.out.println("Clicked submit");
		String expectedTitle = "We welcome your feedback - but we won't get it unless you complete the form correctly.";
		 String originalTitle=cp.errorvalidate().getText();      
       Assert.assertEquals(originalTitle, expectedTitle);
       log.info("validation passed after clicking submit ");
       System.out.println("validation passed after clicking submit");
	}

	@Test(dataProvider = "getData")
	public void validateErrors(String Forename, String Email, String Message) throws IOException {

		ContactPage cp = new ContactPage(driver);
		cp.forename().sendKeys(Forename);
		cp.email().sendKeys(Email);
		cp.message().sendKeys(Message);
		log.info("Populated mandatory fields");
		System.out.println("Populated mandatory fields");
		String expectedTitle = "We welcome your feedback - tell it how it is.";
		 String originalTitle=cp.validate().getText();      
        Assert.assertEquals(originalTitle, expectedTitle);
        log.info("Test passed after validating errors ");
        System.out.println("Test passed after validating errors");
        
	} 

	@DataProvider
	public Object[][] getData() {
		Object[][] data = new Object[1][3];
		data[0][0] = "name";
		data[0][1] = "name@gmail.com";
		data[0][2] = "User";
		return data;
	}
	@AfterTest
	public void teardown() {
		driver.quit();
	}
}