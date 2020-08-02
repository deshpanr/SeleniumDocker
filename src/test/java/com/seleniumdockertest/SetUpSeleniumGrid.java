package com.seleniumdockertest;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class SetUpSeleniumGrid {
	
	@BeforeTest
	public void start() throws IOException, InterruptedException {
		
		Runtime.getRuntime().exec("start start_selenium_grid_container.sh");
	     Thread.sleep(15000);
	}
	
	@AfterTest
	public void stop() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("stop_selenium_grid_container.sh");
	     Thread.sleep(15000);
	}

}
