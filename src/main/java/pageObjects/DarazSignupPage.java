package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DarazSignupPage {
    private WebDriver driver;

    private By phoneNumberField = By.id("phoneNumber");
    private By smsVerificationCodeField = By.id("smsCode");
    private By passwordField = By.id("password");
    private By fullNameField = By.id("fullName");
    private By birthdayMonthDropdown = By.id("birthdayMonth");
    private By birthdayDayDropdown = By.id("birthdayDay");
    private By birthdayYearDropdown = By.id("birthdayYear");
    private By genderDropdown = By.id("gender");
    private By signUpButton = By.id("signUpButton");
    private By errorMessage = By.id("errorMessage");
    private By userSignedUpElement = By.id("homePageElement");

    public DarazSignupPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterPhoneNumber(String phoneNumber) {
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }

    public void enterSmsVerificationCode(String smsCode) {
        driver.findElement(smsVerificationCodeField).sendKeys(smsCode);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void enterFullName(String fullName) {
        driver.findElement(fullNameField).sendKeys(fullName);
    }

    public void selectBirthday(String month, String day, String year) {
        driver.findElement(birthdayMonthDropdown).sendKeys(month);
        driver.findElement(birthdayDayDropdown).sendKeys(day);
        driver.findElement(birthdayYearDropdown).sendKeys(year);
    }

    public void selectGender(String gender) {
        driver.findElement(genderDropdown).sendKeys(gender);
    }

    public void clickSignUpButton() {
        driver.findElement(signUpButton).click();
    }

    public boolean isUserSignedUp() {
        return driver.findElement(userSignedUpElement).isDisplayed();
    }

    public String getErrorMessage() {
        WebElement errorElement = driver.findElement(errorMessage);
        return errorElement.isDisplayed() ? errorElement.getText() : null;
    }
}
