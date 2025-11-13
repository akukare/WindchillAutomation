package com.itc.testcases;
 
import org.testng.Assert;
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
 
@Listeners(CustomListeners.class)
public class ReassignChangeNotice extends BaseTest{
	HomePage home;
	BrowsePage browse;
	ProductPage product;
	ReassignTasksPage reassigntasks;
	ChangeNoticePage ChangeNotice;
	NewPartPage newPartPage;
	BOMPageActions BOMPag;
	private XMLReader xmlReader;
	private String productName;
	String changeNoticeName;
	String changeTaskName;
	String folderDropdownText;
	String ChangeObjects;
 
	@BeforeClass
	public void setup() {
		config = ConfigReader.getProperties();
		xmlReader = new XMLReader();
		productName = xmlReader.getData("product");
		changeNoticeName = "CN_New - " + generateRandomNumber(5);
		changeTaskName = "CT_New - " + generateRandomNumber(5);
		ChangeObjects=xmlReader.getData("ChangeObjects");
	}
 
	@BeforeMethod
	 public void beforeMerhod() {
		initializeDriver();
		home = new HomePage();
		browse = new BrowsePage();
		product = new ProductPage();
		reassigntasks = new ReassignTasksPage();
		ChangeNotice = new ChangeNoticePage();
		newPartPage = new NewPartPage();
		BOMPag =new BOMPageActions();
	 }

	@Test
    @TestInfo(FunctionalArea = "Change Management",
        Owner = "slandge",
        Tags = { "QA", "Functional", "05-03-2025" },
        TestCaseID = "",
        Description = "User reassign Change Notice")
	public void verifyReassignChangeNotice() {
		
		LogUtil.info("Login windchill");
	    loginToWindchill("windchillSignOnadmin", "windchillSignOnPasswordadmin");
 
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
		
		LogUtil.info("Create new change task");
		ChangeNotice.clickEditChangeTask();
		ChangeNotice.clearChangeTaskName();
		ChangeNotice.enterChangeTaskName(changeTaskName);   
     	ChangeNotice.clickNextbtn();
        
		LogUtil.info("Click on finish change task");
		ChangeNotice.clickFinish();
		
		LogUtil.info("Click on submit button");
		driver.switchTo().defaultContent();
		ChangeNotice.clickFinish();
		ChangeNotice.clickSubmit();
		
		switchToMainWindow(parentWindow);
		
		ChangeNotice.closeBanner();
		
		LogUtil.info("Navigate to Homepage");
		home.gotohomeIcon();
		String reassigntaskWindow = driver.getWindowHandle();
 
		LogUtil.info("Click on Public Tab" );
		home.gotopublicTab();
  
		home.Reassign_changerequest_select(changeTaskName);
		reassigntasks.click_Reassigntask();
 
		SwitchtoReassigntasksofchange_window(" demouser (demouser: dxp) ");
		reassigntasks.clickOK_btn();
		
		driver.switchTo().window(reassigntaskWindow);
		driver.close();
		
		LogUtil.info("Login windchill with demouser");
		beforeMerhod();
		loginToWindchill("windchillSignOndemouser", "windchillSignOnPassworddemouser");

		LogUtil.info("Navigate to Homepage");
		home.gotohomeIcon();
 
		LogUtil.info("Click on Public Tab" );
		home.gotopublicTab();
		
		LogUtil.info("Click on view info icon on Change Task" );
		ChangeNotice.clickOnViewInfoIconOnChangeTask(changeTaskName);
 
		LogUtil.info("Click on complete change task" );
		ChangeNotice.clickOnCompleteTaskButon();
		
		LogUtil.info("Verify if change task completed");
		boolean isTaskCompleted =ChangeNoticePage.validateChangeTaskStatus(changeTaskName,"Completed");
		Assert.assertTrue(isTaskCompleted,"change task completed");
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



 
