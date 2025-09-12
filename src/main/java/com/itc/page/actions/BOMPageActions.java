package com.itc.page.actions;
 
import java.util.List;
import java.util.NoSuchElementException;
 
import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
 
import com.itc.base.BaseTest;
import com.itc.pagelocators.BOMPageLocators;
import com.itc.utilities.ElementActions;
import com.itc.utilities.LogUtil;
import com.itc.utilities.WaitUtils;
 
public class BOMPageActions extends BaseTest{
	public BOMPageLocators BOMPage;
	public BOMPageActions () {
		this.BOMPage = new BOMPageLocators();
		PageFactory.initElements(driver, this.BOMPage);
	}
	public void collecteAffectedObjects() {
		WaitUtils.waitForSeconds(2);
		waitUntilVisible(BOMPage.collectAffectedObjects,10);
		driver.findElement(BOMPage.collectAffectedObjects).click();
		WaitUtils.waitForSeconds(2);
	}
	public void clickOk() {
		ElementActions.click(BOMPage.okButton);
	}
	public void selectAffectedObjectsCheckbox(String name) {
		waitUntilVisible(BOMPage.itemName(name), 10);
	    List<WebElement> rows = driver.findElements(BOMPage.itemRows);
 
	    for (WebElement row : rows) {
	        try {
	            WebElement link = row.findElement(BOMPage.itemName(name));
	            if (link != null && link.isDisplayed()) {
	            	highlightElement(row);
	                WebElement checkbox = row.findElement(BOMPage.checkbox);
	                if (!checkbox.isSelected()) {
	                	ElementActions.click(checkbox);
	                	WaitUtils.waitForSeconds(1);
	                }
	                break;
	            }
	        } catch (NoSuchElementException e) {
	        	LogUtil.info(e.getMessage());
	        }
	    }
	}
	
	public void selectObject(String objectName) {
		WaitUtils.waitForSeconds(3);
	    waitUntilVisible(BOMPage.checkObject(objectName),10);
		driver.findElement(BOMPage.checkObject(objectName)).click();
		WaitUtils.waitForSeconds(2);
	}
 
}