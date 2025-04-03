import ActionsPCK.BrowserAction;
import Pom.Login;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
    @BeforeMethod
    void setup() throws Exception {
        BrowserAction.setWebDriverToThreadLocalOfDrivers(BrowserAction.Browsers.chrome);
    }

    @AfterMethod
    void teardown() {
        BrowserAction.closeDriver();
    }

    @Test
    void loggingAsStandardUser() {
        Login home = new Login();
        home.navigateToHome();
            home.enterUsername(home.usernames[0]);
            home.enterPassword(home.password);
            home.clickLogin(home.productSelector);
    }
    @Test
    void loggingAsLocked_out_user() {
        Login home = new Login();
        home.navigateToHome();
        home.enterUsername(home.usernames[1]);
        home.enterPassword(home.password);
        home.clickLogin(home.productSelector);
    }

    @Test
    void loggingAsProblem_user() {
        Login home = new Login();
        home.navigateToHome();
        home.enterUsername(home.usernames[2]);
        home.enterPassword(home.password);
        home.clickLogin(home.productSelector);
    }
    @Test
    void loggingAsPerformance_glitch_user() {
        Login home = new Login();
        home.navigateToHome();
        home.enterUsername(home.usernames[3]);
        home.enterPassword(home.password);
        home.clickLogin(home.productSelector);
    }
    @Test
    void loggingWithInvalidUser() {
        Login home = new Login();
        home.navigateToHome();
        home.enterUsername(home.invalidUsername);
        home.enterPassword(home.password);
        home.clickLogin(home.erroSelector);
    }
}
