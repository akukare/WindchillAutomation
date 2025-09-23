package com.itc.testcases;
 
 
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
 
import com.itc.base.BaseTest;
import com.itc.page.actions.BrowsePage;
import com.itc.page.actions.ChangeNoticePage;
 
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
* 4. Create Change Notice  - Test_Change_Notice
*
* Steps:
* -------
* 1. Login to Windchill Server
* 2. Navigate to Home Page
* 3. Click on  Public tab
* 4. Select existing change notice
* 5. Click on Reassign selected task
* 6. Click on Okay button displayed on wizard.
*
* Expected Behaviour:
* -----------
* 1.User should be able to Reassign change notice.
*
* @author "*****"
*/
 
 
public class ReassignChangeNotice extends BaseTest{
	HomePage home;
	BrowsePage browse;
	ProductPage product;
	ReassignTasksPage reassigntasks;
	ChangeNoticePage ChangeNotice;
	private XMLReader xmlReader;
	private String productName;
	String changeNoticeName;
	String changeTaskName;
 
	@BeforeClass
	public void setup() {
		LogUtil.info("Launching login page");
		config = ConfigReader.getProperties();
		xmlReader = new XMLReader();
		productName = xmlReader.getData("product");
		changeNoticeName = "CN_New - " + generateRandomNumber(5);
		changeTaskName = "CT_New - " + generateRandomNumber(5);
	}
 
	@BeforeMethod
	 public void beforeMerhod() {
		LogUtil.info("Login windchill");
		initializeDriver();
		home = new HomePage();
		browse = new BrowsePage();
		product = new ProductPage();
		reassigntasks = new ReassignTasksPage();
		ChangeNotice = new ChangeNoticePage();
	 }

	@Test
    @TestInfo(FunctionalArea = "Change Management",
        Owner = "slandge",
        Tags = { "QA", "Functional", "05-03-2025" },
        TestCaseID = "",
        Description = "User reassign Change Notice")
	public void verifyReassignChangeNotice() {
		LogUtil.info("Login windchill");
		loginToWindchill("windchillSignOndemouser", "windchillSignOnPassword");
 
		LogUtil.info("Navigating to Browse page");
		home.gotoBrowse();
 
		LogUtil.info("Click on recent products");
		browse.RecentProducts();
 
		LogUtil.info("Click on expand  and navigate to \"Folders\" of product");
		browse.openSpecificSectionOfProduct(productName, "Folders");
 
		String parentWindow = driver.getWindowHandle();
		driver.navigate().refresh();
 
		LogUtil.info("Click on action");
		product.gotoActions();
		product.gotoNewLink();
 
		LogUtil.info("Click on new change notice");
		product.gotoNewChangeNoticeLink();
		switchToNewWindow();
 
		LogUtil.info("Create new change notice");
		ChangeNotice.clearName();
		
		ChangeNotice.enterchangerequestname(changeNoticeName);
		ChangeNotice.clickNextbtn();
		ChangeNotice.clickEditChangeTask();
 
		switchToNewWindow();
		switchToWindowByHeader("New Change Notice");
 
		System.out.println("Current window title: " + driver.getTitle());
 
		ChangeNotice.clearChangeTaskName();
		ChangeNotice.enterChangeTaskName(changeTaskName);   
 
		ChangeNotice.clickNextbtn();
 
		LogUtil.info("Click on finish");
		ChangeNotice.clickFinish();
 
		LogUtil.info("Click on submit button");
		ChangeNotice.clickSubmit();
		switchToMainWindow(parentWindow);
 
		LogUtil.info("Navigate to Homepage");
		home.gotohomeIcon();
 
		LogUtil.info("Click on Public Tab" );
		home.gotopublicTab();
 
		String reassigntaskWindow = driver.getWindowHandle();
 
		home.Reassign_changerequest_select(changeTaskName);
		reassigntasks.click_Reassigntask();
		switchToWindowByHeader("Reassign Tasks");
 
		SwitchtoReassigntasksofchange_window(" demouser (demouser: dxp) ");
		reassigntasks.clickOK_btn();

		switchToMainWindow(reassigntaskWindow);
 
		LogUtil.info("Click on Reassign task button" );
		reassigntasks.click_Reassigntask();
		
 
		LogUtil.info("Click on Okay");
		reassigntasks.clickOK_btn();
		
		switchToMainWindow(reassigntaskWindow);
 
		LogUtil.info("Verify Change Request Reassigned" );
		home.VerifyTaskReassigned(changeNoticeName);
 
	}
	
	@AfterTest
	public void tearDown() {
		LogUtil.info("Delete CN");
			home.gotoBrowse();
			browse.RecentProducts();
			browse.openSpecificSectionOfProduct(productName, "Folders");
			product.folderContentsDropdown(xmlReader.getData("Change Objects"));
			product.selectFolderCheckbox(changeNoticeName);
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
 
