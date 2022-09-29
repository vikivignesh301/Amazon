package com.POM.Page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Product_Page {

	public static WebDriver driver;

	@FindBy(xpath = "//span[text()='MORE RESULTS']//following::div[@data-component-type='s-search-result']")
	private List<WebElement> moreresult;
	@FindBy(xpath = "//span[text()='MORE RESULTS']//preceding::div[@data-component-type='s-search-result']")
	private List<WebElement> results;

	public Product_Page(WebDriver driver2) {
		this.driver = driver2;
		PageFactory.initElements(driver, this);
	}

	public List<WebElement> getMoreresult() {
		return moreresult;
	}

	public List<WebElement> getResults() {
		return results;
	}

}
