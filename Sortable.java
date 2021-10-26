package week4;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Sortable {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("	https://jqueryui.com/sortable/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='demo-frame']")));
		Actions perform = new Actions(driver);
		WebElement start = driver.findElement(By.xpath("//li[text()='Item 1']"));
		Point end = driver.findElement(By.xpath("//li[text()='Item 5']")).getLocation();
		int x = end.getX();
		int y = end.getY();
		perform.dragAndDropBy(start, x, y).perform();
		perform.clickAndHold(null).moveByOffset(0, 0).release().perform();
	}
	

}
