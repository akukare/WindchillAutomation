package com.itc.testcases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.itc.Listeners.CustomListeners;
import com.itc.base.BaseTest;
import com.itc.page.actions.BrowsePage;
import com.itc.page.actions.EditProblemReport;
import com.itc.page.actions.HomePage;
import com.itc.page.actions.NewPartPage;
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
* User should be able to Update Problem Report
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
* 2. Navigate to  Public tab
* 3.Select My Task Container
* 4.Select Problem Report Check Box
* 5.Click Accept Icon
* 6.Click Ok Button on Accept Task Window
* 7.Validate Update Icon on Problem Report
* Expected Behaviour:
* -----------------
* 1.User should be able to validate Reassign Problem Report successfully
*
* @author "rsakhare"
*/

@Listeners(CustomListeners.class)
public class UpdateProblemReport extends BaseTest {
	HomePage home;
	BrowsePage browse;
	ProductPage product;
	ProblemReportPage problemreport;
	EditProblemReport editProblem;
	String prName;
	String updatedPRName;
	private XMLReader xmlReader;
	private String productName;
	NewPartPage newPartPage;
	@BeforeClass
	public void setup() throws Exception {

		config = ConfigReader.getProperties();
		xmlReader = new XMLReader();
		productName = xmlReader.getData("product");
		prName = "NewProblemReport-" + generateRandomNumber(6);
		updatedPRName = "UpdatedProblem Report-"+ generateRandomNumber(6);
	}

	@BeforeMethod
	public void initPages() throws Exception {

		initializeDriver();
		home = new HomePage();
		browse = new BrowsePage();
		product = new ProductPage();
		problemreport = new ProblemReportPage();
		editProblem = new EditProblemReport();
		newPartPage = new NewPartPage();
		}

	@Test
    @TestInfo(FunctionalArea = "Update Problem Report",
        Owner = "rsakhare",
        Tags = { "QA", "Functional", "05-03-2025" },
        TestCaseID = "",
        Description = "User Update Problem Report in product")
	public void verifyUpdateProblemReport() {

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
			problemreport.enterproblemreportname(prName);
			problemreport.clickFinish();
			problemreport.clickSubmit();

			LogUtil.info("Navigate to main window");
			switchToMainWindow(parentWindow);

			LogUtil.info("Verify Problem Report is created");
			problemreport.verifyProblemReportCreatedandOpen();

			LogUtil.info(driver.getTitle());
			product.PRActionsButton();
			product.takeActions("Edit");
			BaseTest.switchToNewWindow();
			editProblem.entername(updatedPRName);
			editProblem.clickFinish();
			switchToMainWindow(parentWindow);

			LogUtil.info("Verify Problem Report is Updated");
			problemreport.verifyProblemReportCreatedandOpen();

	}

	@AfterTest()
	public void tearDown() {

			home.gotoBrowse();
			browse.RecentProducts();
			browse.openSpecificSectionOfProduct(productName, "Folders");
			product.folderContentsDropdown(xmlReader.getData("folderDropdownText"));
			product.selectFolderCheckbox(updatedPRName);
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
