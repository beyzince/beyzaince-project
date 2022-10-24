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
        logger.info("press enter");

        homePage.pressEnter();
        logger.info("random product  ");

        productListPage.getRandomProduct();
        logger.info("write product  ");


        productDetailPage.writeProductInformation();
        logger.info("add basket  ");


        productDetailPage.addToBasket();
        logger.info("check price basket   ");


        productDetailPage.checkPriceInBasket();
        logger.info("increase quantity");
        cartPage.increaseQuantity("2 adet");
        logger.info("delete basket  ");
        cartPage.deleteFromBasket();











    }


}
