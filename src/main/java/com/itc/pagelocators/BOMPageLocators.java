package com.itc.pagelocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BOMPageLocators {

    @FindBy(xpath = "//div[@class='x-grid3-row-checker']")
    public WebElement checkbox;

    @FindBy(xpath = "//*[contains(@id,'changeTask_affectedItems_table')]//button[contains(@style,'collect')]")
    public WebElement collectAffectedObjects;

    @FindBy(xpath = "//button[text()='OK']")
    public WebElement okButton;

    @FindBy(xpath = "//div[@id='ext-gen268']//div[@class='x-grid3-hd-checker']")
    public WebElement selectAllRowsCheckbox;

    @FindBy(xpath = "//img[contains(@src, 'details.gif')]")
    public WebElement viewInformationIcon;

    @FindBy(xpath = "//span[@class='x-tab-strip-text ' and text()='Implementation Plan']")
    public WebElement implementationTab;

    @FindBy(xpath = "div[@class='x-grid3-row x-grid3-row-first x-grid3-row-last']//img[contains(@src, 'details.gif')]")
    public WebElement ViewIconCNPage;

    @FindBy(xpath = "//div[contains(text(), 'Affected / Resulting Objects')]")
    public WebElement affectedObjectTab;

    @FindBy(xpath = "//button[contains(text(),'Actions')]")
    public WebElement actions;

    @FindBy(xpath = "//a[@id='object_redline__changeTask_affectedItems_table']")
    public WebElement redline;

    @FindBy(xpath = "//span[normalize-space(text()) ='Create BOM Redline']")
    public WebElement createBOMRedline;

    @FindBy(xpath = "//span[contains(text(), 'Delete BOM')]")
    public WebElement deleteBOMRredline;

    @FindBy(xpath = "//div[@id='changeNotice_implementationPlan_table']//img[normalize-space(@src)='netmarkets/images/details.gif']")
    public WebElement itemRowsImpTab;

    @FindBy(xpath = "//div[@id='table__changeTask_affectedItems_table_TABLE']//div[@class='x-grid3-row-checker']")
    public WebElement afectedobjcheck;

    @FindBy(xpath = "//div[@id='table__changeTask_affectedItems_table_TABLE']//button[normalize-space(text()) ='Actions']")
    public WebElement afectedobjcheckaction;

    @FindBy(xpath = "//button[normalize-space(text()) ='Insert New']")
    public WebElement insertNewButton;

    @FindBy(xpath = "//button[normalize-space(text()) ='Insert Existing']")
    public WebElement insertExistingButton;

    @FindBy(xpath = "//div[@class=\"x-form-item \"]//label[contains(text(),'Number:')]/following-sibling::div/div/table/tbody/tr/td/div/input")
    public WebElement serchNumber;

    @FindBy(xpath = "//button[normalize-space(text()) ='Search']")
    public WebElement serchbutton;

    @FindBy(xpath = "//button[normalize-space(text()) ='OK']")
    public WebElement okbutton;

    @FindBy(xpath = "//span[normalize-space(text()) ='View BOM Redline']")
    public WebElement viewBOMRedline;
    
 //Please write from here Dynamic locator method - cannot use @FindBy
    
    public By verifyPartBOM(String name) {
        return By.xpath("//span[contains(text(), '" + name + "')]");
    }
    
    public By checkObject(String objectName) {
        return By.xpath("//*[text()='" + objectName + "']//ancestor::td//preceding-sibling::td//div[contains(@class,'row-checker')]");
    }
    
    public By itemRows = By.xpath("//div[@id='changeTask_affectedItems_table']//table[@class='x-grid3-row-table']");

    public By itemName(String name) {
        return By.xpath("//a[contains(text(), '" + name + "')]");
    }
}
