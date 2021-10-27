package week4;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal {
	public static void main(String[] args) throws Throwable {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		Actions builder = new Actions(driver);
		// driver.switchTo().alert().dismiss();
		WebElement mensfashion = driver.findElement(By.xpath("(//span[text()=\"Men's Fashion\"])[2]"));
		builder.moveToElement(mensfashion).perform();
		WebElement sportsshoe = driver.findElement(By.xpath("(//span[text()='Sports Shoes'])[1]"));
		sportsshoe.click();
		WebElement mensports = driver.findElement(By.xpath("(//div[text()='Sports Shoes'])[1]/following-sibling::div"));
		String shoecount = mensports.getText();
		System.out.println("the total number of shoe is " + shoecount);
		WebElement trainng = driver.findElement(By.xpath("//div[text()='Training Shoes']"));
		trainng.click();
		WebElement popularity = driver.findElement(By.xpath("//div[contains(text(),'Popularity')]"));
		popularity.click();
		WebElement lowtohigh = driver.findElement(By.xpath("//ul[@class='sort-value']/li[2]"));
		lowtohigh.click();
		WebElement productprice1 = driver.findElement(By.xpath("(//span[contains(@id,'display-price')])[1]"));
		String productpriceone = productprice1.getAttribute("data-price");
		WebElement productprice2 = driver.findElement(By.xpath("(//span[contains(@id,'display-price')])[2]"));
		String productpricetwo = productprice2.getAttribute("data-price");
		int parseInt1 = Integer.parseInt(productpriceone);
		int parseInt2 = Integer.parseInt(productpricetwo);
		System.out.println(parseInt1 + " " + parseInt2);
		if (parseInt1 - parseInt2 <= 0) {
			System.out.println("filter applied is from low to high");
		} else {
			System.err.println("filter applied is from high to low");
		}
		WebElement viewmore = driver.findElement(By.xpath("(//button[text()='View More '])[1]"));
		viewmore.click();
		WebElement color = driver.findElement(By.xpath("(//span[@class='filter-color-tile'])[6]"));
		color.click();
		WebElement price1 = driver.findElement(By.xpath("//input[@name='fromVal']"));
		price1.clear();
		price1.sendKeys("900");
		WebElement price2 = driver.findElement(By.xpath("//input[@name='toVal']"));
		price2.clear();
		price2.sendKeys("1200");
		WebElement catagory = driver.findElement(By.xpath("//h1[@class='category-name']"));
		String catagoryname = catagory.getAttribute("category");
		System.out.println("catagory name" + catagoryname);
		if (catagoryname.contains("Training Shoes")) {
			System.out.println("yes we are in sports shoe section");
		} else {
			System.err.println("we are not in sports section");
		}
		WebElement validate2 = driver.findElement(By.xpath("(//div[@class='navFiltersPill'])[1]/a"));
		String validate2name = validate2.getAttribute("data-value");
		if (validate2name.contains("Navy")) {
			System.out.println("we have successfully in navy filter");
		} else {
			System.err.println("we are in some other color filter");
		}
		WebElement quickview = driver.findElement(By.xpath("(//section[@data-dpwlbl='Product Grid'])[1]/div"));
		builder.moveToElement(quickview).perform();
		quickview.click();
		ArrayList<String> window = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(window.get(1));
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("C:\\temp\\screenshot.png"));
		WebElement bill = driver.findElement(By.xpath("//span[@class='payBlkBig']"));
		String billamt = bill.getText();
		System.out.println("the final bill amt is" + billamt);
		WebElement discount = driver.findElement(By.xpath("//span[@class='pdpDiscount ']"));
		String discountpercentage = discount.getText();
		System.out.println("the final discount percentage is" + discountpercentage);
		driver.close();
		System.out.println("Successfull closing of snapdeal testcase");

	}
}
