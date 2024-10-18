package stepDef;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LanguageStepDefinitions {

    WebDriver driver;

    @Given("I am on the Daraz homepage")
    public void i_am_on_the_daraz_homepage() {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        
        // Initialize ChromeDriver and navigate to the Daraz homepage
        driver = new ChromeDriver();
        driver.get("https://www.daraz.pk/");
    }

    @When("I click on the language change button")
    public void i_click_on_the_language_change_button() {
        // Click on the "زبان تبدیل کریں" (Change Language) button
        WebElement languageButton = driver.findElement(By.id("topActionSwitchLang"));
        languageButton.click();
    }

    @When("I select Urdu as the language")
    public void i_select_urdu_as_the_language() {
        // Find the Urdu language option and click it
        WebElement urduOption = driver.findElement(By.xpath("//div[@class='lzd-switch-item ' and @data-lang='ur']"));
        urduOption.click();
    }


    @Then("I return to the homepage")
    public void i_return_to_the_homepage() {
        // Navigate back to the homepage
        driver.get("https://www.daraz.pk/");
    }
}
