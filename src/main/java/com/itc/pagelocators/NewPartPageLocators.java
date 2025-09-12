package com.itc.pagelocators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewPartPageLocators  {

	@FindBy(xpath = "//button[contains(@style,'newpart')]")
	public WebElement newPartLink;
	
	
	@FindBy(xpath = "//select[@id='!~objectHandle~partHandle~!createType']")
	public WebElement getdropdownelement;
	

	@FindBy(xpath = "//tbody/tr[2]/td[3]/input[@type='text']")
	public WebElement PartName;
	
	@FindBy(xpath = "//button[contains(text(),'inish')]")
	
	public WebElement FinishButton;
	
	@FindBy(xpath = "//label[contains(text(),'Create CAD Document')]")
	public WebElement CreateCADDoc_Checkbox;
	
	@FindBy(xpath = "//u[contains(text(),'N')]")
	public WebElement Nextbtn;
	
	@FindBy(xpath = "//select[@id='DocTemplate']")
	public WebElement getdropdownTemplateName;
	
	@FindBy(xpath = "//input[@id='folderbrowser_PDM.searchInListTextBox']")
	public WebElement searchInputFolderPage;
	
}


