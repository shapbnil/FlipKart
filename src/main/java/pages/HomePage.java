package pages;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import utility.Common;

public class HomePage extends Common {
	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	By menuHeader = By.xpath("//*[@class='_114Zhd']/li");
	
	public By login_Close = By.xpath("//*[@class='_2AkmmA _29YdH8']");

	public By mouse_hover = By.xpath("//*[@class='_1QZ6fC _3Lgyp8']");

	public By samsung_click = By.linkText("Samsung");

	public By footerMenu = By.cssSelector("._1DXv5h");
	
	public By search=By.name("q");
	By searchEnter=By.xpath("//button[@type='submit']");
	
	By apple=By.xpath("//*[text()='Apple iPhone SE (Black, 64 GB)']");
	
	By add_cart=By.cssSelector("._2AkmmA._2Npkh4._2MWPVK");
	
	By place_order= By.xpath("//*[text()='Place Order']");

	// 1. Test case ( validate menu header in the home page)
	public void validateMenuHeaders(List<String> menuNames) {
		List<WebElement> headers = driver.findElements(menuHeader);

		for (int i = 0; i < menuNames.size(); i++) {
			if (headers.get(i).getText().equals(menuNames.get(i))) {
				System.out.println("Successfully match header " + menuNames.get(i));
			} else {
				System.out.println("Failure to match header " + menuNames.get(i));
				Assert.fail("Failure to match header " + menuNames.get(i));

			}
		}

	}
		// 3. Test Case(validate mousehover functionality on menu header)
	public void mousehove() throws InterruptedException {
		Actions action = new Actions(driver);
		WebElement elem = driver.findElement(mouse_hover);
		action.moveToElement(elem);
		action.build().perform();

		Thread.sleep(3000);

		// Click on Samsung

		clickObj(driver, samsung_click);

	}

//3. validate footer on home page
	public void footerLink(List<String> footersMenu) {
		WebElement footers = driver.findElement(footerMenu);
		System.out.println(footers.getText());

		for (int i = 0; i < footersMenu.size(); i++) {
			if (footers.getText().contains(footersMenu.get(i))) {
				System.out.println("Successfully match header " + footersMenu.get(i));
			} else {
				Assert.fail("Failure to match footer " + footersMenu.get(i));
			}

		}
	}
	// 4. Validate Search Functionality in home page
	
	public void searchValidation() {
		
		enterText(driver, search, "iphone" );
		clickObj(driver, searchEnter);
		
	}
	//validate iPhone functionality
	public void appleIphone() throws InterruptedException {
		String parent=driver.getWindowHandle();
		String child=null;
		clickObj(driver, apple);
		
		Set<String> windows=driver.getWindowHandles();
		for(String window:windows) {
			if(!window.equals(parent)) {
				child=window;
			}
		}
		driver.switchTo().window(child);
		Thread.sleep(3000);
		clickObj(driver, add_cart);
		
		Thread.sleep(3000);
		String title=driver.getTitle();
		System.out.println(title);
		String expected_title="Shopping Cart | Flipkart.com";
		Assert.assertEquals(expected_title, title);
		
		clickObj(driver, place_order);
		
		Thread.sleep(3000);
		// SwithTo Parent Window
		driver.switchTo().window(parent);
		driver.findElement(search).clear();
		enterText(driver, search, "sumsung");
		Thread.sleep(2000);
		driver.findElement(search).sendKeys(Keys.ENTER);
		
	}

}
