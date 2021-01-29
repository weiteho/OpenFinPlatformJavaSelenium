package com.openfin.demo;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class OpenFinPlatformSelenium {

	/**
	 * target a window by title
	 * 
	 * @param webDriver
	 *            instance of WebDriver
	 * @param windowTitle
	 *            title to match
	 * @return true if successful
	 * @throws Exception
	 */
	private static boolean switchWindow(WebDriver webDriver, String windowTitle) throws Exception {
		boolean found = false;
		long start = System.currentTimeMillis();
		while (!found) {
			for (String name : webDriver.getWindowHandles()) {
				try {
					System.out.println("found window name: " + name);
					webDriver.switchTo().window(name);
					System.out.println("found window title: " + webDriver.getTitle());
					System.out.println("found window url: " + webDriver.getCurrentUrl());
					if (webDriver.getTitle().equals(windowTitle)) {
						found = true;
						break;
					}
				}
				catch (NoSuchWindowException wexp) {
					// some windows may get closed during Runtime startup
					// so may get this exception depending on timing
					System.out.println("Ignoring NoSuchWindowException " + name);
				}
			}
			Thread.sleep(1000);
			if ((System.currentTimeMillis() - start) > 5 * 1000) {
				break;
			}
		}

		if (!found) {
			System.out.println(windowTitle + " not found");
		}
		return found;
	}

	/**
	 * Sleep
	 * 
	 * @param secs
	 */
	private static void sleep(long secs) {
		System.out.println("Sleeping for " + secs + " seconds");
		try {
			Thread.sleep(secs * 1000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		String execPath = System.getProperty("ExecPath");
		String execArgs = System.getProperty("ExecArgs");
		String remoteDriverURL = System.getProperty("RemoteDriverURL");

		WebDriver driver;

		ChromeOptions options = new ChromeOptions();
		options.setBinary(execPath);
		options.addArguments(execArgs);
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		System.out.println("Creating remote driver " + remoteDriverURL);
		driver = new RemoteWebDriver(new URL(remoteDriverURL), capabilities);

		System.out.println("Got the driver " + driver.getCurrentUrl());
		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

		try {
			if (switchWindow(driver, "My Custom Frame")) {
				System.out.println("switched to custom frame");
				sleep(5);
				System.out.println("click on AddTab button");
				driver.findElement(By.id("addTab")).click();
			}
			sleep(5);
			if (switchWindow(driver, "My App")) {
				System.out.println("switched to My App Tab");
				sleep(5);
				System.out.println("click on ClickMe button");
				driver.findElement(By.id("clickMe")).click();
			}
			sleep(5);

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			driver.quit();
		}

	}

}
