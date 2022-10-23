package driverManager;

import groovyjarjarcommonscli.Options;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.opera.OperaOptions;

public class DriverOptions {

    public ChromeOptions getChromeOptions(){
        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("ignore-certificate-errors");
//        chromeOptions.addArguments("start-maximized");
//        chromeOptions.addArguments("ignore-certificate-errors");
////        chromeOptions.addArguments("--headless");
//        chromeOptions.addArguments("--disable-dev-shm-usage");
//        chromeOptions.addArguments("--no-sandbox");
//        chromeOptions.addArguments("--disable-popup-blocking");
        return chromeOptions;
    }
    public static FirefoxOptions getFirefoxOptions(){
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setAcceptUntrustedCertificates(true);
        firefoxProfile.setAssumeUntrustedCertificateIssuer(false);
        //Use No Proxy Settings
        firefoxProfile.setPreference("network.proxy.type", 0);
        //Set Firefox profile to capabilities
        firefoxOptions.setCapability(FirefoxDriver.PROFILE, firefoxOptions);
        return firefoxOptions;
    }
//    public static Options getOperaOptions(){
//        OperaOptions options = new OperaOptions();
//        return options;
//    }

    }

