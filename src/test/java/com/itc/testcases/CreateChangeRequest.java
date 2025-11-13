package com.itc.testcases; 
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.itc.Listeners.CustomListeners;
import com.itc.base.BaseTest;
import com.itc.page.actions.BOMPageActions;
import com.itc.page.actions.BrowsePage;
import com.itc.page.actions.ChangeRequestPage;
import com.itc.page.actions.HomePage;
import com.itc.page.actions.NewPartPage;
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
* User should be able to create new change notice.
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
*
* Expected Behaviour:
* -----------
* 1.User should be able to create new change request.
*
* @author "*****"
*/
@Listeners(CustomListeners.class)
public class CreateChangeRequest extends BaseTest {
		HomePage home;
		BrowsePage browse;
		ProductPage product;
		private XMLReader xmlReader;
		private String productName;
		String changeRequestName;
		ChangeRequestPage ChangeRequest ;
		String ChangeObjects;
		NewPartPage newPartPage;
		BOMPageActions BOMPag;
 
		@BeforeClass
			public void setup() {
			config = ConfigReader.getProperties();
			xmlReader = new XMLReader();
			productName = xmlReader.getData("product");
			ChangeObjects=xmlReader.getData("ChangeObjects");
			changeRequestName = "CR_New - " + generateRandomNumber(5);
		}
 
		@BeforeMethod
		public void beforeMethod() throws Exception {
			initializeDriver();
			home = new HomePage();
			browse = new BrowsePage();
			product = new ProductPage();
			ChangeRequest = new ChangeRequestPage();
			newPartPage = new NewPartPage();
			BOMPag =new BOMPageActions();
		}

		@Test
	    @TestInfo(FunctionalArea = "Create New Chnage Request",
	        Owner = "rsakhare",
	        Tags = { "QA", "Functional", "05-03-2025" },
	        TestCaseID = "",
	        Description = "User creates a New Change Request in product")
		
		public void verifyCreateChangeRequest() {
		
				LogUtil.info("Login windchill" );
				loginToWindchill("windchillSignOndemouser", "windchillSignOnPassword");
  
				LogUtil.info("Navigating to Browse" );
				home.gotoBrowse();
 
				LogUtil.info("Click on recent products" );
				browse.RecentProducts();
 
				LogUtil.info("Click on expand  and navigate to \"Folders\" of product" );
				browse.openSpecificSectionOfProduct(productName, "Folders");
				String parentWindow = driver.getWindowHandle();
 
				LogUtil.info("Click on Action " );
				product.gotoActions();
				product.gotoNewLink();
 
				LogUtil.info(" click on Create new chnage request link " );
				product.gotoNewChangeRequestLink();
				switchToNewWindow();
 
				LogUtil.info(" Enter change request name" );
				ChangeRequest.enterchangerequestname(changeRequestName);
				ChangeRequest.clickNextbtn();
				System.out.println(driver.getTitle());
 
				LogUtil.info(" Click on next button");
				ChangeRequest.clickNextbtn();
 
				LogUtil.info(" Click on Finish button");
				ChangeRequest.clickFinish();
 
				LogUtil.info(" Click on Submit button");
				ChangeRequest.clickSubmit();
				switchToMainWindow(parentWindow);
 
				LogUtil.info(" Verify  Chnage Request Created");
				ChangeRequest.verifyChangeRequestCreated();
		}
	 
	@AfterTest
	public void tearDown() {
		    LogUtil.info("Delete CR");
			home.gotoBrowse();
			browse.RecentProducts();
			browse.openSpecificSectionOfProduct(productName, "Folders");
			product.folderContentsDropdown(ChangeObjects);
			newPartPage.searchObject(changeRequestName);
			BOMPag.selectObject(changeRequestName);
			product.gotoActions();
			product.takeActions("Delete");
			try {
			WaitUtils.isAlertPresent(); 
			LogUtil.info("Alert was accepted.");
			performAlertAction("accept");
			}catch(Exception e) {
			}
			sessionEnd();
	}
	}