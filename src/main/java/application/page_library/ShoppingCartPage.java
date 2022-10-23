package application.page_library;

import base.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage extends Base {

    @FindBy (id = "order_step")
    public WebElement orderSteps;

    public ShoppingCartPage() {
        PageFactory.initElements(driver, this);
    }

}
