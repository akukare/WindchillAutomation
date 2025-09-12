package com.itc.pagelocators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewDocumentLocators {

	
	@FindBy(xpath = "//select[@id='createType']")
	public WebElement getdropdownelement;
	
	@FindBy(xpath = "//select[@id='primary0contentSourceList']")
	public WebElement getprimarycontentdropdownelement;
	
	@FindBy(xpath = "//table[@class=\"attributePanel-group-panel\"]//input[contains(@name,'name_col_name___textbox') and @type='text']")
	public WebElement DocName;
	
   @FindBy(xpath = "//button[contains(text(),'inish')]")
   public WebElement FinishButton;
	
}
