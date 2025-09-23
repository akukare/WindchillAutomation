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
import com.itc.page.actions.ProblemReportPage;
import com.itc.page.actions.ProductPage;
import com.itc.utilities.ConfigReader;
import com.itc.utilities.LogUtil;
import com.itc.utilities.TestInfo;
import com.itc.utilities.WaitUtils;
import com.itc.utilities.XMLReader;

/**
*
* Summary
*------------------
* User should be able to Create Problem Report
*
* Prerequisite :
* -----------------
* 1. Test User Credentials - Product Manager , Design Engineer
* 2. Product Name
* 3. Part Name
* 5. softType
* 6. partNumber
* 7. docType
* 8. SecurityLabelType
*
* Steps:
* -----------------
* 1. Login to Windchill with valid credentials
* 2. Navigate to  product container.
* 3. Click on expand  and navigate to "Folders" of product.
* 4. Click  on Actions --> New ---->New Problem Report
* 5. "User fills in required details in the form and clicks on ""Next" (Note: All fields marked in * are mandatory fields")
*6. User will search and add required " Affected Items" for which problem report  is created  and clicks on "Next"
*7. User will attach any local file or URL Link or external storage and click on "Next"
*8. User will add associated process objects and associated reference objects and click on "Finish"
*
* Expected Behaviour:
* -----------------
* 1.User should be able to create Create Problem Report successfully
*
* @author "*****"
*/

@Listeners(CustomListeners.class)
public class CreateProblemReport extends BaseTest {
	HomePage home;
	BrowsePage browse;
	ProductPage product;
	ProblemReportPage problemreport;
	String partName;
	private XMLReader xmlReader;
	private String productName;

	@BeforeClass
	public void setup() throws Exception {

		config = ConfigReader.getProperties();
		xmlReader = new XMLReader();
		productName = xmlReader.getData("product");
		partName = "testProblemReport-" + generateRandomNumber(6);
	}

	@BeforeMethod
	public void initPages() throws Exception {

		initializeDriver();
		home = new HomePage();
		browse = new BrowsePage();
		product = new ProductPage();
		problemreport = new ProblemReportPage();
		}
	
	@Test
    @TestInfo(FunctionalArea = "Create New Problem Report",
        Owner = "rsakhare",
        Tags = { "QA", "Functional", "05-03-2025" },
        TestCaseID = "",
        Description = "User creates a New Problem Report in product")
	public void verifyCreateProblemReport() {

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
			product.takeActions("New Problem Report");

			LogUtil.info("User fills in required details in the form and clicks on 'Finish'.");
			switchToWindowByHeader("New Problem Report");
			problemreport.enterproblemreportname(partName);
			problemreport.clickFinish();
			problemreport.clickSubmit();

			LogUtil.info("Navigate to main window");
			switchToMainWindow(parentWindow);
			product.folderContentsDropdown(xmlReader.getData("folderDropdownText"));

			LogUtil.info("Verify Problem Report is created");
			problemreport.verifyProblemReportCreatedandOpen();
			
	}

	@AfterClass()
	public void tearDown() {

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
			WaitUtils.waitForSeconds(2);
			sessionEnd();
	}
}
