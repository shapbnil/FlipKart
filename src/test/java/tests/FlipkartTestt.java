package tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.map.HashedMap;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import pages.HomePage;
import utility.Common;

public class FlipkartTestt {

	//@Test
	public void validateMenuHeaders() throws InterruptedException {
		
		List<String> menuNames=new ArrayList<String>();
		menuNames.add("Electronics");
		menuNames.add("TVs & Appliances");
		menuNames.add("Men");
		menuNames.add("Women");
		menuNames.add("Baby & Kids");
		menuNames.add("Home & Furniture");
		menuNames.add("Sports, Books & More");
		menuNames.add("Flights");
		menuNames.add("Offer Zone");
		
		Common common=new Common();
		WebDriver driver =common.lunchBrowser();
		common.openUrl(driver);
		
		HomePage home=new HomePage(driver);
		common.clickObj(driver, home.login_Close);
		home.validateMenuHeaders(menuNames);
		home.mousehove();
		home.searchValidation();
		home.appleIphone();
		
		//home.tearDown(driver);
		
	}
	/*
	 * @Test public void footerValidation() { List<String> footersMenu=new
	 * ArrayList<String>();
	 * 
	 * footersMenu.add("Sell On Flipkart"); footersMenu.add("Advertise");
	 * footersMenu.add("Gift Cards"); footersMenu.add("Help Center");
	 * footersMenu.add("© 2007-2020 Flipkart.com");
	 * 
	 * Common common=new Common(); WebDriver driver =common.lunchBrowser();
	 * common.openUrl(driver);
	 * 
	 * HomePage home=new HomePage(driver); common.clickObj(driver,
	 * home.login_Close); home.footerLink(footersMenu); home.tearDown(driver);
	 * 
	 * }
	 * 
	 */
	@Test
	public void validateNameAndPriceOnYourcartScreen() throws InterruptedException {
		Common common=new Common();
		WebDriver driver =common.lunchBrowser();
		common.openUrl(driver);		

		HomePage home=new HomePage(driver);
		common.clickObj(driver, home.login_Close);
		
		driver.findElement(By.name("q")).sendKeys("samsung");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		
		
		//Samsung Metro 313 Dual Sim:       2,130
		//Samsung Galaxy M11 (Black, 32 GB) : 11,839
		
		HashedMap<String, String> mobiles=new HashedMap<String, String>();
		mobiles.put("Samsung Metro 313 Dual Sim", "2,130");
		mobiles.put("Samsung Galaxy M11 (Black, 32 GB)", "11,839");
		
			
		for (Map.Entry<String, String> mobileList : mobiles.entrySet()) { // What is line for?
			System.out.println(mobileList.getKey());
			System.out.println(mobileList.getValue());
			
			String mobileName =driver.findElement(By.xpath("(//div[@class='_3wU53n'])[1]")).getText();
			String mobilePrice =driver.findElement(By.xpath("(//div[@class='_6BWGkk'])[1]")).getText();
			
			
			String parent=driver.getWindowHandle();
			
			driver.findElement(By.xpath("(//div[@class='_3wU53n'])[1]")).click();
			
			
			String child=null;
			
			Set<String> windows=driver.getWindowHandles();
			for(String window:windows) {
				if(!window.equals(parent)) {
					child=window;
				}
			}
			driver.switchTo().window(child);
			
			Thread.sleep(2000);
			String mobileName_Menu= driver.findElement(By.xpath("//*[@class='_35KyD6']")).getText();
			String mobilePrice_Menu= driver.findElement(By.xpath("//*[@class='_1vC4OE _3qQ9m1']")).getText();
			
			
			if(mobileName_Menu.contains(mobileName) && mobilePrice.contains(mobilePrice_Menu)) {
				System.out.println("Successfully match mobile name price in menu page");
			}else {
				System.out.println("Failed to match mobile name and price in manu page");
				System.out.println("Actual "+ mobileName_Menu+"Expected "+mobileName);
				System.out.println("Actual "+ mobilePrice_Menu+"Expected "+mobilePrice);
			}
			
			driver.findElement(By.xpath("//*[text()='ADD TO CART']")).click();
			
			Thread.sleep(2000);
			String mobileName_Yourcart=driver.findElement(By.xpath("//*[@class='_325-ji _3ROAwx']")).getText();
			String mobilePrice_Yourcart= driver.findElement(By.xpath("//*[@class='pMSy0p XU9vZa']")).getText();
			
			if(mobileName_Yourcart.contains(mobileName) && mobilePrice.contains(mobilePrice_Yourcart)) {
				System.out.println("Successfully match mobile name price in yourcart page");
			}else {
				System.out.println("Failed to match mobile name and price in yourcart page");
				System.out.println("Actual "+ mobileName_Yourcart+"Expected "+mobileName);
				System.out.println("Actual "+ mobilePrice_Yourcart+"Expected "+mobilePrice);
			}
			
		}
		
		
	}
	
	public void demo() {
		
		ArrayList<String> ar=new ArrayList<String>();
		ar.add("10");
		ar.add("abc");
		ar.add(null);
		System.out.println(ar);
		
		for(int i=0;i<ar.size();i++) {
			System.out.println(ar.get(i));//10 abc  null
		}
		
		for(String a : ar) {
			System.out.println(a);
		}
		
		Iterator<String> i= ar.iterator();
		
		while(i.hasNext()){
			System.out.println(i.next());//10 abc null
		}
	}
}
