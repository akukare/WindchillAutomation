package com.itc.pagelocators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReassignTasksPageLocators {
	
	@FindBy(xpath = "//select[@id='assignTo']")
	public WebElement getdropdownreassignto;
	
	@FindBy(xpath = "//u[contains(text(),'O')]")
	public WebElement OK_btn;
	
	@FindBy(xpath = "//div[@id='projectmanagement.overview.assignments.list.toolBar']/table[1]/tbody[1]/tr[1]/td[1]/table[1]/tbody[1]/tr[1]/td[2]/table[1]/tbody[1]/tr[2]/td[2]/em[1]/button[1]")
	public WebElement Reassigntasks;
}
