package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.BaseTest;

public class CartPage extends  BasePage {
    WebDriver driver;
    WebDriverWait wait;
    public static By orderSummaryTitle = By.xpath("//*[@class='m-orderSummary__title']");
    public static By productSalePrice = By.xpath("//*[@class='m-productPrice__salePrice']");
    public static By removeCartItemBtn = By.xpath("//*[@id='removeCartItemBtn0-key-0']");
    public static By removeCartTitle = By.xpath("//*[@id='notifyTitle']");
    public static By emptyMessageBtn = By.xpath("//*[@class='m-empty__messageBtn']");


    public CartPage() {
        this.driver = BaseTest.driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public void increaseQuantity(String piece) throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("//*[@id='quantitySelect0-key-0']"));
        //option[@value='2']


        int index = 1;
        Select select = new Select(element);
        select.selectByIndex(index);
        String actualOption = select.getFirstSelectedOption().getText();
        System.out.println(actualOption);
        Assert.assertEquals(piece, actualOption);

    }


    public void deleteFromBasket() {

            click(removeCartItemBtn);
            checkElementVisible(removeCartTitle);
            checkElementVisible(emptyMessageBtn);
            String actualTitle = getAttribute(emptyMessageBtn, "title");
            String expectedTitle = "Alışverişe Devam Et";
            Assert.assertEquals(expectedTitle, actualTitle);
    }

}