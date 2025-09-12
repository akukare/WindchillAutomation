package com.itc.page.actions;

import org.openqa.selenium.support.PageFactory;

import com.itc.base.BaseTest;
import com.itc.pagelocators.CommonLocators;
import com.itc.pagelocators.EditPromotionRequestLocators;
import com.itc.utilities.ElementActions;
import com.itc.utilities.WaitUtils;

public class EditPromotionRequestPage extends BaseTest {
	
	
	public EditPromotionRequestLocators EditPromotionRequest;
	public CommonLocators commonLocators;
	
	public EditPromotionRequestPage() {
		this.EditPromotionRequest=new EditPromotionRequestLocators();
		PageFactory.initElements(driver, this.EditPromotionRequest);
		this.commonLocators = new CommonLocators();
		PageFactory.initElements(driver, this.commonLocators);
	}
	
	public void gotoActions() {
		ElementActions.click(EditPromotionRequest.Actions);
	}

	public void clickEdit()
	{
		ElementActions.click(EditPromotionRequest.Edit);
	}


	public void entername(String Name) {

		ElementActions.click(EditPromotionRequest.Name);
		clear(EditPromotionRequest.Name);
    	WaitUtils.waitForSeconds(1);
		ElementActions.sendKeys(EditPromotionRequest.Name, Name);
		
	}

	public void clickFinish() {
		ElementActions.click(EditPromotionRequest.FinishButton);
	}
	
}
