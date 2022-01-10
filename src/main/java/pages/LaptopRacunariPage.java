package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LaptopRacunariPage extends BasePage{

    //Web elementi
    @FindBy(xpath = "//div//label[text()='Acer']")
    WebElement acerCheckboxField;

    @FindBy(xpath = "//label[@for='filter_Veličina ekrana_2_ec']")
    WebElement velicinaEkranaCheckboxField;

    @FindBy(xpath = "//label[text()='1920x1080']")
    WebElement rezolucijaChechboxField;

    @FindBy(xpath = "//div[@class='sort-products js-show-sort']")
    WebElement sortDropdownMenu;

    @FindBy(xpath = "//div[text()='Nazivu']")
    WebElement sortByPoNazivu;

    @FindBy(xpath = "//div[text()='Ceni rastuće']")
    WebElement sortByCeniRastuce;

    @FindBy(xpath = "//div[text()='Ceni opadajuće']")
    WebElement sortByCeniOpadajuce;

    @FindBy(xpath = "//div[@id='fnc-product-name-165906']")
    WebElement acerLaptop;

    @FindBy(xpath = "//a[@data-blue-product-name='Asus Laptop UX363JA-WB502T 13,3\"/Intel i5/8 GB/512 GB SSD/Windows']")
    WebElement addToCartAcerLaptop;

    @FindBy(xpath = "//a[@data-blue-product-name='Lenovo laptop IP 5 82FE00J0YA 14\" FHD/ Intel i7-1165G7/16 GB/512 ']")
    WebElement addToCartLenovoLaptop;

    @FindBy(xpath = "basket-header js-basket-header")
    WebElement shoppingCartBadge;

    @FindBy(xpath = "//span[@class='js-br-proizvoda br-proizvoda']")
    WebElement numberOfItemsInCartBadge;

    @FindBy(xpath = "//a[@class='header-basket-items-buy']")
    WebElement zavrsiKupovinuButton;

    @FindBy(xpath = "//i[@class='thm-dismiss']")
    WebElement removeItemFromSoppingCart;

    @FindBy(xpath = "//a[@class='basket grid fnc-add-to-basket']")
    WebElement addToCartButton;

    //Konstruktor

    public LaptopRacunariPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
        this.driver = driver;
        sleep();
    }

    //Metode nad web elementima

    public LaptopRacunariPage clickOnAcerCheckboxField(){
        acerCheckboxField.click();
        return this;
    }

    public LaptopRacunariPage clickSortDropdownMenu(){
        sortDropdownMenu.click();
        return this;
    }
    //add Acer laptop to shooping cart and verify that number 1 is displayed on cart badge
//    public ShoppingCartPage addAcerLaptopToCart(){
//        waitForElement(acerLaptop);
//        addToCartAcerLaptop.click();
//        waitForElement(numberOfItemsInCartBadge);
//        Actions moveHover = new Actions(driver);
//        moveHover.moveToElement(driver.findElement(By.xpath("//span[@class='js-br-proizvoda br-proizvoda']"))).perform();
//        waitForElement(zavrsiKupovinuButton);
//        zavrsiKupovinuButton.click();
//        return new ShoppingCartPage(driver);
//    }

    public LaptopRacunariPage clickSortByPoNazivu(){
        sortDropdownMenu.click();
        waitForElement(sortByPoNazivu);
        sortByPoNazivu.click();
        return this;
    }

    public LaptopRacunariPage clickSortByPoCeniRastuce(){
        sortDropdownMenu.click();
        waitForElement(sortByCeniRastuce);
        sortByCeniRastuce.click();
        return this;
    }

    public LaptopRacunariPage clickSortByPoCeniOpadajuce(){
        sortDropdownMenu.click();
        waitForElement(sortByCeniOpadajuce);
        sortByCeniOpadajuce.click();
        return this;
    }

    public LaptopRacunariPage addToCartLenovoLaptop(){
        addToCartLenovoLaptop.click();
        return this;
    }

    public LaptopRacunariPage itemNumberInShoopingCart(){
        shoppingCartBadge.isDisplayed();
        return this;
    }

    public LaptopRacunariPage removeItemFromCart(){
        removeItemFromSoppingCart.click();
        driver.navigate().refresh(); //broj 1 ostaje i posle klika na remove button gubi se tek kada se stranica refresuje
        return this;
    }
    //uzimamo cenu proizvoda
    public List<String> getAllItemPrices() {
        List<WebElement> itemPricesElements = driver.findElements(By.xpath("//div[@class='price']"));
        List<String> itemPricesList = new ArrayList<String>();
        for(WebElement itemPrice : itemPricesElements) {
            String price = itemPrice.getText();
            price = price.trim().replaceAll("RSD", "");
            price = price.replaceAll("\\.","");
            price = price.replaceAll("\s","");
            price = price.replaceAll(",",".");
            itemPricesList.add(price);
        }
        return itemPricesList;
    }
    //uzimamo name listu pre sortiranja
    public List<String> getAllItemNames() {
        List<WebElement> itemNamesElements = driver.findElements(By.xpath("//div[@class='product-name-grid']"));
        List<String> itemNamesList = new ArrayList<String>();
        for(WebElement itemName : itemNamesElements) {
            String name = itemName.getText();
            itemNamesList.add(name);
        }
        return itemNamesList;

    }
    //uzimamo name listu posle sortiranja
    public List<String> getAllItemNamesAfterSort() {
        List<WebElement> itemNamesElements = driver.findElements(By.xpath("//div[@class='product-name-grid']"));
        List<String> itemNamesList = new ArrayList<String>();
        for(WebElement itemName : itemNamesElements) {
            String name = itemName.getText();
            itemNamesList.add(name);
        }
        return itemNamesList;
    }
    public ShoppingCartPage addItemToCartAndGoToCart(String itemName) {
        String customXpath = "//a[@data-blue-product-name='" + itemName + "']";
        List<WebElement> elements = driver.findElements(By.xpath(customXpath));
        assert elements.size()!=0 : "Array is empty";
        elements.get(0).click();
        waitForElement(zavrsiKupovinuButton);
        zavrsiKupovinuButton.click();
        return new ShoppingCartPage(driver);
    }
    public LaptopRacunariPage addItemToCart(String itemName) {
        String customXpath = "//a[@data-blue-product-name='" + itemName + "']";
        List<WebElement> elements = driver.findElements(By.xpath(customXpath));
        assert elements.size()!=0 : "Array is empty";
        elements.get(0).click();
        return this;
    }

    public LaptopRacunariPage clickOnItem(String itemName) {
        List<WebElement> elements = driver.findElements(By.xpath("//a[@class='product-link']"));
        assert elements.size()!=0 : "Array is empty";
        elements.get(0).click();
        return this;
    }
    //metoda za shooping cart item number
    public boolean  isShoppingCartEmpty() {
        boolean isShoppingCartNumberShown = false;
        try {
            isShoppingCartNumberShown = numberOfItemsInCartBadge.isDisplayed();
        }catch (Exception error) {}
        return isShoppingCartNumberShown;
    }

    public Integer getNumberOfItemsFromShoppingCartIcon() {
        waitForElement(numberOfItemsInCartBadge);
        String number = numberOfItemsInCartBadge.getText();
        return Integer.valueOf(number);
    }

}
