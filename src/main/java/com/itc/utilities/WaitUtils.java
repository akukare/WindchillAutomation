package com.itc.utilities;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.itc.base.BaseTest;

public class WaitUtils extends BaseTest{

	private LogUtil log;

	public WaitUtils() {
		this.log = new LogUtil();
	}

	public static WebElement waitForElementVisible(By locator, int timeoutInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (TimeoutException e) {
			throw new RuntimeException("Element not visible within " + timeoutInSeconds + " sec: " + locator, e);
		}
	}

	public WebElement waitForElementClickable(By locator, int timeoutInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
			return wait.until(ExpectedConditions.elementToBeClickable(locator));
		} catch (TimeoutException e) {
			throw new RuntimeException("Element not clickable within " + timeoutInSeconds + " sec: " + locator, e);
		}
	}

	public Boolean waitForElementInvisble(By locator, int timeoutInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
			return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		} catch (TimeoutException e) {
			throw new RuntimeException("Element not invisible within " + timeoutInSeconds + " sec: " + locator, e);
		}
	}

	public Boolean waitForURLContains(String url, int timeoutInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
			return wait.until(ExpectedConditions.urlContains(url));
		} catch (TimeoutException e) {
			throw new RuntimeException("URL did not match even after " + timeoutInSeconds + " sec: " + url, e);
		}
	}

	public Boolean waitForURLToBe(String url, int timeoutInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
			return wait.until(ExpectedConditions.urlToBe(url));
		} catch (TimeoutException e) {
			throw new RuntimeException("URL did not match even after " + timeoutInSeconds + " sec: " + url, e);
		}
	}

	public boolean isElementPresent(By locator, int timeoutInSeconds) {
		try {
			WebElement element = waitForElementVisible(locator, timeoutInSeconds);
			return element != null;
		} catch (TimeoutException | NoSuchElementException e) {
			return false;
		}
	}
	
	public static void waitForSeconds(int seconds) {
		if (seconds <= 0)
			return;
		CountDownLatch latch = new CountDownLatch(1);
		try {
			latch.await(seconds, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException("Interrupted while waiting with latch", e);
		}
	}
	
	public  WebElement waitUntilVisible(By locator, int maxWaitTime) {
		try {
			Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(maxWaitTime))
					.pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class)
					.ignoring(StaleElementReferenceException.class);

			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (TimeoutException e) {
			throw new RuntimeException("Element not clickable within " + maxWaitTime + " sec: " + locator, e);
		}
	}
	
	/**
	 * Checks if a browser alert is present.
	 *
	 * @return true if an alert is present, false otherwise
	 */
	public static boolean isAlertPresent() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
			wait.until(ExpectedConditions.alertIsPresent());
			return true;
		} catch (TimeoutException e) {
			throw new RuntimeException("Alert not Present: " + e);
		}
	}

}