package stepDef;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.DarazSearchPage;
import utils.ExcelReader;

public class SearchStepDefinitions {
    private WebDriver driver;
    private DarazSearchPage darazSearchPage;
    private static final String FILE_PATH = "src/test/resources/testdata/testData.xlsx";

    @Given("user is on the Daraz homepage")
    public void user_is_on_the_daraz_homepage() {
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        driver.get("https://www.daraz.pk/");
        darazSearchPage = new DarazSearchPage(driver);
    }

    @When("user searches for products from Excel sheet {string}")
    public void user_searches_for_products_from_excel_sheet(String sheetName) throws IOException, InterruptedException {
        ExcelReader excelReader = new ExcelReader(FILE_PATH);
        List<String> searchKeywords = excelReader.getSearchKeywords(sheetName);

        for (String keyword : searchKeywords) {
            try {
                // Enter the search keyword and initiate search
                darazSearchPage.enterSearchKeyword(keyword);
                darazSearchPage.clickSearchButton();

                // Wait for the search results to load
                if (darazSearchPage.areSearchResultsDisplayed()) {
                    System.out.println("Search results displayed for keyword: " + keyword);
                } else {
                    System.out.println("No search results found for keyword: " + keyword);
                }

            } catch (TimeoutException e) {
                // Handling the TimeoutException and printing an informative message
                System.out.println("Search results not found within the time limit for keyword: " + keyword);
            }

            // Navigate back to the home page after each search to reset the state
            driver.navigate().back();

            // A small wait to allow page to load before the next search
            Thread.sleep(2000); // Adjust based on page load speed
        }
    }

    @Then("search results are displayed for the keywords from Excel")
    public void search_results_are_displayed_for_keywords_from_excel() throws InterruptedException {
        boolean resultsDisplayed = darazSearchPage.areSearchResultsDisplayed();
        if (resultsDisplayed) {
            System.out.println("Search results are displayed for the keywords.");
        } else {
            System.out.println("No search results found for the keywords.");
        }

        // Close the driver after the test execution is completed
        DriverManager.quitDriver();
    }
}
