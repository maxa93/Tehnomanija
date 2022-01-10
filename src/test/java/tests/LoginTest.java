package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;
import pages.Strings;

public class LoginTest extends BaseTest{

    /**
     * Login test with Valid credentials
     * Steps:
     * 1. Navigate to https:https://www.tehnomanija.rs/ and accept cookies
     * 2. Click on Prijava modal
     * 3. Enter valid username
     * 4. Enter valid password
     * 5. Click login button
     *
     * Expected result
     * 5. Verify that user is logged in and on home page
     */
    @Test
    public void loginWithValidEmailAndPassword(){
        driver = openChromeDriver();
        try {
            print("Navigate to https:https://www.tehnomanija.rs/ and accept cookies");
            BasePage basePage = new BasePage(driver).clickSlazemSeButton();
            print("Clik on prijava modal");
            basePage.clickPrijavaModalButton();
            print("Enter valid username/email");
            basePage.enterUsernameTextField(Strings.VALID_EMAIL);
            print("Enter valid password");
            basePage.enterPasswordTextField(Strings.VALID_PASSWORD);
            print("Click on login button");
            LoginPage loginPage = basePage.clickLoginButton();
            print("Verify that user is logged in");
            String currentURL = driver.getCurrentUrl();
            Assert.assertTrue(currentURL.contains(Strings.LOGIN_PAGE_URL), "We are not logged in. Expected url : " + Strings.LOGIN_PAGE_URL +
                                ". Actual: " + currentURL);
        } finally {
            driver.quit();
        }
    }

    /**
     * Login test with Invalid credentials
     * Steps:
     * 1. Navigate to https:https://www.tehnomanija.rs/ and accept cookies
     * 2. Click on Prijava modal
     * 3. Enter invalid username
     * 4. Enter valid password
     * 5. Click login button
     *
     * Expected result
     * 5.Verify that user is not logged in and error message is shown
     */
    @Test
    public void loginWithInvalidEmailAndValidPassword(){
        driver = openChromeDriver();
        try {
            print("Navigate to https:https://www.tehnomanija.rs/ and accept cookies");
            BasePage basePage = new BasePage(driver).clickSlazemSeButton();
            print("Clik on prijava modal");
            basePage.clickPrijavaModalButton();
            print("Enter invalid username/email");
            basePage.enterUsernameTextField(Strings.INVALID_EMAIL);
            print("Enter valid password");
            basePage.enterPasswordTextField(Strings.VALID_PASSWORD);
            print("Click on login button");
            basePage.clickLoginButton();
            print("Verify that user is not logged in and error message is shown");
            String currentErrorMessage = basePage.getloginErrorMessage();
            Assert.assertTrue(currentErrorMessage.equals(Strings.ERROR_WRONG_EMAIL_FORMAT),
                    "Wrong error: Expected : " + Strings.ERROR_WRONG_EMAIL_FORMAT + ". Actual: "+ currentErrorMessage);
        } finally {
            driver.quit();
        }
    }
    /**
     * Login test with empty credentials
     * Steps:
     * 1. Navigate to https:https://www.tehnomanija.rs/
     * 2. Click on Prijava modal
     * 3. Leave the email field blank
     * 4. Leave the password field blank
     * 5. Click login button
     *
     * Expected result
     * 5.Verify that error message is shown
     */
    @Test
    public void loginWitEmptyEmailAndPasswordTextField(){
        driver = openChromeDriver();
        try {
            print("Navigate to https:https://www.tehnomanija.rs/ and accept cookies");
            BasePage basePage = new BasePage(driver).clickSlazemSeButton();
            print("Clik on prijava modal");
            basePage.clickPrijavaModalButton();
            print("Leave the email field blank");
            basePage.enterUsernameTextField(Strings.EMPTY_EMAIL);
            print("Leave the password field blank");
            basePage.enterPasswordTextField(Strings.EMPTY_PASSWORD);
            print("Click on login button");
            basePage.clickLoginButton();
            print("Verify that user is not logged in and error message is shown");
            String currentErrorMessage = basePage.getloginErrorMessage();
            Assert.assertTrue(currentErrorMessage.equals(Strings.ERROR_EMAIL_AND_PASSWORD_ARE_REQUIRED), "Wrong error: Expected : " + Strings.ERROR_EMAIL_AND_PASSWORD_ARE_REQUIRED + ". Actual: "
                    + currentErrorMessage);
        } finally {
              driver.quit();
        }
    }
    /**
     * Logout test
     * Steps:
     * 1. Navigate to https:https://www.tehnomanija.rs/
     * 2. Click on Prijava modal
     * 3. Enter valid username
     * 4. Enter valid password
     * 5. Click login button
     * 6. Click on logout button
     *
     * Expected result
     * 5. Verify that user is logged in and on login page
     * 6. Verify that user is logged out and on main page
     */
    @Test
    public void logout(){
        driver = openChromeDriver();
        try {
            print("Navigate to https:https://www.tehnomanija.rs/ and accept cookies");
            BasePage basePage = new BasePage(driver).clickSlazemSeButton();
            print("Clik on prijava modal");
            basePage.clickPrijavaModalButton();
            print("Enter valid username/email");
            basePage.enterUsernameTextField(Strings.VALID_EMAIL);
            print("Enter valid password");
            basePage.enterPasswordTextField(Strings.VALID_PASSWORD);
            print("Click on login button");
            LoginPage loginPage = basePage.clickLoginButton();
            print("Verify that user is logged in");
            String currentURL = driver.getCurrentUrl();
            Assert.assertTrue(currentURL.contains(Strings.LOGIN_PAGE_URL), "We are not logged in. Expected url : " + Strings.LOGIN_PAGE_URL +
                    ". Actual: " + currentURL);
            print("Click on logout button in prijava modal");
            loginPage.clickLogoutButton();
            print("Verify that user is logout ");
            String currentURLlogout = driver.getCurrentUrl();
            Assert.assertTrue(currentURL.contains(Strings.MAIN_PAGE_URL), "We are not logout . Expected url : " + Strings.MAIN_PAGE_URL +
                    ". Actual: " + currentURL);




        } finally {
            driver.quit();
        }
    }
}
