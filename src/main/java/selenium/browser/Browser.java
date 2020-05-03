package selenium.browser;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Browser {
    private static Logger logger = Logger.getLogger(Browser.class);
    static int defaultTimeOutInSeconds = 120;
    static WebDriver driver = null;

    public Browser(WebDriver driver) {
        Browser.driver = driver;
    }

    public static void get(String sUrl) {
        logger.info("Opening the url : " + sUrl);
        clearCookies();
        driver.get(sUrl);
        logger.info("Url : " + sUrl + " gets opened successfully.");
    }

    public static void navigate(String sUrl) {
        logger.info("Navigating to the url : " + sUrl);
        clearCookies();
        driver.navigate().to(sUrl);
        logger.info("Navigated to the url : " + sUrl + " successfully.");
    }

    public static String getTitle() {
        logger.info("Fetching the title.");
        String sTitle = driver.getTitle().trim();
        logger.info("Title fetched : " + sTitle);
        return sTitle;
    }

    public static String getCurrentUrl() {
        logger.info("Fetching the current url.");
        String sCurrentUrl = driver.getCurrentUrl().trim();
        logger.info("Fetched url is : " + sCurrentUrl);
        return sCurrentUrl;
    }

    public static void clearCookies() {
        logger.info("Going to clear cookies.");
        driver.manage().deleteAllCookies();
        logger.info("Cookies are cleared successfully.");
    }

    public static void click(By locator) {
        logger.info("Waiting for element to be clickable.");
        WebElement element = waitForElementClickable(locator);
        logger.info("Going to click element having locator : " + locator);
        element.click();
        logger.info("Element clicked having locator : " + locator);
    }

    public static void sendKeys(By locator, String sInputValue) {
        logger.info("Waiting for element to be visible.");
        WebElement element = waitForElementVisibility(locator);
        element.clear();
        logger.info("Going to enter char sequence : " + sInputValue + " to the element having locator : " + locator);
        element.sendKeys(sInputValue);
        logger.info("Char sequence : " + sInputValue + " populated to the element having locator : " + locator);
    }

    public static WebElement waitForElementVisibility(By locator) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, defaultTimeOutInSeconds);
        logger.info("Waiting for element visibility, having locator : " + locator + " for : "
                + defaultTimeOutInSeconds);
        return webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForElementPresence(By locator) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, defaultTimeOutInSeconds);
        logger.info("Waiting for element presence, having locator : " + locator + " for : "
                + defaultTimeOutInSeconds + " seconds.");
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static WebElement waitForElementClickable(By locator) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, defaultTimeOutInSeconds);
        logger.info("Waiting for element to be clickable, having locator : " + locator + " for : "
                + defaultTimeOutInSeconds + " seconds.");
        return webDriverWait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static List<WebElement> waitForAllElementsPresence(By locator) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, defaultTimeOutInSeconds);
        logger.info("Waiting for all elements presence, having locator : " + locator + " for : "
                + defaultTimeOutInSeconds + " seconds.");
        return webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public static void waitForElementInvisibility(By locator) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, defaultTimeOutInSeconds);
        logger.info("Waiting for element to be invisible, having locator : " + locator + " for : "
                + defaultTimeOutInSeconds + " seconds.");
        WebElement element = waitForElementVisibility(locator);
        webDriverWait.until(ExpectedConditions.invisibilityOf(element));
    }


}
