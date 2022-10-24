package driverManager;

import dataProvider.PropertyManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public class Driver {
    private Driver() {}
    private static WebDriver driver;

    public static WebDriver get() {
        if (driver == null) {
            String browser = PropertyManager.get("browser");
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;

                case "opera":

                    WebDriverManager.getInstance(OperaDriver.class).setup();
                    driver = new OperaDriver();
                    break;
            }
            String url = PropertyManager.get("url");
            driver.get(url);
            driver.manage().window().maximize();

        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
