import ActionsPCK.BrowserAction;
import Pom.Cart;
import Pom.Login;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest {


    @BeforeMethod
    void setup() throws Exception {
        BrowserAction.setWebDriverToThreadLocalOfDrivers(BrowserAction.Browsers.firefox);

    }

    /*  @AfterMethod
      void teardown() {
          BrowserAction.closeDriver();
      }
  */
    @Test
    void addToCartAsStandardUser() {
        Login home = new Login();
        home.navigateToHome();
        home.validLogin(home.usernames[0], home.password);
        Cart cart = new Cart();
        cart.addingItemToCart(String.format(cart.cartItem, "29.99"));
        cart.addingItemToCart(String.format(cart.cartItem, "49.99"));
        cart.addingItemToCart(String.format(cart.cartItem, "15.99"));
        Assert.assertEquals(cart.verifyProductInCart(), "3");
    }
}
