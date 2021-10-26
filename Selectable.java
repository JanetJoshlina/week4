package week4;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Selectable {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://jqueryui.com/selectable/");
		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='demo-frame']")));
		Actions perform = new Actions(driver);
		WebElement start = driver.findElement(By.xpath("//li[text()='Item 1']"));
		WebElement end = driver.findElement(By.xpath("//li[text()='Item 2']"));
		// perform.clickAndHold(start).moveToElement(end).release().perform();
		WebElement five = driver.findElement(By.xpath("(//ol[@id='selectable']/li)[5]"));
		WebElement seven = driver.findElement(By.xpath("(//ol[@id='selectable']/li)[7]"));
		perform.keyDown(Keys.CONTROL).click(five).click(seven).keyUp(Keys.CONTROL).perform();
	}

}
