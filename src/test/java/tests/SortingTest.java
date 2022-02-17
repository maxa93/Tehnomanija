package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LaptopRacunariPage;
import pages.Strings;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SortingTest extends BaseTest{
    /**
     * Sorting test by price ascending
     * Steps:
     * 1. Navigate to https:https://www.tehnomanija.rs/ and accept cookies
     * 2. Mouse hover on burger menu
     * 3. Mouse hover on it shop
     * 4. Click on svi laptopovi
     * 5. Choose filters brand acer
     * 6. Click on sorting dropdown menu and select sort by price ascending
     *
     * Expected result
     * 6. Verify that items sorted by price ascending
     */
    @Test
    public void SortItemsByPriceAsccending(){
        driver = openChromeDriver();
        try {
            print("Navigate to https:https://www.tehnomanija.rs/ and accept cookies");
            BasePage basePage = new BasePage(driver).clickSlazemSeButton();
            print("Pass the mouse over burger menu");
            basePage.mouseHoverBurgerMenuButton();
            print("Pass the mouse over it shop");
            basePage.mouseHoverItShopButton();
            print("Click on svi laptopovi");
            LaptopRacunariPage laptopRacunariPage = basePage.clickSviLaptopoviButton();
            print("Click on filter Acer");
            laptopRacunariPage.clickOnAcerCheckboxField();
            print("Sort items by price ascending");
            List<String> itemPricesUnsorted = laptopRacunariPage.getAllItemPrices();
            laptopRacunariPage.clickSortByPoCeniRastuce();
            print("Verify that item sorted by price ascending");
            List<String> itemPricesSorted = laptopRacunariPage.getAllItemPrices();
            for(int i = 0; i<itemPricesSorted.size()-1; i++) {
                float first = Float.parseFloat(itemPricesSorted.get(i));
                float second = Float.parseFloat(itemPricesSorted.get(i+1));
                if (second < first)
                    System.out.println("ERROR. Array is not sorted");
                    print("Actual " + first + " < " + "Expected " + second);
            }
        } finally {
            driver.quit();
        }
    }
    /**
     * Sorting test by price descending
     * Steps:
     * 1. Navigate to https:https://www.tehnomanija.rs/ and accept cookies
     * 2. Mouse hover on burger menu
     * 3. Mouse hover on it shop
     * 4. Click on svi laptopovi
     * 5. Choose filters brand Acer
     * 6. Click on sorting dropdown menu and select sort by price descending
     *
     * Expected result
     * 6. Verify that items sorted by price descending
     */
    @Test
    public void SortItemsByPriceDescending(){
        driver = openChromeDriver();
        try {
            print("Navigate to https:https://www.tehnomanija.rs/ and accept cookies");
            BasePage basePage = new BasePage(driver).clickSlazemSeButton();
            print("Pass the mouse over burger menu");
            basePage.mouseHoverBurgerMenuButton();
            print("Pass the mouse over it shop");
            basePage.mouseHoverItShopButton();
            print("Click on svi laptopovi");
            LaptopRacunariPage laptopRacunariPage = basePage.clickSviLaptopoviButton();
            print("Click on filter Acer");
            laptopRacunariPage.clickOnAcerCheckboxField();
            print("Sort items by price descending");
            List<String> itemPricesUnsorted = laptopRacunariPage.getAllItemPrices();
            laptopRacunariPage.clickSortByPoCeniOpadajuce();
            print("Verify that items sorted by price descending");
            List<String> itemPricesSorted = laptopRacunariPage.getAllItemPrices();
            for(int i = 0; i<itemPricesSorted.size()-1; i++) {
                float first = Float.parseFloat(itemPricesSorted.get(i));
                float second = Float.parseFloat(itemPricesSorted.get(i+1));
                if (second > first)
                    System.out.println("ERROR. Array is not sorted");
                    print("Actual " + first + " > " + "Expected " + second);
            }
        } finally {
            driver.quit();
        }
    }
    /**
     * Sorting test by item name
     * Steps:
     * 1. Navigate to https:https://www.tehnomanija.rs/ and accept cookies
     * 2. Enter product name in search text field
     * 3. Click on sorting dropdown menu and select sort by name A to Z
     *
     * Expected result
     * 3. Verify that items sorted by name
     */
    @Test
    public void SortItemsByName(){
        driver = openChromeDriver();
        try {
            print("Navigate to https:https://www.tehnomanija.rs/ and accept cookies");
            BasePage basePage = new BasePage(driver).clickSlazemSeButton();
            print("Enter product name in search text field");
            LaptopRacunariPage laptopRacunariPage = basePage.enterSearchBoxField(Strings.LAPTOP_RACUANRI);
            print("Sort items by name");
            List<String> itemNamesUnsorted = laptopRacunariPage.getAllItemNames();
            laptopRacunariPage.clickSortByPoNazivu();
            print("Verify that item sorted by name ");
            List<String> itemNamesSorted = laptopRacunariPage.getAllItemNames();
            laptopRacunariPage.verifySorting(itemNamesSorted);
            Assert.assertNotEquals(itemNamesUnsorted,itemNamesSorted);
        } finally {
           driver.quit();
        }
    }
}

