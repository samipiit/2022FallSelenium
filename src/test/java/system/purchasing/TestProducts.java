package system.purchasing;

import application.page_library.HomePage;
import application.page_library.ItemPage;
import application.page_library.ProductsPage;
import base.Base;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestProducts extends Base {

    @Test
    public void testNavigateToItemPage() throws IOException {
        HomePage homePage = new HomePage();

        String[] testData = excel.readStringArray(testDataFilePath, "TestProducts");
        String searchTerm = testData[0];
        int index = Integer.parseInt(testData[1]);

        ProductsPage products = homePage.systemBar.doSearch(searchTerm);
        products.selectProduct(index);
        switchToIFrame(products.itemIFrame);

        Assert.assertEquals(products.getItemName(), testData[2]);
    }

}
