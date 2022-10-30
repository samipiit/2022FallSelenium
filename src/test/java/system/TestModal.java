package system;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestModal extends Base {

    @Test
    public void testModalWindow() throws InterruptedException {
        Thread.sleep(2000);
        WebElement formButton = driver.findElement(By.id("Form"));
        WebElement formFrame = driver.findElement(By.xpath("//iframe[contains(@class, 'demo-frame lazyloaded')]"));

        switchToIFrame(formFrame);

        WebElement createUserButton = driver.findElement(By.xpath("//button[contains(text(), 'Create new user')]"));
        WebElement nameInput = driver.findElement(By.xpath("//input[@id='name']"));
        WebElement emailInput = driver.findElement(By.xpath("//input[@id='email']"));
        WebElement passwordInput = driver.findElement(By.xpath("//input[@id='password']"));
        WebElement createAccountButton = driver.findElement(By.xpath("//button[contains(text(), \"Create an account\")]"));

        String name = "Burhan Uddin";

        safeClickOnElement(createUserButton);
        clearSendKeysToElement(nameInput, name);
        clearSendKeysToElement(emailInput, "BUddin@gmail.com");
        clearSendKeysToElement(passwordInput, "Selenium123");
        safeClickOnElement(createAccountButton);

        WebElement lastTableRowName = driver.findElement(By.xpath("//table[@id='users']/tbody/tr[last()]/td[1]"));

        Assert.assertEquals(lastTableRowName.getText(), name);

    }
}
