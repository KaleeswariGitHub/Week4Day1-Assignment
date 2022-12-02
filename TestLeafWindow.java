package week4.day1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestLeafWindow {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions tfOptions = new ChromeOptions();
		tfOptions.addArguments("--disable-notifications");
		ChromeDriver tfWindowDriver = new ChromeDriver(tfOptions);
		tfWindowDriver.manage().window().maximize();
		tfWindowDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		tfWindowDriver.get("https://leafground.com/window.xhtml;jsessionid=node0ssnceyfd5mtq1u38yu3inp0zh436503.node0");

		tfWindowDriver.findElement(By.xpath("//span[text()='Open']")).click();
		
		Set<String> winOpenSet = tfWindowDriver.getWindowHandles();
		List<String> winList = new ArrayList<>(winOpenSet);
		
		tfWindowDriver.switchTo().window(winList.get(1));
		System.out.println(tfWindowDriver.getTitle());
		tfWindowDriver.close();
		tfWindowDriver.switchTo().window(winList.get(0));
		tfWindowDriver.findElement(By.xpath("//span[text()='Open Multiple']")).click();

		Set<String> winOpenSet1 = tfWindowDriver.getWindowHandles();
		List<String> winList1 = new ArrayList<>(winOpenSet1);
		System.out.println("Number of tabs opened newly: " + (winList1.size()-1));
		tfWindowDriver.switchTo().window(winList1.get(0));
		tfWindowDriver.findElement(By.xpath("//span[text()='Close Windows']")).click();
		
		Set<String> winOpenSet2 = tfWindowDriver.getWindowHandles();
		List<String> winList2 = new ArrayList<>(winOpenSet2);
		System.out.println("Number of tabs opened second: " + (winList2.size()));
		System.out.println(winList2);
		
		for(int i=1;i<=winList2.size()-1;i++)
		{
			tfWindowDriver.switchTo().window(winList2.get(i));
			tfWindowDriver.close();
		}
		
		tfWindowDriver.switchTo().window(winList2.get(0));
		
		tfWindowDriver.findElement(By.xpath("//span[text()='Open with delay']")).click();
		
		Set<String> winOpenSet3 = tfWindowDriver.getWindowHandles();
		List<String> winList3 = new ArrayList<>(winOpenSet3);
		System.out.println("Number of tabs opened second: " + (winList3.size()));
		System.out.println(winList3);

		for(int j=1;j<=winList3.size()-1;j++)
		{
			tfWindowDriver.switchTo().window(winList3.get(j));
			tfWindowDriver.close();
		}
		

	}

}
