package week4;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Resizable {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://jqueryui.com/resizable/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='demo-frame']")));
		WebElement size = driver.findElement(By.xpath("(//div[contains(@class,'resizable-handle')])[3]"));
		Actions perform = new Actions(driver);
		perform.dragAndDropBy(size, 250,250).perform();
	}

}
