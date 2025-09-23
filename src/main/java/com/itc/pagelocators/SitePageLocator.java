package com.itc.pagelocators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SitePageLocator {

	@FindBy(xpath = "//span[@class='x-tab-strip-text siteNavigation-icon']")
	public WebElement siteTab;
	
	@FindBy(xpath = "//div[@class='x-tree-node-el x-tree-node-leaf x-unselectable file']//span[text()='Utilities']")
	public WebElement Utilities;
	
	@FindBy(xpath = "//a[normalize-space()='Preference Management']")
	public WebElement preferenceManagementLink;
	
	@FindBy(xpath = "//div[@class='x-grid3-body']//div[text()='Change Management']/preceding-sibling::div")
	public WebElement changeManagementExpand;
	
	@FindBy(xpath = "//div[text()='Enable BOM Redline']")
	public WebElement ebrRightClick;
	
	@FindBy(xpath = "//span[text()='Set Preference']")
	public WebElement setPreference;
	
	@FindBy(xpath = "//button[text()='Revert to Default']")
	public WebElement revertButton;
	
	@FindBy(xpath = "//input[@type='radio' and @value='True']")
	public WebElement setPreferenceValue;
	
	@FindBy(xpath = "//button[@id='ext-gen37']")
	public WebElement clickOkButton;

}
