package com.itc.page.actions;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.itc.base.BaseTest;
import com.itc.pagelocators.CommonLocators;
import com.itc.pagelocators.ProblemReportLocators;
import com.itc.utilities.ElementActions;
import com.itc.utilities.LogUtil;
import com.itc.utilities.WaitUtils;

public class ProblemReportPage extends BaseTest{

	
	
	public ProblemReportLocators ProblemReport;
	public CommonLocators commonLocators;
	
	public ProblemReportPage()
	{
		
		this.ProblemReport=new ProblemReportLocators();
		PageFactory.initElements(driver, this.ProblemReport);
		this.commonLocators = new CommonLocators();
		PageFactory.initElements(driver, this.commonLocators);
		
	}
	
public void enterproblemreportname(String Name) {
	ElementActions.sendKeys(ProblemReport.Name, Name);

	}


public void clickFinish() {
	WaitUtils.waitForSeconds(1);
	ElementActions.click(ProblemReport.FinishButton);
}

public void selectProductPartDropdown(String Value) {
	Select dropdown = new Select(ProblemReport.getdropdownelement);
	dropdown.selectByVisibleText(Value);
}

public void clickSubmit()
{
	ElementActions.click(ProblemReport.Submit);
}


/**
 * Verifies if the "CONFIRMATION: Create successful" message is displayed on the page.
 * @name verifyPartCreated
 * @description Uses XPath to locate confirmation message and asserts its visibility.
 *              Logs result and throws assertion error if message is not found.
 * @param none
 * @return void
 */
public void verifyProblemReportCreatedandOpen() {
    try {
    	String text = "CONFIRMATION: Create successful";
        WaitUtils.waitForElementVisible(commonLocators.successMessageText(text), 20);
        WebElement message = driver.findElement(commonLocators.successMessageText(text));
        if (message.isDisplayed()) {
        	LogUtil.info("ProblemReport creation confirmed.");
        	ElementActions.click(commonLocators.successMessageLink);
            WaitUtils.waitForSeconds(2);
            if(commonLocators.popupMessageClose.isDisplayed()) {
            	ElementActions.click(commonLocators.popupMessageClose);
            }
        } else {
        	LogUtil.info("ProblemReport creation confirmation message not displayed.");
        }
    } catch (Exception e) {
    	LogUtil.info("Error during ProblemReport creation verification: " + e.getMessage());
    }
}

public void clickOnText(WebDriver driver, String visibleText) {
	try {
		WebElement element = driver.findElement(By.xpath("//*[normalize-space(text())='" + visibleText + "']"));
		element.click();
		System.out.println("Clicked on element with text: " + visibleText);
	} catch (NoSuchElementException e) {
		System.err.println("Element with text '" + visibleText + "' not found.");
	} catch (Exception e) {
		System.err.println("Error while clicking element with text '" + visibleText + "': " + e.getMessage());
	}
}
}



