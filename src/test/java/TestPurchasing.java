import application.page_library.HomePage;
import application.page_library.SearchResultsPage;
import base.Base;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestPurchasing extends Base {

    @Test
    public void testSearchItem() {
        HomePage homePage = new HomePage();
        String searchTerm = "Halloween Costumes for Cats";
        SearchResultsPage searchResultsPage = homePage.doSearch(searchTerm);

        Assert.assertEquals(searchResultsPage.getSearchText(), searchTerm);
    }

    @Test
    public void testPersonalizedEventsCarousel() {
        HomePage homePage = new HomePage();
        homePage.clickPersonalizedEventsCarouselNextButton();
        homePage.clickPersonalizedEventsCarouselPreviousButton();

        boolean isDisabled = Boolean.parseBoolean(homePage.personalizedEventsCarouselPreviousButton.getAttribute("aria-disabled"));

        Assert.assertTrue(isDisabled);
    }


}
