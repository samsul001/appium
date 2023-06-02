package com.eCommerce.v001;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class eCommerce_tc3 extends BaseTest{
	
	@Test
	public void addProductToCart() throws InterruptedException {
		
		driver.findElement(AppiumBy.id("android:id/text1")).click();
		scrollToFindElement("Bahrain");
		WebElement bahrain = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Bahrain']"));
		explicitlyWait(bahrain);
		bahrain.click();
		
		driver.setClipboardText("TestUser");
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys(driver.getClipboardText());
		driver.hideKeyboard();
		
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		scrollToFindElement("Jordan 6 Rings");
		
		int productCount = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName")).size();
		
		for(int i=0; i<productCount ; i++) {
			
			String productName = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName")).get(i).getText();
			
			if(productName.equalsIgnoreCase("Jordan 6 Rings")) {
				driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
			}
		}
		
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		
		WebElement cartElement = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Cart']"));
		explicitlyWait(cartElement, "text", "Cart");
		
		String lastPageProduct = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/productName")).getAttribute("text");
		Assert.assertEquals(lastPageProduct, "Jordan 6 Rings");
		
	}

}
