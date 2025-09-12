package com.itc.pagelocators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RecentProductsLocators {

	@FindBy(xpath = "//li[@id='object_main_navigation__productNavigation']")
	public WebElement RecentProductsTab;
	
	
	@FindBy(xpath = "//li[@id='object_main_navigation__productNavigation']")
	public WebElement gotoProductFolder;
	
	@FindBy(linkText = "Auto_test")
	public WebElement gotoProductsubFolder;
	
	
}
