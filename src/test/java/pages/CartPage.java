package pages;

import com.aventstack.extentreports.ExtentTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.BaseTest;

import java.io.IOException;
import java.util.List;

public class CartPage extends  BasePage {

    WebDriver driver;
    WebDriverWait wait;
    //ElementHelper elementHelper;
    ExtentTest logger;

    public static By orderSummaryTitle = By.xpath("//*[@class='m-orderSummary__title']");
    public static By productSalePrice = By.xpath("//*[@class='m-productPrice__salePrice']");
    public static By quantityDropdown = By.xpath("//*[@id='quantitySelect0-key-0']");
    public static By removeCartItemBtn = By.xpath("//*[@id='removeCartItemBtn0-key-0']");
    public static By removeCartNotifTitle = By.xpath("//*[@id='notifyTitle']");
    public static By emptyMessageBtn = By.xpath("//*[@class='m-empty__messageBtn']");


    public CartPage() {
        this.driver = BaseTest.driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public void increaseQuantity(String quantity)  {
        //try {
           // Select select = new Select(quantityDropdown);
            Select select= new Select(quantityDropdown);

            List<WebElement> options = select.getOptions();
            int optionSize = options.size();
            for (int i = 0; i < optionSize; i++) {
                if (options.get(i).getText().contains(quantity)) {
                    select.selectByValue(quantity);
                    break;
                }
            }
//            String actualQuantity = select.getFirstSelectedOption().getText();
//            if ((quantity + "piece").equals(actualQuantity)) {
//                Assert.assertEquals(quantity + "piece", actualQuantity);
//            } else {
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }

    public void deleteFromBasket() {

            click(removeCartItemBtn);
            checkElementVisible(removeCartNotifTitle);
            checkElementVisible(emptyMessageBtn);
            String actualTitle = getAttribute(emptyMessageBtn, "title");
            String expectedTitle = "Continue Shopping";
            Assert.assertEquals(expectedTitle, actualTitle);
    }

}