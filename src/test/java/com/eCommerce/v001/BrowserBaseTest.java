package com.eCommerce.v001;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

public class BrowserBaseTest {

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
				
				//Appium client code --> Appium Server --> UiAutomator2 -->Mobile device
				UiAutomator2Options option = new UiAutomator2Options();
				option.setDeviceName("SamsulEmulator");
				option.setChromedriverExecutable("C:\\Users\\samsu\\eclipse-workspace\\Appium\\Drivers\\chromedriver.exe");
				option.setCapability("browserName", "Chrome");
				
				//Android driver
				driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), option);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
	}
	

	@AfterClass
	public void tearDown() {
		
		driver.quit();
		service.stop();
	}
	
	
	//Explicit Wait
	public void explicitlyWait(WebElement element) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void explicitlyWait(WebElement element, String attribute, String value) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.attributeContains(element, attribute, value));
	}
	
	public double getFormattedAmt(String amt, int substringIndexStartFrom) {
		double formattedAmount = Double.parseDouble(amt.substring(substringIndexStartFrom));
		return formattedAmount;
	}
}
