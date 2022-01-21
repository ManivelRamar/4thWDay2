package week4.day2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ResizeJQ 
{

	public static void main(String[] args) 
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://jqueryui.com/resizable/");
		driver.manage().window().maximize();
		
		Actions click = new Actions(driver);
		
		driver.switchTo().frame(0);
		
		WebElement move = driver.findElement(By.xpath("//h3[text()='Resizable']/following::div[3]"));
		
		click.dragAndDropBy(move, 130, 170).perform();

	}

}
