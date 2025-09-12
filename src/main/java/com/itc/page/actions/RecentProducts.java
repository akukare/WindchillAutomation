package com.itc.page.actions;

import org.openqa.selenium.support.PageFactory;

import com.itc.base.BaseTest;
import com.itc.pagelocators.RecentProductsLocators;

public class RecentProducts extends BaseTest {

	public RecentProductsLocators RecentProducts;

	public RecentProducts() {

		this.RecentProducts = new RecentProductsLocators();
		PageFactory.initElements(driver, this.RecentProducts);
	}

	public void gotoProductFolder() {

		RecentProducts.gotoProductFolder.click();
	}

	public void gotoProductsubFolder() {

		RecentProducts.gotoProductsubFolder.click();
	}

}
