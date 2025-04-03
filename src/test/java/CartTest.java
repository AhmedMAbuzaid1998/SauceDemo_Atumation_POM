import ActionsPCK.BrowserAction;
import Pom.Cart;
import Pom.Login;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest {


    @BeforeMethod
    void setup() throws Exception {
        BrowserAction.setWebDriverToThreadLocalOfDrivers(BrowserAction.Browsers.chrome);

    }

    @Test
    void addToCartAsStandardUser() {
        Login home = new Login();
        home.navigateToHome();
        home.validLogin(home.usernames[0], home.password);
        Cart cart=new Cart();
        cart.addingItemToCart();
       Assert.assertEquals(cart.verifyProductInCart(),"1");
    }
}
