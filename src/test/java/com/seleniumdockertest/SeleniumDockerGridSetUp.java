package com.seleniumdockertest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

public class SeleniumDockerGridSetUp {
	
	
	@BeforeTest
	public void start() throws IOException, InterruptedException {
		String[] cmd = { "/bin/sh", "-c", "cd /var/lib/jenkins/workspace/Git_Project_Checkout_Job; ls -l;docker-compose up" };
		Process p =Runtime.getRuntime().exec(cmd);
	     p.waitFor(60, TimeUnit.SECONDS);
	}

	
	/*
	 * @AfterTest public void stop() throws IOException, InterruptedException {
	 * String[] cmd = { "/bin/sh",
	 * "-c","cd /var/lib/jenkins/workspace/Git_Project_Checkout_Job; ls -l;docker-compose down"
	 * }; Process p=Runtime.getRuntime().exec(cmd); p.waitFor(20, TimeUnit.SECONDS);
	 * }
	 */
}
