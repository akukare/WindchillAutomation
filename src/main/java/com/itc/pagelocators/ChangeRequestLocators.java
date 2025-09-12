package com.itc.pagelocators;
 
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
 
public class ChangeRequestLocators{

	@FindBy(xpath = "//td[@attrid='name']/input[@type='text']")

	public WebElement Name;

	@FindBy(xpath = "//button[contains(text(),'ext')]")

	public WebElement Nextbtn;

	@FindBy(xpath = "//input[@id='ext-comp-1058']")

	public WebElement Addbynumber;

	@FindBy(xpath = "//button[@id='ext-gen39']")

	public WebElement  FinishButton;

	@FindBy(xpath = "//b[contains(text(),'Submit Now')]")

	public WebElement  Submit;	
 
	@FindBy(xpath = "input[@id='ext-comp-1058']")

	public WebElement  AddNumbertextbox;	
 
	@FindBy(xpath = "//b[contains(text(), 'CONFIRMATION: Create successful')]")

	public WebElement successMessage;

	@FindBy(xpath = "//a[@class = 'msgIdentityText']")

	public WebElement successMessageLink;	

	@FindBy(xpath = "//div[@class = 'x-tool x-tool-close']")

	public WebElement licenseAlertClose;

	@FindBy(xpath = "//td[contains(@attrid, 'name')]")

	public WebElement nameElement;

}

 