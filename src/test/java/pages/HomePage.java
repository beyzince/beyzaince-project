package pages;

import driverManager.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import util.ReadExcelFile;

import java.io.IOException;

public class HomePage extends  BasePage{
    WebDriver driver;

    public HomePage() {
        super();
        PageFactory.initElements(driver,this);
    }


    public  static By headerLogo= By.className("o-header__logo");
    public static By onetrustAcceptBtn = By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]");
    public static By searchInput = By.xpath("//*[contains(@class,'default-input o-header__search--input')]");

    public void openPage() {
        Driver.get();
        checkElementVisible(onetrustAcceptBtn);
        checkElementClickable(onetrustAcceptBtn);
        click(onetrustAcceptBtn);

    }
    public void checkPage() {
        checkElementVisible(headerLogo);
        String actualTitle =getAttribute(headerLogo,"title");
        String expectedTitle = "Beymen";
        Assert.assertEquals(expectedTitle, actualTitle);

    }

    public void searchInInput(int column, String expectedText) throws IOException {
        setText(searchInput, ReadExcelFile.readFromExcel(column));


    }
    public void clearSearch() throws InterruptedException {
        setText(searchInput, Keys.CONTROL + "A" + Keys.DELETE);
        Thread.sleep(2000);
        findBy(searchInput).clear();
        Thread.sleep(4000);


    }
    public void pressEnter()  {

        setText(searchInput, "" + Keys.ENTER);
    }
   }

