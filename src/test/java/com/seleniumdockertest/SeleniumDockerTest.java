package com.seleniumdockertest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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

public class SeleniumDockerTest {
	
	public static WebDriver driver;
	
	@BeforeClass
	public void start() throws IOException, InterruptedException {
		String[] cmd = { "/bin/sh", "-c", "cd /var/lib/jenkins/workspace/Git_Project_Checkout_Job; ls -l;docker-compose up" };
		Process p =Runtime.getRuntime().exec(cmd);
	     p.waitFor(60, TimeUnit.SECONDS);
	}

	
	@BeforeMethod
	@Parameters("browser")
	public void setUp(String browser) throws MalformedURLException {
		
		if(browser.equals("chrome")) {
			WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
			DesiredCapabilities capability = new DesiredCapabilities();
			capability.setCapability("browserName", "chrome");
			driver = new RemoteWebDriver(new URL("http://13.235.48.246:4444/wd/hub"), capability);
			
	}
		else if(browser.equals("firefox")) {
			WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
			DesiredCapabilities capability = new DesiredCapabilities();
			capability.setCapability("browserName", "firefox");
			driver = new RemoteWebDriver(new URL("http://13.235.48.246:4444/wd/hub"), capability);
		}
		
		driver.manage().window().maximize();
		driver.get("https://www.freshworks.com/");

}
	
	@Test
	public void getTitleTest() {
		
		String title =driver.getTitle();
		Assert.assertEquals(title, "A fresh approach to customer engagement");
		System.out.println("title: "+title);
	}
	
	@Test
	public void getFooterLinkTest() {
		List<WebElement>footerLinks =driver.findElements(By.xpath("//ul[@class='footer-nav']/li/a"));
		for (WebElement links : footerLinks) {
			System.out.println(links.getText());
		}
		Assert.assertEquals(footerLinks.size(), 29);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
	/*
	 * @AfterClass public void stop() throws IOException, InterruptedException {
	 * String[] cmd = { "/bin/sh",
	 * "-c","cd /var/lib/jenkins/workspace/Git_Project_Checkout_Job; ls -l;docker-compose down"
	 * }; Process p=Runtime.getRuntime().exec(cmd); p.waitFor(20, TimeUnit.SECONDS);
	 * }
	 */
}
