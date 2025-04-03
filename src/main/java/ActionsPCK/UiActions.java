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
        this.driver = BrowserAction.drivers.get();
    }

    public void clickOn(String selector, Locators l, boolean assertion, String expectedElementSelector, Locators l2) {
        By b = returnElementLocatorBy(selector, l);
        waitUntil(b, ExpectedConditionsEnum.presenceOfElement);
        WebElement element = driver.findElement(b);

        try {
            if (assertion) {
                waitUntil(b, ExpectedConditionsEnum.ElementToBeClickable);
            }
            // Try single click
            element.click();
        } catch (Exception e1) {
            try {
                // Try double click
                Actions actions = new Actions(driver);
                actions.doubleClick(element).perform();
            } catch (Exception e2) {
                try {
                    // Try hover then click
                    Actions actions = new Actions(driver);
                    actions.moveToElement(element).click().perform();
                } catch (Exception e3) {
                    try {
                        // Try clicking with Enter key
                        element.sendKeys(Keys.ENTER);
                    } catch (Exception e4) {
                        try {
                            // Try JavaScript click as the last resort
                            JavascriptExecutor executor = (JavascriptExecutor) driver;
                            executor.executeScript("arguments[0].click();", element);
                        } catch (Exception e5) {
                            Assert.fail("Couldn't click the element due to: " + e5.getMessage());
                        }
                    }
                }
            }
        }
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );

        By expectedElement = returnElementLocatorBy(expectedElementSelector, l2);
        try {
            assertNotNull(waitUntil(expectedElement, ExpectedConditionsEnum.ElementToBeClickable));
        }
        catch (Exception e) {
            Assert.fail("Expected element not found after clicking on the element: " + e.getMessage());
        }

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
            WebElement element = null;
            switch (condition) {
                case presenceOfElement:

                    element = (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.presenceOfElementLocated(b));
                    return element;

                case ElementToBeClickable:
                    element = (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.elementToBeClickable(b));
                    return element;


                default:
                    element = null;
                    Assert.fail("Wrong condition");
            }
            return element;
        } catch (Exception e) {
            //Assert.fail("Couldn't find the element because of " + e.getMessage());
            return null;
        }
    }

    public WebElement waitUntil(By element, ExpectedCondition<WebElement> s) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(10)).until(s);

        } catch (Exception e) {
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
