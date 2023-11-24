package step.definition;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.RegistrationPage;
import pages.DashboardPage;
import utils.BrowserUtil;

public class Steps {
	
	RemoteWebDriver driver;
	
	@After
	public void afterScenario() {
		driver.quit();
	}
	
	@Given("user opens the browser")
	public void openBrowser() throws IllegalArgumentException {
	    driver = BrowserUtil.getDriver();
	}

	@When("user navigates to the Home page")
	public void navigateToHomePage() {
	    driver.navigate().to("file:///C:/OpenAthens/registration-test/index.html");
	}
	
	@Then("check Home page is displayed with introductory text")
	public void verifyHomePageDisplayed() {
		String title = driver.getTitle();
		Assert.assertEquals(title, "Registration Test"); //bug - page title of “Home page” as per Requirements
		
		HomePage obj = new HomePage(driver);
		String h1Text = obj.home_headerText.getText();
		Assert.assertEquals(h1Text, "Registration Test");
		String paraText = obj.home_paraText.getText();
		Assert.assertEquals(paraText, "This is an example registration and dashboard page proof of concept. Good luck!");
	}
	
	@When("user clicks on 'Register' link")
	public void clicksRegisterLink() {
		HomePage obj = new HomePage(driver);
		obj.home_linkRegister.click();
	}
	
	@Then("check Registration page is displayed")
	public void checkRegistrationPageIsDisplayed() {
		String title = driver.getTitle();
		Assert.assertEquals(title, "Registration Test");
		
		RegistrationPage obj = new RegistrationPage(driver);
		boolean flag = obj.headerText.isDisplayed();
		Assert.assertEquals(flag, true, "Check Registration page header is displayed!");
	}
	
	/*
	 * Enter Registration form details using data from DataTable as:
	 * | Name | Email | Web address | Interests | Password | Confirm password |
	 */
	@When("user enter Registration form details as:")
	public void enterRegistrationFormDetails(DataTable table) {
		List<String> data = table.row(0);
		String name = data.get(0);
		String email = data.get(1);
		String webAddress = data.get(2);
		String interests = data.get(3);
		String password = data.get(4);
		String confirmPassword = data.get(5);
		
		RegistrationPage obj = new RegistrationPage(driver);
		if(name != null) {
			obj.inputName.sendKeys(name);
		}
		if(email != null) {
			obj.inputEmail.sendKeys(email);
		}
		if(webAddress != null) {
			obj.inputUrl.sendKeys(webAddress);
		}
		if(interests != null) {
			driver.switchTo().frame("iframe");
			
			String[] strArray = interests.split(",");
			for(String str : strArray) {
				obj.textareaInterests.sendKeys(str);
				obj.textareaInterests.sendKeys(Keys.ENTER);
			}
			driver.switchTo().parentFrame();
		}
		if(password != null) {
			obj.inputPassword.sendKeys(password);
		}
		if(confirmPassword != null) {
			obj.inputConfirmPassword.sendKeys(confirmPassword);
		}
	}
	
	@When("user clicks on Register button")
	public void clickRegisterButton() {
		RegistrationPage obj = new RegistrationPage(driver);
		obj.buttonRegister.click();
	}
	
	@Then("check Dashboard page is displayed")
	public void checkDashboardIsDisplayed() {
		String title = driver.getTitle();
		Assert.assertEquals(title, "Dasboard"); //typo in page title
		
		DashboardPage obj = new DashboardPage(driver);
		boolean flag = obj.headerText.isDisplayed();
		Assert.assertEquals(flag, true, "Check Dashboard page header is displayed!");
	}
	
	@Then("verify Dashboard shows success alert {string} and welcome message {string}")
	public void checkDashboardSuccessAlertAndWelcomeMessage(String alert, String message) {
		DashboardPage obj = new DashboardPage(driver);
		SoftAssert sa = new SoftAssert();
		
		sa.assertEquals(obj.alertSuccess.getText(), alert, "Success alert didn't match!");
		sa.assertEquals(obj.welcomeMessage.getText(), message, "Welcome message didn't match!");
		sa.assertAll();
	}
	
	@Then("verify Dashboard table shows a list of {int} jobs")
	public void verifyDashboardTableShowsJobs(int noOfJobs) {
		List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));
		Assert.assertEquals(rows.size(), noOfJobs, "Dashboard jobs table has incorrect no. of rows!");
	}
	
	@When("user enters {string} in Email field")
	public void entersTextIntoEmailField(String email) {
		RegistrationPage obj = new RegistrationPage(driver);
		obj.inputEmail.clear();
		obj.inputEmail.sendKeys(email);
	}
	
	@When("user enters {string} in Password field")
	public void entersTextIntoPasswordField(String pwd) {
		RegistrationPage obj = new RegistrationPage(driver);
		obj.inputPassword.clear();
		obj.inputPassword.sendKeys(pwd);
	}
	
	@When("user enters {string} in Confirm password field")
	public void entersTextInConfirmPasswordField(String pwd) {
		RegistrationPage obj = new RegistrationPage(driver);
		obj.inputConfirmPassword.clear();
		obj.inputConfirmPassword.sendKeys(pwd);
	}
	
	@Then("check email validation error message is displayed")
	public void checkEmailValidationErrorMessageDisplayed() {
		RegistrationPage obj = new RegistrationPage(driver);
		boolean flag = obj.emailValidationErrorMessage.isDisplayed();
		Assert.assertEquals(flag, true, "Email validation error message is not displayed!");
	}
	
	@Then("check email validation error message is not present")
	public void checkEmailValidationErrorMessageIsNotPresent() {
		List<WebElement> list = driver.findElements(By.xpath("//p[text()='You must provide a valid email address']"));
		Assert.assertEquals(list.size(), 0, "Email validation error message is present!");
	}

	@Then("check password length validation error message is displayed")
	public void checkPasswordLengthValidationErrorMessageDisplayed() {
		RegistrationPage obj = new RegistrationPage(driver);
		boolean flag = obj.passwordLengthValidationErrorMessage.isDisplayed();
		Assert.assertEquals(flag, true, "Password length validation error message is not displayed!");
	}
	
	@Then("check password length validation error message is not present")
	public void checkPasswordLengthValidationErrorMessageIsNotPresent() {
		List<WebElement> list = driver.findElements(By.xpath("//p[text()='Your password must be longer than 8 characters']"));
		Assert.assertEquals(list.size(), 0, "Password length validation error message is present!");
	}
	
	@Then("check passwords match validation error message is displayed")
	public void checkPasswordsMatchValidationErrorMessageIsDisplayed() {
		RegistrationPage obj = new RegistrationPage(driver);
		boolean flag = obj.passwordsMatchValidationErrorMessage.isDisplayed();
		Assert.assertEquals(flag, true, "Passwords Match validation error message is not displayed!");
	}
	
	@Then("check passwords match validation error message is not present")
	public void checkPasswordsMatchValidationErrorMessageIsNotPresent() {
		List<WebElement> list = driver.findElements(By.xpath("//p[text()='Your passwords did not match']"));
		Assert.assertEquals(list.size(), 0, "Passwords match validation error message is present!");
	}

}
