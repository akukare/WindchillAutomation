package com.itc.pagelocators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePageLocators {

	@FindBy(xpath = "//a[@id='object_main_navigation_nav']")
	public WebElement BrowseTab;
	
	@FindBy(xpath = "//button[contains(@style,'newpart')]")
	public WebElement newPart;

	@FindBy(xpath = "//form/div/div/div/div/div/div[2]/table/tbody/tr/td/table/tbody/tr/td[5]/table/tbody/tr[2]/td[2]/em/button")
	public WebElement newFolder;

	@FindBy(xpath = "//td[@attrid='name']/input")
	public WebElement newFolderName;

	@FindBy(xpath = "//button[contains(text(),'inish')]")
	public WebElement newFolderFinish;

	@FindBy(xpath = "//div[@id='homePageIconID']")
	public WebElement homepageIcon;
	
	@FindBy(xpath = "//span[contains(text(),'Public')]")
	public WebElement publicTab;
	
	@FindBy(xpath = "//div[@id='projectmanagement.overview.assignments.list.toolBar']/table[1]/tbody[1]/tr[1]/td[1]/table[1]/tbody[1]/tr[1]/td[2]/table[1]/tbody[1]/tr[2]/td[2]/em[1]/button[1]")
	public WebElement reassign;
	
	@FindBy(xpath = "//form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[1]/table[1]/tbody[1]/tr[1]/td[1]/table[1]/tbody[1]/tr[2]/td[2]")
	public WebElement newDocument;
	
	
}
