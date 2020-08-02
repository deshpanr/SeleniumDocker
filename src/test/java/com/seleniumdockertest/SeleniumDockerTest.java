package com.seleniumdockertest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumDockerTest {
	
	public WebDriver driver;
	
	@BeforeMethod
	@Parameters("browser")
	public void setUp(String browser) throws MalformedURLException {
		
		if(browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			DesiredCapabilities capability = new DesiredCapabilities();
			capability.setCapability("browserName", "chrome");
			driver = new RemoteWebDriver(new URL("http://13.127.37.148:4444/wd/hub"), capability);
			
	}
		else if(browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			DesiredCapabilities capability = new DesiredCapabilities();
			capability.setCapability("browserName", "firefox");
			driver = new RemoteWebDriver(new URL("http://13.127.37.148:4444/wd/hub"), capability);
		}
		
		driver.manage().window().maximize();
		driver.get("https://www.freshworks.com/");

}
	
	@Test
	public void getTitleTest() {
		
		String title =driver.getTitle();
		Assert.assertEquals(title, "A fresh approach to customer engagement");
	}
	
	@Test
	public void getFooterLinkTest() {
		List<WebElement>footerLinks =driver.findElements(By.xpath("//ul[@class='footer-nav']/li/a"));
		for (WebElement links : footerLinks) {
			System.out.println(links.getText());
		}
		Assert.assertEquals(footerLinks.size(), 30);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
