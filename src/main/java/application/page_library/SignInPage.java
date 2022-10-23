package application.page_library;

import base.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage extends Base {

    @FindBy (id = "email")
    public WebElement emailTextBox;

    @FindBy (id = "passwd")
    public WebElement passwordTextBox;

    @FindBy (id = "SubmitLogin")
    public WebElement signInButton;

    public SignInPage() {
        PageFactory.initElements(driver, this);
    }

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



}
