package selenium.listeners;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import selenium.browser.Browser;
import selenium.context.Base;
import selenium.driver.Driver;
import selenium.driver.DriverFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WebDriverListener implements IInvokedMethodListener {

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            String browserName = ((Base) method.getTestMethod().getInstance()).getBrowser();
            WebDriver driver = DriverFactory.createInstance(browserName, ((Base) method
                    .getTestMethod().getInstance()).getAppUrl(), method.getTestMethod().getMethodName());
            System.out.println("Initializing webdriver session --> Thread ID: " + Thread.currentThread()
                    .getId());
            System.out.println("Running test --> " + method.getTestMethod().getMethodName());
            Driver.setWebDriver(driver);
            Browser browser = new Browser(Driver.getDriver());
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            WebDriver driver = Driver.getDriver();
            if (driver != null) {
                try {
                    takeScreenshotOnFailure(testResult);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Closing webdriver session: " + Thread.currentThread().getId());
                driver.quit();
            }
        }
    }

    public void takeScreenshotOnFailure(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            File screenShot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.FILE);
            File destination = new File("target/failure-screenshots/" + testResult.getName() + "-"
                    + new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(new Date()) + ".png");
            FileUtils.copyFile(screenShot, destination);
            InputStream screenShotStream = new FileInputStream(destination);
            byte[] screen = IOUtils.toByteArray(screenShotStream);
            //saveScreenshot(screen);
        }
    }

    /*@Attachment(value = "Screenshot of the failure", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }*/
}
