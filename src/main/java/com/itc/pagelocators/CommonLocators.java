package com.itc.pagelocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CommonLocators {

	@FindBy(xpath = "//b[contains(text(), 'CONFIRMATION: Create successful')]")
	public WebElement successMessage;
	
	public By successMessageText(String text) {
		return By.xpath("//b[contains(text(), '" + text + "')]");
	}
	
	@FindBy(xpath = "//a[@class = 'msgIdentityText']")
	public WebElement successMessageLink;
	
	@FindBy(xpath = "//div[@class = 'x-tool x-tool-close']")
	public WebElement popupMessageClose;
}
