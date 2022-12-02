package week4.day1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FramesCherCher {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions cherOptions = new ChromeOptions();
		cherOptions.addArguments("--disable-notifications");
		ChromeDriver cherChromeDriver = new ChromeDriver(cherOptions);
		cherChromeDriver.manage().window().maximize();
		cherChromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		cherChromeDriver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");
		
	    String getTopic = cherChromeDriver.findElement(By.xpath("//span[text()='Not a Friendly Topic']")).getText();
		System.out.println("Topic: "+getTopic);
		
		cherChromeDriver.switchTo().frame("frame1");
		cherChromeDriver.findElement(By.xpath("//input")).sendKeys("This is a good day");
		cherChromeDriver.switchTo().frame("frame3");
		cherChromeDriver.findElement(By.xpath("//input[@id='a']")).click();
		Thread.sleep(3000);
		cherChromeDriver.switchTo().frame("frame2");
		System.out.println("hi");
		WebElement animalElement = cherChromeDriver.findElement(By.xpath("//select[@id='animals']"));
		animalElement.click();
		Select animalSelect = new Select(animalElement);
		
		animalSelect.selectByVisibleText("Big Baby Cat");
	}

}
