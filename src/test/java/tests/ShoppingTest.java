package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class ShoppingTest extends BaseTest{  //mozda neki od ova 3 testa ne bude radio jer nema proizvoda na lageru,
                                            // u tom slucaju se samo zameni proizvod
    /**
     * Add item to cart and try to buy it without accepting th terms
     * Steps:
     * 1. Navigate to https:https://www.tehnomanija.rs/ and accept cookies
     * 2. Enter product name in search text field
     * 3. Add Acer laptop to shooping cart
     * 4. Enter ordering credentials and data
     * 5. Click cash on delivery
     * 6. Click confirm purchase
     *
     * Expected result
     * 6.Verify that error message confirm purchase is shown
     */
    @Test
    public void addItemToCartAndTryToBuyItWithoutAcceptingTheTerms(){
        driver = openChromeDriver();
        try {
            print("Navigate to https:https://www.tehnomanija.rs/ and accept cookies");
            BasePage basePage = new BasePage(driver).clickSlazemSeButton();
            print("Enter product name in search text field");
            LaptopRacunariPage laptopRacunariPage = basePage.enterSearchBoxField(Strings.HP_LAPTOP_SPECTRE_X360);
            print("Add laptop to shooping cart and go to shopping cart");
            ShoppingCartPage shoppingCartPage = laptopRacunariPage.addItemToCartAndGoToCart(Strings.HP_LAPTOP_SPECTRE);
            print("Enter ordering credentials and data");
            shoppingCartPage.enterImePrezimeEmailMobilni(Strings.IME,Strings.PREZIME,Strings.EMAIL,Strings.BROJ_MOBILNOG);
            shoppingCartPage.enterUlicaGradPostanskiBroj(Strings.ENTER_STREET_CITY_POST_CODE);
            print("Click cash on delivery");
            shoppingCartPage.clickPlacanjePouzecem();
            print("Click confirm purchase");
            shoppingCartPage.clickPotvrdiKupovinu();
            print("Verify that error message confirm purchase is shown");
            String currentErrorMessage = shoppingCartPage.getPotvrdiKupovinuErrorMessage();
            Assert.assertTrue(currentErrorMessage.equals(Strings.ERROR_MESSAGE_POTVRDI_KUPOVINU),
                    "Wrong error: Expected : "+ Strings.ERROR_MESSAGE_POTVRDI_KUPOVINU + ". Actual: "+ currentErrorMessage);

        } finally {
            driver.quit();
        }
    }
    /**
     * Add item and remove it from cart from laptop racunari page
     * Steps:
     * 1. Navigate to https:https://www.tehnomanija.rs/ and accept cookies
     * 2. Enter product name in search text field
     * 3. Add Lenovo laptop to shooping cart
     * 4. Remove item from cart
     *
     * Expected result
     * 3. Verify that shopping cart badge shows number 1
     * 4. Verify that shopping cart does not show any number
     */
    @Test
    public void testAddAndRemoveItemFromCartFromLaptopRacunariPage(){
        driver = openChromeDriver();
        try {
            print("Navigate to https:https://www.tehnomanija.rs/ and accept cookies");
            BasePage basePage = new BasePage(driver).clickSlazemSeButton();
            print("Enter product name in search text field");
            LaptopRacunariPage laptopRacunariPage = basePage.enterSearchBoxField(Strings.LAPTOP_RACUANRI);
            print("Add laptop to shooping cart");
            laptopRacunariPage.addItemToCart(Strings.ASUS_LAPTOP);
            print("Verify that shopping cart badge shows number 1");
            Integer numberOfItems = laptopRacunariPage.getNumberOfItemsFromShoppingCartIcon();
            assert numberOfItems.equals(1) : "Wrong number of items: Expected: 1. Actual: " + numberOfItems;
            assert laptopRacunariPage.isShoppingCartEmpty()==true : "Error. Shopping cart item number should be shown";
            print("Remove item from cart");
            laptopRacunariPage.removeItemFromCart();
            print("Verify that shopping cart does not show any number");
            assert laptopRacunariPage.isShoppingCartEmpty()==false : "Error. Shopping cart item number should not be shown";

        } finally {
           driver.quit();
        }

    }
    /**
     * Search for item and clicking on it
     *
     * Test steps:
     * 1. Navigate to https:https://www.tehnomanija.rs/ and accept cookies
     * 2. Enter an item name in search field
     * 3. From search results find that item and click on it
     *
     * Expected result:
     * 3. Verify that the same item you searched is shown on item page
     */
    @Test
    public void testSearch(){
        driver = openChromeDriver();
        try {
            print("Navigate to https:https://www.tehnomanija.rs/ and accept cookies");
            BasePage basePage = new BasePage(driver).clickSlazemSeButton();
            print("Enter product name in search text field");
            LaptopRacunariPage laptopRacunariPage = basePage.enterSearchBoxField(Strings.HP_LAPTOP_SPECTRE_X360);
            print("Find item from search result and click on it");
            SelectedProductPage selectedProductPage = laptopRacunariPage.clickOnItem(Strings.HP_LAPTOP_SPECTRE);
            print("Verify that the same item you searched is shown on item page");
            assert selectedProductPage.ActualTitle().contains(Strings.HP_LAPTOP_SPECTRE): "Error. Expected title to be: " + "HP Laptop Spectre x360 Convertible 14-ea0057nn 13,5\"/Intel Core i";
          //  String actualTitle = driver.findElement(By.xpath("//h1[@id='fnc-product-name-161300']")).getText();
          //  assert "HP Laptop Spectre x360 Convertible 14-ea0057nn 13,5\"/Intel Core i".contains(actualTitle) : "Error. Expected title to be: " + "HP Laptop Spectre x360 Convertible 14-ea0057nn 13,5\"/Intel Core i" + ".Actual: " + actualTitle;


        } finally {
            driver.quit();
        }
    }

}
