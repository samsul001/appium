package com.eCommerce.v001;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class eCommerce_tc6_hybridApp extends BaseTest{

	//HybridApp - Hybrid App is an Android/iOS app which having scope of web browser (Mix of Native and web app is called Hybrid App)
	//NativeApp - Native App is an Android/iOS app which does not have scope of web browser
	@Test
	public void navToChromeBroserfromNativeApp() throws InterruptedException {
		
		driver.findElement(AppiumBy.id("android:id/text1")).click();
		scrollToFindElement("Cape Verde");
		//WebElement ele = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Cape Verde']"));
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Cape Verde']")).click();
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Sams");
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
		driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
		
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		try {
		WebElement cartEle = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title"));		
		explicitlyWait(cartEle, "text", "Cart");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		List<WebElement> productPrices = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productPrice"));
		int count = productPrices.size();
		double totalSum = 0;
		for(int i=0;i<count;i++) {
			String priceAmount = productPrices.get(i).getText();
			double price = getFormattedAmt(priceAmount, 1);
			totalSum = totalSum+price;
		}
		
		String displaySum = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
		double displayFormatted=getFormattedAmt(displaySum, 1);
		
		Assert.assertEquals(totalSum, displayFormatted);
		
		WebElement termsEle = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/termsButton"));
		longpressAction(termsEle);
		driver.findElement(AppiumBy.id("android:id/button1")).click();
		
		driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnProceed")).click();
		Thread.sleep(8000);
		
		//Hybrid app --> After the click, it is directed to google web browser from Native app.
		//Now driver deals elements in google web browser
		//driver context shoul be switched from Native to Web app by the following commands
			//driver.getContextHandles(); <-- it will get all the context handles(Native, webview) and stored in Set collection variable
			//driver.context("WebView"); <--it will switch driver context to web app from Native app
			//driver.context("NativeApp"); <--it will switch driver context to Native app from web app
			//Chrome driver .exe path should be configured to UiAutomator2 option objects. Then only, driver will work in google web app after switching context
		
		Set<String> contexts = driver.getContextHandles();
		for(String contextName: contexts) {
			System.out.println(contextName);
		}
		
		driver.context("WEBVIEW_com.androidsample.generalstore");
		driver.findElement(By.name("q")).sendKeys("Rahul Shetty Academy");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		Thread.sleep(4000);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.context("NATIVE_APP");
		
		
		
	}
}
