package com.POM.Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SingleTonDesignPattern {

	public static WebDriver driver;

	public SingleTonDesignPattern(WebDriver driver2) {
		this.driver = driver2;
		PageFactory.initElements(driver, this);
	}

	public Home_Page getHome_Page() {
		Home_Page h = new Home_Page(driver);
		return h;
	}

	public Product_Page getProduct_Page() {
		Product_Page p = new Product_Page(driver);
		return p;
	}
}
