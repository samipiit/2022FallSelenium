package application.page_library;

import application.shared.SharedStepsUI;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage extends SharedStepsUI {

    @FindBy (id = "order_step")
    public WebElement orderSteps;

    public ShoppingCartPage() {
        PageFactory.initElements(driver, this);
    }

}
