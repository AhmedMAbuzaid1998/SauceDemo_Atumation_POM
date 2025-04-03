package Pom;
import ActionsPCK.UiActions;

public class Checkout {
    UiActions uiActions;
    //public String cartIcon="//span[@class='fa-layers-counter shopping_cart_badge']";
    public String cart="shopping_cart_container";
    public String yourCart="//div[@class='subheader' and text()='Your Cart']";
    public String checkBtn="//a[@class='btn_action checkout_button']";
    public String firstName="first-name";
    public String lastName="last-name";
    public String postal="postal-code";
    public String confirmOrder="//input[@value='CONTINUE']";
    public String finish="//a[@class='btn_action cart_button']";
    public String compeleteMsg="//h2[@class='complete-header']";
    public String first="John";
    public String last="Doe";
    public String postalCode="12345";



    public Checkout() {
        uiActions=new UiActions();
    }
    public void goToTheCart() {
        uiActions.clickOn(cart, UiActions.Locators.id,true,yourCart, UiActions.Locators.XPath);
    }
    public void checkoutInformation() {
        uiActions.clickOn(checkBtn, UiActions.Locators.XPath,true,firstName, UiActions.Locators.id);
        uiActions.sendText(firstName, UiActions.Locators.id,first);
        uiActions.sendText(lastName, UiActions.Locators.id,last);
        uiActions.sendText(postal, UiActions.Locators.id,postalCode);
        uiActions.clickOn(confirmOrder, UiActions.Locators.XPath,true,finish, UiActions.Locators.XPath);

    }
    public String compeletTheOrder() {
        uiActions.clickOn(finish, UiActions.Locators.XPath,true,compeleteMsg, UiActions.Locators.XPath);
        return uiActions.getElementText(compeleteMsg, UiActions.Locators.XPath);
    }

}


