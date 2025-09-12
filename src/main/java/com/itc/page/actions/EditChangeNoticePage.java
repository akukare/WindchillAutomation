package com.itc.page.actions;

import org.openqa.selenium.support.PageFactory;

import com.itc.base.BaseTest;
import com.itc.pagelocators.EditChangeNoticeLocators;

public class EditChangeNoticePage extends BaseTest{
	
	

		public EditChangeNoticeLocators EditChangeNotice;
		
		public EditChangeNoticePage() {
			this.EditChangeNotice=new EditChangeNoticeLocators();
			PageFactory.initElements(driver, this.EditChangeNotice);
		}
		
		public void gotoActions() {
			click(EditChangeNotice.Actions);
		}

		public void clickEdit()
		{
			click(EditChangeNotice.Edit);
		}

		public void entername(String Name) {
			
			EditChangeNotice.Name.clear();
			type(EditChangeNotice.Name,Name);
			
		}

		public void clickFinish() {
			click(EditChangeNotice.FinishButton);
		}
		
		public void clearNameToUpdate() {
			 clear(EditChangeNotice.Name);
		}
	}

