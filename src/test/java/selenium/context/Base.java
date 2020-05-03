package selenium.context;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.ITestResult;
import org.testng.annotations.*;
import selenium.browser.Browser;
import selenium.config.FilePath;
import selenium.driver.Driver;
import selenium.driver.DriverFactory;
import selenium.listeners.WebDriverListener;
import selenium.util.PropertiesUtil;

import java.io.IOException;
import java.util.Properties;

@Listeners(WebDriverListener.class)
public class Base {
    protected String appUrl;
    protected String browser;
    String profile;

    public String getProfile() {
        return profile;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public String getBrowser() {
        return browser;
    }

    @BeforeSuite
    public void beforeSuite() {
        String sAppPropFP = FilePath.appPropPath;
        PropertiesUtil propertiesUtil = new PropertiesUtil(sAppPropFP);
        browser = propertiesUtil.getPropValue("browser.mode");
        appUrl = propertiesUtil.getPropValue("app.url");
    }

    @BeforeClass
    public void beforeClass() {

    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("In before method.");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("In after method.");
    }

    @AfterClass
    public void afterClass() {

    }

    @AfterSuite
    public void afterSuite() {

    }
}
