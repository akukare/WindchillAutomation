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
import com.itc.page.actions.NewDocumentPage;
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
* User should be able to Create WTDocument.
*
* Prerequisite :
* -----------------
* 1. Test User Credentials - Product Manager , Design Engineer
* 2. Product Name
* 3. Part Name
* 4. documentName
*
* Steps:
* -------
* 1. Login to Windchill Server
* 2. Navigate to Product Container
* 3. Click on expand  and navigate to "Folders" of product 
* 4. Click  on Actions --> New ---->New Part 
* 5. User fills in required details in the form. (Note: All fields marked in * are mandatory fields).
* 8. Click Finish
* Expected Behaviour:
* -----------
* 1.User should be able to Create WTDocument Successfully.
*
* @author "***"
*/


@Listeners(CustomListeners.class)
public class CreateWTDocument extends BaseTest {
	HomePage home;
	BrowsePage browse;
	ProductPage product;
	String docName;
	private XMLReader xmlReader;
	private String productName;
	private String docDropdown;
	private String primarySourceContent;
	private String folderDropdownText;
	private NewDocumentPage newDocument;

	@BeforeClass
	public void setup() throws Exception {
		config = ConfigReader.getProperties();
		xmlReader = new XMLReader();
		productName = xmlReader.getData("product");
		docDropdown = xmlReader.getData("docDropdown");
		primarySourceContent = xmlReader.getData("primarySourceContent");
		folderDropdownText = xmlReader.getData("folderDropdownText");
		docName = "Document-" + generateRandomNumber(6);
	}

	@BeforeMethod
	public void initPages() throws Exception {
		initializeDriver();
		home = new HomePage();
		browse = new BrowsePage();
		product = new ProductPage();
		newDocument = new NewDocumentPage(); 
	}

	@Test
    @TestInfo(FunctionalArea = "Create WTDocument",
        Owner = "rsakhare",
        Tags = { "QA", "Functional", "05-03-2025" },
        TestCaseID = "",
        Description = "User creates a New WTDocument in product")
	public void verifyCreateDocument() {

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
			product.takeActions("New Document");

			LogUtil.info("User fills in required details in the form and clicks on 'Finish'.");
			switchToWindowByHeader("New Document");

			LogUtil.info("Selecting document type");
	        newDocument.selectProductDocDropdown(docDropdown);

	        LogUtil.info("Selecting primary type");
	        newDocument.SelectDocprimarytypedropDown(primarySourceContent);
	
	        LogUtil.info("Entering document name");
	        newDocument.enterdocname(docName);
	
	        LogUtil.info("Clicking finish to create document");
	        newDocument.clickFinish();

	        switchToMainWindow(parentWindow);

			LogUtil.info("Verify Part is created");
			newDocument.verifyPartCreatedandOpen();

	}

	@AfterClass()
	public void tearDown() {
		    LogUtil.info("Delete Doc");
			home.gotoBrowse();
			browse.RecentProducts();
			browse.openSpecificSectionOfProduct(productName, "Folders");
			product.folderContentsDropdown(folderDropdownText);
			product.selectFolderCheckbox(docName);
			product.gotoActions();
			product.takeActions("Delete");
			if (WaitUtils.isAlertPresent()) {
				performAlertAction("gettext");
				performAlertAction("accept");
				LogUtil.info("Alert was accepted.");
			} else {
				LogUtil.info("No alert Present");
			}
			WaitUtils.waitForSeconds(1);
			sessionEnd();
	}
}
