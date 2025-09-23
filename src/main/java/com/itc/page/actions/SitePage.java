package com.itc.page.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.itc.base.BaseTest;
import com.itc.pagelocators.SitePageLocator;
import com.itc.utilities.ElementActions;

import groovy.time.Duration;

public class SitePage extends BaseTest{
	
		public SitePageLocator Site;
		
		public SitePage()
		{
			this.Site=new SitePageLocator();
			PageFactory.initElements(driver, this.Site);
			
		}

		public void Site() {
			ElementActions.click(Site.siteTab);
		}
		
		public void Utilities() {
			ElementActions.click(Site.Utilities);
		}
		
		public void PreferenceManagement() {
			ElementActions.click(Site.preferenceManagementLink);
		}
		
		public void ExpandChangeManagement() {
			ElementActions.click(Site.changeManagementExpand);
		}
		
		public void rightClickEBR() {
		    try {
		        Actions actions = new Actions(driver);
		        actions.contextClick(Site.ebrRightClick).perform();
		        System.out.println("Right-clicked on element: " );
		    } catch (Exception e) {
		        throw new RuntimeException("Failed to right-click on: ", e);
		    }
		}
		
		public void SetPreference() {
			ElementActions.click(Site.setPreference);
		}

		public void ClickOnRevertButton() {
			ElementActions.click(Site.revertButton);
		}
		
		public void SetPreferenceValue() {
			ElementActions.click(Site.setPreferenceValue);
		}
		public void ClickOkButton() {
			ElementActions.click(Site.clickOkButton);
		}
		
}
