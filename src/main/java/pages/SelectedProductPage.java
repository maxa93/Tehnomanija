package pages;

import org.openqa.selenium.By;
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
    public String ActualTitle(){
        String actualTitle = driver.findElement(By.xpath("//h1[@id='fnc-product-name-161300']")).getText();
       // assert "HP Laptop Spectre x360 Convertible 14-ea0057nn 13,5\"/Intel Core i".contains(actualTitle) : "Error. Expected title to be: " + "HP Laptop Spectre x360 Convertible 14-ea0057nn 13,5\"/Intel Core i" + ".Actual: " + actualTitle;
        return actualTitle;
    }

}
