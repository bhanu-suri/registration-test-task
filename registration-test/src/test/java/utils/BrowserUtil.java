package utils;

import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserUtil {
	
	private static final int IMPLICIT_WAIT = 5;
	private static final int PAGE_LOAD_WAIT = 10;
	
	public static RemoteWebDriver getDriver() throws IllegalArgumentException {
		
		String browser = System.getProperty("browser");
		System.out.println("******Browser passed from mvn cmd-line is: " + browser);
		
		RemoteWebDriver driver = null;
		if(browser == null) {
			System.out.println("Browser cmd-line argument is null. Defaulting driver to Chrome.");
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		else {
			throw new IllegalArgumentException("Invalid browser value: "+browser);
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_WAIT));
		
		return driver;
	}

}
