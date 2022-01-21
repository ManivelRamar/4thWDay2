package week4.day2;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SelectableJQ 
{

	public static void main(String[] args) 
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://jqueryui.com/selectable/");
		driver.manage().window().maximize();
		
		Actions click = new Actions(driver);
		
		driver.switchTo().frame(0);
		
		List<WebElement> selectable = new ArrayList<WebElement>(driver.findElements(By.tagName("li")));
		
		click.keyDown(Keys.CONTROL).click(selectable.get(0)).click(selectable.get(2)).click(selectable.get(4)).keyUp(Keys.CONTROL).build().perform();
		
	}

}
