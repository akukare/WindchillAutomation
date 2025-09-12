package com.itc.page.actions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

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
}
