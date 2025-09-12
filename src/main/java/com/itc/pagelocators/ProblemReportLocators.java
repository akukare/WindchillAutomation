package com.itc.pagelocators;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ProblemReportLocators {
	
	
	@FindBy(xpath = "//td[@attrid='name']/input[@type='text']")
	public WebElement Name;
	
	
	@FindBy(xpath = "//button[contains(text(),'ext')]")
	public WebElement Nextbtn;
	
	@FindBy(xpath = "//select[@id='!~objectHandle~partHandle~!createType']")
	public WebElement getdropdownelement;

	
	@FindBy(xpath = "//input[@id='ext-comp-1058']")
	public WebElement Addbynumber;
	
	@FindBy(xpath = "//button[@id='ext-gen39']")
	public WebElement  FinishButton;
	
	
	@FindBy(xpath = "//b[contains(text(),'Submit Now')]")
	public WebElement  Submit;
	
	@FindBy(xpath = "//form[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/table[1]/tbody[1]/tr[1]/td[2]/div[1]/input[1]")
	public WebElement  AddNumbertextbox;	

}
