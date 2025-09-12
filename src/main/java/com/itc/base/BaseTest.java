 package com.itc.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.Base64;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.itc.page.actions.ReassignTasksPage;
import com.itc.utilities.ConfigReader;
import com.itc.utilities.DriverFactory;
import com.itc.utilities.LogUtil;
import com.itc.utilities.VideoRecorderUtility;
import com.itc.utilities.WaitUtils;
import com.itc.utilities.createBugInServiceNow;



public class BaseTest {
	public static Properties config;
	protected static WebDriver driver;
	protected boolean testFailed = false;
public static void initializeDriver() {
        config = ConfigReader.getProperties();

        String browser = config.getProperty("browser");
        long implicitWait = Long.parseLong(config.getProperty("implicit.wait"));

        DriverFactory.initDriver(browser);
        driver = DriverFactory.getDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
    }
	
	 public static void loginToWindchill(String username, String encryptedPassword) {
	        try {
	        	String url = config.getProperty("testsiteurl");
		        String userName = config.getProperty(username);
				String password = config.getProperty(encryptedPassword);
				String decryptedPassword = getDecodePassword(password);
		        String fullUrl = url.replace("https://", "https://" + userName + ":" + decryptedPassword + "@");
		        driver.get(fullUrl);
	        } catch (Exception e) {
	        	System.err.println(e.getMessage());
	        }

	        closeInitialPopup();
	    }
	 private static void closeInitialPopup() {
	        try {
	            WebElement popupCloseButton = driver.findElement(
	                By.xpath("//div[contains(@class, 'x-window-mc')]//div[contains(@class, 'x-tool-close')]")
	            );
	            popupCloseButton.click();
	        } catch (NoSuchElementException e) {
	        } catch (Exception e) {
	        }
	 }

	/**
	 * @name getDecodePassword
	 * @description Decodes the Base-64 encoded password string
	 * @param encodedPassword
	 * @author Rengesh
	 * @return password
	 */
	public static String getDecodePassword(String encodedPassword) {
		byte[] decodeBytes = Base64.getDecoder().decode(encodedPassword);
		return new String(decodeBytes);
	}
	
	public static int generateRandomNumber(int length) {
		if (length < 1) {
			throw new IllegalArgumentException("Length must be at least 1");
		}
		int minValue = (int) Math.pow(10, length - 1);
		int maxValue = (int) Math.pow(10, length) - 1;
		return new Random().nextInt(maxValue - minValue + 1) + minValue;
	}
	
    public static void switchToNewWindow() {
    	WaitUtils.waitForSeconds(3);
    	for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
    	WaitUtils.waitForSeconds(3);
        }
  
	
    public static void switchBack() {
        driver.switchTo().defaultContent();
    }
	
	public void switchToWindowByHeader(String headerText) {
		try {
			String originalWindow = driver.getWindowHandle();
			WaitUtils.waitForSeconds(2);
			for (String windowHandle : driver.getWindowHandles()) {
				driver.switchTo().window(windowHandle);
				WaitUtils.waitForSeconds(1);
				if (driver.getTitle().contains(headerText)) {
					return;
				}
				try {
					WebElement headerElement = driver
							.findElement(By.xpath("//*[contains(text(), '" + headerText + "')]"));
					if (headerElement.isDisplayed()) {
						return;
					}
				} catch (NoSuchElementException ignore) {
					System.err.println(ignore.getMessage());
				}
			}
			driver.switchTo().window(originalWindow);
			System.out.println("No matching window found for header: " + headerText);
		} catch (Exception e) {
			System.err.println("Window not switch to child: " + e.getMessage());
        }
	}
	
	public void switchToMainWindow(String parentWindowHandle) {
		try {
			WaitUtils.waitForSeconds(2);
			driver.switchTo().window(parentWindowHandle);
		} catch (Exception e) {
			System.err.println("Window not switch to parent: " + e.getMessage());
        }
	}
	
	public void refreshPage() {
		try {
			driver.navigate().refresh();
		} catch (Throwable t) {
			System.err.println("Page Refresh Error" + t.getMessage());
        }
	}
	
//	public static void sessionEnd() {
//
//			driver.manage().deleteAllCookies();
//			DriverFactory.quitDriver();
//		
//	}
	
	/**
	 * Performs alert actions based on the provided option.
	 *
	 * @param action The desired alert action: "accept", "dismiss", or "gettext".
	 * @return true if the action was successful; false if no alert was present or
	 *         action failed.
	 */
	public boolean performAlertAction(String action) {
		try {
			Alert alert = driver.switchTo().alert();
			switch (action.toLowerCase()) {
			case "accept":
				alert.accept();
				return true;
			case "dismiss":
				alert.dismiss();
				return true;
			case "gettext":
				LogUtil.info("Alert text: " + alert.getText());
				WaitUtils.waitForSeconds(2);
				return true;
			default:
				throw new IllegalArgumentException("Unsupported alert action: " + action);
			}
		} catch (NoAlertPresentException e) {
			createBugInServiceNow.create("Alert Not present", "No Alert Present: " + e.getMessage());
		} catch (Exception e) {
			createBugInServiceNow.create("Alert handling Error", "failed to handle alert: " + e.getMessage());
		}
		return false;
	}

	/**
	 * Highlights a WebElement with yellow styling using JavaScript.
	 *
	 * @param element Target WebElement to highlight
	 */
	public static void highlightElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='2px solid red';" + "arguments[0].style.backgroundColor='yellow';",
				element);
	}
	
	/**
	 * Waits until the specified element is visible, retrying periodically until timeout.
	 *
	 * @param locator      By locator of the target element.
	 * @param maxWaitTime  Maximum wait time in seconds.
	 * @return The visible WebElement if found; null otherwise.
	 */
	public WebElement waitUntilVisible(By locator, int maxWaitTime) {
	    try {
	        Wait<WebDriver> wait = new FluentWait<>(driver)
	                .withTimeout(Duration.ofSeconds(maxWaitTime))
	                .pollingEvery(Duration.ofMillis(500))
	                .ignoring(NoSuchElementException.class)
	                .ignoring(StaleElementReferenceException.class);

	        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	    } catch (TimeoutException e) {
	    	System.out.println(e.getMessage());
	        return null;
	    }
	}
	
	public static void SwitchtoReassigntasksofchange_window(String Name) 
	{
		System.out.println(driver.getTitle());
		ReassignTasksPage reassigntasks=new ReassignTasksPage();
		reassigntasks.gotoReassignto_dropdown();
		reassigntasks.Select_reassignto(Name);
 	}

	/**
	 * Blocks the current thread for the given number of seconds using CountDownLatch.
	 *
	 * @param seconds Number of seconds to wait. Must be ≥ 0.
	 */
	public static void waitForSeconds(int seconds) {
	    if (seconds <= 0) return;
	    CountDownLatch latch = new CountDownLatch(1);
	    try {
	        latch.await(seconds, TimeUnit.SECONDS);
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	        throw new RuntimeException("Interrupted while waiting with latch", e);
	    }
	}
	
	/**
	 * Clicks on the provided WebElement after asserting its visibility and highlights it in red.
	 *
	 * @param element WebElement to be clicked
	 */
	public static void click(WebElement element) {
	    try {
	        Assert.assertTrue(element.isDisplayed(), "Element should be visible on the page.");
	        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", element);
	        element.click();
	        //test.log(com.relevantcodes.extentreports.LogStatus.INFO, "Clicking on : " + element);
	    } catch (Exception e) {
	        createBugInServiceNow.create("WebElement Error", "Assertion failed or element not visible/clickable");
	    }
	}
	
	/**
	 * Waits for the specified WebElement to become visible within the given timeout.
	 *
	 * @param element            The WebElement to wait for.
	 * @param waitTimeInSeconds  Maximum time to wait in seconds before timing out.
	 *
	 * This method:
	 * - Initializes a WebDriverWait with the provided duration.
	 * - Waits until the element becomes visible on the page.
	 * - Logs an error message if the visibility check times out.
	 *
	 * Useful for synchronization to ensure the element is interactable before proceeding.
	 */

	public void waitForElementVisible(WebElement element, int waitTimeInSeconds) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSeconds));
	        wait.until(ExpectedConditions.visibilityOf(element));
	    } catch (TimeoutException e) {
	    	System.out.println("Element not visible after waiting " + waitTimeInSeconds + " seconds: " + element);
	    }
	}
    public static void type(WebElement element, String value) {
        try {
            element.sendKeys(value);
        } catch (Throwable t) {
            createBugInServiceNow.create("WebElement Error", "Type failed: " + t.getMessage());
           // screenshotOnFailure("TypeFailure");
            throw t;
        }
    }
	
	/**
	* Clears the value from the specified input field.
	* @name clear
	* @description Waits for visibility and clears the current content of the field.
	* @param element WebElement to be cleared.
	* @return void
	*/
	public void clear(WebElement element) {
	    try {
	        waitForElementVisible(element, 20);
	        element.clear();
	    } catch (Exception e) {
	    	System.out.println("Unable to clear input field: " + e.getMessage());
	    }
	}
	
	 

	    @AfterMethod
	    public void handleTestFailure(ITestResult result) {
	        if (result.getStatus() == ITestResult.FAILURE) {
	            testFailed = true;

	            LogUtil.info("Test Failed: " + result.getName());

	            // Stop video if needed
	            try {
	                VideoRecorderUtility.stopRecording();
	            } catch (Exception e) {
	                LogUtil.info("Failed to stop video recording: " + e.getMessage());
	            }

	            // Close the browser session
	            sessionEnd(); // This should quit the driver
	        }
	    }

	    protected void sessionEnd() {
	      	    driver.manage().deleteAllCookies();
				DriverFactory.quitDriver();
	        }
	    }

