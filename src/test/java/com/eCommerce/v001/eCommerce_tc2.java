package com.eCommerce.v001;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class eCommerce_tc2 extends BaseTest{
	
	//User cannot directly locating locator Toast message during the inspection
	//Tagc//"android.widget.Toast" tag is used to cerate Toast messages in Android
	//By using the tag "//android.widget.Toast" and attribute "name" that holds the "value" which will be shown as Toast message.
	
	@Test
	public void validatingToastMessage() {

		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale")).click();
		
		driver.findElement(AppiumBy.id("android:id/text1")).click();
		scrollToFindElement("Austria");		
		//driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Austria\"));"));
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Austria']")).click();
		
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		String toastMsg = driver.findElement(AppiumBy.xpath("//(android.widget.Toast)[1]")).getAttribute("name");
		Assert.assertEquals(toastMsg, "Please enter your name");
	}

}
