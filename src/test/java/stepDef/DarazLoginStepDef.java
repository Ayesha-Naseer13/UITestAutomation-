package stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;
import pageObjects.DarazLoginPage;

import java.util.List;
import java.util.Map;

public class DarazLoginStepDef {
    private WebDriver driver;
    private DarazLoginPage darazLoginPage;

    @Given("user is on the Daraz login page")
    public void user_is_on_the_daraz_login_page() {
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        driver.get("https://member.daraz.pk/user/login");
        darazLoginPage = new DarazLoginPage(driver);
    }

    @When("user enters valid credentials")
    public void user_enters_valid_credentials(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        String username = data.get(0).get("username");
        String password = data.get(0).get("password");
        darazLoginPage.enterUsername(username);
        darazLoginPage.enterPassword(password);
        // Consider replacing with WebDriverWait in actual implementation
        Thread.sleep(20);
    }

    @When("user enters invalid credentials")
    public void user_enters_invalid_credentials(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        String username = data.get(0).get("username");
        String password = data.get(0).get("password");
        darazLoginPage.enterUsername(username);
        darazLoginPage.enterPassword(password);
        // Consider replacing with WebDriverWait in actual implementation
        Thread.sleep(20);
    }

    @When("user enters empty credentials")
    public void user_enters_empty_credentials() throws InterruptedException {
        darazLoginPage.enterUsername("");
        darazLoginPage.enterPassword("");
        // Consider replacing with WebDriverWait in actual implementation
        Thread.sleep(20);
    }

    @When("user enters credentials")
    public void user_enters_credentials(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        String username = data.get(0).get("username");
        String password = data.get(0).get("password");

        // Pass empty string if the value is null (missing username or password)
        darazLoginPage.enterUsername(username != null ? username : "");
        darazLoginPage.enterPassword(password != null ? password : "");
        
        // Consider replacing with WebDriverWait in actual implementation
        Thread.sleep(20);
    }


    @And("user clicks on the Daraz login button")
    public void user_clicks_on_the_daraz_login_button() throws InterruptedException {
        darazLoginPage.clickLoginButton();
        // Consider replacing with WebDriverWait in actual implementation
        Thread.sleep(20);
    }

    @Then("user is navigated to the Daraz home page")
    public void user_is_navigated_to_the_daraz_home_page() throws InterruptedException {
        if (darazLoginPage.isUserLoggedIn()) {
            System.out.println("User is successfully logged in and navigated to the home page.");
        } else {
            System.out.println("Login failed.");
        }
        // Consider replacing with WebDriverWait in actual implementation
        Thread.sleep(20);
        DriverManager.quitDriver();
    }

    @Then("an error message is displayed")
    public void an_error_message_is_displayed() throws InterruptedException {
        String errorMessage = darazLoginPage.getErrorMessage();
        if (errorMessage != null && !errorMessage.isEmpty()) {
            System.out.println("Error message displayed: " + errorMessage);
        } else {
            System.out.println("No error message displayed.");
        }
        // Consider replacing with WebDriverWait in actual implementation
        Thread.sleep(20);
        DriverManager.quitDriver();
    }
}
