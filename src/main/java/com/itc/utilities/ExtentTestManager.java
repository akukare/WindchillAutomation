package com.itc.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.itc.base.BaseTest;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager extends BaseTest {

    private static ExtentReports extent = ExtentManager.getInstance();

    // Map for storing ExtentTest instances with Thread ID key (to support parallel runs)
    private static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }

    public static synchronized ExtentTest createTest(String testName, String description) {
        ExtentTest test = extent.createTest(testName, description);
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }
}
