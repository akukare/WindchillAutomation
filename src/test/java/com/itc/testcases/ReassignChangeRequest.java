package com.itc.testcases;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
 
import com.itc.base.BaseTest;
import com.itc.page.actions.BrowsePage;
import com.itc.page.actions.ChangeNoticePage;
import com.itc.page.actions.ChangeRequestPage;
import com.itc.page.actions.HomePage;
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
*--------------
* User should be able to Reassign Change Notice.
*
* Prerequisite :
* -----------------
* 1. Test User Credentials - Product Manager , Design Engineer
* 2. Product Name
* 3. Part Name
* 4. Create Change Request  - Test_Change_Request
*
* Steps:
* -------
* 1. Login to Windchill Server
* 2. Navigate to Home Page
* 3. Click on  Public tab
* 4. Click  on Actions --> New ---->New Part 
* 5. Select existing change request
* 6. Click on Reassign selected task
* 7. Click on Okay button displayed on wizard.
*
* Expected Behaviour:
* -----------
* 1.User should be able to Reassign change request.
*
* @author "*****"
*/
 
public class ReassignChangeRequest  extends BaseTest {
	HomePage home;
	BrowsePage browse;
	ProductPage product;
	ReassignTasksPage reassigntasks;
	ChangeRequestPage ChangeRequest;
	String changeRequestName;
  	String productName;
	ChangeNoticePage ChangeNotice;
	private XMLReader xmlReader;
 
	@BeforeClass
	public void setup() {
		LogUtil.info("Launching login page");
		config = ConfigReader.getProperties();
		xmlReader = new XMLReader();
		productName = xmlReader.getData("product");
		changeRequestName = "CR_New - " + generateRandomNumber(5);
	}
 
	@BeforeMethod
	 public void beforeMerhod() {
		LogUtil.info("Login windchill");
		initializeDriver();
		home = new HomePage();
		browse = new BrowsePage();
		product = new ProductPage();
		reassigntasks = new ReassignTasksPage();
	    ChangeRequest = new ChangeRequestPage();
	 }
	@Test
    @TestInfo(FunctionalArea = "Change Management",
        Owner = "slandge",
        Tags = { "QA", "Functional", "05-03-2025" },
        TestCaseID = "",
        Description = "User reassign Change Request")
	public void reassignchangerequest() {
		LogUtil.info("Login windchill");
		loginToWindchill("windchillSignOndemouser", "windchillSignOnPassword");
 
		LogUtil.info("Navigating to Browse page");
		home.gotoBrowse();
		waitForSeconds(3);
 
		LogUtil.info("Click on recent products");
		browse.RecentProducts();
 
		LogUtil.info("Click on expand  and navigate to \"Folders\" of product" );
		browse.openSpecificSectionOfProduct(productName, "Folders");
		String ParentWindow = driver.getWindowHandle();
 
		LogUtil.info("Navigate to product page ");
		LogUtil.info("Click on Action ");
		product.gotoActions();
		product.takeActions("New");
		product.takeActions("New Change Request");
		switchToWindowByHeader("New Change Request");
 
		LogUtil.info(" click on Create new chnage request link ");
 
		LogUtil.info(" Enter change request name");
		ChangeRequest.enterchangerequestname(changeRequestName);
		ChangeRequest.clickNextbtn();
		System.out.println(driver.getTitle());
		waitForSeconds(3);
 
		LogUtil.info(" Click on next button");
		ChangeRequest.clickNextbtn();
 
		waitForSeconds(3);
 
		LogUtil.info(" Click on Finish button");
		ChangeRequest.clickFinish();
 
		LogUtil.info(" Click on Submit button");
		ChangeRequest.clickSubmit();
		waitForSeconds(1);
 
		switchToMainWindow(ParentWindow);
		waitForSeconds(1);
 
		LogUtil.info(" Verify  Chnage Request Created");
		ChangeRequest.verifyChangeRequestCreated();
 
		LogUtil.info("Click on Home icon");
		home.gotohomeIcon();
		LogUtil.info("Click on Public Tab");
		home.gotopublicTab();
		waitForSeconds(3);
 
		String reassigntaskWindow = driver.getWindowHandle();
 
		LogUtil.info("Select Chnage request");
		home.Reassign_changerequest_select(changeRequestName);
		LogUtil.info("Click on Reassign");
		reassigntasks.click_Reassigntask();
		waitForSeconds(2);
		switchToWindowByHeader("Reassign Tasks");
		SwitchtoReassigntasksofchange_window(" demouser (demouser: dxp) ");
		LogUtil.info("Click ok button");
		reassigntasks.clickOK_btn();
		waitForSeconds(2);
 
		switchToMainWindow(reassigntaskWindow);
		waitForSeconds(2);
 
		LogUtil.info("Verify Change Request Reassigned");
		home.VerifyTaskReassigned(changeRequestName);
}
	@AfterTest
	public void tearDown() {
		
			home.gotoBrowse();
			browse.RecentProducts();
			browse.openSpecificSectionOfProduct(productName, "Folders");
			product.folderContentsDropdown(xmlReader.getData("Change Objects"));
			product.selectFolderCheckbox(changeRequestName);
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
//			signOutWindchill(true);
		}  
	}

