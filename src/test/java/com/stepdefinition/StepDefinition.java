package com.stepdefinition;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.POM.Page.Home_Page;
import com.POM.Page.Product_Page;
import com.POM.Page.SingleTonDesignPattern;
import com.Property_FIle.ConfigurationHelper;
import com.mav.practice.Advance_code.XLSX_Reader;
import com.runner.RunnerClassAmazon;

import baseClassAmazon.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition extends BaseClass {

	public static WebDriver driver = RunnerClassAmazon.driver;
	public static String expectedDDvalue;
	public static String expectedinput;
	SingleTonDesignPattern st = new SingleTonDesignPattern(driver);

	@Given("user Launch Application Url")
	public void user_launch_application_url() throws InterruptedException, IOException {

		launchUrl(ConfigurationHelper.getInstance().getUrl());
		screenMaximize();
		sleep();
	}

	@When("user Click The Dropdown Options")
	public void user_click_the_dropdown_options() throws IOException, InterruptedException {
		expectedDDvalue = XLSX_Reader.particlarData("amazon", 2, 0);
		sleep();
		Select s = new Select(st.getHome_Page().getDropdown());
		List<WebElement> ddoptions = s.getOptions();
		for (int i = 0; i < ddoptions.size(); i++) {
			String ddvalue = ddoptions.get(i).getText();

			if (expectedDDvalue.equals(ddvalue)) {
				s.selectByVisibleText(ddvalue);
			}
		}
	}

	@When("user Enter The Product")
	public void user_enter_the_product() throws IOException, InterruptedException {
		sleep();
		expectedinput = XLSX_Reader.particlarData("amazon", 2, 1);
		userInput(st.getHome_Page().getSearchbox(), expectedinput);
	}

	@Then("compare The Entered Product and Options")
	public void compare_the_entered_product_and_options() throws InterruptedException {

		sleep();
		List<WebElement> container = st.getHome_Page().getContainer();
		explicitWaitList(container);
		for (int i = 1; i <= container.size(); i++) {
			WebElement input = st.getHome_Page().getInput();
			String text = input.getText();

			if (expectedinput.equalsIgnoreCase(text)) {
				clickonElement(input);
				break;
			}
		}
	}

	@Then("validate The Output")
	public void validate_the_output() {
		List<WebElement> moreresult = st.getProduct_Page().getMoreresult();
		explicitWaitList(moreresult);
		System.out.println("total more results is : " + moreresult.size());
		List<WebElement> results = st.getProduct_Page().getResults();
		explicitWaitList(results);
		System.out.println("total results is : " + results.size());
	}

}
