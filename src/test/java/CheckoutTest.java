import ActionsPCK.BrowserAction;
import Pom.Cart;
import Pom.Checkout;
import Pom.Login;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutTest {

    @BeforeMethod
    void setup() throws Exception {
        BrowserAction.setWebDriverToThreadLocalOfDrivers(BrowserAction.Browsers.firefox);

    }
    @AfterMethod
    void teardown() {
        BrowserAction.closeDriver();
    }

    @Test
    void addToCartAsStandardUser() {
        Login home = new Login();
        home.navigateToHome();
        home.loginAndVerify(home.usernames[0], home.password, home.productSelector);
        Cart cart = new Cart();
        cart.addingItemToCart("29.99");
        Checkout checkout=new Checkout();
        checkout.goToTheCart();
        checkout.checkoutInformation();
        Assert.assertEquals(checkout.compeletTheOrder(),"THANK YOU FOR YOUR ORDER");

    }
}
