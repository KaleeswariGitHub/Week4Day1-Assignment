package week4.day1;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;


public class AmazonWindows {

	public static void main(String[] args) throws IOException {

		WebDriverManager.chromedriver().setup();
		ChromeOptions amazOptions = new ChromeOptions();
		amazOptions.addArguments("--disable-notifications");
		ChromeDriver amazonChromeDriver = new ChromeDriver(amazOptions);
		amazonChromeDriver.manage().window().maximize();
		amazonChromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		amazonChromeDriver.get("https://www.amazon.in/");
		
		amazonChromeDriver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro",Keys.ENTER);
        String getCost = amazonChromeDriver.findElement(By.xpath("//span[@class='a-price-whole']")).getText();
        System.out.println("Cost of the first product is: "+getCost);
        String inCost= getCost.replace(",","");
        int firstPrice =(int) Double.parseDouble(inCost);
        
        amazonChromeDriver.findElement(By.xpath("//h2//span")).click();
        
        Set<String>  windowsList = amazonChromeDriver.getWindowHandles();
		List<String> listOfWindows = new ArrayList<String>(windowsList);
		System.out.println(listOfWindows);
		amazonChromeDriver.switchTo().window(listOfWindows.get(1));
		System.out.println("Child Title: "+amazonChromeDriver.getTitle());
	
		File sourceFile = amazonChromeDriver.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./snaps/amazonPro.jpeg");
		FileUtils.copyFile(sourceFile, destFile);
		amazonChromeDriver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
		
        Set<String> cartWindow = amazonChromeDriver.getWindowHandles();
        List< String> cartList = new ArrayList<>(cartWindow);
        amazonChromeDriver.switchTo().window(cartList.get(1));
		System.out.println("Child Title: "+amazonChromeDriver.getTitle());
		amazonChromeDriver.findElement(By.xpath("//span[text()=' Cart ']/preceding-sibling::input[@class='a-button-input']")).click();
	    String getSubTotal = amazonChromeDriver.findElement(By.xpath("//span[@class='currencyINR']/parent::span")).getText();
	    System.out.println("Tottal is : "+getSubTotal);
	    String st = getSubTotal.replace(",","");
	    int secondPrice = (int)Double.parseDouble(st); 
	 
	    if(firstPrice == secondPrice)
	    	System.out.println("The cost of the product is verified");
	    else 
		  System.out.println("The cost of the product is differed. Please check");
	    
	    amazonChromeDriver.close();
		
		

		
        
	}

}
