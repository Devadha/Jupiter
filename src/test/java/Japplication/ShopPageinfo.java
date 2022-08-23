package Japplication;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.shopPage;
import resources.base;

public class ShopPageinfo extends base{

	public WebDriver driver;
	public shopPage sp;
	public static Logger log = LogManager.getLogger(base.class.getName());

	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
		driver.get(prop.getProperty("url"));
	}
	@Test
	public void basePageNavigat() throws IOException, ParseException {
		shopPage sp = new shopPage(driver);
		sp.clickshop();	
		log.info("Clicked shop");
		System.out.println("Clicked shop");
		sp.addItems();
		log.info("Items added to the cart");
		System.out.println("Items added to the cart");
		sp.cart();
		log.info("Cart page");
		System.out.println("Cart page");
		sp.subtotal();
		log.info("Verified Subtotal for each product");
		System.out.println("Verified Subtotal for each product");
		sp.total();
		log.info("verified total =sum(sub totals)");
		System.out.println("verified total =sum(sub totals)");
	}
	@AfterTest
	public void teardown() {
		driver.quit();
	}
	
}
