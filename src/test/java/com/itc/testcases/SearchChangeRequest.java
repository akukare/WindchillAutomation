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
* User should be able to search new change Request in SerchInTable.
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
* 10. Click Serch In Table
* 11. Enter Created Document Name
* 12. Click Serch In Table Icon
*
* Expected Behaviour:
* -----------
* 1.User should be able to Search created change request in SerchInTable.
*
* @author "*****"
*/
@Listeners(CustomListeners.class)
public class SearchChangeRequest extends BaseTest {
		HomePage home;
		BrowsePage browse;
		ProductPage product;
		String changeRequestName;
		ChangeRequestPage ChangeRequest ;
		String ChangeObjects;
		NewPartPage newPartPage;
		BOMPageActions BOMPag;
		private XMLReader xmlReader;
		private String productName;
		private String folderDropdownText;
 
		@BeforeClass
			public void setup() {
			config = ConfigReader.getProperties();
			xmlReader = new XMLReader();
			productName = xmlReader.getData("product");
			ChangeObjects=xmlReader.getData("ChangeObjects");
			folderDropdownText = xmlReader.getData("folderDropdownText");
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
	    @TestInfo(FunctionalArea = "Search Change Request in SerchInTable",
	        Owner = "rsakhare",
	        Tags = { "QA", "Functional", "05-03-2025" },
	        TestCaseID = "",
	        Description = "User should be able to search new change Request in SerchInTable")
		
		public void verifySearchChangeRequest() {
		
				LogUtil.info("Login windchill" );
				loginToWindchill("windchillSignOndemouser", "windchillSignOnPassword");
  
				LogUtil.info("Navigating to Browse" );
				home.gotoBrowse();
 
				LogUtil.info("Click on recent products" );
				browse.RecentProducts();
 
				LogUtil.info("Click on expand  and navigate to Folders of product" );
				browse.openSpecificSectionOfProduct(productName, "Folders");
 
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

				switchToNewWindow();
		        product.folderContentsDropdown(folderDropdownText);
		        LogUtil.info("Serch Created Document in SerchInTable");
		        product.searchObject(changeRequestName);

				LogUtil.info("Verify Chnage Request Created");
				ChangeRequest.validateChangeRequestIsPresent(changeRequestName);
		}

	@AfterTest
	public void tearDown() {
		    LogUtil.info("Delete Change Request");
			home.gotoBrowse();
			browse.RecentProducts();
			browse.openSpecificSectionOfProduct(productName, "Folders");
			product.folderContentsDropdown(folderDropdownText);
			product.SelectObjectCheckbox(changeRequestName);
			product.gotoActions();
			product.takeActions("Delete");
			WaitUtils.isAlertPresent();

			LogUtil.info("Alert was accepted.");
			performAlertAction("accept");
			WaitUtils.waitForSeconds(2);
			sessionEnd();
	}
}