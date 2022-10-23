package base;

import config.Config;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Wait<WebDriver> flWait;
    public static Properties prop = Config.loadProperties();

    @BeforeTest
    public void initDriver(@Optional("chrome") String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

//        driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        flWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(NoSuchElementException.class);
        driver.get(Base.getURL());
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @AfterTest
    public void tearDown() {
        driver.close();

        if (!(driver instanceof FirefoxDriver)) {
            driver.quit();
        }
    }

    public static String getURL() {
        String url = "";

        if (prop != null) {
            url = prop.getProperty("url");
        }

        return url;
    }

    public void clickElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void sendKeysToElement(WebElement element, String keys) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(keys);
    }

    public String getTextFromElement(WebElement element) {
        String text = "";

        wait.until(ExpectedConditions.visibilityOf(element));
        text = element.getText();

        if (text.equals("")) {
            text = element.getAttribute("innerHTML");
        }

        return text;
    }
}
