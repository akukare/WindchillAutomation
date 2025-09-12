package com.itc.page.actions;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.itc.base.BaseTest;
import com.itc.pagelocators.BrowsePageLocators;
import com.itc.utilities.ElementActions;
import com.itc.utilities.LogUtil;
import com.itc.utilities.WaitUtils;

public class BrowsePage extends BaseTest {

	public BrowsePageLocators Browse;
	
	public BrowsePage()
	{
		
		this.Browse=new BrowsePageLocators();
		PageFactory.initElements(driver, this.Browse);
		
	}
	
	public void RecentProducts(){
		
		ElementActions.click(Browse.RecentProductsTab);
		
	}
	
	public void gotoProducts()
	{

		ElementActions.click(Browse.gotoProducts);
	}
	
	 public void gotoProductFolder() {
		 
		 ElementActions.click(Browse.gotoProductFolder);
	 }
	 
public void gotoProductsubFolder() {
		 

	ElementActions.click(Browse.gotoProductsubFolder);
	 }

public void gotoViewAll() {
	
	ElementActions.click(Browse.gotoViewAll);
}

/**
 * Navigates to a specific section within a product.
 * Expands the product only if it's collapsed (plus icon visible), otherwise directly clicks the section.
 *
 * @param productName  the name of the product to interact with
 * @param sectionName  the section within the product to navigate to
 */
public void openSpecificSectionOfProduct(String productName, String sectionName) {
    try {
        WebElement productElement = driver.findElement(Browse.productElement(productName));
        WaitUtils.waitForElementVisible(Browse.productElement(productName), 20);
        try {
            List<WebElement> plusIcons = driver.findElements(Browse.expandProduct(productName));
            if (!plusIcons.isEmpty() && plusIcons.get(0).isDisplayed()) {
            	ElementActions.click(productElement);
            	WaitUtils.waitForSeconds(1);
            }
        } catch (Exception ex) {
        	LogUtil.info("Expansion check skipped: " + ex.getMessage());
        }
        WebElement sectionElement = driver.findElement(Browse.selectSubProduct(productName, sectionName));
        WaitUtils.waitForElementVisible(Browse.selectSubProduct(productName, sectionName), 20);
        ElementActions.click(sectionElement);
    } catch (Exception e) {
    	LogUtil.info("Error navigating to section: " + sectionName + " in product: " + productName + ". Cause: " + e.getMessage());
    }
}

}
