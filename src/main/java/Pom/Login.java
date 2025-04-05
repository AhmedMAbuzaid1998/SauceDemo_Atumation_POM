package Pom;
import ActionsPCK.UiActions;


public class Login {
    String homepageURL = "https://www.saucedemo.com/v1/";
    UiActions uiActions;
    String LoginBtn="login-button";
    String usernameSelector="user-name";
    String passwordSelector="password";
    public String productSelector="//div[@class='product_label']";
    public String erroSelector="//h3";
    public String[] usernames = {"standard_user", "locked_out_user", "problem_user", "performance_glitch_user"};
    public String password = "secret_sauce";
    public String invalidUsername="user";
    public Login() {
        uiActions=new UiActions();
    }
    public void navigateToHome(){
        uiActions.navigateToPage(homepageURL, LoginBtn,UiActions.Locators.id);
    }

    public void enterUsername(String username){
        uiActions.sendText(usernameSelector,UiActions.Locators.id,username);
    }

    public void enterPassword(String password){
        uiActions.sendText(passwordSelector,UiActions.Locators.id,password);
    }

    public void clickLogin(String expectedSelector){
        try {
            uiActions.clickOn(LoginBtn, UiActions.Locators.id,true,expectedSelector, UiActions.Locators.XPath);
        }
        catch (Exception e) {
            System.out.println("Error occurred while clicking on login button: " + e.getMessage());
            System.out.println(uiActions.getElementText(erroSelector, UiActions.Locators.XPath));

        }
    }
    public void loginAndVerify(String username, String password, String expectedSelector) {
        navigateToHome();
        enterUsername(username);
        enterPassword(password);
        clickLogin(expectedSelector);
    }

   public String returnText(String select, Locators locat){
        if(locat==Locators.XPath)
        {
        return uiActions.getElementText(select, UiActions.Locators.XPath);
   }
    else if(locat==Locators.id)
        {
        return uiActions.getElementText(select, UiActions.Locators.id);
        }
    else{
            return uiActions.getElementText(select, UiActions.Locators.CSS);
        }
    }
    public enum Locators {
        XPath,
        CSS,
        id
    }
}
