package com.itc.page.actions;

import org.openqa.selenium.support.PageFactory;

import com.itc.base.BaseTest;
import com.itc.pagelocators.CommonLocators;
import com.itc.pagelocators.EditProblemReportLocators;
import com.itc.utilities.ElementActions;
import com.itc.utilities.WaitUtils;

public class EditProblemReport extends BaseTest {

public EditProblemReportLocators EditProblem;
public CommonLocators commonLocators;	
	
	public EditProblemReport() {
		this.EditProblem=new EditProblemReportLocators();
		PageFactory.initElements(driver, this.EditProblem);
		this.commonLocators = new CommonLocators();
		PageFactory.initElements(driver, this.commonLocators);
	}


	public void gotoActions() {

		ElementActions.click(EditProblem.Actions);
	}

	public void clickEdit()
	{
		ElementActions.click(EditProblem.Edit);
	}


	public void entername(String Name) {
		clear(EditProblem.Name);
		WaitUtils.waitForSeconds(1);
		ElementActions.sendKeys(EditProblem.Name,Name);

	}

	public void clickFinish() {
		ElementActions.click(EditProblem.FinishButton);
	}

}
