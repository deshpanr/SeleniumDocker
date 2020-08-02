package com.seleniumdockertest;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class SetUpSeleniumGrid {
	
	@BeforeTest
	public void start() throws IOException, InterruptedException {
		String[] cmd = { "/bin/sh", "-c", "cd /var/lib/jenkins/workspace/Git_Project_Checkout_Job; ls -l;docker-compose up" };
		Runtime.getRuntime().exec(cmd);
	     Thread.sleep(15000);
	}
	
	@AfterTest
	public void stop() throws IOException, InterruptedException {
		String[] cmd = { "/bin/sh", "-c", "cd /var/lib/jenkins/workspace/Git_Project_Checkout_Job; ls -l;docker-compose down" };
		Runtime.getRuntime().exec(cmd);
	     Thread.sleep(15000);
	}

}
