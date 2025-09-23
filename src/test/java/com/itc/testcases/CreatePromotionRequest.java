package com.itc.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.itc.Listeners.CustomListeners;
import com.itc.base.BaseTest;
import com.itc.page.actions.BrowsePage;
import com.itc.page.actions.HomePage;
import com.itc.page.actions.NewPartPage;
import com.itc.page.actions.ProductPage;
import com.itc.page.actions.PromotionRequestPage;
import com.itc.utilities.ConfigReader;
import com.itc.utilities.LogUtil;
import com.itc.utilities.TestInfo;
import com.itc.utilities.WaitUtils;
import com.itc.utilities.XMLReader;

/**
*
* Summary
*--------------
* User should be able to Create Promotion Request.
*
* Prerequisite :
* -----------------
* 1. Test User Credentials - Product Manager , Design Engineer
* 2. Product Name
* 3. Part Name
*
* Steps:
* -------
* 1. Login to Windchill Server
* 2. Navigate to Product Container
* 3. Click on expand  and navigate to "Folders" of product 
* 4. Click  on Actions --> New ---->New Part 
* 5. User fills in required details in the form and clicks on "Next". (Note: All fields marked in * are mandatory fields).
* 6. Navigate to Product Container
* 7. Click on expand  and navigate to "Folders" of product 
* 8. Select newly created Part
* 9. Click  on Actions --> New ---->New Promotion Request
* 10. Fill all mandatory fields and click on finish
* 11. Verify New Promotion Request created
*
* Expected Behaviour:
* -----------
* 1.User should be able to Create New Promotion Request.
*
* @author "*****"
*/


@Listeners(CustomListeners.class)
public class CreatePromotionRequest extends BaseTest {
	HomePage home;
	BrowsePage browse;
	ProductPage product;
	NewPartPage newPartPage;
	PromotionRequestPage promotionrequest;
	String partName;
	String prName;
	private XMLReader xmlReader;
	private String productName;

	@BeforeClass
	public void setup() throws Exception {

		config = ConfigReader.getProperties();
		xmlReader = new XMLReader();
		productName = xmlReader.getData("product");
		partName = "newPart-" + generateRandomNumber(6);
		prName = "newPromotionRequest-" + generateRandomNumber(6);
	}

	@BeforeMethod
	public void initPages() throws Exception {

		initializeDriver();
		home = new HomePage();
		browse = new BrowsePage();
		product = new ProductPage();
		newPartPage = new NewPartPage();
		promotionrequest = new PromotionRequestPage();
		}
	@Test
    @TestInfo(FunctionalArea = "BPromotional Request",
        Owner = "rsakhare",
        Tags = { "QA", "Functional", "05-03-2025" },
        TestCaseID = "",
        Description = "User creates a New Promotion Request in product")
	
	public void createPromotionRequest() {
		
			LogUtil.info("Login windchill");
			loginToWindchill("windchillSignOndemouser", "windchillSignOnPassword");

			LogUtil.info("Navigating to Browse page");
			home.gotoBrowse();

			LogUtil.info("Accessing recent products");
			browse.RecentProducts();

			LogUtil.info("Opening Folders section of the product");
			browse.openSpecificSectionOfProduct(productName, "Folders");

			String parentWindow = driver.getWindowHandle();
			LogUtil.info("Click on Actions --> New ---->New Part");

			product.gotoActions();
			product.takeActions("New");
			product.takeActions("New Part");

			LogUtil.info("User fills in required details in the form and clicks on 'Finish'.");
			switchToWindowByHeader("New Part");
			newPartPage.selectProductPartDropdown(xmlReader.getData("partDropdown"));
			newPartPage.enterpartname(partName);
			newPartPage.clickFinish();
			switchToMainWindow(parentWindow);
			LogUtil.info("Verify Part is created");
			newPartPage.verifyPartCreatedandOpen();
			
			home.gotoBrowse();
			browse.RecentProducts();
			browse.openSpecificSectionOfProduct(productName, "Folders");
			product.folderContentsDropdown(xmlReader.getData("folderDropdownText"));
			product.selectFolderCheckbox(partName);
			product.gotoActions();
			product.takeActions("New Promotion Request");

			switchToWindowByHeader("New Promotion Request");


			LogUtil.info("User fills in required details in the form and clicks on 'Finish'.");
			promotionrequest.enterPromotinRequestNamename(prName);
			promotionrequest.clickNextbtn();
			promotionrequest.SelectTargetPromotionStatedropDown("Canceled");
			promotionrequest.clickCheckBox();

			promotionrequest.clickSetPromotionObjectIcon();
			promotionrequest.clickFinish();
			LogUtil.info("New Promotion Request Created");
			promotionrequest.verifyPromotionRequestCreatedandOpen();

	}

	@AfterClass()
	public void tearDown() {
		    LogUtil.info("Delete PR");
			home.gotoBrowse();
			browse.RecentProducts();
			browse.openSpecificSectionOfProduct(productName, "Folders");
			product.folderContentsDropdown(xmlReader.getData("folderDropdownText1"));
			product.selectFolderCheckbox(prName);
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
