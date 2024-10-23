package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DarazSearchPage {
    private WebDriver driver;

    // Locators
    private By searchField = By.xpath("//input[@id='q']"); // Search input field
    private By searchButton = By.xpath("//a[@class='search-box__button--1oH7' and @tabindex='2']"); // Updated for <a> tag as the search button
    private By searchResults = By.xpath("//div[contains(@class, 'c3e8SH')]"); // Adjust locator for search results

    // Constructor
    public DarazSearchPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods

    // Enter the search keyword
    public void enterSearchKeyword(String keyword) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(searchField));
        
        // Clear the search input field
        searchInput.clear();  // This method will explicitly clear the input field.
        
        // Now, enter the new keyword
        searchInput.sendKeys(keyword);
    }

    // Click the search button (Updated for <a> tag)
    public void clickSearchButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchBtn.click();
    }

    // Verify if search results are displayed
    public boolean areSearchResultsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60)); // Increased wait time
        try {
            // Waiting for specific elements within the results
            List<WebElement> results = wait
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='c3e8SH']//a")));
            return results.size() > 0;
        } catch (Exception e) {
            System.out.println("Timeout: No search results were found within the time limit.");
            return false;
        }
    }
}
