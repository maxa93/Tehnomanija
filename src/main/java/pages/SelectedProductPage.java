package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SelectedProductPage extends BasePage{

    //Web elementi
    @FindBy(xpath = "//a[@class='basket fnc-add-to-basket']")
    WebElement dodajUkorpubutton;

    @FindBy(xpath = "//div//a[text()='Završi kupovinu']")
    WebElement zavrsiKupovinuButton;

    //Konstruktor

    public SelectedProductPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    //Metode nad web elementima

    public SelectedProductPage clickDodajuKorpuButton(){
        dodajUkorpubutton.click();
        return this;
    }

    public ShoppingCartPage clickZavrsiKupovinuButton(){
        zavrsiKupovinuButton.click();
        return new ShoppingCartPage(driver);
    }

}
