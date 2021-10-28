package week4;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {
	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		Actions builder = new Actions(driver);
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro");
		driver.findElement(By.xpath("//input[@value='Go']")).click();

		String text = driver.findElement(By.xpath("(//span[@class='a-price-whole'])[1]")).getText();
		System.out.println("the price of the first product is " + text);

		String text2 = driver.findElement(By.xpath("(//span[@class='a-size-base'])[1]")).getText();
		System.out.println("the number of customer ratings are " + text2);

		WebElement star = driver.findElement(By.xpath("(//i[contains(@class,'a-icon-popover')])[1]"));
		star.click();

		String text3 = driver.findElement(By.xpath("(//a[contains(@title,'5 stars')])[3]")).getText();
		System.out.println("the 5 start rating percentage is " + text3);

		driver.findElement(By.xpath("(//span[contains(@class,'a-size-medium')])[2]")).click();
		ArrayList<String> list = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(list.get(1));
		File screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotAs, new File("c:/temp/Screen.png"));
		String productprice = driver.findElement(By.xpath("(//span[@class='a-color-price'])[3]")).getText();
		String[] split = productprice.split(" ");

		driver.findElement(By.id("add-to-cart-button")).click();
		driver.findElement(By.xpath("//span[@id='attach-sidesheet-view-cart-button']")).click();

		String text4 = driver.findElement(By.xpath("(//span[contains(@class,'sc-white-space-nowrap')])[1]")).getText();
		String[] split2 = text4.split(" ");

		if (split[3].equals(split2[2])) {
			System.out.println("cart subtotal is correct");
		} else {
			System.err.println("cart subtotal is not correct");
		}
		System.out.println("Amazon testcase successfull");
	}
}
