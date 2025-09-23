package com.itc.testcases;


import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
* User should be able to view Redline BOM.
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
* 5. Enter change notice name.
* 6. Enter change task name.
* 7. Complete creating change notice.
* 8. Navigate to change notice created and click on view information.
* 9. Navigate to affected object table.
* 10. Select affected object(Part).
* 11. Click on action.
* 12. Click on Create BOM redline.
* 13. Navigate to Affected object table
* 14. Click Action , redline, View.
*
* Expected Behaviour:
* -----------
* 1.User should be able to open redline BOM in view mode.
*
* @author "*****"
*/
 
public class ViewBOMRedline extends BaseTest {
	HomePage home;
	BrowsePage browse;
	ProductPage product;
	NewPartPage newPartPage;
	ChangeNoticePage ChangeNotice;
	BOMPageActions BOMPage;
	String partName;
	String prName;
	String changeNoticeName;
	String changeTaskName;
	private XMLReader xmlReader;
	private String productName;
	String ChangeObjects;

	@BeforeClass
	public void setup() throws Exception {
		LogUtil.info("Launching login page");
		config = ConfigReader.getProperties();
		xmlReader = new XMLReader();
		productName = xmlReader.getData("product");
		partName = "newPart-" + generateRandomNumber(6);
		changeNoticeName = "CN_New - " + generateRandomNumber(5);
		changeTaskName = "CT_New - " + generateRandomNumber(5);
		ChangeObjects=xmlReader.getData("ChangeObjects");
	}

	@BeforeMethod
	public void initPages() throws Exception {
		LogUtil.info("Login windchill");
		initializeDriver();
		home = new HomePage();
		browse = new BrowsePage();
		product = new ProductPage();
		newPartPage = new NewPartPage();
		ChangeNotice = new ChangeNoticePage();
		BOMPage = new BOMPageActions();
	}
	
	@Test
    @TestInfo(FunctionalArea = "Opne BOM Redline in view mode",
        Owner = "slandge",
        Tags = { "QA", "Functional", "05-03-2025" },
        TestCaseID = "",
        Description = "User Opne BOM Redline in view mode")
	public void viewModeBOMRedline() {
			LogUtil.info("Login windchill");
			loginToWindchill("windchillSignOndemouser", "windchillSignOnPassword");

			LogUtil.info("Navigating to Browse page");
			home.gotoBrowse();

			LogUtil.info("Accessing recent products");
			browse.RecentProducts();

			LogUtil.info("Opening Folders section of the product" );
			browse.openSpecificSectionOfProduct(productName, "Folders");

			String parentWindow = driver.getWindowHandle();
			LogUtil.info("Click on Actions --> New ---->New Part");
			WaitUtils.waitForSeconds(3);
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
			product.folderContentsDropdown("Parts");

			product.selectFolderCheckbox(partName);
			product.gotoActions();

			product.gotoNewLink();

			product.gotoNewChangeNoticeLink();
			switchToNewWindow();

			switchToWindowByHeader("New Change Notice");

			LogUtil.info("Create new change notice");
			ChangeNotice.clearName();

			ChangeNotice.enterchangerequestname(changeNoticeName);
			ChangeNotice.clickNextbtn();

			LogUtil.info("Click on edit change task");
			ChangeNotice.clickEditChangeTask();
			WaitUtils.waitForSeconds(2);

			LogUtil.info("Click on clickSelectAffectedObjectTab button ");
			ChangeNotice.clickSelectAffectedObjectTab();

			LogUtil.info("Select part from affected object table");
			BOMPage.selectAffectedObjectsCheckbox(partName);

			LogUtil.info("Click on colelct affected object button");
			BOMPage.collecteAffectedObjects();

			LogUtil.info("Click on Ok button");
			switchToNewWindow();
			WaitUtils.waitForSeconds(3);
			BOMPage.clickOk();

			LogUtil.info("Click on finish");
			switchToNewWindow();
			WaitUtils.waitForSeconds(5);
			BOMPage.switchToCNIframe();

			ChangeNotice.clickIframeFinishButton();
			WaitUtils.waitForSeconds(2);

			LogUtil.info("Click on finish");
			switchToNewWindow();
			ChangeNotice.clickFinish();

			LogUtil.info("Click on submit button");
			ChangeNotice.clickSubmit();

			switchToMainWindow(parentWindow);
			product.folderContentsDropdown("Change Objects");

			LogUtil.info("select Newly created change notice");
			product.selectFolderCheckbox(changeNoticeName);
			waitForSeconds(3);

			LogUtil.info(" click on view information");
			BOMPage.selectViewIcon(changeNoticeName);

			LogUtil.info(" click on Implementation tab");
			switchToNewWindow();
			BOMPage.clickOnImplementationPlan();

			LogUtil.info("click on view information on implementation tab");
			BOMPage.selectViewIconImplementationTab();

			LogUtil.info("select affected object");
			BOMPage.selectAffectedObject();

			LogUtil.info("click on action dropdown");
			BOMPage.AffectedObjActionBtn();

			LogUtil.info("click on redline option");
			BOMPage.RedlineBtn();

			LogUtil.info("click create BOM Redline");
			String currentWindow = driver.getWindowHandle();
			BOMPage.createBOMRedline();

			LogUtil.info("Verify redline is created");
			switchToNewWindow();
			BOMPage.verifyRedlineCreated(partName);

			LogUtil.info("select affected object");
			driver.switchTo().window(currentWindow);
			BOMPage.selectAffectedObject();

			LogUtil.info("click on action dropdown");
			BOMPage.AffectedObjActionBtn();

			LogUtil.info("click on redline option");
			BOMPage.RedlineBtn();
			
			LogUtil.info("Open BOM redline in view mode");
			BOMPage.viewBOMRedline();
			
			LogUtil.info("Verify BOM redline opened in view mode");
			switchToNewWindow();
			BOMPage.verifyRedlineInViewMode(partName);
		}

	@AfterTest
	public void tearDown() {
		    LogUtil.info("Delete view mode");
			home.gotoBrowse();
			browse.RecentProducts();
			browse.openSpecificSectionOfProduct(productName, "Folders");
			product.folderContentsDropdown(ChangeObjects);
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
		

	}
}