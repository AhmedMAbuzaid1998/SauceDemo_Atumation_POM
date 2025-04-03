package Pom;

import ActionsPCK.UiActions;

public class Cart {
    UiActions uiActions;
    public String cartIcon="//span[@class='fa-layers-counter shopping_cart_badge']";
    public String cartItems="//div[@class='inventory_item_price' and text()='29.99']/following-sibling::button";
    public String productSelector="//div[@class='product_label']";

    public Cart() {
        uiActions=new UiActions();
    }
    public void addingItemToCart(){
        uiActions.clickOn(cartItems, UiActions.Locators.XPath,true,cartIcon, UiActions.Locators.XPath);

    }
    public String verifyProductInCart(){
       return uiActions.getElementText(cartIcon,UiActions.Locators.XPath);
    }
}
