package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage extends BasePage{

    //Web elementi
    @FindBy(id = "js-first-name")
    WebElement imeTextField;

    @FindBy(id = "js-last-name")
    WebElement prezimeTextField;

    @FindBy(id = "js-email-main")
    WebElement emailTextField;

    @FindBy(id = "js-mobile")
    WebElement brojMobilnogTextField;

    @FindBy(id = "js-street")
    WebElement ulicaTextField;

    @FindBy(id = "js-street-number")
    WebElement brojUliceTextField;

    @FindBy(id = "js-flat")
    WebElement brojStanaTextField;

    @FindBy(id = "js-floor")
    WebElement spratTextField;

    @FindBy(id = "js-city")
    WebElement gradTextField;

    @FindBy(id = "js-post-code")
    WebElement postanskiBrojTextField;

    @FindBy(xpath = "//label[text()='Plaćanje gotovinski prilikom preuzimanja']")
    WebElement placanjePouzecem;

    @FindBy(id = "rc-anchor-container")
    WebElement recaptchaContainer;

    @FindBy(id = "recaptcha-checkbox-border")
    WebElement nisamRobotCheckbox;

    @FindBy(xpath = "//*[@id=\"mCSB_3_container\"]/div[1]")
    WebElement city;

    @FindBy(xpath = "//*[@id=\"mCSB_2_container\"]/div")
    WebElement streetSelect;

    @FindBy(xpath = "//iframe[@name='chat-widget']")
    WebElement iFrameWidget;

    @FindBy(xpath = "//span[@class='Linkify']")
    WebElement wiget2;

    @FindBy(xpath = "//input[@value='potvrdi kupovinu']")
    WebElement potvrdiKupovinuButton;

    @FindBy(xpath = "//span[text()='* ovo je obavezno polje!']")
    WebElement potvrdiKupovinuErrorMessage;

    //Konstruktor

    public ShoppingCartPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    //Metode nad web elementima
    public ShoppingCartPage iframe(){
        waitForElement(iFrameWidget);
        driver.switchTo().frame(iFrameWidget);
        Actions mouseHover = new Actions(driver);
        mouseHover.moveToElement(driver.findElement(By.xpath("//span[@class='Linkify']"))).perform();
        return this;

    }
    public ShoppingCartPage enterImeTextField(String ime){
        imeTextField.click();
        imeTextField.sendKeys(ime);
        return this;
    }

    public ShoppingCartPage enterImePrezimeEmailMobilni(String ime,String prezime,String email,String mobilni){
        imeTextField.click();
        imeTextField.sendKeys(ime);
        prezimeTextField.click();
        prezimeTextField.sendKeys(prezime);
        emailTextField.click();
        emailTextField.sendKeys(email);
        brojMobilnogTextField.click();
        brojMobilnogTextField.sendKeys(mobilni);
        return this;
    }

    public ShoppingCartPage enterUlicaGradPostanskiBroj(String ulica){
        ulicaTextField.click();
        ulicaTextField.sendKeys(ulica);
        sleep();
        waitForElement(streetSelect);
        streetSelect.click();
        sleep();
        enterBrojUliceTextField(Strings.BROJ_ULICE);
        sleep();
        return this;

    }

    public ShoppingCartPage enterBrojUliceTextField(String brojUlice){
        brojUliceTextField.click();
        brojUliceTextField.sendKeys(brojUlice);
        return this;
    }

    public ShoppingCartPage clickPlacanjePouzecem(){
        placanjePouzecem.click();
        return this;
    }

    public ShoppingCartPage clickPotvrdiKupovinu(){
        potvrdiKupovinuButton.click();
        return this;
    }

    public String getPotvrdiKupovinuErrorMessage(){
        String errorMessage = potvrdiKupovinuErrorMessage.getText();
        return errorMessage;
    }

    public void clickNisamRobotCheckbox(){
        nisamRobotCheckbox.click();
    }
}
