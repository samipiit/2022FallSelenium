package application.page_library;

import base.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage extends Base {

    @FindBy (xpath = "//*[@id='mainContent']//h1/span[2]")
    public WebElement searchText;

    public SearchResultsPage() {
        PageFactory.initElements(driver, this);
    }

    public String getSearchText() {
        return getTextFromElement(searchText);
    }
}
