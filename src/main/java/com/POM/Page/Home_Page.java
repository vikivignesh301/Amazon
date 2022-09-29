package com.POM.Page;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home_Page {

	public static WebDriver driver;

	@FindBy(id = "searchDropdownBox")
	private WebElement dropdown;
	@FindBy(id = "twotabsearchtextbox")
	private WebElement searchbox;
	@FindBy(xpath = "//div[@class='nav-issFlyout nav-flyout']/div/div")
	private List<WebElement> container;
	@FindBy(xpath = "//div[@class='nav-issFlyout nav-flyout']/div/div[\" + i + \"]/div/div")
	private WebElement input;

	public Home_Page(WebDriver driver2) {
		this.driver = driver2;
		PageFactory.initElements(driver, this);
	}

	public WebElement getDropdown() {
		return dropdown;
	}

	public WebElement getSearchbox() {
		return searchbox;
	}

	public List<WebElement> getContainer() {
		return container;
	}

	public WebElement getInput() {
		return input;
	}

}
