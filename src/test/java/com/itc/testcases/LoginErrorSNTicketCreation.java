package com.itc.testcases;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.itc.Listeners.CustomListeners;
import com.itc.base.BaseTest;
import com.itc.utilities.ConfigReader;
import com.itc.utilities.LogUtil;
import com.itc.utilities.TestInfo;
import com.itc.utilities.WaitUtils;
import com.itc.utilities.createBugInServiceNow;


/**
*
* Summary
*------------------
* User should be able to Check SNOW Ticket
*
* Steps:
* -----------------
* 1. Login to Windchill with valid credentials
* 2.Validate Home Page Title
* Expected Behaviour:
* -----------------
* 1.User should be able to validate Home Page Title successfully
*
* @author "*****"
*/



@Listeners(CustomListeners.class)
public class LoginErrorSNTicketCreation extends BaseTest {

	@BeforeClass
	public void setup() throws Exception {
		config = ConfigReader.getProperties();
	}

	@BeforeMethod
	public void initPages() throws Exception {
		initializeDriver();
	}
	
	@Test
    @TestInfo(FunctionalArea = "SN Ticket",
        Owner = "rsakhare",
        Tags = { "QA", "Functional", "05-03-2025" },
        TestCaseID = "",
        Description = "User Can Check SNOW Ticket")
	
	public void verifyLoginErrorSNTicketCreation(){
		
		try {
			LogUtil.info("Login windchill");
			loginToWindchill("windchillSignOndemouser", "windchillSignOnPassword");
			WaitUtils.waitForSeconds(1);
			if (driver.getTitle().contains("PTCWindchill")){
		        org.testng.Assert.assertTrue(driver.getTitle().contains("PTCWindchill"));
		}
			throw new Exception("Page title mismatch.");
			} catch (Exception  e) {

			System.out.println("Creating SNOW Ticket");
	        createBugInServiceNow.create("URL Issue","Invalid URL");	 
			e.printStackTrace();
		}
	}
	@AfterClass()
	public void tearDown() {
	sessionEnd();
	}
}
