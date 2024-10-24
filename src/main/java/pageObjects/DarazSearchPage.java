package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DarazSearchPage {

    WebDriver driver;

    // Locators
    By searchBox = By.id("q");  
    By searchButton = By.className("search-box__button--1oH7");  
    // Constructor
    public DarazSearchPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to enter search term into the search box
    public void writeInSearchBox(String searchTerm) {
        WebElement searchField = driver.findElement(searchBox);
        searchField.clear();
        searchField.sendKeys(searchTerm);
    }

    // Method to click the search button
    public void clickSearchButton() {
        WebElement searchBtn = driver.findElement(searchButton);
        searchBtn.click();
    }
}
