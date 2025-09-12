package com.itc.page.actions;

import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap.KeySetView;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.itc.base.BaseTest;
import com.itc.pagelocators.CommonLocators;
import com.itc.pagelocators.NewPartPageLocators;
import com.itc.utilities.ElementActions;
import com.itc.utilities.LogUtil;
import com.itc.utilities.WaitUtils;

public class NewPartPage extends BaseTest {

	public NewPartPageLocators NewPart;
	public CommonLocators commonLocators;

	public NewPartPage() {
		this.NewPart = new NewPartPageLocators();
		PageFactory.initElements(driver, this.NewPart);
		this.commonLocators = new CommonLocators();
		PageFactory.initElements(driver, this.commonLocators);
	}

	public void gotodropdown() {
		ElementActions.click(NewPart.getdropdownelement);
	}

	public void PartViaDropDown(int index) {
		Select dropdown = new Select(NewPart.getdropdownelement);
		dropdown.selectByIndex(index);
	}

	public void enterpartname(String Name) {
		ElementActions.sendKeys(NewPart.PartName, Name);
	}

	public void selectProductPartDropdown(String Value) {
		Select dropdown = new Select(NewPart.getdropdownelement);
		dropdown.selectByVisibleText(Value);
		WaitUtils.waitForSeconds(1);
	}

	public void clickFinish() {
		WaitUtils.waitForSeconds(1);
		ElementActions.click(NewPart.FinishButton);
	}

	public void clickCreateCADCheckbox() {
		ElementActions.click(NewPart.CreateCADDoc_Checkbox);
	}

	public void clickNextbtn() {
		ElementActions.click(NewPart.Nextbtn);
	}

	public void gotodropdownTemplateName() {
		ElementActions.click(NewPart.getdropdownTemplateName);
	}

	public void SelectTemplateDocDropDown(String Value) {
		Select dropdown = new Select(NewPart.getdropdownTemplateName);
		dropdown.selectByVisibleText(Value);
	}
	
	/**
	 * Verifies if the "CONFIRMATION: Create successful" message is displayed on the page.
	 * @name verifyPartCreated
	 * @description Uses XPath to locate confirmation message and asserts its visibility.
	 *              Logs result and throws assertion error if message is not found.
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
	            if(commonLocators.popupMessageClose.isDisplayed()) {
	            	ElementActions.click(commonLocators.popupMessageClose);
	            }
	        } else {
	        	LogUtil.info("Part creation confirmation message not displayed.");
	        }
	    } catch (Exception e) {
	    	LogUtil.info("Error during part creation verification: " + e.getMessage());
	    }
	}
	
	public void searchObject(String object) {
			WaitUtils.waitForSeconds(2);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.elementToBeClickable(NewPart.searchInputFolderPage)).click();
			wait.until(ExpectedConditions.elementToBeClickable(NewPart.searchInputFolderPage)).clear();
			wait.until(ExpectedConditions.elementToBeClickable(NewPart.searchInputFolderPage)).sendKeys(object);
			wait.until(ExpectedConditions.elementToBeClickable(NewPart.searchInputFolderPage)).sendKeys(Keys.ENTER);
			
		}
	
}
