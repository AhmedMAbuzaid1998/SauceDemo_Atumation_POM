import ActionsPCK.BrowserAction;
import Pom.Login;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
    @BeforeMethod
    void setup() throws Exception {
        BrowserAction.setWebDriverToThreadLocalOfDrivers(BrowserAction.Browsers.firefox);
    }

    @AfterMethod
    void teardown() {
        BrowserAction.closeDriver();
    }

    @Test
    void loggingAsStandardUser() {
        Login home = new Login();
        home.loginAndVerify(home.usernames[0],home.password,home.productSelector);
        Assert.assertTrue(home.returnText(home.productSelector,Login.Locators.XPath).contains("Product"),"Login failed or incorrect page loaded.");
    }
    @Test
    void loggingAsLocked_out_user() {
        Login home = new Login();
        home.loginAndVerify(home.usernames[1],home.password,home.erroSelector);
        Assert.assertTrue(home.returnText(home.erroSelector,Login.Locators.XPath).contains("Epic sadface: "), "Error message is incorrect.");
    }

    @Test
    void loggingAsProblem_user() {
        Login home = new Login();
        home.loginAndVerify(home.usernames[2],home.password,home.productSelector);
        Assert.assertTrue(home.returnText(home.productSelector,Login.Locators.XPath).contains("Product"),"Login failed or incorrect page loaded.");

    }
    @Test
    void loggingAsPerformance_glitch_user() {
        Login home = new Login();
        home.loginAndVerify(home.usernames[3],home.password,home.productSelector);
        Assert.assertTrue(home.returnText(home.productSelector,Login.Locators.XPath).contains("Product"),"Login failed or incorrect page loaded.");

    }
    @Test
    void loggingWithInvalidUser() {
        Login home = new Login();
        home.loginAndVerify(home.invalidUsername,home.password,home.erroSelector);
        Assert.assertTrue(home.returnText(home.erroSelector,Login.Locators.XPath).contains("Epic sadface"), "Error message is incorrect.");
    }
}
