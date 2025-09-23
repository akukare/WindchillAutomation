package com.itc.testcases;

import org.testng.Assert;
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

/**
*
* Summary
*--------------
* User should be able to Login Windchill.
*
* Prerequisite :
* -----------------
* 1. Test User Credentials - Product Manager , Design Engineer
*
* Steps:
* -------
* 1. Login to Windchill Server
* 
* Expected Behaviour:
* -----------
* 1.User should be able to Login Windchill Successfully.
*
* @author "*****"
*/


@Listeners(CustomListeners.class)
public class Login extends BaseTest {

	@BeforeClass
	public void setup() throws Exception {
		config = ConfigReader.getProperties();
	}

	@BeforeMethod
	public void initPages() throws Exception {
		initializeDriver();
	}

	@Test
    @TestInfo(FunctionalArea = "Windchill Login",
        Owner = "rsakhare",
        Tags = { "QA", "Functional", "05-03-2025" },
        TestCaseID = "",
        Description = "User Can Login Windchill Successfully")
	public void verifyWindchilllogin(){

			LogUtil.info("Login windchill");
			loginToWindchill("windchillSignOndemouser", "windchillSignOnPassword");
			WaitUtils.waitForSeconds(1);
			String actualTitle = driver.getTitle();
		     String expectedTitle = "Windchill PDMLink";
		        Assert.assertEquals(actualTitle, expectedTitle, "Page title does not match!");
		    }

	@AfterClass()
	public void tearDown() {
		sessionEnd();
	}
}
