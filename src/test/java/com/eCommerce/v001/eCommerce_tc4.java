package com.eCommerce.v001;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class eCommerce_tc4 extends BaseTest{
	
	@Test
	public void validatingTotalSumOfProdsInCart() {
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
		
		WebElement cartEle = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title"));		
		explicitlyWait(cartEle, "text", "Cart");
		
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
	}

}
