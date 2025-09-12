package com.itc.page.actions;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.itc.base.BaseTest;
import com.itc.pagelocators.CommonLocators;
import com.itc.pagelocators.HomePageLocators;
import com.itc.utilities.ElementActions;
import com.itc.utilities.WaitUtils;

public class HomePage extends BaseTest {

	public HomePageLocators home;
	public CommonLocators commonLocators;

	public HomePage() {
		this.home = new HomePageLocators();
		PageFactory.initElements(driver, this.home);
		this.commonLocators = new CommonLocators();
		PageFactory.initElements(driver, this.commonLocators);

	}

	public void gotoBrowse() {

		ElementActions.click(home.BrowseTab);

	}

	public void gotoSearch() {

	}

	public void gotonewPart() {
		ElementActions.click(home.newPart);

	}

	public void gotonewFolder() {

		ElementActions.click(home.newFolder);
	}

	public void createFolderName(String Name) {
		ElementActions.sendKeys(home.newFolderName, Name);

	}

	public void newFolderFinsh() {

		ElementActions.click(home.newFolderFinish);

	}

	public void gotohomeIcon() {

		ElementActions.click(home.homepageIcon);
	}

	public void gotopublicTab() {
		ElementActions.click(home.publicTab);
		refreshPage();
	}

	public void gotoReassignTab() {
		WaitUtils.waitForSeconds(1);
		ElementActions.click(home.reassign);
	}

	public void gotoNewDocument() {
		ElementActions.click(home.newDocument);
	}

	public void Reassign_changerequest_select(String name) {
		waitUntilVisible(By.xpath("//a[contains(text(), '" + name + "')]"), 10);
		List<WebElement> rows = driver.findElements(By.xpath("//div[@class='x-grid3-scroller']//tbody//tr"));
		for (WebElement row : rows) {
			try {
				WebElement link = row.findElement(By.xpath("//a[contains(text(), '" + name + "')]"));
				if (link != null && link.isDisplayed()) {
					WebElement checkbox = row.findElement(By.xpath("//div[@class='x-grid3-row-checker']"));
					if (!checkbox.isSelected()) {
						checkbox.click();
					}
					break;
				}
			} catch (NoSuchElementException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void VerifyTaskReassigned(String name) {
		waitUntilVisible(By.xpath("//a[contains(text(), '" + name + "')]"), 10);
		List<WebElement> rows = driver.findElements(By.xpath("//div[@class='x-grid3-scroller']//tbody//tr"));
		for (WebElement row : rows) {
			try {
				WebElement link = row.findElement(By.xpath("//a[contains(text(), '" + name + "')]"));
				if (link != null && link.isDisplayed()) {
					WebElement Reassigned = row.findElement(By.xpath("//img[@qtip = 'Reassigned']"));
					if (Reassigned.isDisplayed()) {
						highlightElement(row);
						highlightElement(Reassigned);
						System.out.println("Reassigned task Successful");
					} else {
						System.out.println("Reassigned task not Displayed");
					}
					break;
				}
			} catch (NoSuchElementException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
