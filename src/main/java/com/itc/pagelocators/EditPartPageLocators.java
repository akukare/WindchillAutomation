package com.itc.pagelocators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPartPageLocators {
	
	@FindBy(xpath = "//select[@id='partType']")
	public WebElement getdropdownAssemblyMode;
	
	@FindBy(xpath = "//button[contains(text(),'Check')]")
	public WebElement CheckIn;
		
	@FindBy(xpath = "//button [contains(text(),'ave')]")
	public WebElement SaveButton;
	
	@FindBy(xpath = "//*[@id=\"wip.checkin_step\"]/table/tbody/tr[1]/td[2]/textarea")
	public WebElement Comments;
	
	@FindBy(xpath = "//button[contains(text(),'K') and (@id='ext-gen35')]")
	public WebElement Ok;

}
