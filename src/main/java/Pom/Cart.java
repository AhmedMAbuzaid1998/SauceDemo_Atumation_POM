package Pom;

import ActionsPCK.UiActions;

public class Cart {
    UiActions uiActions;
    public String cartIcon="//span[contains(@class,'fa-layers-counter')]";
    public String cartItem="//div[@class='inventory_item_price' and text()='%s']/following-sibling::button";
    public String productSelector="//div[@class='product_label']";

    public Cart() {
        uiActions=new UiActions();
    }
    public void addingItemToCart(String item){
        uiActions.clickOn(item, UiActions.Locators.XPath,true,productSelector, UiActions.Locators.XPath);

    }
    public String verifyProductInCart(){
       return uiActions.getElementText(cartIcon,UiActions.Locators.XPath);
    }
}
