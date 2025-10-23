package com.itc.pagelocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPageLocators {

    @FindBy(xpath = "//tbody/tr/td/div/a[text()='Autotest1']//ancestor::tr[1]/td[10]")
    public WebElement ViewPartIcon;

    @FindBy(xpath = "//td[@attrid='name']")
    public WebElement ViewPartName;

    @FindBy(xpath = "//div[@id='folderbrowser_PDM.toolBar']//button[@class=' x-btn-text']")
    public WebElement Actions;

    @FindBy(xpath = "//span[contains(text(),'Check Out and Edit')]")
    public WebElement CheckOutandEdit;

    @FindBy(xpath = "//span[contains(text(),'Delete')]")
    public WebElement Delete;

    @FindBy(xpath = "//td[@attrid='partType']")
    public WebElement getAssemblycodewebelement;

    @FindBy(xpath = "//tbody/tr/td/div/a[text()='Autotest1']//ancestor::tr[1]/td[1]")
    public WebElement PartCheckbox;

    @FindBy(xpath = "//form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]//td[7]")
    public WebElement FolderContentTable;

    @FindBy(xpath = "//a[@id='object_folderbrowser_toolbar_new_submenu__folderbrowser_PDM']")
    public WebElement Newlink;

    @FindBy(xpath = "//span[contains(text(),'New Problem Report')]")
    public WebElement NewProblemReportLink;

    @FindBy(xpath = "//span[contains(text(),'New Change Request')]")
    public WebElement NewChangeRequestLink;

    @FindBy(xpath = "//span[contains(text(),'New Change Notice')]")
    public WebElement NewChangeNoticeLink;

    @FindBy(xpath = "//span[contains(text(),'New Promotion Request')]")
    public WebElement NewPromotionRequestLink;

    @FindBy(xpath = "//a[text()='testProblemReport-428165']")
    public WebElement problemreportlink;

    @FindBy(xpath = "//b[contains(text(), 'CONFIRMATION: Create successful')]")
    public WebElement successMessage;

    @FindBy(xpath = "//a[@class = 'msgIdentityText']")
    public WebElement successMessageLink;

    @FindBy(xpath = "//div[@class = 'x-tool x-tool-close']")
    public WebElement licenseAlertClose;

    @FindBy(xpath = "//span[@class='x-menu-item-text' and text()='Edit']")
    public WebElement editOption;

    @FindBy(xpath = "//div[@id='folderbrowser_PDM']//table[@class='x-grid3-row-table']")
    public WebElement itemRows;

    @FindBy(xpath = "//div[@class='x-grid3-row-checker']")
    public WebElement checkbox;
    
    @FindBy(xpath = "//button[contains(text(),'Actions')]")
	public WebElement actionButton;
	
	@FindBy(xpath = "//table[@id='infoPagedetailsPageActionsMenu']//button[contains(text(),'Actions')]")
	public WebElement prActionButton;
    
    //Please write from here Dynamic locator method - cannot use @FindBy
    public By actionMenuItems(String name) {
        return By.xpath("//span[@class='x-menu-item-text' and contains(text(), '" + name + "')]");
    }

    public By itemName(String name) {
        return By.xpath("//a[contains(text(), '" + name + "')]");
    }

    public static final String folderBrowserinputXpath = "//input[@id='folderbrowser_PDMfilterSelect']";
    
    @FindBy(xpath = "//input[@id='folderbrowser_PDM.searchInListTextBox']")
	public WebElement searchInputFolderPage;
	
	public  By documentCheckbox(String documentName) {
	    return By.xpath("//a[contains(text(),'" + documentName + "')]/ancestor::tr//div[@class='x-grid3-cell-inner x-grid3-col-checker']");
	}
 
	public  By checkedOutImage(String partName) {
	    return By.xpath("//a[contains(text(),'" + partName + "')]/ancestor::tr//img[contains(@src,'checkedout_byyou9x9.gif')]");
	}
 
	public By clickOnObj(String partName) {
	    return By.xpath("//a[contains(text(),'" + partName + "')]");
	}
	
	@FindBy(xpath = "//li[@id='infoPageinfoPanelID__infoPage_myTab_object_partInfoRelatedItemsTab']")
	public WebElement  relatedObj;
	
	@FindBy(xpath = "//div[@id='part.relatedPartsDescribedByDocuments.list.toolBar']//button[contains(@style,'newdoc.gif')]")
	public WebElement  newDoc;
	
	public By newname = By.xpath("//*[contains(@name,'newName_OR:wt_u46_part_u46_WTPart')]");
 
	@FindBy(xpath = "//button[contains(text(),'K')]")
	public WebElement clickOk;
}
