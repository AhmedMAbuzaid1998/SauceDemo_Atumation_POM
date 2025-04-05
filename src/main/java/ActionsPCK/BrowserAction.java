package ActionsPCK;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserAction {
    private static ThreadLocal<WebDriver> drivers = new ThreadLocal<>();

    // Initialize WebDriver based on browser choice
    public static void setWebDriverToThreadLocalOfDrivers(Browsers browser) {
        if (drivers.get() != null) {
            closeDriver(); // Ensure previous instance is closed before setting a new one
        }

        switch (browser) {
            case chrome:
                drivers.set(new ChromeDriver());
                break;
            case firefox:
                drivers.set(new FirefoxDriver());
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    // Getter for WebDriver
    public static WebDriver getDriver() {
        if (drivers.get() == null) {
            throw new IllegalStateException("WebDriver is not initialized. Call setWebDriverToThreadLocalOfDrivers() first.");
        }
        return drivers.get();
    }

    // Close WebDriver instance properly
    public static void closeDriver() {
        if (drivers.get() != null) {
            drivers.get().quit();
            drivers.remove(); // Prevent memory leaks
        }
    }

    // Enum for supported browsers
    public enum Browsers {
        chrome,
        firefox
    }
}
