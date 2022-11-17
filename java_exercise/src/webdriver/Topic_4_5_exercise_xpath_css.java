package webdriver;


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


public class Topic_4_5_exercise_xpath_css {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath+"\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver", projectPath+"\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}

	@Test
	public void TC_01_Register_With_Empty_Data() {
		// Locate Submit button element
		WebElement btnSubmit = driver.findElement(By.xpath("//button[@type='submit']"));
		btnSubmit.click();
		
		// Locate empty error messages of first name, email, confirm email, password, confirm password and phone
		WebElement lblFirstNameError = driver.findElement(By.id("txtFirstname-error"));
		WebElement lblEmailError = driver.findElement(By.id("txtEmail-error"));
		WebElement lblConfirmEmailError = driver.findElement(By.id("txtCEmail-error"));
		WebElement lblPasswordError = driver.findElement(By.id("txtPassword-error"));
		WebElement lblConfirmPasswordError = driver.findElement(By.id("txtCPassword-error"));
		WebElement lblPhoneError = driver.findElement(By.id("txtPhone-error"));
		
		// Error message when fields are empty
		String msgEmptyFirstName = "Vui lòng nhập họ tên";
		String msgEmptyEmail = "Vui lòng nhập email";
		String msgEmptyConfirmEmail = "Vui lòng nhập lại địa chỉ email";
		String msgEmptyPassword = "Vui lòng nhập mật khẩu";
		String msgEmptyConfirmPassword = "Vui lòng nhập lại mật khẩu";
		String msgEmptyPhone = "Vui lòng nhập số điện thoại.";
		
		// Assert the error message displaying at GUI is matching with expectations
		Assert.assertEquals(lblFirstNameError.getText(), msgEmptyFirstName);
		Assert.assertEquals(lblEmailError.getText(), msgEmptyEmail);
		Assert.assertEquals(lblConfirmEmailError.getText(), msgEmptyConfirmEmail);
		Assert.assertEquals(lblPasswordError.getText(), msgEmptyPassword);
		Assert.assertEquals(lblConfirmPasswordError.getText(), msgEmptyConfirmPassword);
		Assert.assertEquals(lblPhoneError.getText(), msgEmptyPhone);
	}

	@Test
	public void TC_02_Register_With_Invalid_Email() {
		// Locate first name, email, confirm email, password, confirm password and phone fields
		WebElement txtFirstName = driver.findElement(By.id("txtFirstname"));
		WebElement txtEmail = driver.findElement(By.id("txtEmail"));
		WebElement txtCEmail = driver.findElement(By.id("txtCEmail"));
		WebElement txtPassword = driver.findElement(By.id("txtPassword"));
		WebElement txtCPassword = driver.findElement(By.id("txtCPassword"));
		WebElement txtPhone = driver.findElement(By.id("txtPhone"));
		
		//Input valid information - except email and confirm email	
		input_text(txtFirstName,"Alex Huynh");
		input_text(txtEmail,"abc@abc@");
		input_text(txtCEmail,"abc@abc@");
		input_text(txtPassword,"1234@Abcd");
		input_text(txtCPassword,"1234@Abcd");
		input_text(txtPhone,"0904343245");
		
		// Locate Submit button element
		WebElement btnSubmit = driver.findElement(By.xpath("//button[@type='submit']"));
		btnSubmit.click();
		
		// Locate label of invalid email and invalid confirm email
		WebElement lblInvalidEmail = driver.findElement(By.id("txtEmail-error"));
		WebElement lblInvalidConfirmEmail = driver.findElement(By.id("txtCEmail-error"));
		
		// Error message when Email is invalid
		String msgInvalidEmail = "Vui lòng nhập email hợp lệ";
		String msgInvalidConfirmEmail = "Email nhập lại không đúng";
		
		// Verify the error message displayed at GUI is matching with expectation
		Assert.assertEquals(lblInvalidEmail.getText(), msgInvalidEmail);
		Assert.assertEquals(lblInvalidConfirmEmail.getText(), msgInvalidConfirmEmail);
	}

	@Test
	public void TC_03_Register_With_Incorrect_Confirm_Email() {
		// Locate first name, email, confirm email, password, confirm password and phone fields
		WebElement txtFirstName = driver.findElement(By.id("txtFirstname"));
		WebElement txtEmail = driver.findElement(By.id("txtEmail"));
		WebElement txtCEmail = driver.findElement(By.id("txtCEmail"));
		WebElement txtPassword = driver.findElement(By.id("txtPassword"));
		WebElement txtCPassword = driver.findElement(By.id("txtCPassword"));
		WebElement txtPhone = driver.findElement(By.id("txtPhone"));
		
		//Input valid information - except confirm email
		input_text(txtFirstName,"Alex Huynh");
		input_text(txtEmail,"abc@abc");
		input_text(txtCEmail,"abc@abc@");
		input_text(txtPassword,"1234@Abcd");
		input_text(txtCPassword,"1234@Abcd");
		input_text(txtPhone,"0904343245");
		
		// Locate Submit button element
		WebElement btnSubmit = driver.findElement(By.xpath("//button[@type='submit']"));
		btnSubmit.click();
		
		// Locate label of invalid confirm email
		WebElement lblInvalidConfirmEmail = driver.findElement(By.id("txtCEmail-error"));
		
		// Error message when confirm email is invalid
		String msgInvalidConfirmEmail = "Email nhập lại không đúng";
		
		// Verify the error message displayed at GUI is matching with expectation
		Assert.assertEquals(lblInvalidConfirmEmail.getText(), msgInvalidConfirmEmail);
	}
	
	@Test
	public void TC_04_Register_With_Password_Less_Than_6_Characters() {
		// Locate first name, email, confirm email, password, confirm password and phone fields
		WebElement txtFirstName = driver.findElement(By.id("txtFirstname"));
		WebElement txtEmail = driver.findElement(By.id("txtEmail"));
		WebElement txtCEmail = driver.findElement(By.id("txtCEmail"));
		WebElement txtPassword = driver.findElement(By.id("txtPassword"));
		WebElement txtCPassword = driver.findElement(By.id("txtCPassword"));
		WebElement txtPhone = driver.findElement(By.id("txtPhone"));
		
		//Input valid information - except password and confirm password

		input_text(txtFirstName,"Alex Huynh");
		input_text(txtEmail,"abc@abc");
		input_text(txtCEmail,"abc@abc");
		input_text(txtPassword,"1234");
		input_text(txtCPassword,"1234");
		input_text(txtPhone,"0904343245");
		
		// Locate Submit button element
		WebElement btnSubmit = driver.findElement(By.xpath("//button[@type='submit']"));
		btnSubmit.click();
		
		// Locate label of invalid password and confirm password
		WebElement lblInvalidPassword = driver.findElement(By.id("txtPassword-error"));
		WebElement lblInvalidConfirmPassword = driver.findElement(By.id("txtCPassword-error"));
		
		// Error message when password and confirm password are invalid
		String msgInvalidPassword = "Mật khẩu phải có ít nhất 6 ký tự";
		String msgInvalidConfirmPassword = "Mật khẩu phải có ít nhất 6 ký tự";
		
		
		// Verify the error message displayed at GUI is matching with expectation
		Assert.assertEquals(lblInvalidPassword.getText(), msgInvalidPassword);
		Assert.assertEquals(lblInvalidConfirmPassword.getText(), msgInvalidConfirmPassword);
	}
	@Test
	public void TC_05_Register_With_Incorrect_Confirm_Password() {
		// Locate first name, email, confirm email, password, confirm password and phone fields
		WebElement txtFirstName = driver.findElement(By.id("txtFirstname"));
		WebElement txtEmail = driver.findElement(By.id("txtEmail"));
		WebElement txtCEmail = driver.findElement(By.id("txtCEmail"));
		WebElement txtPassword = driver.findElement(By.id("txtPassword"));
		WebElement txtCPassword = driver.findElement(By.id("txtCPassword"));
		WebElement txtPhone = driver.findElement(By.id("txtPhone"));
		
		//Input valid information - except confirm password

		input_text(txtFirstName,"Alex Huynh");
		input_text(txtEmail,"abc@abc");
		input_text(txtCEmail,"abc@abc");
		input_text(txtPassword,"1234@abcd");
		input_text(txtCPassword,"1234@ab");
		input_text(txtPhone,"0904343245");
		
		// Locate Submit button element
		WebElement btnSubmit = driver.findElement(By.xpath("//button[@type='submit']"));
		btnSubmit.click();
		
		// Locate label of invalid confirm password
		WebElement lblInvalidConfirmPassword = driver.findElement(By.id("txtCPassword-error"));
		
		// Error message when confirm password is invalid
		String msgInvalidConfirmPassword = "Mật khẩu bạn nhập không khớp";
		
		
		// Verify the error message displayed at GUI is matching with expectation
		Assert.assertEquals(lblInvalidConfirmPassword.getText(), msgInvalidConfirmPassword);
	}
	
	@Test
	public void TC_06_Register_With_Incorrect_Phone_Number() {
		// Locate first name, email, confirm email, password, confirm password and phone fields
		WebElement txtFirstName = driver.findElement(By.id("txtFirstname"));
		WebElement txtEmail = driver.findElement(By.id("txtEmail"));
		WebElement txtCEmail = driver.findElement(By.id("txtCEmail"));
		WebElement txtPassword = driver.findElement(By.id("txtPassword"));
		WebElement txtCPassword = driver.findElement(By.id("txtCPassword"));
		WebElement txtPhone = driver.findElement(By.id("txtPhone"));
		
		//Input valid information - except phone

		input_text(txtFirstName,"Alex Huynh");
		input_text(txtEmail,"abc@abc");
		input_text(txtCEmail,"abc@abc");
		input_text(txtPassword,"1234@abcd");
		input_text(txtCPassword,"1234@abcd");
		input_text(txtPhone,"0904343");
		
		// Locate Submit button element
		WebElement btnSubmit = driver.findElement(By.xpath("//button[@type='submit']"));
		btnSubmit.click();
		
		// Locate label of invalid phone
		WebElement lblInvalidPhone = driver.findElement(By.id("txtPhone-error"));
		
		// Error message when phone is invalid
		String msgInvalidPhone = "Số điện thoại phải từ 10-11 số.";
		
		
		// Verify the error message displayed at GUI is matching with expectation
		Assert.assertEquals(lblInvalidPhone.getText(), msgInvalidPhone);
		
		// Input invalid phone provider
		input_text(txtPhone,"1234343");
		
		// Error message when phone provider is invalid
		String msgInvalidPhoneProvider = "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08";
		
		// Verify the error message displayed at GUI is matching with expectation
		Assert.assertEquals(lblInvalidPhone.getText(), msgInvalidPhoneProvider);
		
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
