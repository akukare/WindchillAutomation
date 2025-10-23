
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
* User can be able to Check out and Undo Check out of a Part.
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
* 6. Select Part
* 7. Click on Action
* 8. Click on CheckOut
* 9. Verify Part is CheckOut
* 10.Select Same Part and click on action and Click on Undo Check Out
* Expected Behaviour:
* -----------
* 1.User should be able to Check out and Undo Check out of a Part.
*
* @author "*****"
*/

@Listeners(CustomListeners.class)
public class CheckoutAndUndoCheckoutofPart extends BaseTest {
	HomePage home;
	BrowsePage browse;
	private ProductPage product;
	ProductPage folderDropdownText;
	NewPartPage newPartPage;
	BOMPageActions BOMPag;
	String partName;
	private XMLReader xmlReader;
	private String productName;
	private String foldertext;

	@BeforeClass
	public void setup() throws Exception {
		config = ConfigReader.getProperties();
		xmlReader = new XMLReader();
		productName = xmlReader.getData("product");
		foldertext = xmlReader.getData("folderDropdownText");
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
	    @TestInfo(FunctionalArea = "Check out and Undo Check out of a Part",
	        Owner = "rsakhare",
	        Tags = { "QA", "Functional", "05-03-2025" },
	        TestCaseID = "",
	        Description = "User should be able to Check out and Undo Check out of a Part")
	public void verifyCheckoutAndUndoCheckoutofPart() {
		 
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
			newPartPage.selectProductPartDropdown(xmlReader.getData("partDropdown"));
			newPartPage.enterpartname(partName);
			newPartPage.clickFinish();
			switchToMainWindow(parentWindow);
			product.folderContentsDropdown(foldertext);

			LogUtil.info("Verify Part is created");
			newPartPage.validatePartIsCreated(partName);
			product.SelectObjectCheckbox(partName);
			product.gotoActions();
			
			LogUtil.info("Checkout Part");
			product.takeActions("Check Out");
			performAlertAction("accept");
			
			LogUtil.info("Verify Part is Check Out");
			product.ValidateObjectisCheckedOut(partName);
			
			WaitUtils.waitForSeconds(2);
			product.SelectObjectCheckbox(partName);
			product.gotoActions();

			LogUtil.info("Undo Checkout Part");
			product.takeActions("Undo Checkout");
			performAlertAction("accept");
			WaitUtils.waitForSeconds(2);
	}

	 @AfterClass()
	public void tearDown() {
		    LogUtil.info("Delete Part");
			home.gotoBrowse();
			browse.RecentProducts();
			browse.openSpecificSectionOfProduct(productName, "Folders");
			product.folderContentsDropdown(foldertext);
			newPartPage.searchObject(partName);
			product.SelectObjectCheckbox(partName);
			product.gotoActions();
			product.takeActions("Delete");
		    WaitUtils.isAlertPresent();

		    LogUtil.info("Alert was accepted.");
		    performAlertAction("accept");
			WaitUtils.waitForSeconds(2);
			sessionEnd();
	}
}
