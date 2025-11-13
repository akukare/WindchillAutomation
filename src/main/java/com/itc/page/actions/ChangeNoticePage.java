package com.itc.page.actions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.itc.base.BaseTest;

import com.itc.pagelocators.ChangeNoticeLocators;
import com.itc.utilities.ElementActions;
import com.itc.utilities.LogUtil;
import com.itc.utilities.WaitUtils;

public class ChangeNoticePage extends BaseTest {

	public ChangeNoticeLocators ChangeNotice;

	public ChangeNoticePage() {

		this.ChangeNotice = new ChangeNoticeLocators();

		PageFactory.initElements(driver, this.ChangeNotice);

	}

	public void enterChangeNoticeName(String Name) {
		type(ChangeNotice.Name, Name);
	}

	public void clickNextbtn() {
		WaitUtils.waitForSeconds(2);
		WaitUtils.waitUntilVisible(driver,ChangeNotice.Nextbtn,10).click();
	}

	public void clickFinish() {
		WaitUtils.waitForSeconds(2);
		WaitUtils.waitUntilVisible(driver,ChangeNotice.FinishButton,10).click();
	}

	public void clickSubmit() {
		WaitUtils.waitForSeconds(2);
	    WaitUtils.waitUntilVisible(driver,ChangeNotice.submittButton,10).click();
	}

	public void clickAddnumbertextbox() {
		click(ChangeNotice.AddNumbertextbox);
	}

	public void enternumber(String Number) {
		type(ChangeNotice.AddNumbertextbox, Number);
	}

	public void clearName() {
		clear(ChangeNotice.Name);
	}

	public void clickEditChangeTask() {
		click(ChangeNotice.EditChangeTask);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebElement iframeElement = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[contains(@id,'popCreateWizard')]")));
		driver.switchTo().frame(iframeElement);
		switchToNewWindow();
	}

	public void clearChangeTaskName() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebElement iframeElement = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//iframe[contains(@id,'popCreateWizard')]")));
		driver.switchTo().frame(iframeElement);
		clear(ChangeNotice.ChangeTaskName);
	}

	public void enterChangeTaskName(String Name) {
		type(ChangeNotice.ChangeTaskName, Name);
	}

	public void verifyChangeNoticeCreated() {
		try {
			waitForElementVisible(ChangeNotice.successMessage, 20);
			if (ChangeNotice.successMessage.isDisplayed()) {
				System.out.println("Change Notice creation confirmed.");
				Assert.assertTrue(true, "Change Notice creation successful.");
				click(ChangeNotice.successMessageLink);
				if (ChangeNotice.licenseAlertClose.isDisplayed()) {
					click(ChangeNotice.licenseAlertClose);
				}
			} else {
				LogUtil.info("Confirmation message not displayed.");
				Assert.fail("Change Notice creation confirmation message not displayed.");
			}
		} catch (Exception e) {
			LogUtil.info("Error during Change Notice creation verification: ");
		}
	}

	/**
	 * 
	 * Verifies that the promotional request edit was successful by checking the
	 * updated name.
	 * 
	 * @param expectedName Expected name after edit
	 * 
	 */
	public void verifyChangeNoticeEdited(String expectedName) {
		try {
			waitForElementVisible(ChangeNotice.nameElement, 20);
			String actualName = ChangeNotice.nameElement.getText().trim();
			if (actualName.equals(expectedName)) {
				System.out.println("ChangeNotice edited successfully.");
				highlightElement(ChangeNotice.nameElement);
				Assert.assertTrue(true, "ChangeNotice name updated correctly.");
			} else {
				LogUtil.info("ChangeNotice name not updated as expected.");
				Assert.fail("Expected name: " + expectedName + " | Actual name: " + actualName);
			}
		} catch (Exception e) {
			LogUtil.info("Error during ChangeNotice edit verification: ");
		}
	}

	public void clickSelectAffectedObjectTab() {
		click(ChangeNotice.selectAffectedObjectWindow);
	}
	
	public void clickIframeFinishButton() {
        List<WebElement> buttons = driver.findElements(By.xpath("//button[contains(text(), 'inish')]"));
        buttons.get(0).click();

    }
	
	public void clickOnViewInfoIconOnChangeTask(String taskName) {
		WaitUtils.waitForElementVisible(ChangeNotice.viewInfoIconOnChangeTask(taskName),10).click();
	}
	
	public void clickOnCompleteTaskButon() {
		WaitUtils.waitForSeconds(3);
		WaitUtils.waitForElementClickable(ChangeNotice.completeTaskButton,10).click();
		
	}
	
	public void closeBanner() {
		try {
		WaitUtils.waitUntilVisible(driver,ChangeNotice.closeBanner,10).click();
		}catch(Exception e) {
		}
		WaitUtils.waitForSeconds(4);
	}
	
	 public static boolean validateChangeTaskStatus(String changeTaskName, String status) {
		 WaitUtils.waitForSeconds(4);
	        try {
	            WebElement object = driver.findElement(By.xpath("//tr[.//*[contains(text(),'"+changeTaskName+"')]]//div[contains(text(),'"+status+"')]"));
	            boolean taskCompleted = object.isDisplayed();
	            WaitUtils.waitForSeconds(3);
	            return taskCompleted;
	        } catch (Exception e) {
	            return false;
	        }
	    }

}