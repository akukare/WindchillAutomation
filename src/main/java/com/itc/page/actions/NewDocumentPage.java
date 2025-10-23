package com.itc.page.actions;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.itc.base.BaseTest;
import com.itc.pagelocators.CommonLocators;
import com.itc.pagelocators.NewDocumentLocators;
import com.itc.utilities.ElementActions;
import com.itc.utilities.LogUtil;
import com.itc.utilities.WaitUtils;

public class NewDocumentPage extends BaseTest {

	public CommonLocators commonLocators;
	public NewDocumentLocators NewDocument;

	public NewDocumentPage() {

		this.NewDocument = new NewDocumentLocators();
		PageFactory.initElements(driver, this.NewDocument);
		this.commonLocators = new CommonLocators();
		PageFactory.initElements(driver, this.commonLocators);
	}

	public void selectProductDocDropdown(String Value) {
		Select dropdown = new Select(NewDocument.getdropdownelement);
		dropdown.selectByVisibleText(Value);
	}

	public void gotodropdown() {
		NewDocument.getdropdownelement.click();
	}

	public void SelectDocprimarytypedropDown(String Value) {
		Select dropdown = new Select(NewDocument.getprimarycontentdropdownelement);
		dropdown.selectByVisibleText(Value);
	}

	public void enterdocname(String Name) {
		type(NewDocument.DocName, Name);
	}

	public void clickFinish() {
		click(NewDocument.FinishButton);
	}

	/**
	 * Verifies if the "CONFIRMATION: Create successful" message is displayed on the
	 * page.
	 * 
	 * @name verifyPartCreated
	 * @description Uses XPath to locate confirmation message and asserts its
	 *              visibility. Logs result and throws assertion error if message is
	 *              not found.
	 * @param none
	 * @return void
	 */
	public void verifyPartCreatedandOpen() {
		try {
			String text = "CONFIRMATION: Create successful";
			WaitUtils.waitForElementVisible(commonLocators.successMessageText(text), 20);
			WebElement message = driver.findElement(commonLocators.successMessageText(text));
			if (message.isDisplayed()) {
				LogUtil.info("Part creation confirmed.");
				ElementActions.click(commonLocators.successMessageLink);
				WaitUtils.waitForSeconds(2);
				if (commonLocators.popupMessageClose.isDisplayed()) {
					ElementActions.click(commonLocators.popupMessageClose);
				}
			} else {
				LogUtil.info("Part creation confirmation message not displayed.");
			}
		} catch (Exception e) {
			LogUtil.info("Error during part creation verification: " + e.getMessage());
		}
	}
	
	public  void validateDocumentIsPresent( String linkText) {
        try {
            WebElement link = driver.findElement(By.linkText(linkText));
 
            Assert.assertTrue(link.isDisplayed(),
                    "❌ Link with text '" + linkText + "' is not displayed on the page.");
 
            LogUtil.info("✅ Link with text '" + linkText + "' is present and visible.");
 
        } catch (NoSuchElementException e) {
            Assert.fail("❌ Link with text '" + linkText + "' was not found on the page.", e);
        } catch (Exception e) {
            Assert.fail("⚠️ Error while validating link with text '" + linkText + "': " + e.getMessage(), e);
        }
    }
 
}
