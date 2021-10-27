package week4;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Myntra {
	public static void main(String[] args) throws Throwable {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		Actions builder = new Actions(driver);

		WebElement men = driver.findElement(By.xpath("(//a[text()='Men'])[1]"));
		builder.moveToElement(men).perform();
		WebElement jackets = driver.findElement(By.xpath("(//a[text()='Jackets'])[1]"));
		jackets.click();

		WebElement Jacketcount = driver.findElement(By.className("title-count"));
		String count = Jacketcount.getText();
		System.out.println("the total number of jackets available is " + count);

		String text1 = driver.findElement(By.xpath("(//span[@class='categories-num'])[1]")).getText();
		String[] split = text1.split("");
		String total = "";
		for (int i = 1; i < split.length - 1; i++) {
			total = total + split[i];
		}
		System.out.println("the total number of Jackets " + total);

		String text2 = driver.findElement(By.xpath("(//span[@class='categories-num'])[2]")).getText();
		String[] split2 = text2.split("");
		String total2 = "";
		for (int i = 1; i < split2.length - 1; i++) {
			total2 = total2 + split2[i];
		}
		System.out.println("the total number of Rain Jackets " + total2);

		int total1 = Integer.parseInt(total);
		int total22 = Integer.parseInt(total2);
		String amount = Integer.toString(total1 + total22);
		System.out.println("addition of two catagories " + amount);
		if (amount.equals(count)) {
			System.out.println("the catagory count matches");
		} else {
			System.err.println("the catagory count dosent match");
		}

		WebElement jacket = driver.findElement(By.xpath("(//div[@class='common-checkboxIndicator'])[1]"));
		jacket.click();
		driver.findElement(By.className("brand-more")).click();
		driver.findElement(By.xpath("//input[@placeholder='Search brand']")).sendKeys("Duke");
		driver.findElement(By.xpath("(//div[@class='common-checkboxIndicator'])[11]")).click();
		driver.findElement(By.xpath("//span[contains(@class,'FilterDirectory-close')]")).click();
		List<WebElement> list = driver.findElements(By.xpath("//div[@class='product-productMetaInfo']/h3"));
		for (WebElement x : list) {
			String text = x.getText();
			if (!text.contains("Duke")) {
				System.out.println("not duke products");
				break;
			} else {
				continue;
			}
		}
		System.out.println("all are Duke Products only");
		driver.findElement(By.className("sort-sortBy")).click();
		driver.findElement(By.xpath("(//label[@class='sort-label '])[3]")).click();
		WebElement priceelement = driver.findElement(By.xpath("(//span[@class='product-discountedPrice'])[1]"));
		String price = priceelement.getText();
		System.out.println("price of first product is " + price);
		priceelement.click();
		ArrayList<String> window = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(window.get(1));
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("C:\\temp\\screenshot.png"));
		driver.findElement(By.xpath("//span[text()='WISHLIST']")).click();
		System.out.println("myntra testcase successful");
	}

}
