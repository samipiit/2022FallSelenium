package application.page_library;

import base.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends Base {

    @FindBy (className = "login")
    public WebElement signInButton;

    @FindBy (css = ".shopping_cart > a")
    public WebElement shoppingCartButton;

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    public SignInPage clickSignInButton() {
        clickElement(signInButton);

        return new SignInPage();
    }

    public ShoppingCartPage clickShoppingCartButton() {
        clickElement(shoppingCartButton);

        return new ShoppingCartPage();
    }
}
