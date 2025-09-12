package com.itc.page.actions;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.itc.base.BaseTest;
import com.itc.pagelocators.CommonLocators;
import com.itc.pagelocators.ReassignTasksPageLocators;
import com.itc.utilities.ElementActions;

public class ReassignTasksPage extends BaseTest {

	public ReassignTasksPageLocators reassigntasks;
	public CommonLocators commonLocators;

	public ReassignTasksPage() {

		this.reassigntasks = new ReassignTasksPageLocators();
		PageFactory.initElements(driver, this.reassigntasks);
		this.commonLocators = new CommonLocators();
		PageFactory.initElements(driver, this.commonLocators);
	}

	public void gotoReassignto_dropdown() {

		ElementActions.click(reassigntasks.getdropdownreassignto);
	}

	public void Select_reassignto(String Value) {
		Select reassignto_dropdown = new Select(reassigntasks.getdropdownreassignto);

		reassignto_dropdown.selectByVisibleText(Value);
	}

	public void clickOK_btn() {

		ElementActions.click(reassigntasks.OK_btn);
	}

	public void click_Reassigntask() {
		ElementActions.click(reassigntasks.Reassigntasks);

	}


}
