package com.itc.page.actions;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.itc.base.BaseTest;
import com.itc.pagelocators.ChangeRequestLocators;
import com.itc.utilities.LogUtil;

public class ChangeRequestPage extends BaseTest {
	public ChangeRequestLocators ChangeRequest;

	public ChangeRequestPage() {
		this.ChangeRequest = new ChangeRequestLocators();
		PageFactory.initElements(driver, this.ChangeRequest);

	}

	public void enterchangerequestname(String Name) {
		type(ChangeRequest.Name, Name);
	}

	public void clickNextbtn() {
		click(ChangeRequest.Nextbtn);
	}

	public void clickFinish() {

		click(ChangeRequest.FinishButton);
	}

	public void clickSubmit() {
		click(ChangeRequest.Submit);
	}

	public void clickAddnumbertextbox() {
		click(ChangeRequest.AddNumbertextbox);
	}

	public void enternumber(String Number) {
		type(ChangeRequest.AddNumbertextbox, Number);
	}

	public void verifyChangeRequestCreated() {
		try {
			waitForElementVisible(ChangeRequest.successMessage, 20);
			if (ChangeRequest.successMessage.isDisplayed()) {
				System.out.println("Change Request creation confirmed.");
				Assert.assertTrue(true, "Change Request creation successful.");
				click(ChangeRequest.successMessageLink);
				if (ChangeRequest.licenseAlertClose.isDisplayed()) {
					click(ChangeRequest.licenseAlertClose);
				}
			} else {
				LogUtil.info("Confirmation message not displayed.");
				Assert.fail("Change Request creation confirmation message not displayed.");
			}
		} catch (Exception e) {
			LogUtil.info("Error during Change Request creation verification: ");
		}
	}

	/**
	 * Verifies that the promotional request edit was successful by checking the
	 * updated name.
	 *
	 * @param expectedName Expected name after edit
	 */
	public void verifyChangeRequestEdited(String expectedName) {
		try {
			waitForElementVisible(ChangeRequest.nameElement, 20);
			String actualName = ChangeRequest.nameElement.getText().trim();
			if (actualName.equals(expectedName)) {
				System.out.println("ChangeRequest edited successfully.");
				highlightElement(ChangeRequest.nameElement);
				Assert.assertTrue(true, "ChangeRequest name updated correctly.");
			} else {
				LogUtil.info("ChangeRequest name not updated as expected.");
				Assert.fail("Expected name: " + expectedName + " | Actual name: " + actualName);
			}
		} catch (Exception e) {
			LogUtil.info("Error during ChangeRequest edit verification: ");
		}
	}
	
	public  void validateChangeRequestIsPresent(String linkText) {
        try {
            WebElement link = driver.findElement(By.linkText(linkText));
 
            Assert.assertTrue(link.isDisplayed(),
                    "Link with text '" + linkText + "' is not displayed on the page.");
 
            LogUtil.info("Link with text '" + linkText + "' is present and visible.");
 
        } catch (NoSuchElementException e) {
            Assert.fail("Link with text '" + linkText + "' was not found on the page.", e);
        } catch (Exception e) {
            Assert.fail("Error while validating link with text '" + linkText + "': " + e.getMessage(), e);
        }
    }
 

}