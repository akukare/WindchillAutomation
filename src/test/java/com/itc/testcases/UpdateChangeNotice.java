package com.itc.testcases;
 
  
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
 
import com.itc.base.BaseTest;
import com.itc.page.actions.BrowsePage;
import com.itc.page.actions.ChangeNoticePage;
import com.itc.page.actions.EditChangeNoticePage;
import com.itc.page.actions.HomePage;
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
* User should be able to Update Change Notice.
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
* 4. Click  on Actions --> New ---->New Change Notice
* 5. User fills in required details in the form and clicks on "Next". (Note: All fields marked in * are mandatory fields).
* 6. Click Edit default change plan.
* 7. Update Change Notice attributes in "the form and clicks on "Next". (Note: All fields marked in * are mandatory fields).
* 8. Set Attributes" tab and Click "Next"
* 9. Select appropriate Security label and "Export Controlled" fields and clicks on "Next"
* 10. Click "Next" and Attach any local file or URL Link or external storage and click on "Next"
* 11. Add associated process objects and associated reference objects and click on "Finish"
*
* 12. Navigate to Product Container
* 13. Click on expand  and navigate to "Folders" of product
* 14. Select newly created Change Notice. and right click.
* 15. Click  on Edit.
* 16. Change the attributes and click finish.
*
* Expected Behaviour:
* -----------
* 1.User should be able to Update Change Notice.
*
* @author "*****"
**/
  
public class UpdateChangeNotice extends BaseTest {
	HomePage home;
	BrowsePage browse;
	ProductPage product;
	EditChangeNoticePage EditChangeNotice;
	ChangeNoticePage ChangeNotice;
	private XMLReader xmlReader;	
	private String productName;    
    String changeNoticeName;
    String changeNoticeUpdated;
    
    @BeforeClass
	public void setup() {
    	LogUtil.info("Launching login page");
		config = ConfigReader.getProperties();
		xmlReader = new XMLReader();
		productName = xmlReader.getData("product");
		changeNoticeName = "CN_New - " + generateRandomNumber(5);
	    changeNoticeUpdated = "CN_Updated - " + generateRandomNumber(5);
	}
 
    @BeforeMethod
	public void beforeMethod() throws Exception {
		home = new HomePage();
		browse = new BrowsePage();
		product = new ProductPage();
		EditChangeNotice = new EditChangeNoticePage();
		ChangeNotice = new ChangeNoticePage();
     }
	@Test
    @TestInfo(FunctionalArea = "Change Management",
        Owner = "slandge",
        Tags = { "QA", "Functional", "05-03-2025" },
        TestCaseID = "",
        Description = "User update Change Notice")
	public void verifyUpdateChangeNotice() {
		 
			LogUtil.info("Login windchill");
			loginToWindchill("windchillSignOndemouser", "windchillSignOnPassword");
 
			LogUtil.info("Navigating to Browse page");
			home.gotoBrowse();
 
			LogUtil.info("Click on recent products");
			browse.RecentProducts();
 
			LogUtil.info("Click on expand  and navigate to \"Folders\" of product");
			browse.openSpecificSectionOfProduct(productName, "Folders");
 
			LogUtil.info("Navigate to product page ");
			String parentWindow = driver.getWindowHandle();
			driver.navigate().refresh();
 
			LogUtil.info("Click on action");
			product.gotoActions();
			product.gotoNewLink();
			waitForSeconds(3);
 
			LogUtil.info("Click on new change notice");
			product.gotoNewChangeNoticeLink();
			switchToNewWindow();
			waitForSeconds(3);
 
			LogUtil.info("Create new change notice");
			ChangeNotice.clearName();
			waitForSeconds(3);
			ChangeNotice.enterchangerequestname(changeNoticeName);
			ChangeNotice.clickNextbtn();
 
			LogUtil.info("Click on finish");
			ChangeNotice.clickFinish();
			waitForSeconds(3);
 
			LogUtil.info("Click on submit button");
			ChangeNotice.clickSubmit();
			switchToMainWindow(parentWindow);
 
			LogUtil.info(" Verify  Chnage Notice Created");
			ChangeNotice.verifyChangeNoticeCreated();
 
			LogUtil.info("Select Change Object from folder content dropdown");
			driver.navigate().refresh();
			product.folderContentsDropdown("Change Objects");
			waitForSeconds(3);
 
			LogUtil.info("select Newly created change notice" );
			product.selectFolderCheckbox(changeNoticeName);
			waitForSeconds(3);
 
			LogUtil.info("Right click on created change notice" );
			product.rightClickOnCheckedCheckbox();
			waitForSeconds(3);
 
			LogUtil.info("Click on edit change notice" );
			product.clickEditOption();
 
			waitForSeconds(3);
			switchToWindowByHeader("Edit Change Notice");
 
			LogUtil.info("edit the change notice" );
			switchToNewWindow();
 
			waitForSeconds(3);
			EditChangeNotice.clearNameToUpdate();
			waitForSeconds(3);
			EditChangeNotice.entername(changeNoticeUpdated);
 
			LogUtil.info("Click on finish");
			ChangeNotice.clickFinish();
 
			LogUtil.info("Click on submit button");
			ChangeNotice.clickSubmit();
 
			LogUtil.info(" Verify  Chnage Notice Updated");
			ChangeNotice.verifyChangeNoticeEdited(changeNoticeUpdated);
 
		
	}
 
	@AfterTest
	public void tearDown() {
		    LogUtil.info("Delete CN");
			home.gotoBrowse();
			browse.RecentProducts();
			browse.openSpecificSectionOfProduct(productName, "Folders");
			product.folderContentsDropdown(xmlReader.getData("Change Objects"));
			product.selectFolderCheckbox(changeNoticeUpdated);
			product.gotoActions();
			product.takeActions("Delete");
			if (WaitUtils.isAlertPresent()) {
				performAlertAction("gettext");
				performAlertAction("accept");
				LogUtil.info("Alert was accepted." );
			} else {
				LogUtil.info("No alert Present" );
			}
			WaitUtils.waitForSeconds(2);
			sessionEnd();
		}  
	}
 
