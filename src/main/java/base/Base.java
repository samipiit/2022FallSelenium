package base;

import config.Config;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Wait<WebDriver> flWait;
    public static Properties prop;

    public Base() {
        prop = Config.loadProperties();
    }

    @Parameters ({"browser"})
    @BeforeMethod (alwaysRun = true)
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
        wait = new WebDriverWait(driver, Long.parseLong(prop.getProperty("driver_timeout")));
        flWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(Long.parseLong(prop.getProperty("driver_timeout"))))
                .pollingEvery(Duration.ofMillis(Long.parseLong(prop.getProperty("driver_polling_interval"))))
                .ignoring(NoSuchElementException.class);
        driver.get(prop.getProperty("url"));
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @AfterMethod (alwaysRun = true)
    public void tearDown() {
        driver.close();

        if (!(driver instanceof FirefoxDriver)) {
            driver.quit();
        }
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

    public boolean isElementDisplayed(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));

        return element.isDisplayed();
    }

    public void selectByVisibleText(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);

        wait.until(ExpectedConditions.textToBePresentInElement(element, text));

    }

    public void selectByValue(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }
}
