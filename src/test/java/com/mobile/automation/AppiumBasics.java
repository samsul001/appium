//Types of Element locators of Android app elements
// xpath, acccesibilityId , id, className, AndroidUIAutomator
package com.mobile.automation;

import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumBy;


public class AppiumBasics extends BaseTest {
	
	

	@Test
	public void wifiSettingsName() throws MalformedURLException {
				
		//Actual Automation
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
		driver.findElement(AppiumBy.id("android:id/checkbox")).click();
		driver.findElement(AppiumBy.xpath("(//android.widget.RelativeLayout)[2]")).click();
		
		String alertTitle = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
		Assert.assertEquals(alertTitle, "WiFi settings");
		
		driver.findElement(AppiumBy.id("android:id/edit")).sendKeys("Samsu");
		driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
	}
}
