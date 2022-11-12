package application.page_library;

import application.shared.SharedStepsUI;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage extends SharedStepsUI {

    @FindBy (xpath = "//div[@class='header_user_info']//span")
    public WebElement accountButton;

    public AccountPage() {
        PageFactory.initElements(driver, this);
    }

    public String getAccountName() {
        return getTextFromElement(accountButton);
    }

}
