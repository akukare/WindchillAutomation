package com.itc.page.actions;
 
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.itc.base.BaseTest;

import com.itc.pagelocators.ChangeNoticeLocators;

import com.itc.utilities.LogUtil;
 
 
public class ChangeNoticePage extends BaseTest{

	public ChangeNoticeLocators ChangeNotice;

	public ChangeNoticePage() {

		this.ChangeNotice=new ChangeNoticeLocators();

		PageFactory.initElements(driver, this.ChangeNotice);

	}


		public void enterchangerequestname(String Name) {

			  type(ChangeNotice.Name,Name);

			}

		public void clickNextbtn() {

			click(ChangeNotice.Nextbtn);

		}

		public void clickFinish()

		{

			click(ChangeNotice.FinishButton);

		}
 
		public void clickSubmit()

		{

			click(ChangeNotice.Submit);

		}
 
		public void clickAddnumbertextbox()

		{

			click(ChangeNotice.AddNumbertextbox);

		}

		public void enternumber(String Number)

		{

			type(ChangeNotice.AddNumbertextbox, Number);

		}

		public void clearName() {

			 clear(ChangeNotice.Name);

		}

		public void clickEditChangeTask() {

			click(ChangeNotice.EditChangeTask);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			WebElement iframeElement = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//iframe[contains(@id,'popCreateWizard')]")));
			driver.switchTo().frame(iframeElement);

		}
 
		public void clearChangeTaskName() {

			clear(ChangeNotice.ChangeTaskName);

		}
 
		public void enterChangeTaskName(String Name) {
 
			type(ChangeNotice.Name, Name);

		}

		public void verifyChangeNoticeCreated() {

		    try {

		        waitForElementVisible(ChangeNotice.successMessage, 20);

		        if (ChangeNotice.successMessage.isDisplayed()) {

		            System.out.println("Change Notice creation confirmed.");

		            Assert.assertTrue(true, "Change Notice creation successful.");

		            click(ChangeNotice.successMessageLink);

		            if(ChangeNotice.licenseAlertClose.isDisplayed()) {

		            	click(ChangeNotice.licenseAlertClose);

		            }

		        } else {

		        	LogUtil.info("Confirmation message not displayed.");

		            Assert.fail("Change Notice creation confirmation message not displayed.");

		        }

		    } catch (Exception e) {

		    	LogUtil.info("Error during Change Notice creation verification: " );

		    }

		}

		/**

		 * Verifies that the promotional request edit was successful by checking the updated name.

		 *

		 * @param expectedName Expected name after edit

		 */

		public void verifyChangeNoticeEdited(String expectedName) {

		    try {

		        waitForElementVisible(ChangeNotice.nameElement, 20);

		        String actualName = ChangeNotice.nameElement.getText().trim();

		        if (actualName.equals(expectedName)) {

		            System.out.println("ChangeNotice edited successfully.");

		            highlightElement(ChangeNotice.nameElement);

		            Assert.assertTrue(true, "ChangeNotice name updated correctly.");

		        } else {

		        	LogUtil.info("ChangeNotice name not updated as expected.");

		            Assert.fail("Expected name: " + expectedName + " | Actual name: " + actualName);

		        }

		    } catch (Exception e) {

		    	LogUtil.info("Error during ChangeNotice edit verification: " );

		    }

		}
		public void clickSelectAffectedObjectTab() {
			click(ChangeNotice.selectAffectedObjectWindow);
			
			}
	 
	
		

}



 