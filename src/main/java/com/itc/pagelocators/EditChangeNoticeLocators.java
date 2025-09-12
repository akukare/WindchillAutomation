package com.itc.pagelocators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditChangeNoticeLocators {
	

	@FindBy(xpath = "//button[contains(text(),'Actions')]")
	public WebElement Actions;
	
	@FindBy(xpath = "//ul[1]/li[2]//a[1]/span[contains(text(),'Edit')]")
	public WebElement Edit;
	
	@FindBy(xpath = "//td[@attrid='name']/input[@type='text']")
	public WebElement Name;
	
	@FindBy(xpath = "//button[@id='ext-gen39']")
	public WebElement  FinishButton;

}
