package resources;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
public class base {
public  WebDriver driver;
public Properties prop;
public WebDriver initializeDriver() throws IOException
{

prop= new Properties();
FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties");

prop.load(fis);
String browserName=prop.getProperty("browser");
System.out.println(browserName);

if(browserName.equals("chrome"))
{
 System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Driver\\chromedriver.exe");
driver= new ChromeDriver();
	

}
else if (browserName.equals("firefox"))
{
	System.setProperty("webdriver.gecko.driver", "");
 driver= new FirefoxDriver();

}
else if (browserName.equals("IE"))
{
	System.setProperty("webdriver.ie.driver", "");
	driver= new InternetExplorerDriver();
}

driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
driver.manage().window().maximize();
return driver;


}


public static JSONArray ReadVariant() throws IOException, ParseException
{

		JSONParser jsonParser = new JSONParser();
		FileReader reader = new FileReader(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.json");
		//Read JSON file
		Object obj = jsonParser.parse(reader);
		
		JSONArray itemList = (JSONArray) obj;

		return itemList;
		
			
	}  	
	   


}

