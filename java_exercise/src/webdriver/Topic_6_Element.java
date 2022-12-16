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


public class Topic_6_Element {
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
	public void TC_01_Check_IsDisplayed() {

		driver.get("https://automationfc.github.io/basic-form/index.html");
		Assert.assertEquals(driver.findElement(By.cssSelector("#email")).isDisplayed(),true);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#under_18")).isDisplayed(),true);
		Assert.assertEquals(driver.findElement(By.cssSelector("textarea#edu")).isDisplayed(),true);
		Assert.assertEquals(driver.findElement(By.xpath("//h5[contains(text(),'Name: User5')]")).isDisplayed(), false);
	
	}

	@Test
	public void TC_02_Check_IsEnabled() {

		driver.get("https://automationfc.github.io/basic-form/index.html");
		Assert.assertEquals(driver.findElement(By.cssSelector("#email")).isEnabled(),true);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#under_18")).isEnabled(),true);
		Assert.assertEquals(driver.findElement(By.cssSelector("textarea#edu")).isEnabled(),true);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("select#job1")).isEnabled(),true);
		Assert.assertEquals(driver.findElement(By.cssSelector("select#job2")).isEnabled(),true);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='development']")).isEnabled(),true);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='slider-1']")).isEnabled(),true);
		
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='disable_password']")).isEnabled(),false);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='radio-disabled']")).isEnabled(),false);
		Assert.assertEquals(driver.findElement(By.xpath("//textarea[@id='bio']")).isEnabled(),false);
		
		Assert.assertEquals(driver.findElement(By.xpath("//select[@id='job3']")).isEnabled(),false);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='check-disbaled']")).isEnabled(),false);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='slider-2']")).isEnabled(),false);
		
	}
	
	@Test
	public void TC_03_Check_IsSelected() {

		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.cssSelector("input#under_18")).click();
		driver.findElement(By.cssSelector("input#java")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("input#under_18")).isSelected(),true);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#java")).isSelected(),true);
		
		driver.findElement(By.cssSelector("input#java")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("input#java")).isSelected(),false);	
	}
	
	@Test
	public void TC_04_Register_Mailchimp() throws InterruptedException {

		driver.get("https://login.mailchimp.com/signup/");
		
		driver.findElement(By.cssSelector("input#email")).sendKeys("test@gmail.com");
		driver.findElement(By.cssSelector("input#new_username")).click();
		WebElement password_field = driver.findElement(By.cssSelector("input#new_password"));
		
		password_field.sendKeys("12");
		Thread.sleep(2000); 
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed(), true);
		
		password_field.sendKeys("a");
		Thread.sleep(2000); 
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed(), true);		
		
		password_field.sendKeys("A");
		Thread.sleep(2000); 
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed(), true);		
		
		password_field.sendKeys("12456");
		Thread.sleep(2000); 
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed(), true);		
		
		password_field.clear();
		Thread.sleep(2000); 
		
		password_field.sendKeys("!#");
		Thread.sleep(2000); 
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed(), true);		
		
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
