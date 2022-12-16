package webdriver;


import static org.testng.Assert.assertTrue;

import java.sql.Timestamp;
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


public class Topic_6_Login {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	
	String email_str = "automation_" + timestamp.getTime() + "@gmail.com";
	String pass_str = "123automation";
	
	String firstname = "automation";
	String lastname = "fcautotest";
	String xpath_fname = "//div[@class='box-content']/p[contains(text(),'" + firstname + "')]";
	String xpath_lname = "//div[@class='box-content']/p[contains(text(),'" + lastname + "')]";

	
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
	public void TC_01_Login_with_empty_email_and_password() throws InterruptedException {

		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='block-title']/following::a[@title='My Account']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.cssSelector("button#send2")).click();
		
		driver.findElement(By.xpath("//input[@id='email']/following-sibling::div[contains(text(),'This is a required field.')]")).isDisplayed();
		driver.findElement(By.xpath("//input[@id='pass']/following-sibling::div[contains(text(),'This is a required field.')]")).isDisplayed();
		
	}
	
	@Test
	public void TC_02_Login_with_invalid_email() throws InterruptedException {

		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='block-title']/following::a[@title='My Account']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("123@123.123");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("123456");
		driver.findElement(By.cssSelector("button#send2")).click();
		
		driver.findElement(By.xpath("//div[contains(text(),'Please enter a valid email address. For example johndoe@domain.com.')]")).isDisplayed();
	}
	
	@Test
	public void TC_03_Login_with_pasword_less_than_6_chars() throws InterruptedException {

		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='block-title']/following::a[@title='My Account']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("123");
		driver.findElement(By.cssSelector("button#send2")).click();
		
		driver.findElement(By.xpath("//div[contains(text(),'Please enter 6 or more characters without leading or trailing spaces.')]")).isDisplayed();
	}
	
	@Test
	public void TC_04_Login_with_incorrect_email_password() throws InterruptedException {

		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='block-title']/following::a[@title='My Account']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("123123123123");
		driver.findElement(By.cssSelector("button#send2")).click();
		
		driver.findElement(By.xpath("//span[contains(text(),'Invalid login or password.')]")).isDisplayed();
	}
	
	@Test
	public void TC_05_Create_a_new_account() throws InterruptedException {
		
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='block-title']/following::a[@title='My Account']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstname);
		driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastname);
		driver.findElement(By.cssSelector("input#email_address")).sendKeys(email_str);
		driver.findElement(By.cssSelector("input#password")).sendKeys(pass_str);
		driver.findElement(By.cssSelector("input#confirmation")).sendKeys(pass_str);
		
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		driver.findElement(By.xpath("//span[contains(text(),'Thank you for registering with Main Website Store.')]")).isDisplayed();

		driver.findElement(By.xpath(xpath_fname)).isDisplayed();
		driver.findElement(By.xpath(xpath_lname)).isDisplayed();
		driver.findElement(By.xpath("//p[text()[contains(.,'"+email_str+"')]]")).isDisplayed();
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[contains(text(),'Account')]")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		
		Thread.sleep(8000);
		Assert.assertEquals("Home page", driver.getTitle());
	}
	
	@Test
	public void TC_06_Login_with_valid_email_password() throws InterruptedException {

		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='block-title']/following::a[@title='My Account']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email_str);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(pass_str);
		driver.findElement(By.cssSelector("button#send2")).click();
		
		driver.findElement(By.xpath(xpath_fname)).isDisplayed();
		driver.findElement(By.xpath(xpath_lname)).isDisplayed();
		driver.findElement(By.xpath("//p[text()[contains(.,'"+email_str+"')]]")).isDisplayed();
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
