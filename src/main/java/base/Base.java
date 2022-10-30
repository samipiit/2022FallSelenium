package base;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import config.Config;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.DriverEventListener;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import reporting.ExtentManager;
import reporting.ExtentTestManager;
import utils.ExcelReader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

public class Base {

    public static WebDriver webDriver;
    public static WebDriver driver;
    public static JavascriptExecutor jsDriver;
    public static ExtentReports extent;
    public static WebDriverWait wait;
    public static Wait<WebDriver> flWait;
    public static Properties prop;
    public static ExcelReader excel;
    public static String testDataFilePath;
    private static Robot robot;

    public Base() {
        prop = Config.loadProperties();
        excel = new ExcelReader();
        testDataFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator +
                "test" + File.separator + "resources" + File.separator + "test_data.xlsx";
    }

    @BeforeSuite(alwaysRun = true)
    public void reportSetup(ITestContext context) {
        ExtentManager.setOutputDirectory(context);
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod(alwaysRun = true)
    public void reportInit(Method method) {
        String className = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName();

        ExtentTestManager.startTest(methodName);
        ExtentTestManager.getTest().assignCategory(className);
    }

    @Parameters ({"browser"})
    @BeforeMethod (alwaysRun = true)
    public void initDriver(@Optional("chrome") String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            webDriver = new EdgeDriver();
        }

        EventFiringWebDriver eventDriver = new EventFiringWebDriver(webDriver);
        DriverEventListener driverEventListener = new DriverEventListener();
        driver = eventDriver.register(driverEventListener);

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

    @AfterMethod(alwaysRun = true)
    public void afterEachTestMethod(ITestResult testResult, @Optional("true") String driverConfigEnabled) {
        ExtentTest test = ExtentTestManager.getTest();
        String testName = testResult.getName();
        int testStatus = testResult.getStatus();

        test.getTest().setStartedTime(getTime(testResult.getStartMillis()));
        test.getTest().setEndedTime(getTime(testResult.getEndMillis()));

        for (String group : testResult.getMethod().getGroups()) {
            test.assignCategory(group);
        }

        if (testStatus == ITestResult.FAILURE) {
            if (driver != null) {
                captureScreenshot(driver, testName);
            }
            test.log(LogStatus.FAIL, "TEST FAILED: " + testName);
            test.log(LogStatus.FAIL, testResult.getThrowable());

        } else if (testStatus == ITestResult.SKIP) {
            test.log(LogStatus.SKIP, "TEST SKIPPED: " + testName);
        } else if (testStatus == ITestResult.SUCCESS) {
            test.log(LogStatus.PASS, "TEST PASSED: " + testName);
        }

        ExtentTestManager.endTest();
        extent.flush();
    }

    public void clickElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void sendKeysToElement(WebElement element, String keys) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(keys);
    }

    public void clearSendKeysToElement(WebElement element, String keys) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
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

    public void switchToIFrame(WebElement frame) {
        wait.until(ExpectedConditions.visibilityOf(frame));
        driver.switchTo().frame(frame);
    }

    public void switchToParentFrame() {
        driver.switchTo().defaultContent();
    }

    public void switchToHandle() {
        String parentTab = driver.getWindowHandle();

        Set<String> openTabs = driver.getWindowHandles();

        for (String tab : openTabs) {
            if (!tab.equals(parentTab)) {
                driver.switchTo().window(tab);
            }
        }
    }

    public void jsClickOnElement(WebElement element) {
        jsDriver = (JavascriptExecutor) (driver);
        jsDriver.executeScript("arguments[0].click();", element);
    }

    public void safeClickOnElement(WebElement element) {
        try {
            clickElement(element);
        } catch (ElementClickInterceptedException | StaleElementReferenceException | ElementNotVisibleException e) {
            System.out.println("Unable to click - trying again");
            jsClickOnElement(element);
        } catch (TimeoutException | NoSuchElementException e) {
            System.out.println("Unable to locate element - check element locator and ensure element is being made available");
        }

        System.out.printf("Successfully clicked on %s %n", element);
    }

    public WebElement setElementAttributeValue(String attribute, String value, By by) {
        jsDriver = (JavascriptExecutor) (driver);
        jsDriver.executeScript("arguments[0].setAttribute('" + attribute + "', '" + value + "')", driver.findElement(by));

        return driver.findElement(by);
    }

    public void pressEnterKey() {
        try {
            robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private static void captureScreenshot(WebDriver driver, String testName) {
        String absPath = System.getProperty("user.dir");
        String screenshotFileName = "screenshot_" + testName + ".png";

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenshotFile = new File(absPath + File.separator + "src" + File.separator + "test"
                + File.separator + "reports" + File.separator + screenshotFileName);

        try {
            FileHandler.copy(screenshot, screenshotFile);
            System.out.println("SCREENSHOT TAKEN");
        } catch (Exception e) {
            System.out.println("ERROR TAKING SCREENSHOT: " + e.getMessage());
        }
    }

    private static Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}
