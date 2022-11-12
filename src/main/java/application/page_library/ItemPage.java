package application.page_library;

import base.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ItemPage extends Base {

    @FindBy (xpath = "//h1[@itemprop='name']")
    public WebElement itemName;

    public ItemPage() {
        PageFactory.initElements(driver, this);
    }

    public String getItemName() {
        return getTextFromElement(itemName);
    }


}
