package com.thangamfrm.appiummobileauto;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public abstract class BaseAndroidAppiumTest extends Assert {

	protected String appPackageName;
	protected URL url;
	protected DesiredCapabilities capabilities;
	protected AndroidDriver<MobileElement> driver;

	public void setupAppium(String appFileName, String appPackageName, String downloadUrl) {
		
		this.appPackageName = appPackageName;
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(appFileName).getFile());
		assertTrue(file.exists(), "Android file: " + appFileName + " NOT found! "
				+ "Download: " + downloadUrl + " and place under: src/test/resources");
		
		final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
		try {
			url = new URL(URL_STRING);
		} catch (Exception e) {
			throw new RuntimeException("Unable to connect to Appium Server!!!");
		} 

		capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
		capabilities.setCapability(MobileCapabilityType.APP, file.getAbsolutePath());
		capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		
		// optional
		capabilities.setCapability("platformName", "Android");
		driver = new AndroidDriver<MobileElement>(url, capabilities);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.resetApp();
	}

	protected boolean isElementPresent(String id) {
		try {
			driver.findElementById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public void tearDown() {
		driver.removeApp(appPackageName);
		driver.quit();
	}

}
