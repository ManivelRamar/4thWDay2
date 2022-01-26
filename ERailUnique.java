package week4.day2;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ERailUnique 
{
	public static void main(String[] args) throws InterruptedException 
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://erail.in");
		driver.manage().window().maximize();
		
		WebElement from = driver.findElement(By.id("txtStationFrom"));
		from.clear();
		from.sendKeys("MAS");
		from.sendKeys(Keys.ENTER);
		
		WebElement to = driver.findElement(By.id("txtStationTo"));
		to.clear();
		to.sendKeys("TPJ");
		to.sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		
		driver.findElement(By.id("chkSelectDateOnly")).click();
		
		Thread.sleep(2000);
		
		List<WebElement> railList =driver.findElements(By.xpath("//table[@class='DataTable TrainList TrainListHeader']//tr"));

		int rowCount = railList.size();
		System.out.println("Train List count: "+rowCount);
		
		List<String> trainName = new ArrayList<String>();
		
		for(int i=1; i<rowCount;i++) 
		{		
			String names = driver.findElement(By.xpath("//table[@class='DataTable TrainList TrainListHeader']//tr["+i+"]/td[2]")).getText();
			trainName.add(names);
		}
		
		Set<String> trainSet = new LinkedHashSet<String>(trainName);
		
		System.out.println("The Set of Train: "+trainSet.size());
	}
}