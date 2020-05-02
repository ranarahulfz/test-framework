package selenium.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import selenium.config.FilePath;
import selenium.util.OSValidator;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static final String HEADLESS = "headless";
    private static final String CHROMEDRIVER = "chromedriver";
    private static final String FIREFOXDRIVER = "geckodriver";
    private static String driverPath = null;
    private static final String driverLocForLinux = FilePath.driverPath + "linux" + File.separator;
    private static final String driverLocForWin = FilePath.driverPath + "windows" + File.separator;
    private static final String driverLocForOsx = FilePath.driverPath + "osx" + File.separator;

    public static WebDriver createInstance(String browserName, String appUrl, String methodName) {
        final String browserMode = System.getProperty("mode");
        WebDriver driver = null;
        if (browserName.toLowerCase().contains("firefox")) {
            if (OSValidator.isWindows()) {
                driverPath = driverLocForWin + FIREFOXDRIVER;
            } else if (OSValidator.isMac()) {
                driverPath = driverLocForOsx + FIREFOXDRIVER;
            } else if (OSValidator.isUnix()) {
                driverPath = driverLocForLinux + FIREFOXDRIVER;
            }
            System.setProperty("webdriver.gecko.driver", driverPath);
            if (browserMode != null && browserMode.equals(HEADLESS)) {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                driver = new FirefoxDriver(firefoxOptions);
            } else {
                driver = new FirefoxDriver();
            }
        }
        if (browserName.toLowerCase().contains("chrome")) {
            if (OSValidator.isWindows()) {
                driverPath = driverLocForWin + CHROMEDRIVER;
            } else if (OSValidator.isMac()) {
                driverPath = driverLocForOsx + CHROMEDRIVER;
            } else if (OSValidator.isUnix()) {
                driverPath = driverLocForLinux + CHROMEDRIVER;
            }
            System.setProperty("webdriver.chrome.driver", driverPath);
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            /*chromeOptions.addArguments("disable-extensions");
            chromeOptions.addArguments("disable-popup-blocking");
            chromeOptions.addArguments("version");*/
            //chromeOptions.addArguments("disable-infobars");
            if (browserMode != null && browserMode.equals(HEADLESS)) {
                chromeOptions.addArguments("--headless");
                driver = new ChromeDriver(chromeOptions);
            } else {
                driver = new ChromeDriver(chromeOptions);
            }
            driver.manage().window().maximize();
            driver.get(appUrl);
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        }
        if (browserName.toLowerCase().contains("zalenium")) {
            DesiredCapabilities cap = DesiredCapabilities.chrome();
            cap.setCapability("name", methodName);
            try {
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        if (driver != null) {
            driver.get(appUrl);
        } else {
            System.out.println("********************************************************************");
            System.out.println("* >>>>>>>>> DRIVER IS NOT INITIALIZED, EXECUTION HALTED <<<<<<<<<<< ");
            System.out.println("********************************************************************");
        }
        return driver;
    }
}
