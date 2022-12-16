package webdriver;


import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_6_Browser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath+"\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver", projectPath+"\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Verify_URL() {

		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("(//a[@title='My Account'])[2]")).click();
		Assert.assertEquals("http://live.techpanda.org/index.php/customer/account/login/", driver.getCurrentUrl());
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		Assert.assertEquals("http://live.techpanda.org/index.php/customer/account/create/", driver.getCurrentUrl());
		
	}

	@Test
	public void TC_02_Verify_Title() {

		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("(//a[@title='My Account'])[2]")).click();
		Assert.assertEquals("Customer Login", driver.getTitle());
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		Assert.assertEquals("Create New Customer Account", driver.getTitle());
		
	}
	
	@Test
	public void TC_03_Navigate_Function() {

		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("(//a[@title='My Account'])[2]")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		Assert.assertEquals("http://live.techpanda.org/index.php/customer/account/create/", driver.getCurrentUrl());
		driver.navigate().back();
		Assert.assertEquals("http://live.techpanda.org/index.php/customer/account/login/", driver.getCurrentUrl());
		driver.navigate().forward();
		Assert.assertEquals("Create New Customer Account", driver.getTitle());
	}
	
	@Test
	public void TC_04_Get_Page_Source_Code() {

		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("(//a[@title='My Account'])[2]")).click();
		
		assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		assertTrue(driver.getPageSource().contains("Create an Account"));
	}
	
	public void input_text(WebElement ele, String content) {
		ele.clear();
		ele.sendKeys(content);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
