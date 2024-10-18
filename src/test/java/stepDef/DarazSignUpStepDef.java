package stepDef;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.DarazSignupPage;
import utils.DriverManager;

public class DarazSignUpStepDef {
    private WebDriver driver;
    private DarazSignupPage darazSignUpPage;

    @Given("user is on the Daraz signup page")
    public void user_is_on_the_daraz_signup_page() {
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        driver.get("https://member.daraz.pk/user/register");
        darazSignUpPage = new DarazSignupPage(driver);
    }

    @When("user enters {string} details")
    public void user_enters_details(String detailType, io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        String phoneNumber = data.get(0).get("phoneNumber");
        String smsCode = data.get(0).get("smsCode");
        String password = data.get(0).get("password");
        String fullName = data.get(0).get("fullName");
        String birthdayMonth = data.get(0).get("birthdayMonth");
        String birthdayDay = data.get(0).get("birthdayDay");
        String birthdayYear = data.get(0).get("birthdayYear");
        String gender = data.get(0).get("gender");

        darazSignUpPage.enterPhoneNumber(phoneNumber);
        darazSignUpPage.enterSmsVerificationCode(smsCode);
        darazSignUpPage.enterPassword(password);
        darazSignUpPage.enterFullName(fullName);
        darazSignUpPage.selectBirthday(birthdayMonth, birthdayDay, birthdayYear);
        darazSignUpPage.selectGender(gender);
        waitForPageLoad();
    }

    @And("user clicks on the Daraz signup button")
    public void user_clicks_on_the_daraz_signup_button() {
        darazSignUpPage.clickSignUpButton();
        waitForPageLoad();
    }

    @Then("user is navigated to the Daraz home page")
    public void user_is_navigated_to_the_daraz_home_page() {
        if (darazSignUpPage.isUserSignedUp()) {
            System.out.println("User is successfully signed up and navigated to the home page.");
        } else {
            System.out.println("Signup failed.");
        }
        DriverManager.quitDriver();
    }

    @Then("sign up error message is displayed")
    public void sign_up_error_message_is_displayed() {
        String errorMessage = darazSignUpPage.getErrorMessage();
        if (errorMessage != null && !errorMessage.isEmpty()) {
            System.out.println("Error message displayed: " + errorMessage);
        } else {
            System.out.println("No error message displayed.");
        }
        DriverManager.quitDriver();
    }

    private void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Updated constructor
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("someElementId"))); // Replace with actual element ID
    }
}
