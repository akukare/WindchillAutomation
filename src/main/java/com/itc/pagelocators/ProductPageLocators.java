package com.itc.pagelocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPageLocators {

	@FindBy(xpath = "//tbody/tr/td/div/a[text()='Autotest1']//ancestor::tr[1]/td[10]")
	public WebElement ViewPartIcon;

	public By ViewPartName = By.xpath("//td[@attrid='name']");

	@FindBy(xpath = "//div[@id='folderbrowser_PDM.toolBar']//button[@class=' x-btn-text']")
	public WebElement Actions;

	@FindBy(xpath = "//span[contains(text(),'Check Out and Edit')]")
	public WebElement CheckOutandEdit;

	@FindBy(xpath = "//span[contains(text(),'Delete')]")
	public WebElement Delete;

	public By getAssemblycodewebelement = By.xpath("//td[@attrid='partType']");

	@FindBy(xpath = "//tbody/tr/td/div/a[text()='Autotest1']//ancestor::tr[1]/td[1]")
	public WebElement PartCheckbox;

	@FindBy(xpath = "//form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]//td[7]")
	public By FolderContentTable;

	@FindBy(xpath = "//a[@id='object_folderbrowser_toolbar_new_submenu__folderbrowser_PDM']")
	public WebElement Newlink;

	@FindBy(xpath = "//span[contains(text(),'New Problem Report')]")
	public WebElement NewProblemReportLink;

	@FindBy(xpath = "//span[contains(text(),'New Change Request')]")
	public WebElement NewChangeRequestLink;

	@FindBy(xpath = "//span[contains(text(),'New Change Notice')]")
	public WebElement NewChangeNoticeLink;

	@FindBy(xpath = "//span[contains(text(),'New Promotion Request')]")
	public WebElement NewPromotionRequestLink;

	public By actionMenuItems(String name) {
		return By.xpath("//span[@class='x-menu-item-text' and contains(text(), '" + name + "')]");
	}

//	@FindBy(xpath = "//input[@id='folderbrowser_PDMfilterSelect']")
//	public WebElement folderBrowserinput;
	

	public static final String folderBrowserinputXpath = "//input[@id='folderbrowser_PDMfilterSelect']";

	@FindBy(xpath = "//a[text()='testProblemReport-428165']")
	public WebElement problemreportlink;

	public By itemName(String name) {
		return By.xpath("//a[contains(text(), '" + name + "')]");
	}

	public By itemRows = By.xpath("//div[@id='folderbrowser_PDM']//table[@class='x-grid3-row-table']");
	
	public By checkbox = By.xpath("//div[@class='x-grid3-row-checker']");
	
	@FindBy(xpath = "//b[contains(text(), 'CONFIRMATION: Create successful')]")
	public WebElement successMessage;
	
	@FindBy(xpath = "//a[@class = 'msgIdentityText']")
	public WebElement successMessageLink;
	
	
	@FindBy(xpath = "//div[@class = 'x-tool x-tool-close']")
	public WebElement licenseAlertClose;
	
 
	@FindBy(xpath = "//span[@class='x-menu-item-text' and text()='Edit']")
	public WebElement  editOption;
	

}
