package com.itc.page.actions;

import org.openqa.selenium.support.PageFactory;

import com.itc.base.BaseTest;
import com.itc.pagelocators.EditChangeRequestLocators;

public class EditChangeRequestPage extends BaseTest {

	public EditChangeRequestLocators EditChange;

	public EditChangeRequestPage() {
		this.EditChange = new EditChangeRequestLocators();
		PageFactory.initElements(driver, this.EditChange);
	}

	public void gotoActions() {
		click(EditChange.Actions);
	}

	public void clickEdit() {
		click(EditChange.Edit);
	}

	public void entername(String Name) {

		EditChange.Name.clear();
		type(EditChange.Name, Name);

	}

	public void clickFinish() {
		click(EditChange.FinishButton);
	}

	public void clearNameToUpdate() {
		clear(EditChange.Name);
	}

}
