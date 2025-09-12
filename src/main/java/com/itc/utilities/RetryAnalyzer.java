package com.itc.utilities;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Properties;

public class RetryAnalyzer implements IRetryAnalyzer, IAnnotationTransformer {

    private static int maxRetryCount = 1;
    private int retryCount = 0;

    static {
        String retryEnv = System.getenv("RETRY_MAX_COUNT");
        if (retryEnv != null && !retryEnv.isEmpty()) {
            try {
                maxRetryCount = Integer.parseInt(retryEnv.trim());
                System.out.println("Retry count from ENV: " + maxRetryCount);
            } catch (NumberFormatException e) {
                System.out.println("Invalid RETRY_MAX_COUNT env value. Falling back to properties.");
            }
        }

        if (retryEnv == null || retryEnv.isEmpty()) {
            try {
                File file = new File("src/test/resources/Config.properties");
                System.out.println("Absolute path: " + file.getAbsolutePath());
                System.out.println("Exists: " + file.exists());
                if (file.exists()) {
                    try (InputStream input = new FileInputStream(file)) {
                        Properties prop = new Properties();
                        prop.load(input);
                        String count = prop.getProperty("retry.max.count");
                        if (count != null) {
                            maxRetryCount = Integer.parseInt(count.trim());
                            System.out.println("Retry count from properties: " + maxRetryCount);
                        }
                        System.out.println("Loaded Config.properties successfully.");
                    }
                } else {
                    System.out.println("Config.properties not found at " + file.getAbsolutePath());
                }
            } catch (Exception e) {
                System.out.println("Error reading Config.properties: " + e.getMessage());
            }
        }

        System.out.println("Final retry count being used: " + maxRetryCount);
    }

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            System.out.println("Retrying " + result.getName() + " | Attempt: " + (retryCount + 1));
            retryCount++;
            return true;
        }
        return false;
    }

    @Override
    public void transform(ITestAnnotation annotation, Class testClass,
                          Constructor testConstructor, Method testMethod) {
        if (annotation.getRetryAnalyzerClass() == null) {
            annotation.setRetryAnalyzer(RetryAnalyzer.class);
        }
    }
}
