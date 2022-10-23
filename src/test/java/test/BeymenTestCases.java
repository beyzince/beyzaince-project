package test;

import org.junit.Assert;
import org.junit.Test;
import pages.*;

import java.io.IOException;
import java.sql.Driver;

public class BeymenTestCases extends  BaseTest {

    @Test
    public void beymenTest() throws IOException, InterruptedException {
        //setUp();
        HomePage homePage=new HomePage();
        ProductListPage productListPage = new ProductListPage();
        ProductDetailPage productDetailPage = new ProductDetailPage();
        CartPage cartPage = new CartPage();

        logger.info("open page ");
        homePage.openPage();
        logger.info("check page ");
        homePage.checkPage();
        logger.info("search page ");
        homePage.searchInInput(0,"şort");
        homePage.clearSearch();
        homePage.searchInInput(1,"gömlek");
        homePage.pressEnter();
        productListPage.getRandomProduct();

        productDetailPage.writeProductInformation();

        productDetailPage.addToBasket();

        productDetailPage.checkPriceInBasket();

        cartPage.increaseQuantity("2");

        cartPage.deleteFromBasket();











    }


}
