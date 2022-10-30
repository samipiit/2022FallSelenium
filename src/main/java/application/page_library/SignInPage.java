package application.page_library;

import application.shared.SharedStepsUI;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.event.KeyEvent;

public class SignInPage extends SharedStepsUI {

    @FindBy (id = "email_create")
    public WebElement registrationEmailTextBox;

    @FindBy (xpath = "//form[@id='create-account_form']//div[@class='form-group form-error']")
    public WebElement registrationFormError;

    @FindBy (xpath = "//form[@id='create-account_form']//div[@class='form-group form-ok']")
    public WebElement registrationFormOk;

    @FindBy (id = "SubmitCreate")
    public WebElement registrationSubmitButton;

    @FindBy (id = "email")
    public WebElement emailTextBox;

    @FindBy (id = "passwd")
    public WebElement passwordTextBox;

    @FindBy (id = "SubmitLogin")
    public WebElement signInButton;

    public SignInPage() {
        PageFactory.initElements(driver, this);
    }

    // region Registration
    public void inputRegistrationEmailAddress(String emailAddress) throws AWTException {
        sendKeysToElement(registrationEmailTextBox, emailAddress);

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);

    }

    public RegistrationFormPage clickCreateAnAccountButton() {
        clickElement(registrationSubmitButton);

        return new RegistrationFormPage();
    }

    // endregion

    // region Login
    public void inputEmailAddress(String emailAddress) {
        sendKeysToElement(emailTextBox, emailAddress);
    }

    public void inputPassword(String password) {
        sendKeysToElement(passwordTextBox, password);
    }

    public void clickSignInButton() {
        clickElement(signInButton);
    }

    public AccountPage login(String emailAddress, String password) {
        inputEmailAddress(emailAddress);
        inputPassword(password);
        clickSignInButton();

        return new AccountPage();
    }

    // endregion










}
