package stepDef;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.DarazSearchPage;
import utils.ExcelReader;

public class SearchStepDefinitions {

	WebDriver driver;
	DarazSearchPage searchPage;
	String excelFilePath = "src/test/resources/testdata/testData.xlsx"; 

	@Given("I am on the homepage")
	public void i_am_on_the_homepage() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");

		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.daraz.pk/");

	
		searchPage = new DarazSearchPage(driver);
	}

	@When("I search for the term from Excel")
	public void i_search_for_the_term_from_excel() {
		
		String searchTerm = ExcelReader.getSearchTerm(excelFilePath, "Sheet1", 0, 0);

	
		searchPage.writeInSearchBox(searchTerm);
		searchPage.clickSearchButton();
	}

	@Then("I should see search results")
	public void i_should_see_search_results() {
		
		String currentUrl = driver.getCurrentUrl();

		
		assertTrue(currentUrl.contains("search"), "The URL did not change after the search");
	}

	@After
	public void tearDown() {
		// Close the browser and clean up
		if (driver != null) {
			driver.quit();
		}
	}
}
