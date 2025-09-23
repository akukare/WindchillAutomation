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
* User should be able to create new folder.
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
* 2. Navigate to Test product container .
* 3. Click on expand  and navigate to "Folders" of product.
* 4. Click on New Folder icon.
* 5. Enter the folder name and click finish.
*
* Expected Behaviour:
* -----------
* 1.User should be able to create new folder.
*
* @author "*****"
*/
 
@Listeners(CustomListeners.class)
public class CreatenewFolder extends BaseTest {
 
	HomePage home;
	BrowsePage browse;
	ProductPage product;
	ProductPage folderDropdownText;
	private XMLReader xmlReader;
	private String productName;
	String newFolderName;
	private String foldertext;
 
	@BeforeClass
	public void setup() {

		config = ConfigReader.getProperties();
		xmlReader = new XMLReader();
		productName = xmlReader.getData("product");
		foldertext = xmlReader.getData("folderDropdownText");
		newFolderName = "TestFolder-" + generateRandomNumber(5);
	}
 
	@BeforeMethod
	public void beforeMerhod() {
		initializeDriver();
		home = new HomePage();
		browse = new BrowsePage();
	}

	 @Test
	    @TestInfo(FunctionalArea = "Create New Folder",
	        Owner = "rsakhare",
	        Tags = { "QA", "Functional", "05-03-2025" },
	        TestCaseID = "",
	        Description = "User creates a New Folder in product")
	public void verifyCreateNewfolder() {

			LogUtil.info("Login windchill");
			loginToWindchill("windchillSignOndemouser", "windchillSignOnPassword");
 
			LogUtil.info("Login windchill");
			home.gotoBrowse();
 
			LogUtil.info("Click on recent products");
			browse.RecentProducts();
 
			LogUtil.info("Click on expand  and navigate to Folders of product");
			browse.openSpecificSectionOfProduct(productName, "Folders");
			product.folderContentsDropdown(foldertext);
			LogUtil.info("Click on new folder");
			home.gotonewFolder();
			switchToNewWindow();
			home.createFolderName(newFolderName);
			home.newFolderFinsh();
			switchToNewWindow();
			refreshPage();
			
			LogUtil.info("Verify new folder Created");
			product.verifyFolderCreated();
	}

	 @AfterClass()
		public void tearDown() {
			    LogUtil.info("Delete Part");
				home.gotoBrowse();
				browse.RecentProducts();
				browse.openSpecificSectionOfProduct(productName, "Folders");
				product.folderContentsDropdown(foldertext);
				product.selectFolderCheckbox(newFolderName);
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