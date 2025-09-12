package com.itc.utilities;

import com.aventstack.extentreports.Status;
import com.itc.base.BaseTest;

public class LogUtil extends BaseTest {

    /**
     * Log info step to console and ExtentReport
     * @param message Step description
     */
    public static void info(String message) {
        System.out.println("[INFO] " + message);
        ExtentTestManager.getTest().log(Status.INFO, message);
    }
}