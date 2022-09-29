package com.mav.practice.amazon_project;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mav.practice.Advance_code.XLSX_Reader;

public class Amazon {

	public static void main(String[] args) throws InterruptedException, IOException {
		String expectedDDvalue = XLSX_Reader.particlarData("amazon", 3, 0);
		String expectedinput = XLSX_Reader.particlarData("amazon", 3, 1);

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\VIGNESH S\\eclipse-workspace\\Selenium\\Driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();

		WebElement dropdown = driver.findElement(By.id("searchDropdownBox"));
		Select s = new Select(dropdown);
		List<WebElement> alloptions = s.getOptions();
		Thread.sleep(1000);
		for (int i = 0; i < alloptions.size(); i++) {
			String text = alloptions.get(i).getText();
			if (expectedDDvalue.equals(text)) {
				s.selectByVisibleText(text);
			}
		}
		Thread.sleep(2000);
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(expectedinput);
		Thread.sleep(2000);
		List<WebElement> suggestion = driver.findElements(By.xpath("//div[@class='nav-issFlyout nav-flyout']/div/div"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfAllElements(suggestion));
		for (int i = 1; i <= suggestion.size(); i++) {
			WebElement input = driver
					.findElement(By.xpath("//div[@class='nav-issFlyout nav-flyout']/div/div[" + i + "]/div/div"));
			String text = input.getText();
			if (expectedinput.equalsIgnoreCase(text)) {
				input.click();
				break;
			}
		}
		List<WebElement> moreresult = driver.findElements(
				By.xpath("//span[text()='MORE RESULTS']//following::div[@data-component-type='s-search-result']"));
		wait.until(ExpectedConditions.visibilityOfAllElements(moreresult));
		System.out.println("total more results is : " + moreresult.size());
		
		List<WebElement> results = driver.findElements(
				By.xpath("//span[text()='MORE RESULTS']//preceding::div[@data-component-type='s-search-result']"));
		wait.until(ExpectedConditions.visibilityOfAllElements(results));
		System.out.println("total results is : " + results.size());
	}
}
