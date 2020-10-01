package mediaMarktSearch;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class MediaMarkt {
	@Test
	public void f() throws IOException, InterruptedException {

//	    Define Chrome Browser
		System.setProperty("webdriver.chrome.driver", "C:\\chrome\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

//		Maximize the window and open the link
		driver.manage().window().maximize();
		driver.get("https://www.mediamarkt.com.tr/");
		
//		Get Screenshot
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("C:\\Users\\MehmatAliMemis\\Documents\\Downloads\\screenshot.png"));

//		Manage time outs
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

//		Moves to spesific element
		Actions a = new Actions(driver);
		WebElement allCategories = driver
				.findElement(By.xpath("//a[@class='site-navigation2__link site-navigation2__link--primary-toggle']"));

//		Write to search button
		a.moveToElement(driver.findElement(By.xpath("//input[@placeholder='Ne aramýþtýnýz?']"))).click()
				.keyDown(Keys.SHIFT).sendKeys("IPHONE").build().perform();

//		ContextClick's mean right click
		a.moveToElement(allCategories).contextClick().build().perform();
		Thread.sleep(5000L);

//		Opens the new link tab 
		WebElement popularDevices = driver.findElement(By.xpath("//div[@class='ms-row']//div[1]//div[2]"));
		System.out.println(popularDevices.findElements(By.tagName("a")).size());

		for (int i=0; i<popularDevices.findElements(By.tagName("a")).size(); i++)
		{
			String clickonlinkTab = Keys.chord(Keys.CONTROL, Keys.ENTER);
			popularDevices.findElements(By.tagName("a")).get(i).sendKeys(clickonlinkTab);
		}
		
//		Opens all the tabs 
		Set<String> abc=driver.getWindowHandles();
		Iterator<String> it=abc.iterator();
		
		while(it.hasNext()) {
			
			driver.switchTo().window(it.next());
			System.out.println(driver.getTitle());
		}

		
	}
}
