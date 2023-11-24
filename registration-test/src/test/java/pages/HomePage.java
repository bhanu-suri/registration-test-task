package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	public HomePage(RemoteWebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='jumbotron']//h1")
	public WebElement home_headerText;
	
	@FindBy(xpath="//div[@class='jumbotron']//p[1]")
	public WebElement home_paraText;
	
	@FindBy(xpath="//a[text()='Register']")
	public WebElement home_linkRegister;
}
