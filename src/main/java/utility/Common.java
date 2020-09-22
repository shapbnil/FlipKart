package utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Common {
	WebDriver driver;

	public WebDriver lunchBrowser() {

		String browser = "chrome";

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", ".\\Drivers\\msedgedriver.exe");
			driver = new EdgeDriver();

		} else {
			System.setProperty("webdriver.gecko.driver", ".\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		return driver;
	}

	public void openUrl(WebDriver driver) {
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
	}

	public void clickObj(WebDriver driver, By by) {
		driver.findElement(by).click();
	}

	public void enterText(WebDriver driver, By by, String input) {
		driver.findElement(by).sendKeys(input);
	}
	
	public void tearDown(WebDriver driver) {
		driver.close();
	}

}