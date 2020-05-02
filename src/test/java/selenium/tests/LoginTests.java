package selenium.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import selenium.context.Base;
import selenium.driver.Driver;


public class LoginTests extends Base {
    WebDriver driver = null;
    @Test
    public void loginTest(){
        driver = Driver.getDriver();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.getCurrentUrl(), "");
        softAssert.assertEquals(driver.getTitle(), "");
        softAssert.assertAll();
    }

}
