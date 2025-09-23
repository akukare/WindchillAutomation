package com.itc.testcases;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.itc.Listeners.CustomListeners;
import com.itc.base.BaseTest;
import com.itc.page.actions.BrowsePage;
import com.itc.page.actions.EditPartPage;
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
* User should be able to Update part.
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
* 6. Search and click on New Part which is ceated in above step
* 7. Click on "Actions" --> Edit from Details Page
* 8. Update/Edit fields on "Edit Part" form and Click "Finish"
*
* Expected Behaviour:
* -----------
* 1.User should be able to edit and update part.
*
* @author "*****"
*/

@Listeners(CustomListeners.class)
public class UpdatePart extends BaseTest {
	HomePage home;
	BrowsePage browse;
	ProductPage product;
	NewPartPage newPartPage;
	String partName;
	EditPartPage editpart;
	String updatedPartName;
	private XMLReader xmlReader;
	private String productName;

	@BeforeClass
	public void setup() throws Exception {
		config = ConfigReader.getProperties();
		xmlReader = new XMLReader();
		productName = xmlReader.getData("product");
		partName = "Part-" + generateRandomNumber(6);
		updatedPartName = "UpdatedProblem Report-"+ generateRandomNumber(6);
	}

	@BeforeMethod
	public void initPages() throws Exception {

		initializeDriver();
		home = new HomePage();
		browse = new BrowsePage();
		product = new ProductPage();
		newPartPage = new NewPartPage();
		editpart = new EditPartPage();
	}

	@Test
    @TestInfo(FunctionalArea = "Update Part",
        Owner = "rsakhare",
        Tags = { "QA", "Functional", "05-03-2025" },
        TestCaseID = "",
        Description = "User Can Update part in product")
	public void updatepart() {

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

			product.ActionsButton();
			product.takeActions("Check Out and Edit");
			switchToNewWindow();

			editpart.SelectViaDropDownAssemblymode("Component");
			
			editpart.CheckInButton();
			editpart.OkButton();
			switchToMainWindow(parentWindow);

	}

	@AfterClass()
	public void tearDown() {
		    LogUtil.info("Delete Part");
			home.gotoBrowse();
			browse.RecentProducts();
			browse.openSpecificSectionOfProduct(productName, "Folders");
			product.folderContentsDropdown(xmlReader.getData("folderDropdownText"));
			product.selectFolderCheckbox(partName);
			product.gotoActions();
			product.takeActions("Delete");
			if (WaitUtils.isAlertPresent()) {
				performAlertAction("gettext");
				performAlertAction("accept");
				LogUtil.info("Alert was accepted.");
			} else {
				LogUtil.info("No alert Present");
			}
			sessionEnd();
		}
}
 
