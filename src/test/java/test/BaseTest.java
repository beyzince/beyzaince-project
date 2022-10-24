package test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import dataProvider.PropertyManager;
import driverManager.Driver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

//@ExtendWith(TestResultLogger.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class BaseTest {
    public static WebDriver driver;
    final static Logger logger= Logger.getLogger(BaseTest.class);
//    public static DriverOptions driverOptions;
;
    public ExtentReports report;
     public ExtentHtmlReporter htmlReporter;




     @Before
    public  void setUp(){
        logger.info("Tests are starting");
        //driverOptions=new DriverOptions();
        //properties=PropertyManager.get();

        driver= Driver.get();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        report=new ExtentReports();

        String projectPath=System.getProperty("user.dir");
        String path=projectPath+"/test-output/ExtentReport.html";
        htmlReporter = new ExtentHtmlReporter(path);

        report.attachReporter(htmlReporter);

        htmlReporter.config().setReportName(" Beyza ince Proje");

        report.setSystemInfo("Environment", "Test");
        report.setSystemInfo("User Name", "beyzaince");
        report.setSystemInfo("BrowserType", PropertyManager.get("browser"));
        report.setSystemInfo("OS", System.getProperty("os.name"));






    }
    public WebDriver getDriver() {
        return driver;
    }


    @After
    public void tearDown(){
        logger.info("test are ending!");
        if(driver!=null){
            driver.quit();
        }
    }




}
