package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    //Web elementi
    @FindBy(className = "user-name")
    WebElement prijavaModalButton;

    @FindBy(id = "js-logout")
    WebElement logoutButton;

    @FindBy( id = "fnc-search_field")
    WebElement searchTestField;

    //Konstruktor
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    //Metode nad web elementima

    public LoginPage clickLogoutButton(){
        Actions moveHover = new Actions(driver);
        moveHover.moveToElement(prijavaModalButton).perform();
        waitForElement(logoutButton);
        logoutButton.click();
        return this;
    }

    public LaptopRacunariPage enterProductNameInSearchField(String nazivProizvoda){
        searchTextField.click();
        searchTextField.sendKeys(nazivProizvoda);
        searchTextField.click();
        sleep();
        return new LaptopRacunariPage(driver);

    }

}
