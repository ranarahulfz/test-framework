package selenium.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import selenium.browser.Browser;
import selenium.context.Base;
import selenium.driver.Driver;

public class LoginTests extends Base {

    @Test
    public void loginTest(){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(Browser.getCurrentUrl(), "https://www.google.com/");
        softAssert.assertEquals(Browser.getTitle(), "Google");
        softAssert.assertAll();
    }

}
