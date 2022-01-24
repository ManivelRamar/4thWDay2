package week4.day2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SortableTestLeaf 
{
	public static void main(String[] args) throws InterruptedException 
	{
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/sorttable.html");
		driver.manage().window().maximize();
		
		List<WebElement> elements = driver.findElements(By.xpath("//table[@id='table_id']//td[2]"));
		
		int rows = elements.size();
		
		List<String> names = new ArrayList<String>();
		
		for (int i=1; i<=rows; i++) 
		{
			String list = driver.findElement(By.xpath("//table[@id='table_id']//tr["+i+"\n]/td[2]")).getText();
			names.add(list);
		}
		
		Thread.sleep(2000);
		Collections.sort(names);
		System.out.println("The default name list: \n"+names);
		
		driver.findElement(By.xpath("//th[text()='Name']")).click();
		
		List<String> namesSorted = new LinkedList<String>();
		
		for (int i=1; i<=rows; i++) 
		{
			String text2 = driver.findElement(By.xpath("//table[@id='table_id']//tr["+i+"\n]/td[2]")).getText();
			namesSorted.add(text2);
		}
		
		System.out.println("The Sorted List: \n"+namesSorted);
				
		if(names.equals(namesSorted))
		{
			System.out.println("\n The names in the table has been sorted successfully");
		}
		else
		{
			System.out.println("\n The names are not get sorted");
		}
	}
}
