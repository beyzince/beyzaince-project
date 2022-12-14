package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.BaseTest;

import java.util.Random;

import static pages.ProductDetailPage.productDescription;

public class ProductListPage extends  BasePage {
    WebDriver driver;
    WebDriverWait wait;
    public static By productList = By.xpath("//*[contains(@class, 'o-productList__itemWrapper')]");


    public ProductListPage() {
        this.driver = BaseTest.driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public void getRandomProduct()  {

            Random random = new Random();
            int productSize = findElements(productList).size();
            int count = random.nextInt(productSize);
            WebElement product = findElements(productList).get(count);
            scrollToElement(product);
            product.click();
            checkElementVisible(productDescription);

        }

    }

