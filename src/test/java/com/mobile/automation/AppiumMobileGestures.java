package com.mobile.automation;

import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class AppiumMobileGestures extends BaseTest{
	
	

	//@Test
	public void longPressGesture() throws InterruptedException {
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='Views']")).click();
		driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
		driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
		WebElement ele = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='People Names']"));

		longpressAction(ele);
		
		WebElement txtSampleMenu = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Sample menu']"));
		
		Assert.assertEquals(txtSampleMenu.getText(), "Sample menu");
		
		Assert.assertTrue(txtSampleMenu.isDisplayed());
		Thread.sleep(2000);
	}
	
	//Scroll gesture by using UiAutomator
	//androidUIAutomator locator helps to scroll and find the element on the flow.
	@Test
	public void scrollDownGesture() {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		//driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView3\"));"));
		scrollToFindElement("WebView3");
	}	
	
	
	//Scroll entire page by using Javascript events	
	 //@Test 
	 public void scrollDownToTillEnd() {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		scrollTillEnd();
	 }
	 
	 //Swife gesture
	 //@Test
	 public void swipeGesture() {
		 driver.findElement(AppiumBy.accessibilityId("Views")).click();
		 driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
		 driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();
		 WebElement firstImg = driver.findElement(AppiumBy.xpath("//android.widget.ImageView[1]"));
		 Assert.assertEquals(firstImg.getAttribute("focusable"), "true");
		 
		 swipeAction(firstImg, "left");
		 
		 Assert.assertEquals(firstImg.getAttribute("focusable"), "false");
		 
	 }
	 
	 //Drag and Drop gesture
	 //@Test
	 public void dragAndDropGesture() {
		 driver.findElement(AppiumBy.accessibilityId("Views")).click();
		 driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
		 
		 WebElement source = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_1"));
		 
		 dragAndDropTo(source, 825, 714);
		 
		 String dropResult = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_result_text")).getText();
		 Assert.assertEquals(dropResult, "Dropped!");
	 }
	 
	 //Miscellanous Appium Actions
	 //@Test
	 public void miscellanous() {
		 driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		 driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click();
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
