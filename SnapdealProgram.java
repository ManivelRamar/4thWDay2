package week4.day2;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapdealProgram 
{

	public static void main(String[] args) throws InterruptedException, IOException 
	{
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();

		Actions mouse = new Actions(driver);

		mouse.moveToElement(driver.findElement(By.xpath("//span[text()=\"Men's Fashion\"]"))).click().build().perform();

		mouse.moveToElement(driver.findElement(By.xpath("//span[text()='Sports Shoes']"))).click().build().perform();
		
		Thread.sleep(2000);
		String shoeCount = driver.findElement(By.xpath("//div[text()='Sports Shoes for Men']/following-sibling::div")).getText();
		System.out.println("Total Count of Men Sports shoes: "+ shoeCount);

		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		
		mouse.moveToElement(driver.findElement(By.xpath("//div[@class='sort-selected']"))).click().build().perform();
		mouse.moveToElement(driver.findElement(By.xpath("//ul[@class=\"sort-value\"]/li[2]"))).click().build().perform();

		WebElement fromRate = driver.findElement(By.xpath("//input[@name='fromVal']"));
		fromRate.clear();
		fromRate.sendKeys("900");
		
		WebElement toRate = driver.findElement(By.xpath("//input[@name='toVal']"));
		toRate.clear();
		toRate.sendKeys("1200");

		Thread.sleep(1000);

		driver.findElement(By.xpath("//div[contains(text(),'GO')]")).click();
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[@class='filter-color-tile Navy ']")).click();

		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(text(),'View More')]")).click();
				
		
		
		String filter1= driver.findElement(By.xpath("//div[@class='filters']//a[contains(text(),'900')]")).getText();
		Thread.sleep(1000);
		
		
		if(filter1.contains("900"))
		{
			System.out.println("Filter applied successfully");
		}
		else
		{
			System.out.println("Filter not applied");
		}
		
		Thread.sleep(2000);
		mouse.moveToElement(driver.findElement(By.xpath("//img[@class='product-image wooble']"))).build().perform();

		driver.findElement(By.xpath("//div[contains(text(),'Quick View')]")).click();
		
		Thread.sleep(2000);
		System.out.println("Product Price: "+driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText());
		System.out.println("Product discount: "+driver.findElement(By.className("percent-desc")).getText());
		
		WebElement imgFile = driver.findElement(By.xpath("//ul[@id=\"bx-slider-qv-image-panel\"]"));
		
		File source = imgFile.getScreenshotAs(OutputType.FILE);
		File destination = new File("./images/SnapDeal.png");
		FileUtils.copyFile(source, destination);
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[contains(@class,\"close1\")]")).click();
	
		driver.close();
	}

}
