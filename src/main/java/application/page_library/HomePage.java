package application.page_library;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage extends Base {

    @FindBy (id = "gh-ac")
    public WebElement searchBar;

    @FindBy (id = "gh-btn")
    public WebElement searchButton;

    @FindBy (xpath = "//div[@id='personalized_events1']//button[@class='carousel__control carousel__control--prev']")
    public WebElement personalizedEventsCarouselPreviousButton;

    @FindBy (xpath = "//div[@id='personalized_events1']//button[@class='carousel__control carousel__control--next']")
    public WebElement personalizedEventsCarouselNextButton;

    @FindBy (xpath = "//div[@id='personalized_events1']//div[@class='hl-standard-carousel__container']//div[@class='carousel__viewport']/ul/li")
    public List<WebElement> personalizedEventsCarouselItems;

    public HomePage() {
        PageFactory.initElements(driver, this);
    }


    public void inputSearchText(String searchTerm) {
        sendKeysToElement(searchBar, searchTerm);
    }

    public void clickSearchButton() {
        clickElement(searchButton);
    }

    public SearchResultsPage doSearch(String searchTerm) {
        inputSearchText(searchTerm);
        clickSearchButton();

        return new SearchResultsPage();
    }

    public void clickPersonalizedEventsCarouselPreviousButton() {
        if (personalizedEventsCarouselPreviousButton.isEnabled()) {
            personalizedEventsCarouselPreviousButton.click();
        }
    }

    public void clickPersonalizedEventsCarouselNextButton() {
        if (personalizedEventsCarouselNextButton.isEnabled()) {
            personalizedEventsCarouselNextButton.click();
        }
    }



}
