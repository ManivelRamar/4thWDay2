package week4.day2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MyntraProgram 
{
	static ChromeDriver driver;
	public static void main(String[] args) throws InterruptedException, IOException 
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		
		Actions click = new Actions(driver);
		
		click.moveToElement(driver.findElement(By.xpath("//a[text()='Men']"))).build().perform();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='Jackets']")).click();
		
		String totalCount = driver.findElement(By.xpath("//span[@class='title-count']")).getText();
		
		String categoryCount1 = driver.findElement(By.xpath("(//span[@class='categories-num'])[1]")).getText();
		String categoryCount2 = driver.findElement(By.xpath("(//span[@class='categories-num'])[2]")).getText();
		
		int count1 = Integer.parseInt(categoryCount1.substring(1, 5));
		int count2 = Integer.parseInt(categoryCount2.substring(1, 3));
		int baseTotal = Integer.parseInt(totalCount.split(" ")[2]);
		
		int categoryTotal = count1+count2;
		
		if(categoryTotal==baseTotal)
		{
			System.out.println("There are same count");
		}
		else
		{
			System.out.println("There are different count");
		}
		
		driver.findElement(By.xpath("(//span[@class=\"categories-num\"]//following-sibling::div)[1]")).click();
		driver.findElement(By.className("brand-more")).click();
		driver.findElement(By.className("FilterDirectory-searchInput")).sendKeys("Duke",Keys.ENTER);
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//span[@class='FilterDirectory-count']/following-sibling::div")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//span[contains(@class,'sprites-remove')]")).click();
		
		int totalProducts = 0;
		
		totalProducts = brandName(totalProducts);
		driver.findElement(By.xpath("//li[@class='pagination-next']/a")).click();
		Thread.sleep(2000);
		
		totalProducts = brandName(totalProducts);
		driver.findElement(By.xpath("//li[@class='pagination-next']/a")).click();
		Thread.sleep(2000);
		
		totalProducts = brandName(totalProducts);
		driver.findElement(By.xpath("//li[@class='pagination-next']/a")).click();
		Thread.sleep(2000);
		
		totalProducts = brandName(totalProducts);
		System.out.println("Total Number of Products :" + totalProducts);
		
		click.moveToElement(driver.findElement(By.xpath("//div[@class='sort-sortBy']"))).build().perform();
		Thread.sleep(1000);
		click.moveToElement(driver.findElement(By.xpath("(//div[@class='sort-sortBy'])//li[3]"))).click().build().perform();		
		Thread.sleep(1000);
		
		System.out.println("Price of the First Product : "+ driver.findElement(By.xpath("//span[@class='product-discountedPrice']")).getText());

		driver.findElement(By.xpath("//div[contains(@class,'ratingsContainer')]/following::a")).click();

		driver.switchTo().window(new ArrayList<String>(driver.getWindowHandles()).get(1));
		
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination = new File("./Images/DukeOnMyntra.png");
		FileUtils.copyFile(source, destination);

		driver.findElement(By.xpath("//span[text()='WISHLIST']")).click();
		Thread.sleep(2000);
		
		driver.close();
	}

	private static int brandName(int totalProducts) 
	{
		String brand = "";
		List<WebElement> productBrandList = driver.findElements(By.xpath(("//h3[@class='product-brand']")));
		
		for (WebElement element : productBrandList)
		{
			brand = element.getText();
			
			if (brand.equalsIgnoreCase("Duke")) 
			{
				totalProducts++;
			}
		}
		return totalProducts;
	}
}
