package week4.day2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DraggableJQ 
{

	public static void main(String[] args) 
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://jqueryui.com/draggable/");
		driver.manage().window().maximize();
		
		Actions click = new Actions(driver);
		
		driver.switchTo().frame(0);
		
		
		WebElement drag = driver.findElement(By.id("draggable"));
		
		click.dragAndDropBy(drag, 90, 75).perform();

	}

}
