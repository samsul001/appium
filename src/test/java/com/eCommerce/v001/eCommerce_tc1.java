package com.eCommerce.v001;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class eCommerce_tc1 extends BaseTest{
	

	//Filling initial form of General store app
	@Test
	public void fillForm() {
	
		
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("TestUSer");
		driver.hideKeyboard(); //To hide keyboard if it is opening
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale")).click();
		
		driver.findElement(AppiumBy.id("android:id/text1")).click();
		scrollToFindElement("Austria");		
		//driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Austria\"));"));
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Austria']")).click();
		
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
	}
}
