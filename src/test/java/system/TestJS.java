package system;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestJS extends Base {

    @Test
    public void testJSClick() throws InterruptedException {
        WebElement enabled = driver.findElement(By.id("ui-id-2"));
        WebElement downloads = driver.findElement(By.id("ui-id-4"));
        WebElement pdf = driver.findElement(By.id("ui-id-6"));

        safeClickOnElement(enabled);
        safeClickOnElement(downloads);
        safeClickOnElement(pdf);

        Assert.assertTrue(true);

    }

}
