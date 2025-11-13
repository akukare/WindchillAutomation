
package com.itc.testcases;

import org.testng.annotations.AfterClass;
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
import com.itc.page.actions.SitePage;
import com.itc.utilities.ConfigReader;
import com.itc.utilities.LogUtil;
import com.itc.utilities.TestInfo;
import com.itc.utilities.WaitUtils;
import com.itc.utilities.XMLReader;

/**
*
* Summary
*--------------
* User should be able to Create New part.
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
*
* Expected Behaviour:
* -----------
* 1.User should be able to Create new part.
*
* @author "*****"
*/


@Listeners(CustomListeners.class)
public class EditBOMReadlineAddingNewPart extends BaseTest {
	HomePage home;
	BrowsePage browse;
	ProductPage product;
	NewPartPage newPartPage;
	SitePage sitePage;
	String partName;
	ChangeNoticePage ChangeNotice;
	BOMPageActions BOMPage;
	private XMLReader xmlReader;
	private String productName;
	String changeNoticeName;
	String changeTaskName;

	@BeforeClass
	public void setup() throws Exception {

		config = ConfigReader.getProperties();
		xmlReader = new XMLReader();
		productName = xmlReader.getData("product");
		partName = "Part-" + generateRandomNumber(6);
		changeNoticeName = "CN_New - " + generateRandomNumber(5);
		changeTaskName = "CT_New - " + generateRandomNumber(5);
	}

	@BeforeMethod
	public void initPages() throws Exception {

		initializeDriver();
		home = new HomePage();
		browse = new BrowsePage();
		product = new ProductPage();
		newPartPage = new NewPartPage();
		sitePage = new SitePage();
		ChangeNotice = new ChangeNoticePage();
		BOMPage = new BOMPageActions();
	}
	@Test
    @TestInfo(FunctionalArea = "BOM Redline",
        Owner = "rsakhare",
        Tags = { "QA", "Functional", "05-03-2025" },
        TestCaseID = "",
        Description = "User Can Able to Add New Part in BOM Redline")
	public void createnewpart() {
		
			LogUtil.info("Login windchill");
			loginToWindchill("windchillSignOndemouser", "windchillSignOnPassword");

			LogUtil.info("Navigating to Browse page");
			home.gotoBrowse();
	
			LogUtil.info("Navigate to site");
			sitePage.Site();
			
			LogUtil.info("Navigate to Utiliti");
			sitePage.Utilities();
			
			LogUtil.info("Click On Preference Management");
			sitePage.PreferenceManagement();

			LogUtil.info("Expand Change Management");
			sitePage.ExpandChangeManagement();

			sitePage.rightClickEBR();
			sitePage.SetPreference();
			switchToNewWindow();

			LogUtil.info("Set Value Yes");
			sitePage.SetPreferenceValue();

			LogUtil.info("ClickOk Button");
			sitePage.ClickOkButton();
			switchToNewWindow();
			//home.gotohomeIcon();
			home.gotoBrowse();
			
			


			WaitUtils.waitForSeconds(3);
			LogUtil.info("Accessing recent products");
			browse.RecentProducts();
			
			LogUtil.info("Opening Folders section of the product");
			browse.openSpecificSectionOfProduct("testProduct", "Folders");

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

			ChangeNotice.enterChangeNoticeName(changeNoticeName);
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
			
			LogUtil.info("Click on Next Button");
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
			waitForSeconds(3);
			
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
			BOMPage.createBOMRedline();
			waitForSeconds(3);
			LogUtil.info(" Navigate to BOM Window");
			switchToNewWindow();

			BOMPage.switchToBOMIframe();

			LogUtil.info("Create New Child Part On BOM");
			BOMPage.InsertNewPartOnBOM();

			switchToNewWindow();

			newPartPage.selectProductPartDropdown(xmlReader.getData("partDropdown"));
			newPartPage.enterpartname(partName);
			newPartPage.clickFinish();

			waitForSeconds(3);
			switchToNewWindow();
			LogUtil.info("Verify Part is created");
			BOMPage.verifyPartCreated(partName);
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
