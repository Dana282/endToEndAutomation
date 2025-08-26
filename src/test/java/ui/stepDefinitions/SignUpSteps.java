package ui.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ui.pages.BasePage;
import ui.pages.MainPage;
import utils.Driver;



public class SignUpSteps extends BasePage {
    WebDriver driver = Driver.getDriver();
    MainPage mainPage = new MainPage(driver);
    private static final Logger logger = LogManager.getLogger(SignUpSteps.class);


    @Given("user is on {string}")
    public void user_is_on(String url) {
        logger.info("Opening the url: " + url);
        driver.get(url);
        logger.info("Successfully loaded new page: " + url);
    }

    @When("user clicks on sign up button")
    public void user_clicks_on_sign_up_button() {
        mainPage.signUpButton.click();
        logger.info("user clicked on sign up button");
    }

    @When("user provides email {string}, password {string}, confirm password {string}")
    public void user_provides_email_password_confirm_password(String email, String password, String confirmPassword) {
        mainPage.email.sendKeys(faker.internet().emailAddress());
        logger.info("user entered email: " + email);

        mainPage.password.sendKeys(password);
        logger.info("user entered password: " + password);

        mainPage.confirmPassword.sendKeys(confirmPassword);
        logger.info("user entered confirmation password: " + confirmPassword);
    }

    @When("user clicks on continue")
    public void user_clicks_on_continue() {
    mainPage.continueBtn.click();
    logger.info("user clicked on continue button");
    }

    @Then("verify sign up form appeared")
    public void verify_sign_up_form_appeared() {
    waitVisible(mainPage.firstName);
        Assert.assertTrue(mainPage.firstName.isDisplayed());
        logger.info("Sign up form is displayed");
    }

    @Then("user provides first name, last name, name of business, area of business, currency")
    public void user_provides_first_name_last_name_name_of_business_area_of_business_currency() {
    mainPage.firstName.sendKeys(faker.name().firstName());
    mainPage.lastName.sendKeys(faker.name().lastName());
    mainPage.nameOfBusiness.sendKeys(faker.company().name());
    mainPage.area0fBusinessDropdown.click();
    waitVisible(mainPage.areaOfBusinessOption);
    mainPage.areaOfBusinessOption.click();
    logger.info("User selected area of business");
    mainPage.address.sendKeys(faker.address().fullAddress());
    mainPage.currencyDropdown.click();
    waitVisible(mainPage.currencyOption);
    logger.info("User selected currency");
    mainPage.currencyOption.click();
    logger.info("User entered firstname, lastname, address");

    }
    @Then("user clicks on final green sign up button")
    public void user_clicks_on_final_green_sign_up_button() {
    mainPage.signUpBtn2.click();
    logger.info("User clicked on final sign up button");
    }

    @Then("verify user is signed in")
    public void verify_user_is_signed_in() throws InterruptedException {
    Thread.sleep(2000);
    Assert.assertTrue(driver.getCurrentUrl().contains("infographics"));
    logger.info("User in dashboard");
    }

    @Override
    public String name() {
        return "";
    }
}
