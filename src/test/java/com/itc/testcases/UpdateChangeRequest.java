package com.itc.testcases;
 
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
 
import com.itc.base.BaseTest;
import com.itc.page.actions.BrowsePage;
import com.itc.page.actions.ChangeRequestPage;
import com.itc.page.actions.EditChangeRequestPage;
import com.itc.page.actions.HomePage;
import com.itc.page.actions.ProductPage;
import com.itc.utilities.ConfigReader;
import com.itc.utilities.LogUtil;
import com.itc.utilities.TestInfo;
import com.itc.utilities.WaitUtils;
import com.itc.utilities.XMLReader;
 
/**
*
* Summary
*--------------
* User should be able Update Change Request.
*
* Prerequisite :
* -----------------
* 1. Test User Credentials - Product Manager , Design Engineer
* 2. Product Name
* 3. Part Name
* 4. documentName
* 5. softType
* 6. partNumber
*
* Steps:
* -------
* 1. Login to Windchill Server
* 2. Navigate to Product folder
* 3. Navigate to Structure Tab of the Part
* 4. Click  on Actions --> New ---->New Change Request
* 5. User fills in required details in the form and clicks on "Next". (Note: All fields marked in * are mandatory fields).
* 6. User will search and add required " End Items" associated to problem report and clicks on "Next"
* 7. User will search and add required " Affected Items" for which problem report  is created  and clicks on "Next"
* 8. User will attach any local file or URL Link or external storage and click on "Next"
* 9. User will add associated process objects and associated reference objects and click on "Finish"
* 10. Navigate to product container.
* 11. Click on expand  and navigate to "Folders" of product.
* 13. Select newly created Change Request and right click.
* 14. Click  on Edit.
* 15. Change the attributes and click finish.
* 
* Expected Behaviour:
* -----------
* 1.User should be able to create new change request.
*
* @author "*****"
*/
 
public class UpdateChangeRequest extends BaseTest {
	    HomePage home;
		BrowsePage browse;
		ProductPage product;
		EditChangeRequestPage editChangeRequest;
		ChangeRequestPage ChangeRequest;
		private XMLReader xmlReader;
		private String productName;
		String changeRequestName;
		String changeRequestNameUpdated;
		@BeforeClass
		public void setup() {
			LogUtil.info("Launching login page");
			config = ConfigReader.getProperties();
			xmlReader = new XMLReader();
			productName = xmlReader.getData("product");
			changeRequestName = "CR_New - " + generateRandomNumber(3);
			changeRequestNameUpdated = "CR_Updated - " + generateRandomNumber(3);
	}
	 @BeforeMethod
		public void beforeMethod() throws Exception {
			home = new HomePage();
			browse = new BrowsePage();
			product = new ProductPage();
			editChangeRequest = new EditChangeRequestPage();
			ChangeRequest = new ChangeRequestPage();
		}
	 @Test
	    @TestInfo(FunctionalArea = "Change Request",
	        Owner = "slandge",
	        Tags = { "QA", "Functional", "05-03-2025" },
	        TestCaseID = "",
	        Description = "User update Change Request")
		public void updatechangerequest() {
		 
			LogUtil.info("Login windchill");
			loginToWindchill("windchillSignOndemouser", "windchillSignOnPassword");
 
			LogUtil.info("Navigating to Browse page");
			home.gotoBrowse();
			waitForSeconds(3);
 
			LogUtil.info("Click on recent products");
			browse.RecentProducts();
 
			LogUtil.info("Click on expand  and navigate to \"Folders\" of product");
			browse.openSpecificSectionOfProduct(productName, "Folders");
			waitForSeconds(3);
			String parentWindow = driver.getWindowHandle();
 
			LogUtil.info("Navigate to product page ");
			ProductPage product = new ProductPage();
 
			LogUtil.info("Click on Action ");
			product.gotoActions();
			product.gotoNewLink();
			waitForSeconds(3);
 
			LogUtil.info(" click on Create new chnage request link ");
			product.gotoNewChangeRequestLink();
			switchToNewWindow();
			waitForSeconds(3);
 
			LogUtil.info(" Enter change request name");
			ChangeRequest.enterchangerequestname(changeRequestName);
			ChangeRequest.clickNextbtn();
			System.out.println(driver.getTitle());
			waitForSeconds(3);
 
			LogUtil.info(" Click on next button");
			ChangeRequest.clickNextbtn();
			waitForSeconds(3);		 
			System.out.println(driver.getTitle());			 
			waitForSeconds(3);
 
			LogUtil.info(" Click on Finish button");
			ChangeRequest.clickFinish();
 
			LogUtil.info(" Click on Submit button");
			ChangeRequest.clickSubmit();
			
			LogUtil.info("Verify change request created");
			ChangeRequest.verifyChangeRequestCreated();
 
			switchToMainWindow(parentWindow);
			waitForSeconds(5);
 
			LogUtil.info("Select Change Object option from folder content dropdown");
			product.folderContentsDropdown("Change Objects");
			waitForSeconds(3);
 
			LogUtil.info("select Newly created change Request");
			product.selectFolderCheckbox(changeRequestName);
			waitForSeconds(3);
 
			LogUtil.info("Select change request from folder content dropdown");
			product.rightClickOnCRCheckedCheckbox();
			waitForSeconds(3);
			product.clickEditOptionChangeRequest();
			
			LogUtil.info("Switch to new window");
			switchToWindowByHeader("Edit Change Request");
	    	 switchToNewWindow();
			waitForSeconds(3);
			
			LogUtil.info("Clear existing name ");
			editChangeRequest.clearNameToUpdate();
			
			LogUtil.info("Enter name to update");
			waitForSeconds(3);
			editChangeRequest.entername(changeRequestNameUpdated);
			
			LogUtil.info("click on finish button");
			editChangeRequest.clickFinish();
			
			LogUtil.info("Verify change request edited");
			ChangeRequest.verifyChangeRequestEdited(changeRequestNameUpdated);
			
		}  

	 @AfterTest
		public void tearDown() {
		        LogUtil.info("Delete CN");
 				home.gotoBrowse();
				browse.RecentProducts();
				browse.openSpecificSectionOfProduct(productName, "Folders");
				product.folderContentsDropdown(xmlReader.getData("Change Objects"));
				product.selectFolderCheckbox(changeRequestNameUpdated);
				product.gotoActions();
				product.takeActions("Delete");
				if (WaitUtils.isAlertPresent()) {
					performAlertAction("gettext");
					performAlertAction("accept");
					LogUtil.info("Alert was accepted.");
				} else {
					LogUtil.info("No alert Present");
				}
				WaitUtils.waitForSeconds(2);
				sessionEnd();
			} 
	}