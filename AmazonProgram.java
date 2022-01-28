package week4.day2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonProgram 
{

	public static void main(String[] args) throws InterruptedException, IOException 
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		Actions click = new Actions(driver);
		
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Oneplus 9 pro",Keys.ENTER);
		Thread.sleep(2000);
		
		String Price = driver.findElement(By.className("a-price-whole")).getText();

		
		System.out.println("Customer ratings : " + driver.findElement(By.xpath("//span[@class='a-size-base a-color-base s-underline-text']")).getText());

		click.moveToElement(driver.findElement(By.xpath("//span[@class='a-icon-alt']"))).click().build().perform();
		Thread.sleep(2000);
		System.out.println("5 Star ratings : " + driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base a-text-beside-button a-text-bold']")).getText());

		click.moveToElement(driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']"))).click().build().perform();

		driver.switchTo().window(new ArrayList<String>(driver.getWindowHandles()).get(1));

		WebElement productImage = driver.findElement(By.xpath("//div[@id='imgTagWrapperId']/img"));
		
		File source = productImage.getScreenshotAs(OutputType.FILE);
		File destination = new File("./images/Device.png");
		FileUtils.copyFile(source, destination);

		driver.findElement(By.id("add-to-cart-button")).click();
		Thread.sleep(3000);
		
		String cart = driver.findElement(By.xpath("//span[@id='attach-accessory-cart-subtotal']")).getText();
		cart = cart.substring(1,7);
		
		if(Price.equals(cart))
			System.out.println("Product and cart amount are same");
		else
			System.out.println("Product and cart amount different");

	}
}
