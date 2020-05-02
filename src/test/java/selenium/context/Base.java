package selenium.context;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.ITestResult;
import org.testng.annotations.*;
import selenium.driver.Driver;
import selenium.driver.DriverFactory;
import selenium.listeners.WebDriverListener;

import java.io.IOException;

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
        System.out.println("In before suite");
        browser = "chrome";
        appUrl = "https://q.smartusys.net/SCM_7_5_2-scp/";
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("In before class");
    }

    @AfterClass
    public void afterClass() {

    }

    @AfterSuite
    public void afterSuite() {

    }
}
