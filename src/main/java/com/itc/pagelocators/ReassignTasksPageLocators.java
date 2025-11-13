package com.itc.pagelocators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReassignTasksPageLocators {
	
	@FindBy(xpath = "//select[@id='assignTo']")
	public WebElement getdropdownreassignto;
	
	@FindBy(xpath = "//u[contains(text(),'O')]")
	public WebElement OK_btn;
	
	@FindBy(xpath = "//button[contains(@style,'task_reassign')]")
	public WebElement Reassigntasks;
	
	 //Please write from here Dynamic locator method - cannot use @FindBy
}
