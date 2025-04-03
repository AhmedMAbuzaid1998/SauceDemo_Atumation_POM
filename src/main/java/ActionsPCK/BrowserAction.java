package ActionsPCK;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserAction{
    public static ThreadLocal<WebDriver> drivers = new ThreadLocal<>();

    public static void setWebDriverToThreadLocalOfDrivers(Browsers browser) {
            switch (browser){
                case chrome:
                    drivers.set(new ChromeDriver());
                    break;

                case firefox:
                    drivers.set(new FirefoxDriver());
                    break;
        }
    }



    public static void closeDriver(){
        drivers.get().quit();
    }

    public enum Browsers{
        chrome,
        firefox
    }


}