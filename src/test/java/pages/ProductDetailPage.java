package pages;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.BaseTest;

import java.util.Random;

import static pages.CartPage.orderSummaryTitle;
import static pages.CartPage.productSalePrice;

public class ProductDetailPage  extends  BasePage{
    WebDriver driver;
    WebDriverWait wait;

    private String detailPrice;
    public static By productDescription = By.xpath("//*[@class='o-productDetail__description']");
    public static By productColor = By.xpath("//*[@class='m-colorsSlider__top']//label");
    public static By productPrice = By.xpath("//*[@id='priceNew']");
    public static By productSize = By.xpath("//*[contains(@class, 'm-variation__item')]");
    public static By addBasketBtn = By.xpath("//*[@id='addBasket']");

    public static By addedToCartTitle = By.xpath("//*[@class='m-notification__title']");

    public static By addedToCartBtn = By.xpath("//*[@class='m-notification__button btn']");


    public ProductDetailPage() {
        this.driver = BaseTest.driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public void writeProductInformation() {
        writeToTxt(productDescription, productColor, productPrice);
    }

    public void addToBasket()  {
        detailPrice = getText(productPrice);
        int bodySizeCount = findElements(productSize).size();

        while (true) {
                Random random = new Random();
                int count = random.nextInt(bodySizeCount);
                WebElement selectedBody = findElements(productSize).get(count);
                if (!selectedBody.getAttribute("class").contains("-disabled")) {
                    selectedBody.click();
                    break;
                }
            }
            click(addBasketBtn);
            checkElementVisible(addedToCartTitle);
            String actualText = getText(addedToCartTitle);
            String expectedText = "Sepete Eklendi";
            Assert.assertEquals(expectedText, actualText);
    }

    public void checkPriceInBasket() {

            click(addedToCartBtn);
            checkElementVisible(orderSummaryTitle);
            String salePrice = getText(productSalePrice);
            Assert.assertEquals(detailPrice, salePrice);
    }
}