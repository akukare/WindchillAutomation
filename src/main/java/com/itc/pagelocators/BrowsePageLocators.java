package com.itc.pagelocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BrowsePageLocators {
	
	
	
	@FindBy(xpath = "//a[@id='object_main_navigation_nav']")
	public WebElement BrowsePagetab;
	
	@FindBy(xpath = "//li[@id='object_main_navigation__productNavigation']")
	public WebElement RecentProductsTab;
	
	@FindBy(xpath = "//div[@id='netmarkets.product.list.titleBar']/../following-sibling::div[1]//div[contains(@class,'scroller')]/div/div[1]//a[text()='Snowmobile']")
	public WebElement gotoProducts;
	
	@FindBy(xpath = "//span[contains(text(),'Folders')]")
	public WebElement gotoProductFolder;
	
	@FindBy(linkText = "Auto_test")
	public WebElement gotoProductsubFolder;
	
	@FindBy(xpath = "//a[normalize-space()='View All']")
	public WebElement gotoViewAll;
	
	public By productElement(String productName) {
		return By.xpath("//div[@id='productNavigation']//span[contains(text(), '" + productName + "')]");
	}
	
	public By expandProduct(String productName) {
		return By.xpath("//span[contains(text(), '" + productName + "')]/following::img[contains(@class, 'x-tree-elbow-end-plus')]");
	}
	
	public By selectSubProduct(String productName, String sectionName) {
		return By.xpath("//span[contains(text(), '" + productName + "')]/following::span[contains(text(), '" + sectionName + "')]");
	}
	
	public By actionMenu(String name) {
		return By.xpath("//span[@class='x-menu-item-text' and contains(text(), '" + name + "')]");
	}
	

}
