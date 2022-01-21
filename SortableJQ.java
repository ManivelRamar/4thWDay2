package week4.day2;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SortableJQ 
{

	public static void main(String[] args) 
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://jqueryui.com/sortable/");
		driver.manage().window().maximize();
		
		Actions click = new Actions(driver);
		
		driver.switchTo().frame(0);
		
		List<WebElement> sortable = new ArrayList<WebElement>(driver.findElements(By.tagName("li")));
		
		click.dragAndDrop(sortable.get(6), sortable.get(0)).build().perform();
		
		

	}

}
