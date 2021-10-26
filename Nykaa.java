package week4;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.nykaa.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement brands = driver.findElement(By.xpath("//a[text()='brands']"));
		WebElement loreal = driver.findElement(By.xpath("(//a[text()=\"L'Oreal Paris\"])[1]"));
		Actions perform = new Actions(driver);
		perform.moveToElement(brands).perform();
		loreal.click();
		WebElement popularity = driver.findElement(By.xpath("//span[contains(text(),'popularity')]"));
		popularity.click();
		WebElement toprated = driver.findElement(By.xpath("//span[text()='customer top rated']"));
		toprated.click();
		WebElement category = driver.findElement(By.xpath("//span[text()='Category']"));
		category.click();
		WebElement hair = driver.findElement(By.xpath("//span[text()='Hair']"));
		hair.click();
		WebElement care = driver.findElement(By.xpath("//span[text()='Hair Care']"));
		care.click();
		WebElement shampoo = driver.findElement(By.xpath("//span[text()='Shampoo']"));
		shampoo.click();
		WebElement concern = driver.findElement(By.xpath("//span[text()='Concern']"));
		concern.click();
		WebElement colorProtect = driver.findElement(By.xpath("//span[text()='Color Protection']"));
		colorProtect.click();
		WebElement filtered = driver
				.findElement(By.xpath("//span[text()='Filters Applied']/parent::div/following-sibling::div//span"));
		String filteredTxt = filtered.getText();
		if (filteredTxt.contains("Shampoo")) {
			System.out.println("we are in the shapoo section");
		} else {
			System.err.println("we are not in correct section");
		}
		WebElement colorShampoo = driver
				.findElement(By.xpath("(//div[text()=\"L'Oreal Paris Colour Protect Shampoo\"])[1]"));
		colorShampoo.click();
		ArrayList<String> list = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(list.get(1));
		Select sel = new Select(driver.findElement(By.xpath("//SELECT[@title='SIZE']")));
		sel.selectByVisibleText("175ml");
		String mrp = driver.findElement(By.xpath("(//span[text()='MRP:'])[1]/following-sibling::span")).getText();
		System.out.println("MRP of the product is" + mrp);
		WebElement addtobag = driver.findElement(By.xpath("(//span[text()='ADD TO BAG'])[1]"));
		addtobag.click();
		WebElement bag = driver.findElement(By.xpath("//span[@class='cart-count']"));
		bag.click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='css-acpm4k']")));
		String grandTotal = driver.findElement(By.xpath("//div[@class='value medium-strong']")).getText();
		System.out.println("The Grand total is" + grandTotal);
		WebElement proceed = driver.findElement(By.xpath("//span[text()='Proceed']"));
		proceed.click();
		String grandTotalFinal = driver.findElement(By.xpath("//div[text()='Grand Total']/following-sibling::div"))
				.getText();
		if (grandTotal.equals(grandTotalFinal)) {
			System.out.println("yes Grand Total matches");
		} else {
			System.err.println("ERROR IN TALLY");
		}
		WebElement guest = driver.findElement(By.xpath("(//button[contains(@class,'big')])[2]"));
		guest.click();
		System.out.println("Successfull Nykaa Automation");
	}
}
