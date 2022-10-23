package pages;

import driverManager.Driver;
import driverManager.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import test.BaseTest;
import util.ReadExcelFile;

import java.io.IOException;

public class HomePage extends  BasePage{
    WebDriver driver;
    WebDriverWait wait;

    public HomePage() {
        super();
        PageFactory.initElements(driver,this);


    }



    public  static By headerLogo= By.className("o-header__logo");
    public static By onetrustAcceptBtn = By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]");

   // public static By headerLogo = By.xpath("//*[@class='o-header__logo']");

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
//        String actualText = getAttribute(searchInput, "value");
//        Assert.assertEquals(expectedText, actualText);

    }
    public void clearSearch() throws InterruptedException {
        Thread.sleep(2000);
//        WebElement query = driver.findElement(By.id("searchInput"));
//        query.sendKeys("the moon");
//        query.clear();
        setText(searchInput, Keys.CONTROL + "A" + Keys.DELETE);
        findBy(searchInput).clear();


        Thread.sleep(2000);


        //String actualText = getAttribute(searchInput, "value");
        //String expectedText = "";
        //Assert.assertEquals(expectedText, actualText);

    }
    public void pressEnter()  {
        setText(searchInput, "" + Keys.ENTER);
    }
   }

