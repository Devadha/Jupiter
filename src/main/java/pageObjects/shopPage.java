package pageObjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import resources.base;

public class shopPage extends base {
	public static WebDriver driver;
	public ArrayList<String> ItemNameList;
	public ArrayList<Integer> ItemQuantityList;
	public String name;
	Double totalquantity;
	By shop = By.xpath("//div[@class='nav-collapse']/ul/li[2]");
	By price = By.xpath("//*[@class=\"product ng-scope\"]/div/p/span");
	By Cart = By.xpath("//*[contains(text(),'Cart')]");
	By eachprice = By.xpath("//table/tbody/tr/td[2]");

	public shopPage(WebDriver driver) {
		this.driver = driver;
	}

	public void addItems() throws IOException, ParseException {
		JSONArray itemList = ReadVariant();
		ArrayList<Integer> ItemQuantityList = new ArrayList<Integer>();
		ArrayList<String> ItemNameList = new ArrayList<String>();
		for (int i = 0; i < itemList.size(); i++) {
			JSONObject items = (JSONObject) itemList.get(i);
			JSONObject item = (JSONObject) items.get("item");
			String itemName = (String) item.get("name");
			int itemQuantity = ((Long) item.get("quantity")).intValue();
			ItemQuantityList.add(itemQuantity);
			ItemNameList.add(itemName);
		}
		List<WebElement> products = driver.findElements(By.xpath("//h4[@class='product-title ng-binding']"));
		for (int i = 0; i < products.size(); i++) {
			name = products.get(i).getText();
			if (ItemNameList.contains(name)) {
				int index = ItemNameList.indexOf(name);
				for (int j = 0; j < ItemQuantityList.get(index); j++) {
					driver.findElements(By.xpath("//a[@class='btn btn-success']")).get(i).click();
				}
			}
		}
	}

	public void clickshop() {
		driver.findElement(shop).click();

	}

	public void cart() {
		driver.findElement(Cart).click();
		System.out.println("cart clicked");
	}

	public void subtotal() {
		List<WebElement> eachprices = driver.findElements(eachprice);
		for (int k = 0; k < eachprices.size(); k++) {
			String quant = eachprices.get(k).getText();
			quant = quant.substring(1);
			//System.out.println(quant);
			List<WebElement> sizequants = driver.findElements(By.xpath("//input[@name='quantity']"));
			String quants = sizequants.get(k).getAttribute("value");
		//	System.out.println(quants);
			Double totalquantity = Double.parseDouble(quant) * Integer.parseInt(quants);
			System.out.println("Subtotal :"+totalquantity);
		}

	}

	public void total() {
		Double count = 0.0;
		List<WebElement> totals = driver.findElements(By.xpath("//table/tbody/tr/td[4]"));
		for (int k = 0; k < totals.size(); k++) {
			String sum = totals.get(k).getText();
			sum = sum.substring(1);
			count = count + Double.parseDouble(sum);
		}
		System.out.println("Total value :"+count);
	}
}
