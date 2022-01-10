package tests;

import org.testng.annotations.Test;
import pages.BasePage;
import pages.Strings;

public class SocialNetworkTest extends BaseTest {
    /** Test Facebook link
     * Steps:
     * 1. Navigate to https:https://www.tehnomanija.rs/ and accept cookies
     * 2. Click on facebook button in footer menu
     *
     * Expected results:
     *2.Verify that user is redirected to facebook link page
     */
    @Test
    public void facebookLinkTest(){
        driver = openChromeDriver();
        try {
            print("Navigate to https:https://www.tehnomanija.rs/ and accept cookies");
            BasePage basePage = new BasePage(driver).clickSlazemSeButton();
            print("Click on facebook button");
            basePage.clickFacebookButton();
            print("Verify that user is redirected to facebook link page");
            String currentURL=driver.getCurrentUrl();
            assert currentURL.equals(Strings.FACEBOOK_PAGE_URL) : "Error.Wrong url.Expected to be on Facebook page, but i'm on "+currentURL;
        } finally {
            driver.quit();
        }
    }

}
