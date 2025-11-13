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
import com.itc.page.actions.ChangeNoticePage;
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
* 4. Click  on Actions --> New ---->New Change Notice
* 5. User fills in required details in the form and clicks on "Next". (Note: All fields marked in * are mandatory fields).
* 6. Click Edit default change plan.
* 7. Update Change Notice attributes in "
* 8. Set Attributes" tab and Click "Next"
* 9. Select appropriate Security label and "Export Controlled" fields and clicks on "Next"
* 10. Click "Next" and Attach any local file or URL Link or external storage and click on "Next"
* 11. Add associated process objects and associated reference objects and click on "Finish"
*
* Expected Behaviour:
* -----------
* 1.User should be able to create new change notice.
*
* @author "*****"
*/
 

@Listeners(CustomListeners.class)
public class CreateChangeNotice extends BaseTest{
 
	HomePage home;
	BrowsePage browse;
	ProductPage product;
	ChangeNoticePage ChangeNotice;
	private XMLReader xmlReader;
	private String productName;
	String changeNoticeName;
	String ChangeObjects;
	NewPartPage newPartPage;
	BOMPageActions BOMPag;
 
	@BeforeClass
	public void setup() throws Exception {
		config = ConfigReader.getProperties();
		xmlReader = new XMLReader();
		productName = xmlReader.getData("product");
		changeNoticeName = "CN_New - " + generateRandomNumber(5);
		ChangeObjects=xmlReader.getData("ChangeObjects");
}

 
	@BeforeMethod
	public void beforeMethod() throws Exception {
		initializeDriver();
		home = new HomePage();
		browse = new BrowsePage();
		product = new ProductPage();
		ChangeNotice = new ChangeNoticePage();
		newPartPage = new NewPartPage();
		BOMPag =new BOMPageActions();
	}
	  @Test
	    @TestInfo(FunctionalArea = "CHANGE_MANAGEMENT",
	        Owner = "slandge",
	        Tags = { "QA", "Functional", "05-03-2025" },
	        TestCaseID = "",
	        Description = "Verify that any eligible type of Change Task can be created based on association rules(1:M).")
	
	public void verifyCreateChangeNotice() {
			LogUtil.info("Login windchill");
			loginToWindchill("windchillSignOndemouser", "windchillSignOnPassworddemouser");
 
			LogUtil.info("Navigating to Browse page");
			home.gotoBrowse();
 
			LogUtil.info("Click on recent products");
			browse.RecentProducts();
 
			LogUtil.info("Click on expand  and navigate to \"Folders\" of product");
			browse.openSpecificSectionOfProduct(productName, "Folders");
			String parentWindow = driver.getWindowHandle();
 
			LogUtil.info("Click on action");
			product.gotoActions();
			product.gotoNewLink();
 
			LogUtil.info("Click on new change notice");
			product.gotoNewChangeNoticeLink();
			switchToNewWindow();
 
			LogUtil.info("Create new change notice");
			ChangeNotice.clearName();
 
			ChangeNotice.enterChangeNoticeName(changeNoticeName);
			ChangeNotice.clickNextbtn();
 
			LogUtil.info("Click on finish");
			ChangeNotice.clickFinish();
			switchToMainWindow(parentWindow);
 
			LogUtil.info(" Verify  Chnage Notice Created");
			ChangeNotice.verifyChangeNoticeCreated();
	}
 
		@AfterTest
		public void tearDown() {
			    LogUtil.info("Delete CN");
				home.gotoBrowse();
				browse.RecentProducts();
				browse.openSpecificSectionOfProduct(productName, "Folders");
				product.folderContentsDropdown(ChangeObjects);
				newPartPage.searchObject(changeNoticeName);
				BOMPag.selectObject(changeNoticeName);
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