package ActionsPCK;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static org.testng.Assert.assertNotNull;


public class UiActions {

    public WebDriver driver;

    public UiActions() {
        this.driver = BrowserAction.getDriver();
    }

    public void clickOn(String selector, Locators l, boolean assertion, String expectedElementSelector, Locators l2) {
        By b = returnElementLocatorBy(selector, l);
        waitUntil(b, ExpectedConditionsEnum.presenceOfElement);
        WebElement element = driver.findElement(b);

        if (assertion) {
            waitUntil(b, ExpectedConditionsEnum.ElementToBeClickable);
        }

        boolean clicked = tryClick(element) || tryDoubleClick(element) || tryHoverClick(element) || tryEnterKey(element) || tryJSClick(element);

        if (!clicked) {
            Assert.fail("Couldn't click the element using any method.");
        }

        waitForPageLoad();
        assertExpectedElementVisible(expectedElementSelector, l2);
    }

    private boolean tryClick(WebElement element) {
        try {
            element.click();
            return true;
        } catch (Exception ignored) { return false; }
    }

    private boolean tryDoubleClick(WebElement element) {
        try {
            new Actions(driver).doubleClick(element).perform();
            return true;
        } catch (Exception ignored) { return false; }
    }

    private boolean tryHoverClick(WebElement element) {
        try {
            new Actions(driver).moveToElement(element).click().perform();
            return true;
        } catch (Exception ignored) { return false; }
    }

    private boolean tryEnterKey(WebElement element) {
        try {
            element.sendKeys(Keys.ENTER);
            return true;
        } catch (Exception ignored) { return false; }
    }

    private boolean tryJSClick(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            return true;
        } catch (Exception ignored) { return false; }
    }

    private void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );
    }

    private void assertExpectedElementVisible(String selector, Locators l) {
        By expectedElement = returnElementLocatorBy(selector, l);
        assertNotNull(waitUntil(expectedElement, ExpectedConditionsEnum.ElementToBeClickable),
                "Expected element not found after clicking.");
    }


    public void sendText(String selector, Locators l, String text) {
        By b = returnElementLocatorBy(selector, l);
        waitUntil(b, ExpectedConditionsEnum.presenceOfElement);
        WebElement element = driver.findElement(b);

        try {
            element.clear();
            element.sendKeys(text);
        } catch (Exception e1) {
            try {
                // Try sending text using Actions
                Actions actions = new Actions(driver);
                actions.moveToElement(element).click().sendKeys(text).perform();
            } catch (Exception e2) {
                try {
                    // Try setting value using JavaScript
                    JavascriptExecutor executor = (JavascriptExecutor) driver;
                    executor.executeScript("arguments[0].value=arguments[1];", element, text);
                } catch (Exception e3) {
                    Assert.fail("Couldn't send text due to: " + e3.getMessage());
                }
            }
        }
    }


    public WebElement waitUntil(By b, ExpectedConditionsEnum condition) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            switch (condition) {
                case presenceOfElement:
                    return wait.until(ExpectedConditions.presenceOfElementLocated(b));
                case ElementToBeClickable:
                    return wait.until(ExpectedConditions.elementToBeClickable(b));
                default:
                    throw new IllegalArgumentException("Invalid condition: " + condition);
            }
        } catch (TimeoutException e) {
            Assert.fail("Timeout while waiting for element: " + b.toString());
            return null;
        }
    }

    public void navigateToPage(String url, String selector, Locators l) {
        driver.get(url);
        By b = returnElementLocatorBy(selector, l);

        WebElement element = waitUntil(b, ExpectedConditionsEnum.presenceOfElement);
        assertNotNull(element, "Navigation Failed to this Website " + url);
    }


    public By returnElementLocatorBy(String selector, Locators l) {
        switch (l) {
            case XPath:
                return new By.ByXPath(selector);

            case id:
                return new By.ById(selector);

            case CSS:
                return new By.ByCssSelector(selector);
            default:
                return null;
        }
    }
    public String getElementText(String selector,Locators locator){
        By b = returnElementLocatorBy(selector, locator);
        waitUntil(b, ExpectedConditionsEnum.presenceOfElement);
        WebElement element = driver.findElement(b);
        return element.getText();
    }
    public void acceptAlert(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public enum Locators {
        XPath,
        CSS,
        id
    }

    enum ExpectedConditionsEnum {
        presenceOfElement,
        ElementToBeClickable
    }


}
