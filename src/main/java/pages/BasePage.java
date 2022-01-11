package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class BasePage {
    WebDriver driver = null;
    //Web elementi
    @FindBy(xpath = "//span[text()='Slažem se']")
    WebElement slazemSeButton;

    @FindBy(id = "permanent_cookies")
    WebElement trajniCookieCheckbox;

    @FindBy(id = "statistics_cookies")
    WebElement statistikaCookieCheckbox;

    @FindBy(id = "marketing_cookies")
    WebElement markentingCookieCheckbox;

    @FindBy(xpath = "//div[@class='basket-header js-basket-header']")
    WebElement korpaButton;

    @FindBy(xpath = "//li//input[@name='email']")
    WebElement usernameTextField;

    @FindBy(xpath = "//li//input[@name='password']")
    WebElement passwordTextField;

    @FindBy(className = "thm-menu-hamburger")
    WebElement burgerMenu;

    @FindBy(xpath = "//a[@title='Laptopovi i dodatna oprema']")
    WebElement laptopoviDodatnaOpremaButton;

    @FindBy(xpath = "//img[@src='https://static.tehnomanija.rs/UserFiles/menu_icons/476-icon.svg']")
    WebElement itShopButton;

    @FindBy(xpath = "//div//a[text()='Registrujte se']")
    WebElement registrujteSeButton;

    @FindBy(xpath = "//div//a[@title='Svi laptopovi']")
    WebElement sviLaptopoviButton;

    @FindBy(className = "user-name")
    WebElement prijavaModalButton;

    @FindBy(id = "login_dropdown")
    WebElement loginDropdown;

    @FindBy(xpath = "//iframe[@title='LiveChat chat widget']")
    WebElement liveChatWidget;

    @FindBy(className = "login-button")
    WebElement loginButton;

    @FindBy(xpath = "//div[@class='login_error_box']")
    WebElement loginErrorMessage;

    @FindBy(xpath = "//input[@id='fnc-search_field']")
    WebElement searchTextField;

    @FindBy(xpath = "//input[@id='search_btn']")
    WebElement searchButton;

    @FindBy(xpath = "//li//i[@class='fa fa-facebook']")
    WebElement facebookPageLink;




    //Konstruktor
    public BasePage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    //Metode nad web elementima

    public String getloginErrorMessage(){
        String errorMessage = loginErrorMessage.getText();
        return errorMessage;
    }
    public BasePage clickSlazemSeButton(){
        slazemSeButton.click();
        sleep();
        waitForElement(prijavaModalButton);
        return this;
    }

    public BasePage clickTrajniCookieCheckbox(){
        trajniCookieCheckbox.click();
        return this;
    }


    public BasePage clickStatistikaCookieCheckbox(){
        statistikaCookieCheckbox.click();
        return this;
    }

    public BasePage clickMarkentingCookieCheckbox(){
        markentingCookieCheckbox.click();
        return this;
    }

    public BasePage isDisplayedLiveChatWidget(){
        liveChatWidget.isDisplayed();
        return this;
    }

    public BasePage clickKorpaButton(){
        korpaButton.click();
        return this;
    }

    public BasePage clickPrijavaModalButton(){
        prijavaModalButton.click();
        loginDropdown.isDisplayed();
        return this;
    }

    public LoginPage clickLoginButton(){
        loginButton.click();
        return new LoginPage(driver);
    }

    public BasePage loginDropdown(){
        loginDropdown.isDisplayed();
        return this;
    }

    public BasePage clickRegistrujSeButton(){
        registrujteSeButton.click();
        return this;
    }

    public BasePage enterUsernameTextField(String username){
        usernameTextField.click();
        usernameTextField.sendKeys(username);
        return this;
    }

    public BasePage enterPasswordTextField(String password){
        passwordTextField.click();
        passwordTextField.sendKeys(password);
        return this;
    }

    public LaptopRacunariPage enterSearchBoxField(String nazivProizvoda){
        searchTextField.click();
        searchTextField.sendKeys(nazivProizvoda);
        searchButton.click();
        sleep();
        return new LaptopRacunariPage(driver);
    }

    public BasePage mouseHoverBurgerMenuButton(){
        Actions moveHover = new Actions(driver);
        moveHover.moveToElement(driver.findElement(By.xpath("//i[@class='thm-menu-hamburger']"))).perform();
        waitForElement(itShopButton);
        return this;
    }


    public BasePage mouseHoverItShopButton(){
        Actions moveHover = new Actions(driver);
        moveHover.moveToElement(driver.findElement(By.xpath("//img[@src='https://static.tehnomanija.rs/UserFiles/menu_icons/476-icon.svg']"))).perform();
        waitForElement(sviLaptopoviButton);
        return this;

    }

    public LaptopRacunariPage clickSviLaptopoviButton(){
        sviLaptopoviButton.click();
        sleep();
        return new LaptopRacunariPage(driver);
    }

    public BasePage clickFacebookButton(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", facebookPageLink);
        sleep();
        facebookPageLink.click();
        sleep();
        sleep();
        String parentTab = driver.getWindowHandle();
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        return this;
    }



    //print metoda
    public void print(String text) {
        System.out.println(text);
    }


    //Wait for element
    public void waitForElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    //sleep
    public void sleep() {

        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //da li je neki web element prisutan nas stranici
    public boolean isElementPresent(WebElement element) {
        print("Element is present");
        try {
            boolean isPresent = element.isDisplayed();
            return true;
        } catch (Exception e) {
            print(e.getMessage());
            print("Element is not present on page");
            return  false;
        }

    }

}
