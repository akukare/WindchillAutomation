package com.itc.pagelocators;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
 
public class BOMPageLocators {
 
	public By itemRows = By.xpath("//div[@id='changeTask_affectedItems_table']//table[@class='x-grid3-row-table']");
	
	public By itemName(String name) {
		return By.xpath("//a[contains(text(), '" + name + "')]");
	}
	
	public By checkbox = By.xpath("//div[@class='x-grid3-row-checker']");
	
	public By collectAffectedObjects = By.xpath("//*[contains(@id,'changeTask_affectedItems_table')]//button[contains(@style,'collect')]");
	
	public By okButton = By.xpath("button[text()='OK']");
	
	public By checkObject(String objectName) {
	return By.xpath("//*[text()='"+objectName+"']//ancestor::td//preceding-sibling::td//div[contains(@class,'row-checker')]");
	}

	
}