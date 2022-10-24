package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.BaseTest;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BasePage  {
    public WebDriver driver;
    public WebDriverWait wait;
    public BasePage() {
        this.driver = BaseTest.driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public List<WebElement> presenceElements(By locator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public  void waitForLocator(By locator){
        wait.until(ExpectedConditions.elementToBeClickable(locator));

    }


    public WebElement findBy(By locator) {
        return driver.findElement(locator);
    }
//
    public List<WebElement> findElements(By locator) {
        List<WebElement> elements = presenceElements(locator);
        return elements;
    }

//    public List<WebElement> findAll(By locator){
//        return driver.findElements(locator);
//    }
    //getPage
//    public void getPage(String pagePath){
//        if (pagePath!=null){
//            driver.get(.concat(pagePath));
//        }else{
//            driver.get();
//        }
//    }
//    //alertAccept
//    public void alertAccept(){
//
//        driver.switchTo().alert().accept();
//    }


//    public void clearCookies()
//    {
//        driver.manage().deleteAllCookies();
//    }

    public void click(By locator){
        waitForLocator(locator);
        findBy(locator).click();
    }
    public void setText(By locator,String text){
        waitForLocator(locator);
        findBy(locator).clear();
        findBy(locator).sendKeys(text);
        findBy(locator).clear();

    }
    public String getText(By locator){
        waitForLocator(locator);
        return findBy(locator).getText();

    }
    public  boolean isDisplayed(By locator){
        waitForLocator(locator);
        return findBy(locator).isDisplayed();

    }
    public void checkElementVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOf(findBy(locator)));
    }

    public String getAttribute(By locator, String attr) {
        return findBy(locator).getAttribute(attr);
    }
//
    public void checkElementClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(findBy(locator)));
    }

    public void scrollToElement(WebElement element) {
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        ((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle, element);
    }

    public void writeToTxt(By descriptionKey, By colorKey, By priceKey) {
        try {
            String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
            FileWriter fileWriter = new FileWriter("src/test/resources" + dateName + ".txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("Ürün Açıklaması : " + getText(descriptionKey));
            printWriter.println("Ürün Rengi : " + getText(colorKey));
            printWriter.println("Ürün Fiyatı : " + getText(priceKey));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
