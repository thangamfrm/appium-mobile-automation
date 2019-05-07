package com.thangamfrm.appiummobileauto;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AndroidSampleAppTest extends BaseAndroidAppiumTest {

	@BeforeClass
	public void setupAppium() {
		super.setupAppium("sample.apk", "com.afollestad.materialdialogssample", 
				"https://github.com/afollestad/material-dialogs/raw/master/sample/sample.apk");
	
		// try this instead of installing and uninstalling the app - each time
		//capabilities.setCapability("appPackage", "com.afollestad.materialdialogssample");
	}
	
	@Test(enabled = true)
	public void testSimpleButtonAndDialog() throws InterruptedException {
		String btnShowDialogId = appPackageName + ":id/basic_buttons";
		assertTrue(isElementPresent(btnShowDialogId), "Button to display dialog (Agree/Dismiss) NOT found!");
		driver.findElementById(btnShowDialogId).click();
		String txtMsgId = appPackageName + ":id/md_text_message";
		assertTrue(isElementPresent(txtMsgId), "Dialog Text NOT found!");
		String popupTitle = driver.findElementById(txtMsgId).getText();
		assertEquals(popupTitle, "Let Google help apps determine location. This means sending anonymous location data to Google, "
				+ "even when no apps are running.", "Popup Title didn't match!!!");
		// assert the text here --
		String btnAgreeId = appPackageName + ":id/md_button_positive";
		assertTrue(isElementPresent(btnAgreeId), "Agree Button NOT found!");
		driver.findElementById(btnAgreeId).click();
		assertFalse(isElementPresent(btnAgreeId), "Agree Button still Displayed!!!");
		
		String btnBasicLongId = appPackageName + ":id/basic_long";
		assertTrue(isElementPresent(btnBasicLongId), "Button Basic Long NOT found!");
	}

	@AfterClass
	public void tearDown() {
		super.tearDown();
	}

}
