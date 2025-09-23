package com.itc.page.actions;
 
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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
	    WaitUtils.waitUntilVisible(driver,BOMPage.collectAffectedObjects, 10);
	    BOMPage.collectAffectedObjects.click();
	    WaitUtils.waitForSeconds(2);
	}

	public void clickOk() {
		ElementActions.click(BOMPage.okButton);
	}
	
	public void selectObject(String objectName) {
		WaitUtils.waitUntilVisible(driver,BOMPage.checkObject(objectName), 10);
	    driver.findElement(BOMPage.checkObject(objectName)).click();
	}
	
	public void switchToCNIframe() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebElement iframeElement = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[contains(@id,'popCreateWizard')]")));
		driver.switchTo().frame(iframeElement);
	}
	
	public void clickSelectAllRowsCheckBox() {
		ElementActions.click(BOMPage.selectAllRowsCheckbox);
	}
	public void clickOnViewInformation() {
		ElementActions.click(BOMPage.viewInformationIcon);
	}
	public void clickOnViewInformationOnCNPage() {
		ElementActions.click(BOMPage.ViewIconCNPage);
	}
	public void clickAffectedObjectTab() {
		ElementActions.click(BOMPage.affectedObjectTab);
	}
	public void clickOnImplementationPlan() {
		WaitUtils.waitForSeconds(2);
		WaitUtils.waitUntilVisible(driver,BOMPage.implementationTab,10);
		BOMPage.implementationTab.click();
	}
	public void selectAffectedObject() {
		WaitUtils.waitForSeconds(2);
		WaitUtils.waitUntilVisible(driver,BOMPage.afectedobjcheck,10);
		BOMPage.afectedobjcheck.click();
	}
	public void clickActionDropdown() {
		ElementActions.click(BOMPage.actions);
	}
	public void selctRedlineOption() {
		ElementActions.click(BOMPage.redline);
	}
	public void createBOMRedline() {
		WaitUtils.waitForSeconds(2);
		WaitUtils.waitUntilVisible(driver,BOMPage.createBOMRedline,10);
		BOMPage.createBOMRedline.click();
		WaitUtils.waitForSeconds(2);
	}
	public void deleteBOMRedline() {
		ElementActions.click(BOMPage.deleteBOMRredline);
	}
	public void selectAffectedObjectsCheckbox(String objectName) {
		WaitUtils.waitForSeconds(3);
		WaitUtils.waitUntilVisible(driver,BOMPage.checkObject(objectName),10);
		driver.findElement(BOMPage.checkObject(objectName)).click();
	}

	public void selectViewIconImplementationTab() {
		WaitUtils.waitForSeconds(2);
		WaitUtils.waitUntilVisible(driver,BOMPage.itemRowsImpTab,10);
		BOMPage.itemRowsImpTab.click();
		WaitUtils.waitForSeconds(2);
	}
	public void AffectedObjActionBtn() {
		WaitUtils.waitForSeconds(2);
		WaitUtils.waitUntilVisible(driver,BOMPage.afectedobjcheckaction,10);
		BOMPage.afectedobjcheckaction.click();
		WaitUtils.waitForSeconds(2);
	}
	public void RedlineBtn() {
		WaitUtils.waitForSeconds(2);
		WaitUtils.waitUntilVisible(driver,BOMPage.redline,10);
		BOMPage.redline.click();
		WaitUtils.waitForSeconds(2);
	}

	public void InsertNewPartOnBOM() {
		WaitUtils.waitForSeconds(2);
		WaitUtils.waitUntilVisible(driver,BOMPage.insertNewButton,10).click();
		WaitUtils.waitForSeconds(2);
	}
	public void InsertExistingPartOnBOM() {
		WaitUtils.waitForSeconds(2);
		WaitUtils.waitUntilVisible(driver,BOMPage.insertExistingButton,10).click();
		WaitUtils.waitForSeconds(2);
	}

	public void switchToBOMIframe() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebElement iframeElement = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[contains(@id,'AnnotatedSBIframe')]")));
		driver.switchTo().frame(iframeElement);
	}
	public void NewPartBOMIframe() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebElement iframeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[contains(@id,'submitFrame')]")));
		driver.switchTo().frame(iframeElement);
	}
	public void verifyRedlineCreated(String name) {
 
		String actualTitle = driver.getTitle();
		String expectedTitle = name;  
		Assert.assertTrue(actualTitle.contains(expectedTitle),
		        "Window title does not contain expected text. Actual: " + actualTitle);
	}
	public void verifyPartCreated(String partName) {
		WaitUtils.waitForSeconds(2);
		WaitUtils.waitUntilVisible(driver,BOMPage.verifyPartBOM(partName),10).click();
		WaitUtils.waitForSeconds(2);
	}
	public void serchNumber(String number) {
		WaitUtils.waitForSeconds(2);
		WaitUtils.waitUntilVisible(driver,BOMPage.serchNumber,10).click();
		WaitUtils.waitUntilVisible(driver,BOMPage.serchNumber,10).sendKeys(number);
		WaitUtils.waitForSeconds(2);
	}
 
	public void ClickSerch() {
		WaitUtils.waitForSeconds(2);
		WaitUtils.waitUntilVisible(driver,BOMPage.serchbutton,10).click();
		WaitUtils.waitForSeconds(2);
	}
	public void ClickOk() {
		WaitUtils.waitForSeconds(2);
		WaitUtils.waitUntilVisible(driver,BOMPage.okbutton,10).click();
		WaitUtils.waitForSeconds(2);
	}
	public void viewBOMRedline() {
		WaitUtils.waitForSeconds(2);
		WaitUtils.waitUntilVisible(driver,BOMPage.viewBOMRedline,10);
		BOMPage.viewBOMRedline.click();
		WaitUtils.waitForSeconds(2);
	}
	
	public void verifyRedlineInViewMode(String name) { 
		String actualTitle = driver.getTitle();
		String expectedTitle = name;  
		Assert.assertTrue(actualTitle.contains(expectedTitle),"Window title does not contain expected text. Actual: " + actualTitle);
	}
	
	public void selectViewIcon(String name) {
	    waitUntilVisible(BOMPage.itemName(name), 10);
	    List<WebElement> rows = driver.findElements(BOMPage.itemRows);
	    for (WebElement row : rows) {
	        try {
	            WebElement link = row.findElement(BOMPage.itemName(name));
	            if (link != null && link.isDisplayed()) {
	                highlightElement(row);
	                WebElement viewIcon = row.findElement((By) BOMPage.viewInformationIcon);
	                if (!viewIcon.isSelected()) {
	                    ElementActions.click(viewIcon);
	                    WaitUtils.waitForSeconds(1);  
	                }
	                break;  
	            }
	        } catch (NoSuchElementException e) {
	            LogUtil.info(e.getMessage());
	        }
	    }
	}
}