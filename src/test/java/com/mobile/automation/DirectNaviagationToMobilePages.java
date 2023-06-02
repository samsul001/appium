package com.mobile.automation;

import org.openqa.selenium.DeviceRotation;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

//By capturing App Package name and App Activity name, We can directly open ANY MOBILE PAGES without naviagting from home page.
//To capture App Package name and app activity page detail, Virtual device should be navigate to the Page and should execute below command:

//adb shell dumpsys window | find "mCurrentFocus"
	//mCurrentFocus=Window{4cc993 u0 io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies}
	//App Package Name = io.appium.android.apis
	//App Activity Name = io.appium.android.apis.preference.PreferenceDependencies
//this method of naviagtion is not recommended usually. If navigation steps were already tested many times, then this method can be applied.

public class DirectNaviagationToMobilePages extends BaseTest{

	@Test
	public void miscellanous() {
		
		 Activity activity = new Activity("io.appium.android.apis", "io.appium.android.apis.preference.PreferenceDependencies");
		 driver.startActivity(activity);
		 
		 driver.findElement(AppiumBy.id("android:id/checkbox")).click();
		 
		 //Rotating mobile screen to Landscape mode
		 DeviceRotation landScape = new DeviceRotation(0, 0, 90);
		 driver.rotate(landScape);
		 
		 driver.findElement(AppiumBy.xpath("(//android.widget.RelativeLayout)[2]")).click();
		 String alertTitle = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
		 Assert.assertEquals(alertTitle, "WiFi settings");
		 
		 //Copy text to clipBoard
		 driver.setClipboardText("Sam Test");
		 driver.findElement(AppiumBy.id("android:id/edit")).sendKeys(driver.getClipboardText());
		 
		 //Add mobile key events
		 driver.pressKey(new KeyEvent(AndroidKey.ENTER));
		 driver.findElement(AppiumBy.id("android:id/button1")).click();
		 
		 //Add mobile key events
		 driver.pressKey(new KeyEvent(AndroidKey.BACK));
		 driver.pressKey(new KeyEvent(AndroidKey.HOME));		 
	 }
	
}
