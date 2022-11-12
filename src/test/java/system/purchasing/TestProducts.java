package system.purchasing;

import application.page_library.HomePage;
import application.page_library.ProductsPage;
import base.Base;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestProducts extends Base {

    @Test (dataProviderClass = data_providers.DataProviders.class, dataProvider = "testSelectItem")
    public void testSelectItem(String searchTerm, String index, String expected) {
        HomePage homePage = new HomePage();

//        HashMap<String, String> testData = excel.getDataModel(testDataFilePath, "TestProducts");
//        String searchTerm = testData.get("searchTerm");
//        int index = Integer.parseInt(testData.get("index"));

        ProductsPage products = homePage.systemBar.doSearch(searchTerm);
        products.selectProduct(Integer.parseInt(index));
        switchToIFrame(products.itemiFrame);

        Assert.assertEquals(products.getItemNameQuickViewiFrame(), expected);
    }

    @Test
    public void testAddItemToCartFromProductsPage() throws InterruptedException {
        HomePage homePage = new HomePage();
        ProductsPage productsPage = homePage.systemBar.doSearch("Dress");

        productsPage.hoverClickAddToCart(2);

        String expectedText = "Product successfully added to your shopping cart";
        String actualText = getTextFromElement(productsPage.productNameModalWindow).trim();
        Assert.assertEquals(actualText, expectedText);

    }

}
