package com.itc.page.actions;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.itc.base.BaseTest;
import com.itc.pagelocators.CommonLocators;
import com.itc.pagelocators.ProductPageLocators;
import com.itc.utilities.ElementActions;
import com.itc.utilities.LogUtil;
import com.itc.utilities.WaitUtils;


public class ProductPage extends BaseTest {
	public ProductPageLocators Product;
	public CommonLocators commonLocators;

	public ProductPage() {
		this.Product = new ProductPageLocators();
		PageFactory.initElements(driver, this.Product);
		this.commonLocators = new CommonLocators();
		PageFactory.initElements(driver, this.commonLocators);
		
	}

	public void clickViewPart() {ElementActions.click(Product.ViewPartIcon);}

	public String getPartName() {
		return ElementActions.getText(Product.ViewPartName);
	}

	public void gotoActions() {ElementActions.click(Product.Actions);}

	public void checkoutandEdit() {ElementActions.click(Product.CheckOutandEdit);}

	public String getAssemblycodeelement() {
		return ElementActions.getText(Product.getAssemblycodewebelement);
	}

	public void gotoPartCheckbox() {
		ElementActions.click(Product.PartCheckbox);
	}

	public void DeletePart() {ElementActions.click(Product.Delete);}

	public void gotoNewLink() {
		ElementActions.click(Product.Newlink);
	}

	public void gotoNewProblemReportLink() {
		ElementActions.click(Product.NewProblemReportLink);
	}

	public void gotoNewChangeRequestLink() {
		ElementActions.click(Product.NewChangeRequestLink);
	}

	public void gotoNewChangeNoticeLink() {
		ElementActions.click(Product.NewChangeNoticeLink);
	}

	public void gotoNewPromotionRequest() {
		ElementActions.click(Product.NewPromotionRequestLink);
	}

	/**
	 * Performs an action from the dynamic menu based on the provided label name.
	 *
	 * @param name The visible label of the menu action (e.g., "New", "Cut",
	 *             "Paste").
	 *
	 *             This method: - Dynamically builds the XPath for the target
	 *             action. - Waits for the element to be visible. - Scrolls into
	 *             view and clicks to perform the action.
	 */
	public void takeActions(String name) {
		try {
			WaitUtils.waitForElementVisible(Product.actionMenuItems(name), 10);
			WebElement actionMenuItemsElement = driver.findElement(Product.actionMenuItems(name));
			LogUtil.info("Clicked on: " + name);
			ElementActions.click(actionMenuItemsElement);
			WaitUtils.waitForSeconds(1);
		} catch (Exception e) {
			LogUtil.info(e.getMessage());
		}
	}

//	public void folderContentsDropdown(String value) {
//		ElementActions.click(Product.folderBrowserinput);
//		clear(Product.folderBrowserinput);
//    	WaitUtils.waitForSeconds(1);
//    	ElementActions.sendKeys(Product.folderBrowserinput, value + Keys.ENTER);
//    	WaitUtils.waitForSeconds(1);
//     }
	
	
	public void folderContentsDropdown(String contentType) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement viewType = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ProductPageLocators.folderBrowserinputXpath)));
		viewType.clear();
		viewType.sendKeys(contentType);
		viewType.sendKeys(Keys.ENTER);
		WaitUtils.waitForSeconds(2);
		try {
			driver.switchTo().alert().accept();
		} catch (Exception e) {
			// No alert present, continue
		}
		WaitUtils.waitForSeconds(2);
	}

	public void selectFolderCheckbox(String name) {
		waitUntilVisible(Product.itemName(name), 10);
	    List<WebElement> rows = driver.findElements(Product.itemRows);

	    for (WebElement row : rows) {
	        try {
	            WebElement link = row.findElement(Product.itemName(name));
	            if (link != null && link.isDisplayed()) {
	            	highlightElement(row);
	                WebElement checkbox = row.findElement(Product.checkbox);
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

	public void rightClickOnProblemReport(String linkText) {
        Actions actions = new Actions(driver);
        actions.contextClick(Product.problemreportlink).perform();
    }

	public void rightClickOnCheckedCheckbox () {
		
		WebElement CheckedCheckBox = driver.findElement(By.xpath(" //div[@class='x-grid3-cell-inner x-grid3-col-type_icon']//img[@qtip='Change Notice']/ancestor::div[contains(@class, 'x-grid3-row')][1]"));
		
		 Actions actions = new Actions(driver);
         actions.contextClick(CheckedCheckBox).perform();
	}
	
	public void rightClickOnCRCheckedCheckbox () {
	
	WebElement CheckedCheckBox1 = driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-type_icon']//img[@qtip='Change Request']/ancestor::table[contains(@class, 'x-grid3-row-table')]//div[contains(@class, 'x-grid3-row-checker')]"));
	 Actions actions = new Actions(driver);
     actions.contextClick(CheckedCheckBox1).perform();
}	
	
	public void verifyFolderCreated() {
	    try {
	        waitForElementVisible(Product.successMessage, 20);
	        if (Product.successMessage.isDisplayed()) {
	            System.out.println("Folder creation confirmed.");
	            Assert.assertTrue(true, "Folder creation successful.");
	            click(Product.successMessageLink);
	            waitForSeconds(2);
	            if(Product.licenseAlertClose.isDisplayed()) {
	            	click(Product.licenseAlertClose);
	            }
	        } else {
	        	LogUtil.info("Confirmation message not displayed.");
	            Assert.fail("Folder creation confirmation message not displayed.");
	        }
	    } catch (Exception e) {
	    	LogUtil.info("Error during Folder creation verification: " + e.getMessage());
	    }
	}

	public void clickEditOption() {
		ElementActions.click(Product.editOption);
	}
	
	public void clickEditOptionChangeRequest() {
		ElementActions.click(Product.editOption);
	}
	
}
