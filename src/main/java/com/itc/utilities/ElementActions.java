package com.itc.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.itc.base.BaseTest;


public class ElementActions extends BaseTest{

	private final static int defaultTimeout = 10;
	static WaitUtils waitUtils;

	public ElementActions() {
		waitUtils = new WaitUtils();
	}

	public void clear(By locator) {
		try {
			WebElement element = WaitUtils.waitForElementVisible(locator, defaultTimeout);
			((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", element);
			element.clear();
		} catch (Exception e) {
			System.err.println("Failed to clear field: " + locator + " → " + e.getMessage());
			throw e;
		}
	}


	public static void sendKeys(By locator, String value) {
		try {
			WebElement element = WaitUtils.waitForElementVisible(locator, defaultTimeout);
			((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", element);
			element.sendKeys(value);
		} catch (Exception e) {
			System.err.println("Failed to send keys to: " + locator + " → " + e.getMessage());
			throw e;
		}
	}
	
	public static void sendKeys(WebElement element, String value) {
		try {
			Assert.assertTrue(element.isDisplayed(), "Element not Displayed");
			((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", element);
			element.sendKeys(value);
		} catch (Exception e) {
			System.err.println("Failed to sendkeys: " + element + " → " + e.getMessage());
			throw e;
		}
	}

	public static void click(By locator) {
		try {
			WebElement element = waitUtils.waitForElementClickable(locator, defaultTimeout);
			((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", element);
			element.click();
		} catch (Exception e) {
			System.err.println("Failed to click element: " + locator + " → " + e.getMessage());
			throw e;
		}
	}
	
	public static void click(WebElement element) {
        try {
            Assert.assertTrue(element.isDisplayed(), "Element not visible");
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", element);
            element.click();
        } catch (Exception e) {
			System.err.println("Failed to click element: " + element + " → " + e.getMessage());
			throw e;
		}
        
    }

	public static String getText(By locator) {
		try {
			WebElement element = driver.findElement(locator);
			return element.getText().trim(); // Removes leading/trailing whitespace
		} catch (Exception e) {
			System.out.println("[getText] Error retrieving text: " + e.getMessage());
			throw e;
		}
	}

	public void scrollToElement(By locator) {
		try {
			WebElement element = driver.findElement(locator);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
			System.out.println("Scrolled to element: " + locator.toString());
		} catch (Exception e) {
			System.out.println("Error scrolling to element: " + e.getMessage());
		}
	}
}