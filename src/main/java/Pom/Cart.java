package Pom;

import ActionsPCK.UiActions;

public class Cart {
    UiActions uiActions;
    public String cartIcon = "//span[contains(@class,'fa-layers-counter')]";
    public String cartItem = "//div[@class='inventory_item_price' and text()='%s']/following-sibling::button";
    public String productSelector = "//div[@class='product_label']";

    public Cart() {
        uiActions = new UiActions();
    }

    public void addingItemToCart(String itemPrice) {
        String itemXPath = String.format(cartItem, itemPrice);
        uiActions.clickOn(itemXPath, UiActions.Locators.XPath, true, cartIcon, UiActions.Locators.XPath);
    }

    public void removeItemFromCart(String itemPrice) {
        String itemXPath = String.format(cartItem, itemPrice);
        uiActions.clickOn(itemXPath, UiActions.Locators.XPath, true, cartIcon, UiActions.Locators.XPath);
    }

    public String verifyProductInCart() {
        try {
            return uiActions.getElementText(cartIcon, UiActions.Locators.XPath);
        } catch (Exception e) {
            return "0";
        }
    }
}
