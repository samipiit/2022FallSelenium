package system.letskodeit;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestLetsKodeIt extends Base {

    @Test
    public void testSwitchToTab() {
        WebElement openWindow = driver.findElement(By.id("openwindow"));
        clickElement(openWindow);

        switchToHandle();
        WebElement pageTextElement = driver.findElement(By.xpath("//h1[@class='dynamic-heading margin-bottom-20']"));

        Assert.assertEquals(getTextFromElement(pageTextElement), "All Courses");

    }
}
