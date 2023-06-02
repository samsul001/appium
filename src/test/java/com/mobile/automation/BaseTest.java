package com.mobile.automation;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {
	
	public AppiumDriverLocalService service;
	public AndroidDriver driver;
	
	@BeforeClass
	public void configureAppium() throws MalformedURLException {
		//Start Appium Server Programmatically
				//main.js file is respensible to start Appium server 
				//main.js file location --> C:\Users\samsu\AppData\Roaming\npm\node_modules\appium\build\lib\main.js
				//IP address(127.0.0.1) and port number #4723 should be mentioned to start appium server at the concern IP address & Port
				service= new AppiumServiceBuilder()
						.withAppiumJS(new File("C://Users//samsu//AppData//Roaming//npm//node_modules//appium//build//lib//main.js"))
						.withIPAddress("127.0.0.1").usingPort(4723).build();
				service.start();
				
				
	}
	
	@BeforeMethod
	public void startApp() throws MalformedURLException {
		//Appium client code --> Appium Server --> UiAutomator2 -->Mobile device
		UiAutomator2Options option = new UiAutomator2Options();
		option.setDeviceName("SamsulEmulator");
		option.setApp("C://Users//samsu//eclipse-workspace//Appium//src//test//java//resources//ApiDemos-debug.apk");
		
		//Android driver
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), option);
	}
	
	@AfterMethod
	public void returnToHome() {
		driver.quit();
	}
	
	/*
	 * mobile: longClickGesture
		This gesture performs long click action on the given element/coordinates. Available since Appium v1.19
		
		Supported arguments
		elementId: The id of the element to be clicked. If the element is missing then both click offset coordinates must be provided. If both the element id and offset are provided then the coordinates are parsed as relative offsets from the top left corner of the element.
		x: The x-offset coordinate
		y: The y-offset coordinate
		duration: Click duration in milliseconds. 500 by default. The value must not be negative

	((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
		    "elementId", ((RemoteWebElement) element).getId()
		));
	*/
	public void longpressAction(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", 
				ImmutableMap.of("elementId", ((RemoteWebElement)ele).getId(), "duration" ,2000));
	}
	
	public void scrollToFindElement(String elementText) {
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+elementText+"\"));"));
	  //driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Austria\"));"));
	}
	
	public void scrollTillEnd() {
		boolean canScrollMore;
		do {
		canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
			    "left", 100, "top", 100, "width", 200, "height", 200,
			    "direction", "down",
			    "percent", 3.0
			));
		
		}while(canScrollMore);
	}
	
	public void swipeAction(WebElement element, String direction) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement)element).getId(),
			    "direction", direction,
			    "percent", 0.75
			));
	}
	
	public void dragAndDropTo(WebElement element, int xAxis , int yAxis) {
		driver.executeScript("mobile: dragGesture" , ImmutableMap.of(
				 "elementId" , ((RemoteWebElement)element).getId(),
				 "endX",xAxis,
				 "endY",yAxis
		 ));
	}
	
	
	
	@AfterClass
	public void tearDown() {
		
		service.stop();
	}

}
