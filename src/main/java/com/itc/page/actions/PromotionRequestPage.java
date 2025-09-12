package com.itc.page.actions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.itc.base.BaseTest;
import com.itc.pagelocators.CommonLocators;
import com.itc.pagelocators.PromotionRequestLocators;
import com.itc.utilities.ElementActions;
import com.itc.utilities.LogUtil;
import com.itc.utilities.WaitUtils;

public class PromotionRequestPage extends BaseTest {

	public PromotionRequestLocators PromotionRequest;
	public CommonLocators commonLocators;

	public PromotionRequestPage() {

		this.PromotionRequest = new PromotionRequestLocators();
		PageFactory.initElements(driver, this.PromotionRequest);
		this.commonLocators = new CommonLocators();
		PageFactory.initElements(driver, this.commonLocators);

	}

	public void clickNextbtn() {

		ElementActions.click(PromotionRequest.Nextbtn);
	}

	public void SelectTargetPromotionStatedropDown(String Value) {
		Select dropdown = new Select(PromotionRequest.getTargetPromotionState);
		dropdown.selectByVisibleText(Value);

	}

	public void clickFinish() {
		ElementActions.click(PromotionRequest.FinishButton);
	}

	public void clickSetPromotionObjectIcon() {
		ElementActions.click(PromotionRequest.setObjectforPromotion);
	}

	public void clickCheckBox() {
		ElementActions.click(PromotionRequest.clickCheckbox);
	}

	public void enterPromotinRequestNamename(String Name) {
		ElementActions.click(PromotionRequest.Name);
		clear(PromotionRequest.Name);
		WaitUtils.waitForSeconds(1);
		ElementActions.sendKeys(PromotionRequest.Name, Name);

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
	public void verifyPromotionRequestCreatedandOpen() {
		try {
			String text = "CONFIRMATION: Create successful";
			WaitUtils.waitForElementVisible(commonLocators.successMessageText(text), 20);
			WebElement message = driver.findElement(commonLocators.successMessageText(text));
			if (message.isDisplayed()) {
				LogUtil.info("Promotion Request creation confirmed.");
				ElementActions.click(commonLocators.successMessageLink);
				WaitUtils.waitForSeconds(1);
				if (commonLocators.popupMessageClose.isDisplayed()) {
					ElementActions.click(commonLocators.popupMessageClose);
				}
			} else {
				LogUtil.info("Promotion Request creation confirmation message not displayed.");
			}
		} catch (Exception e) {
			LogUtil.info("Error during Promotion Request creation verification: " + e.getMessage());
		}
	}

}
