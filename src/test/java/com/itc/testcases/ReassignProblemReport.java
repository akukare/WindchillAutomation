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
import com.itc.page.actions.ReassignTasksPage;
import com.itc.utilities.ConfigReader;
import com.itc.utilities.LogUtil;
import com.itc.utilities.TestInfo;
import com.itc.utilities.WaitUtils;
import com.itc.utilities.XMLReader;


/**
*
* Summary
*------------------
* User should be able to Reassign Problem Report
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
* 5.Click Reassign Icon
* 6.Click Ok Button on Reassign Task Window
* 7.Validate Reassign Icon on Problem Report
* Expected Behaviour:
* -----------------
* 1.User should be able to validate Reassign Problem Report successfully
*
* @author "****"
*/


@Listeners(CustomListeners.class)
public class ReassignProblemReport extends BaseTest {
	HomePage home;
	BrowsePage browse;
	ProductPage product;
	ProblemReportPage problemreport;
	ReassignTasksPage reassigntasks;
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
		reassigntasks = new ReassignTasksPage();
		}

	@Test
    @TestInfo(FunctionalArea = "Problem Report",
        Owner = "rsakhare",
        Tags = { "QA", "Functional", "05-03-2025" },
        TestCaseID = "",
        Description = "User can Reassign Problem Reportt")
	
	public void verifyReassignProblemReport() {
		
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
			refreshPage();
			problemreport.selectProductPartDropdown(xmlReader.getData("partDropdown"));
			
			LogUtil.info("Verify Problem Report is created");
			problemreport.verifyProblemReportCreatedandOpen();

			LogUtil.info(driver.getTitle());

			LogUtil.info("Navigate to home icon");
			home.gotohomeIcon();

			LogUtil.info("Navigate to public tab");
			home.gotopublicTab();

			String reassigntaskWindow = driver.getWindowHandle();

			home.Reassign_changerequest_select(partName);
			reassigntasks.click_Reassigntask();

			switchToWindowByHeader("Reassign Tasks");

			reassigntasks.clickOK_btn();

			switchToMainWindow(reassigntaskWindow);
			home.VerifyTaskReassigned(partName);

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
			WaitUtils.waitForSeconds(2);
			sessionEnd();
	}
}
