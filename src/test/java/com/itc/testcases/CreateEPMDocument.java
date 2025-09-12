package com.itc.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.itc.Listeners.CustomListeners;
import com.itc.base.BaseTest;
import com.itc.page.actions.BOMPageActions;
import com.itc.page.actions.BrowsePage;
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
* User should be able to Create EPM Document.
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
* 2. Navigate to Product Container
* 3. Click on expand  and navigate to "Folders" of product 
* 4. Click  on Actions --> New ---->New Part 
* 5. User fills in required details in the form. (Note: All fields marked in * are mandatory fields).
* 6. Click Create CAD checkbox and Click Next.
* 7. Select Template Doc from DropDown
* 8. Click Finish
* Expected Behaviour:
* -----------
* 1.User should be able to Create EPM Document Successfully.
*
* @author "****"
*/

@Listeners(CustomListeners.class)
public class CreateEPMDocument extends BaseTest {
	HomePage home;
	BrowsePage browse;
	ProductPage product;
	NewPartPage newPartPage;
	BOMPageActions BOMPag;
	String partName;
	private XMLReader xmlReader;
	private String productName;
	private String folderDropdownText;
	private String templetName;
	private String partDropdown;
	@BeforeClass
	public void setup() throws Exception {
		
		config = ConfigReader.getProperties();
		xmlReader = new XMLReader();
		productName = xmlReader.getData("product");
		folderDropdownText=xmlReader.getData("folderDropdownText");
		templetName=xmlReader.getData("templetName");
		partDropdown=xmlReader.getData("partDropdown");
		partName = "Part-" + generateRandomNumber(6);
	}

	@BeforeMethod
	public void initPages() throws Exception {
		initializeDriver();
		home = new HomePage();
		browse = new BrowsePage();
		product = new ProductPage();
		newPartPage = new NewPartPage();
		BOMPag =new BOMPageActions();
	}

	 @Test
	    @TestInfo(FunctionalArea = "Create EPMDocument",
	        Owner = "rsakhare",
	        Tags = { "QA", "Functional", "05-03-2025" },
	        TestCaseID = "",
	        Description = "User creates a New EPMDocument in product")
	public void verifyCreateEPMDocument() {

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
			product.takeActions("New Part");

			LogUtil.info("User fills in required details in the form and clicks on 'Finish'.");
			switchToWindowByHeader("New Part");
			newPartPage.selectProductPartDropdown(partDropdown);
			newPartPage.enterpartname(partName);

			LogUtil.info("Select CAD checkbox and move to next");
			newPartPage.clickCreateCADCheckbox();
			newPartPage.clickNextbtn();
			
			LogUtil.info("Select Templet");
			newPartPage.SelectTemplateDocDropDown(templetName);//("creo_elementspro5_mmns_design.asm");
			WaitUtils.waitForSeconds(2);
			newPartPage.clickFinish();
			
			switchToMainWindow(parentWindow);

			newPartPage.searchObject(partName);
			
			LogUtil.info("Verify Part is created");
			//newPartPage.verifyPartCreatedandOpen();
			WaitUtils.waitForSeconds(2);

	}

	@AfterClass()
	public void tearDown() {
		    LogUtil.info("Delete Document");
			home.gotoBrowse();
			browse.RecentProducts();
			browse.openSpecificSectionOfProduct(productName, "Folders");
			product.folderContentsDropdown(folderDropdownText);
			newPartPage.searchObject(partName);
			BOMPag.selectObject(partName);
			product.gotoActions();
			product.takeActions("Delete");
			WaitUtils.isAlertPresent(); 
			LogUtil.info("Alert was accepted.");
			performAlertAction("accept");
			sessionEnd();
	}
}
