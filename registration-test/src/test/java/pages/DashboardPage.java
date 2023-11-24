package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
	
	public DashboardPage(RemoteWebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h1[text()='Dashboard']")
	public WebElement headerText;
	
	@FindBy(className="alert-success")
	public WebElement alertSuccess;
	
	@FindBy(xpath="//h2[@class='ng-binding']")
	public WebElement welcomeMessage;

}
