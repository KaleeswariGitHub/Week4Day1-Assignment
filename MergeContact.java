package week4.day1;
/*
 * //Pseudo Code
 *  	
 *  
 *  
 * 
 * 13. Verify the title of the page
 */

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {

	public static void main(String[] args) throws InterruptedException {
	
//1. Launch URL "http://leaftaps.com/opentaps/control/login"
		WebDriverManager.chromedriver().setup();
		ChromeOptions mergeOptions = new ChromeOptions();
		mergeOptions.addArguments("--disable-notifications");
		ChromeDriver mergeDriver = new ChromeDriver(mergeOptions);
		mergeDriver.manage().window().maximize();
		mergeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		mergeDriver.get("http://leaftaps.com/opentaps/control/login");
//	 2. Enter UserName and Password Using Id Locator
		mergeDriver.findElement(By.id("username")).sendKeys("Demosalesmanager");
	    mergeDriver.findElement(By.id("password")).sendKeys("crmsfa");
//   3. Click on Login Button using Class Locator
		mergeDriver.findElement(By.className("decorativeSubmit")).click();
//	 4. Click on CRM/SFA Link
		mergeDriver.findElement(By.xpath("//a[contains(text(),'CRM/')]")).click();
//	 5. Click on contacts Button
		mergeDriver.findElement(By.xpath("//a[text()='Contacts']")).click();
// 	6. Click on Merge Contacts using Xpath Locator
		mergeDriver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();
//	7. Click on Widget of From Contact
		mergeDriver.findElement(By.xpath("//input[@id='partyIdFrom']/following::img")).click();
//	8. Click on First Resulting Contact	
// for this i have to open a set and insert set to list and then get the windowshandle.
        
		Set<String> windowSet = mergeDriver.getWindowHandles();
		List<String> windowList = new ArrayList<>(windowSet);
		System.out.println("Windows opened: "+windowList);
		
		mergeDriver.switchTo().window(windowList.get(1));
		
		mergeDriver.findElement(By.xpath("//td[@class='x-grid3-col x-grid3-cell x-grid3-td-partyId x-grid3-cell-first ']//a[@class='linktext']")).click();
	
//	9. Click on Widget of To Contact
		mergeDriver.switchTo().window(windowList.get(0));
		mergeDriver.findElement(By.xpath("//input[@id='partyIdTo']/following::img")).click();
//	10. Click on Second Resulting Contact
		Set<String> windowSet1 = mergeDriver.getWindowHandles();
		List<String> windowList1 = new ArrayList<>(windowSet1);
//		System.out.println("Windows opened: "+windowList1);
		mergeDriver.switchTo().window(windowList1.get(1));
		mergeDriver.findElement(By.xpath("//div[@class='x-grid3-row    x-grid3-row-alt']//a")).click();
//	11. Click on Merge button using Xpath Locator
		mergeDriver.switchTo().window(windowList1.get(0));
		mergeDriver.findElement(By.xpath("//a[text()='Merge']")).click();

//	12. Accept the Alert
		
		Alert messAlert = mergeDriver.switchTo().alert();
		messAlert.accept();
	    String mergeTitle = mergeDriver.getTitle();
	    System.out.println(mergeTitle);

	}

}
