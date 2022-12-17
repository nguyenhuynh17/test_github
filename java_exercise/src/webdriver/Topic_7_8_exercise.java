package webdriver;


import static org.testng.Assert.assertTrue;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

public class Topic_7_8_exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	
	String email_str = "auto_" + timestamp.getTime() + "@gm.com";
	String usr_name = "mngr463260";
	String pass = "ezysabe";

	
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
	public void TC_01_Handle_textbox_textarea() throws InterruptedException {

		driver.get("https://demo.guru99.com/v4");
		
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(usr_name);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pass);
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		Assert.assertEquals("Guru99 Bank Manager HomePage", driver.getTitle().trim());
		driver.findElement(By.xpath("//a[contains(text(),'New Customer')]")).click();
		
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys("automation");
		driver.findElement(By.cssSelector("input#dob")).sendKeys("01011990");
		driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys("automation address");
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys("automation city");
		driver.findElement(By.xpath("//input[@name='state']")).sendKeys("automation state");
		driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys("0123456789");
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email_str);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("autopassword");
		
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		
		Thread.sleep(10000);
		
		String cust_id = driver.findElement(By.xpath("//table[@id='customer']//td[contains(text(),'Customer ID')]/following-sibling::td")).getText();
		
		Assert.assertEquals("automation", driver.findElement(By.xpath("//table[@id='customer']//td[contains(text(),'Customer Name')]/following-sibling::td")).getText());
		Assert.assertEquals("automation address", driver.findElement(By.xpath("//table[@id='customer']//td[contains(text(),'Address')]/following-sibling::td")).getText());
		Assert.assertEquals("automation city", driver.findElement(By.xpath("//table[@id='customer']//td[contains(text(),'City')]/following-sibling::td")).getText());
		Assert.assertEquals("automation state", driver.findElement(By.xpath("//table[@id='customer']//td[contains(text(),'State')]/following-sibling::td")).getText());
		Assert.assertEquals("123456", driver.findElement(By.xpath("//table[@id='customer']//td[contains(text(),'Pin')]/following-sibling::td")).getText());
		Assert.assertEquals("0123456789", driver.findElement(By.xpath("//table[@id='customer']//td[contains(text(),'Mobile No.')]/following-sibling::td")).getText());
		Assert.assertEquals(email_str, driver.findElement(By.xpath("//table[@id='customer']//td[contains(text(),'Email')]/following-sibling::td")).getText());
	
		driver.findElement(By.xpath("//a[contains(text(),'Edit Customer')]")).click();
		Thread.sleep(10000);
		
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(cust_id);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
		
		Thread.sleep(5000);
		
		Assert.assertEquals("automation", driver.findElement(By.xpath("//input[@name='name']")).getAttribute("value"));
		Assert.assertEquals("automation address", driver.findElement(By.xpath("//textarea[@name='addr']")).getText());
		
		input_text(driver.findElement(By.xpath("//textarea[@name='addr']")),"edit address");
		input_text(driver.findElement(By.xpath("//input[@name='city']")),"edit city");
		input_text(driver.findElement(By.xpath("//input[@name='state']")),"edit city");
		input_text(driver.findElement(By.xpath("//input[@name='pinno']")),"987654");
		input_text(driver.findElement(By.xpath("//input[@name='telephoneno']")),"9876543210");
		
		driver.findElement(By.xpath("//input[@name='sub']")).click();
		
		Thread.sleep(10000);
		
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(cust_id);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//textarea[@name='addr']")).getText(), "edit address");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='city']")).getAttribute("value"),"edit city");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='state']")).getAttribute("value"),"edit city");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='pinno']")).getAttribute("value"),"987654");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='telephoneno']")).getAttribute("value"),"9876543210");
		
		
	}
	
	@Test
	public void TC_02_Handle_textbox_textarea() throws InterruptedException {

		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.xpath("//a[@href='/web/index.php/pim/viewPimModule']")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Add Employee')]")).click();
		driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys("autofirstname");
		driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys("autolastname");
		driver.findElement(By.xpath("//label[contains(text(),'Employee Id')]/parent::div/following-sibling::div/input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		driver.findElement(By.xpath("//label[contains(text(),'Employee Id')]/parent::div/following-sibling::div/input")).sendKeys(Keys.chord(Keys.DELETE));
		
//		Thread.sleep(10000);
		int random4digits = (int)(Math.random()*9000)+1000;
		String user_name = "testauto_"+ ""+random4digits;
        String user_id = ""+random4digits;
		System.out.println(user_id);
		driver.findElement(By.xpath("//label[contains(text(),'Employee Id')]/parent::div/following-sibling::div/input")).sendKeys(user_id);
		driver.findElement(By.xpath("//p[contains(.,'Create Login Details')]/following-sibling::div")).click();
		driver.findElement(By.xpath("//label[contains(text(),'Username')]/parent::div/following-sibling::div/input")).sendKeys(user_name);
		
		driver.findElement(By.xpath("(//input[@type='password'])[1]")).sendKeys("tEst12!345");
		driver.findElement(By.xpath("(//input[@type='password'])[2]")).sendKeys("tEst12!345");
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		Thread.sleep(10000);
		
		driver.findElement(By.xpath("//a[contains(text(),'Employee List')]")).click();
		driver.findElement(By.xpath("//label[contains(.,'Employee Id')]/parent::div/following-sibling::div/input")).sendKeys(user_id);
		Thread.sleep(6000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//div[@role='row'])[2]")).click();
		Thread.sleep(6000);
		
		Assert.assertEquals("autofirstname", driver.findElement(By.xpath("//input[@name='firstName']")).getAttribute("value"));
		Assert.assertEquals("autolastname", driver.findElement(By.xpath("//input[@name='lastName']")).getAttribute("value"));
		Assert.assertEquals(user_id, driver.findElement(By.xpath("//label[contains(.,'Employee Id')]/parent::div/following-sibling::div/input")).getAttribute("value"));
		
		driver.findElement(By.xpath("//a[contains(.,'Immigration')]")).click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//h6[contains(.,'Assigned Immigration Records')]/following-sibling::button[@type='button']")).click();
		driver.findElement(By.xpath("//label[contains(.,'Number')]/parent::div/following-sibling::div/input")).sendKeys("123456789");
		driver.findElement(By.xpath("//textarea[@placeholder='Type Comments here']")).sendKeys("texting");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		Thread.sleep(6000);
		driver.findElement(By.xpath("//button[@type='button']/i[@class='oxd-icon bi-pencil-fill']")).click();
		Thread.sleep(6000);
		Assert.assertEquals("123456789", driver.findElement(By.xpath("//label[contains(.,'Number')]/parent::div/following-sibling::div/input")).getAttribute("value"));
		Assert.assertEquals("texting", driver.findElement(By.xpath("//textarea[@placeholder='Type Comments here']")).getAttribute("value"));
		
		driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']")).click();
		driver.findElement(By.xpath("//a[contains(.,'Logout')]")).click();
		
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(user_name);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("tEst12!345");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.xpath("//span[contains(.,'My Info')]")).click();
		Thread.sleep(6000);
		
		Assert.assertEquals("autofirstname", driver.findElement(By.xpath("//input[@name='firstName']")).getAttribute("value"));
		Assert.assertEquals("autolastname", driver.findElement(By.xpath("//input[@name='lastName']")).getAttribute("value"));
		Assert.assertEquals(user_id, driver.findElement(By.xpath("//label[contains(.,'Employee Id')]/parent::div/following-sibling::div/input")).getAttribute("value"));
		
		driver.findElement(By.xpath("//a[contains(.,'Immigration')]")).click();
		Thread.sleep(6000);
		
		driver.findElement(By.xpath("//button[@type='button']/i[@class='oxd-icon bi-pencil-fill']")).click();
		Thread.sleep(10000);
		Assert.assertEquals("123456789", driver.findElement(By.xpath("//label[contains(.,'Number')]/parent::div/following-sibling::div/input")).getAttribute("value"));
		Assert.assertEquals("texting", driver.findElement(By.xpath("//textarea[@placeholder='Type Comments here']")).getAttribute("value"));
		
		
		
	}
	
	@Test
	public void TC_03_Handle_dropdown_list() throws InterruptedException {

		driver.get("https://demo.nopcommerce.com/");
		
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		
		driver.findElement(By.cssSelector("input#gender-male")).click();
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("automation");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("lastname");
		
		Select select_date = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		Select select_month = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		Select select_year = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		
		select_date.selectByValue("1");
		select_month.selectByValue("5");
		select_year.selectByValue("1980");
		
		driver.findElement(By.cssSelector("input#Email")).sendKeys(email_str);
		driver.findElement(By.cssSelector("input#Password")).sendKeys("12345678");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("12345678");
		driver.findElement(By.cssSelector("button#register-button")).click();
		
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//div[contains(text(),'Your registration completed')]")).isDisplayed();
		driver.findElement(By.xpath("//a[@class='button-1 register-continue-button']")).click();
	
		driver.findElement(By.xpath("//a[@class='ico-account']")).click();
		
		Assert.assertEquals("1", driver.findElement(By.xpath("//select[@name='DateOfBirthDay']/option[@selected]")).getText());
		Assert.assertEquals("May", driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']/option[@selected]")).getText());
		Assert.assertEquals("1980", driver.findElement(By.xpath("//select[@name='DateOfBirthYear']/option[@selected]")).getText());
	
	}
	
	@Test
	public void TC_04_Handle_dropdown_list_2() throws InterruptedException {

		driver.get("https://rode.com/en/support/where-to-buy");
		
		Select select_country = new Select(driver.findElement(By.xpath("//select[@id='country']")));

		select_country.selectByValue("Vietnam");
		
		driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
		Thread.sleep(5000);
		List<WebElement> list_dealer = driver.findElements(By.xpath("//h3[contains(text(),'Dealers')]/following-sibling::div/div"));
		for(WebElement item:list_dealer) {
			String s = item.findElement(By.cssSelector("h4")).getText();
			System.out.println(s);
		}
	}
	
	@Test
	public void TC_06_Handle_custom_dropdown_list() throws InterruptedException {

		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
		driver.findElement(By.cssSelector("span#speed-button")).click();
		driver.findElement(By.xpath("//div[contains(text(),'Slower')]")).click();
		Assert.assertEquals(true, driver.findElement(By.xpath("//span[contains(text(),'Slower')]")).isDisplayed());
		
		driver.findElement(By.cssSelector("span#speed-button")).click();
		driver.findElement(By.xpath("//div[contains(text(),'Medium')]")).click();
		Assert.assertEquals(true, driver.findElement(By.xpath("//span[contains(text(),'Medium')]")).isDisplayed());
		
		driver.findElement(By.cssSelector("span#speed-button")).click();
		driver.findElement(By.xpath("//div[contains(text(),'Faster')]")).click();
		Assert.assertEquals(true, driver.findElement(By.xpath("//span[contains(text(),'Faster')]")).isDisplayed());

		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		driver.findElement(By.xpath("//div[@role='listbox']")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Christian')]")).click();
		Assert.assertEquals(true, driver.findElement(By.xpath("//div[contains(text(),'Christian')]")).isDisplayed());
		
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		driver.findElement(By.xpath("//div[@class='btn-group']")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Second Option')]")).click();
		Assert.assertEquals(true, driver.findElement(By.xpath("//li[contains(text(),'Second Option')]")).isDisplayed());
		
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("argentina");
		driver.findElement(By.xpath("//span[contains(text(),'Argentina')]")).click();
		Assert.assertEquals(true, driver.findElement(By.xpath("//div[contains(text(),'Argentina')]")).isDisplayed());
		
		driver.get("https://www.honda.com.vn/o-to/du-toan-chi-phi");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector("button#selectize-input")));
		Thread.sleep(3000);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("button#selectize-input")));
		Thread.sleep(3000);
		//		driver.findElement(By.cssSelector("button#selectize-input")).click();
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//a[@title='CITY L']")));

		driver.findElement(By.xpath("//a[@title='CITY L']")).click();
		Select select_province = new Select(driver.findElement(By.cssSelector("select#province")));
		select_province.selectByVisibleText("Cần Thơ");
		
		Select select_fee = new Select(driver.findElement(By.cssSelector("select#registration_fee")));
		select_fee.selectByVisibleText("Khu vực II");
		
		driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
		driver.findElement(By.xpath("(//button[@class='ms-choice'])[1]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'February')]/preceding-sibling::input")).click();
		Assert.assertEquals("February", driver.findElement(By.xpath("(//button[@class='ms-choice'])[1]/span")).getText());
		driver.findElement(By.xpath("//span[contains(text(),'March')]/preceding-sibling::input")).click();
		Assert.assertEquals("February, March", driver.findElement(By.xpath("(//button[@class='ms-choice'])[1]/span")).getText());
		driver.findElement(By.xpath("//span[contains(text(),'April')]/preceding-sibling::input")).click();
		driver.findElement(By.xpath("//span[contains(text(),'May')]/preceding-sibling::input")).click();
		Assert.assertEquals("4 of 12 selected", driver.findElement(By.xpath("(//button[@class='ms-choice'])[1]/span")).getText());
		driver.findElement(By.xpath("(//span[contains(text(),'[Select all]')])[1]/preceding-sibling::input")).click();
		Assert.assertEquals("All selected", driver.findElement(By.xpath("(//button[@class='ms-choice'])[1]/span")).getText());
		

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
