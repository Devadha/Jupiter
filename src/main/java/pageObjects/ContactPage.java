package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ContactPage {

	public WebDriver driver;

	By Contact = By.xpath("//div[@class='nav-collapse']/ul/li[3]");
	By Submit = By.xpath("//a[text()='Submit']");
	By Text = By.xpath("//div[@class='alert alert-error ng-scope']");
	By Forename = By.xpath("//input[@id='forename']");
	By Email = By.xpath("//input[@id='email']");
	By Message = By.xpath("//textarea[@id='message']");
	By errorvalidate=By.xpath("//div[@class='alert alert-error ng-scope']");
	By validate=By.xpath("//div[@class='alert alert-info ng-scope']");

	public ContactPage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickContact() {
		driver.findElement(Contact).click();

	}

	public void clicksubmit() {
		driver.findElement(Submit).click();

	}

	public WebElement forename() {
		return driver.findElement(Forename);

	}

	public WebElement email() {
		return driver.findElement(Email);

	}

	public WebElement message() {
		return driver.findElement(Message);

	}
	public WebElement errorvalidate() {
		return driver.findElement(errorvalidate);
	
	}
	public WebElement validate() {
		return driver.findElement(validate);
	
	}
}
