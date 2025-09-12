package com.itc.pagelocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PromotionRequestLocators {

	@FindBy(xpath = "//td[@attrid='name']/input[@type='text']")
	public WebElement Name;

	@FindBy(xpath = "//button[contains(text(),'ext')]")
	public WebElement Nextbtn;
	
	@FindBy(xpath = "//input[@id='ext-comp-1058']")
	public WebElement Addbynumber;
	
	@FindBy(xpath = "//button[contains(text(),'inish')]")
	public WebElement  FinishButton;
	
	
	@FindBy(xpath = "//b[contains(text(),'Submit Now')]")
	public WebElement  Submit;
	
	@FindBy(xpath = "//select[@id='maturityState']")
	public WebElement getTargetPromotionState;
	
	@FindBy(xpath = "//table[@id='promotionRequest.promotionObjectspromoteObjects_shortcutbar']//button[@class=' x-btn-text blist']")
	public WebElement setObjectforPromotion;
	
	@FindBy(xpath = "//td[contains(@attrid, 'name')]")
	public WebElement nameElement;

	public By commentTextArea = By.xpath("//div[@class = 'x-grid3-cell-inner x-grid3-col-promotionTargetComments']/textarea");
	
	@FindBy(xpath = "//div[@class = 'x-grid3-row-checker']")
	public WebElement clickCheckbox;
}
