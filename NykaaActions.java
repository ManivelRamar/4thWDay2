package week4.day2;

	import java.util.ArrayList;
	import java.util.List;
	import java.util.Set;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.Select;

	import io.github.bonigarcia.wdm.WebDriverManager;

	public class NykaaActions 
	{
		public static void main(String[] args) throws InterruptedException 
		{
			WebDriverManager.chromedriver().setup();
			ChromeDriver driver = new ChromeDriver();
			driver.get("https://www.nykaa.com/");
			driver.manage().window().maximize();
			
			Actions click = new Actions(driver);
			
			WebElement brand = driver.findElement(By.xpath("//a[text()='brands']"));
			click.moveToElement(brand).perform();
			
			driver.findElement(By.id("brandSearchBox")).sendKeys("L'Oreal Paris");
			driver.findElement(By.xpath("//a[text()=\"L'Oreal Paris\"]")).click();
			
			if(driver.getTitle().contains("L'Oreal Paris"))
			{
				System.out.println("Title contains \"L'Oreal Paris\"");
			}
			else
			{
				System.out.println("Title not contains \"L'Oreal Paris\"");
			}
			
			driver.findElement(By.className("sort-name")).click();
			driver.findElement(By.xpath("//span[text()='customer top rated']")).click();
			driver.findElement(By.xpath("//span[text()='Category']")).click();
			driver.findElement(By.xpath("//span[text()='Hair']")).click();
			driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
			driver.findElement(By.xpath("//span[text()='Shampoo']")).click();
			driver.findElement(By.xpath("//span[text()='Concern']")).click();
			driver.findElement(By.xpath("//span[text()='Color Protection']")).click();
			Thread.sleep(2000);
			
			WebElement filter1 = driver.findElement(By.xpath("//span[text()='Filters Applied']/following::div[2]"));
			String appliedFilter1 = filter1.getText();
			
			WebElement filter2 = driver.findElement(By.xpath("//span[text()='Filters Applied']/following::div[3]"));
			String appliedFilter2 = filter2.getText();
			
			if(appliedFilter1.contains("Shampoo") && appliedFilter2.contains("Color"))
			{
			  System.out.println("Filter applied sucssfully"); 
			}
			else
			{
			  System.out.println("Filter not applied");
			}
			 
			driver.findElement(By.xpath("//div[text()=\"L'Oreal Paris Colour Protect Shampoo\"]")).click();
			
			Set<String> windowSet = driver.getWindowHandles();
			List<String> windows = new ArrayList<String>(windowSet);
			
			driver.switchTo().window(windows.get(1));
			
			WebElement productSize = driver.findElement(By.xpath("//select[@title='SIZE']"));
			Select grams = new Select(productSize);
			grams.selectByValue("1");
			
			System.out.println("MRP : "+driver.findElement(By.xpath("//span[text()='MRP:']/following-sibling::span")).getText());
			
			driver.findElement(By.xpath("//span[text()='ADD TO BAG']")).click();
			Thread.sleep(4000);
			driver.findElement(By.xpath("//span[@class='cart-count']")).click();
			Thread.sleep(3000);
			driver.switchTo().frame(0);
			
			String cartValue = driver.findElement(By.xpath("//div[@class='value medium-strong']")).getText();
			
			System.out.println("Total cart value:"+cartValue);
			
			driver.findElement(By.xpath("//span[text()='PROCEED']")).click();
			
			driver.switchTo().defaultContent();
			
			driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();
			
			String grandTotal = driver.findElement(By.xpath("//div[@class='payment-details-tbl grand-total-cell prl20']//span")).getText();
			System.out.println("Grand total:"+grandTotal);
			
			if(cartValue.contains(grandTotal)) 
			{
				System.out.println("Price are same");
			}else 
			{
				System.out.println("There are different price");
			}
			driver.quit();
		}
	}