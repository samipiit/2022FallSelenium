package system.shopping_cart;

import application.page_library.HomePage;
import application.page_library.ShoppingCartPage;
import base.Base;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestShoppingCart extends Base {

    @Test (groups = {"smoke", "shopping_cart"})
    public void testNavigationToShoppingCart() {
        HomePage homePage = new HomePage();

        ShoppingCartPage shoppingCart = homePage.clickShoppingCartButton();

        Assert.assertTrue(isElementDisplayed(shoppingCart.orderSteps));
    }
}
