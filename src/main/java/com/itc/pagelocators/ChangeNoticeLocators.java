package com.itc.pagelocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChangeNoticeLocators {
	@FindBy(xpath = "//td[@attrid='name']/input[@type='text']")
	public WebElement Name;

	@FindBy(xpath = "//button[contains(text(),'ext')]")
	public WebElement Nextbtn;
	
	@FindBy(xpath = "//input[@id='ext-comp-1058']")
	public WebElement Addbynumber;
	
	@FindBy(xpath = "//button[contains(normalize-space(),'Finish')]")
	public WebElement FinishButton;
	
	@FindBy(xpath = "//b[contains(text(),'Submit Now')]")
	public WebElement Submit;
	
	@FindBy(xpath = "input[@id='ext-comp-1058']")
	public WebElement AddNumbertextbox;
	
	@FindBy(xpath = "//img[@*[name()='ext:qtip']='Edit change task']")
	public WebElement EditChangeTask;
	
	@FindBy(xpath = "//*[contains(text(),'New Change Task')]/parent::*//input[contains(@id,'name') and contains(@type,'text')]")
	public WebElement ChangeTaskName;

	@FindBy(xpath = "//b[contains(text(), 'CONFIRMATION: Create successful')]")
	public WebElement successMessage;
	
	@FindBy(xpath = "//a[@class = 'msgIdentityText']")
	public WebElement successMessageLink;
	
	@FindBy(xpath = "//div[@class = 'x-tool x-tool-close']")
	public WebElement licenseAlertClose;
	
	@FindBy(xpath = "//td[contains(@attrid, 'name')]")
	public WebElement nameElement;
	
	@FindBy(xpath = "//li[contains(@id,'affectedAndResultingItems')]")
    public WebElement selectAffectedObjectWindow;
	
	@FindBy(xpath = "//table[contains(@id,'submitNowBtn')]//button")
    public WebElement submittButton;
	
	@FindBy(xpath = "//button[@id='workflowEsignCompleteButton']")
    public WebElement completeTaskButton;
	
	@FindBy(xpath = "//div[@class='x-tool x-tool-close']")
    public WebElement closeBanner;
	
	
	public static By viewInfoIconOnChangeTask(String objName) {
        return By.xpath("//tr[.//*[contains(text(),'"+objName+"')]]//img[@src='netmarkets/images/details.gif']");
    }
	
	 //Please write from here Dynamic locator method - cannot use @FindBy
 
}