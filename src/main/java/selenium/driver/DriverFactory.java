package selenium.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static final String HEADLESS = "headless";

    public static WebDriver createInstance(String browserName, String appUrl, String methodName) {
        final String browserMode = System.getProperty("mode");
        String sDownloadDefaultPath = "";
        WebDriver driver = null;
        if (browserName.toLowerCase().contains("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
            chromeOptions.setExperimentalOption("useAutomationExtension", false);
            chromeOptions.addArguments("test-type");
            chromeOptions.addArguments("--disable-web-security");
            chromeOptions.addArguments("--always-authorize-plugins");
            chromeOptions.addArguments("disable-infobars");
            //chromeOptions.addArguments("--disable-geolocation");
            chromeOptions.addArguments("--enable-strict-powerful-feature-restrictions");
            HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("credentials_enable_service", false);
            chromePrefs.put("profile.password_manager_enabled", false);
            chromePrefs.put("download.default_directory", sDownloadDefaultPath);
            chromeOptions.setExperimentalOption("prefs", chromePrefs);
            WebDriverManager.chromedriver().setup();
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
