package com.seleniumdockertest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumDockerFirefoxTest {
	public static RemoteWebDriver driver;
	
	@BeforeClass
	public void setUp() throws MalformedURLException {
		
		WebDriverManager.firefoxdriver().setup();
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability("browserName", "firefox");
		driver = new RemoteWebDriver(new URL("http://52.66.247.213:4444/wd/hub"), capability);
		driver.manage().window().maximize();
		driver.get("https://www.freshworks.com/");

}
	
	@Test
	public void getTitleTest() {
		
		String title =driver.getTitle();
		Assert.assertEquals(title, "A fresh approach to customer engagement");
		System.out.println("title on Firefox browser: "+title);
	}
	
	@Test
	public void getFooterLinkTest() {
		List<WebElement>footerLinks =driver.findElements(By.xpath("//ul[@class='footer-nav']/li/a"));
		for (WebElement links : footerLinks) {
			System.out.println("Firefox Browser "+links.getText());
		}
		Assert.assertEquals(footerLinks.size(), 29);
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	
	  
}
