package com.itc.page.actions;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.itc.base.BaseTest;
import com.itc.pagelocators.CommonLocators;
import com.itc.pagelocators.EditPartPageLocators;
import com.itc.utilities.ElementActions;
import com.itc.utilities.WaitUtils;

public class EditPartPage extends BaseTest {

	public EditPartPageLocators EditPart;
	public CommonLocators commonLocators;

	public EditPartPage() {
		this.EditPart = new EditPartPageLocators();
		PageFactory.initElements(driver, this.EditPart);
		this.commonLocators = new CommonLocators();
		PageFactory.initElements(driver, this.commonLocators);
	}

	public void gotodropdownAssemblymode() {
		ElementActions.click(EditPart.getdropdownAssemblyMode);
	}

	public void saveButton() {
		ElementActions.click(EditPart.SaveButton);
	}

	public void SelectViaDropDownAssemblymode(String Value) {
		Select dropdown = new Select(EditPart.getdropdownAssemblyMode);
		dropdown.selectByVisibleText(Value);
	}

	public void CheckInButton() {
		ElementActions.click(EditPart.CheckIn);
	}

	public void EnterComments(String Comments) {
		WaitUtils.waitForSeconds(1);
		ElementActions.sendKeys(EditPart.Comments, Comments);
	}

	public void OkButton() {
		ElementActions.click(EditPart.Ok);
	}

}
