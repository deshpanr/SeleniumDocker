package com.seleniumdockertest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class SetUpSeleniumGrid {
	
	@BeforeTest
	public void start() throws IOException, InterruptedException {
		String[] cmd = { "/bin/sh", "-c", "cd /var/lib/jenkins/workspace/Git_Project_Checkout_Job; ls -l;docker-compose up" };
		Process p =Runtime.getRuntime().exec(cmd);
	     p.waitFor(15, TimeUnit.SECONDS);
	}
	
	/*
	 * @AfterTest public void stop() throws IOException, InterruptedException {
	 * String[] cmd = { "/bin/sh", "-c",
	 * "cd /var/lib/jenkins/workspace/Git_Project_Checkout_Job; ls -l;docker-compose down"
	 * }; Process p=Runtime.getRuntime().exec(cmd); p.waitFor(15, TimeUnit.SECONDS);
	 * }
	 */

}
