package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
	
	public RegistrationPage(RemoteWebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h1[text()='Registration']")
	public WebElement headerText;
	
	@FindBy(id="name")
	public WebElement inputName;
	
	@FindBy(id="email")
	public WebElement inputEmail;
	
	@FindBy(id="url")
	public WebElement inputUrl;
	
	@FindBy(id="jobInterests")
	public WebElement textareaInterests;
	
	@FindBy(id="password")
	public WebElement inputPassword;
	
	@FindBy(id="confirmPassword")
	public WebElement inputConfirmPassword;

	@FindBy(xpath="//button[text()='Register']")
	public WebElement buttonRegister;
	
	@FindBy(xpath="//p[text()='You must provide a valid email address']")
	public WebElement emailValidationErrorMessage;
	
	@FindBy(xpath="//p[text()='You must provide at least one job interest.']")
	public WebElement interestsValidationErrorMessage;
	
	@FindBy(xpath="//p[text()='Your password must be longer than 8 characters']")
	public WebElement passwordLengthValidationErrorMessage;
	
	@FindBy(xpath="//p[text()='Your passwords did not match']")
	public WebElement passwordsMatchValidationErrorMessage;
	
}
