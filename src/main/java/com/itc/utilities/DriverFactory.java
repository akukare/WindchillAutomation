package com.itc.utilities;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

//This class ensures each thread gets into its own WebDriver instance to enable thread-safe parallel execution
public class DriverFactory {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	/**
	 * @name getDriver
	 * @description Returns the WebDriver instance associated with the current
	 *              thread
	 * @author Rengesh
	 * @return WebDriver
	 */
	public static WebDriver getDriver() {
		if (driver == null) {
            throw new IllegalStateException("Driver not initialized.");
        }
		return driver.get();
	}

	/**
	 * @name setDriver
	 * @description Sets the WebDriver instance for the current thread
	 * @author Rengesh
	 * @return void
	 */
	public static void setDriver(WebDriver driverInstance) {
		driver.set(driverInstance);
	}

	/**
	 * @name quitDriver
	 * @description Quits the WebDriver and removes it from ThreadLocal to prevent
	 *              memory leaks
	 * @author Rengesh
	 * @return void
	 */
	public static void quitDriver() {
		if (driver.get() != null) {
			driver.get().quit();
			driver.remove();
		}
	}

	/**
	 * @name initDriver
	 * @description Initializes the WebDriver instance based on the specified
	 *              browser and os name Supported browsers: Chrome, Edge, Firefox,
	 *              Safari
	 * @param browser
	 * @author Rengesh
	 * @return void
	 */
	public static void initDriver(String browser) {
		String os = System.getProperty("os.name").toLowerCase();
		String driverPath = System.getProperty("user.dir") + File.separator + "drivers";

		try {
			switch (browser.toLowerCase()) {
			case "chrome":
			    WebDriverManager.chromedriver().setup(); 
			    ChromeOptions chromeOptions = new ChromeOptions();
			    chromeOptions.addArguments("--remote-allow-origins=*");
			    driver.set(new ChromeDriver(chromeOptions));
			    break;

			case "edge":
				setDriverPath(os, "edge", driverPath);
				driver.set(new EdgeDriver());
				break;

			case "firefox":
				setDriverPath(os, "firefox", driverPath);
				driver.set(new FirefoxDriver());
				break;

			case "safari":
				if (!os.contains("mac")) {
					throw new UnsupportedOperationException("Safari is only supported on macOS.");
				}
				driver.set(new SafariDriver());
				break;

			default:
				throw new IllegalArgumentException("Unsupported browser: " + browser);
			}

			getDriver().manage().window().maximize();
			System.out.println("Browser launched successfully: " + browser);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to initialize browser", e);
		}
	}

	/**
	 * @name setDriverPath
	 * @description Sets the system property for the appropriate WebDriver
	 *              executable path based on the browser and the operating system
	 * @param os
	 * @param browser
	 * @param basePath
	 * @author Rengesh
	 * @return void
	 */
	private static void setDriverPath(String os, String browser, String basePath) {
		String driverName;

		if (browser.equalsIgnoreCase("safari"))
			return;

		switch (browser.toLowerCase()) {
		case "chrome":
			driverName = os.contains("win") ? "chromedriver.exe" : "chromedriver";
			break;
		case "firefox":
			driverName = os.contains("win") ? "geckodriver.exe" : "geckodriver";
			break;
		case "edge":
			driverName = os.contains("win") ? "msedgedriver.exe" : "msedgedriver";
			break;
		default:
			throw new IllegalArgumentException("Invalid browser for setting driver path: " + browser);
		}

		String driverPath = basePath + File.separator + browser + File.separator + driverName;
		System.setProperty("webdriver." + browser + ".driver", driverPath);
		System.out.println("Set " + browser + " driver path: " + driverPath);
	}
}