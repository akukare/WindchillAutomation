package com.itc.Listeners;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.itc.base.BaseTest;
import com.itc.utilities.ExtentManager;
import com.itc.utilities.ExtentTestManager;
import com.itc.utilities.VideoRecorderUtility;

public class CustomListeners extends BaseTest implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        // Optional: any setup before suite starts
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Start test logging
        ExtentTestManager.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());

        // Start video recording
        try {
            VideoRecorderUtility.startRecording(result.getMethod().getMethodName());
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Video recording failed to start: " + e.getMessage());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().log(Status.PASS, "Test Passed");

        try {
            VideoRecorderUtility.stopRecording();
            VideoRecorderUtility.deleteRecording();
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to stop/delete video: " + e.getMessage());
        }
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
        ExtentTestManager.getTest().fail(result.getThrowable());

        // Attach screenshot
        try {
            String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            ExtentTestManager.getTest().fail("Screenshot:",
                MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to capture screenshot: " + e.getMessage());
        }

        // Stop video recording and attach downloadable video link using relative path
        try {
            File videoFile = VideoRecorderUtility.stopRecording();

            if (videoFile != null && videoFile.exists()) {
                // Define the target directory relative to the project root
                File targetDir = new File(System.getProperty("user.dir") + "/test-output/test-videos/");
                if (!targetDir.exists()) {
                    targetDir.mkdirs();
                }

                // Copy the video file to the target directory
                File targetFile = new File(targetDir, videoFile.getName());
                if (!videoFile.getAbsolutePath().equals(targetFile.getAbsolutePath())) {
                    java.nio.file.Files.copy(videoFile.toPath(), targetFile.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                }

                // Construct relative path from the Extent report HTML to the video file
                // Assuming your report is in 'test-output' folder, videos inside 'test-output/test-videos/'
                String relativeVideoPath = "test-videos/" + targetFile.getName();

                // Create clickable link to download or open the video in new tab
                String videoDownloadLink = "<a href='" + relativeVideoPath + "' target='_blank' download>Click to download video</a>";

                // Attach the link in the report
                ExtentTestManager.getTest().info("Video Recording: " + videoDownloadLink);
            }
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to attach video: " + e.getMessage());
        }
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");

        try {
            VideoRecorderUtility.stopRecording();
            VideoRecorderUtility.deleteRecording();
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to stop/delete video: " + e.getMessage());
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ExtentTestManager.getTest().log(Status.WARNING, "Test Partially Failed (within success %)");
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.getInstance().flush();
    }
}
