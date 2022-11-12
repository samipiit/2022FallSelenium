package application.page_library;

import application.shared.SharedStepsUI;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage extends SharedStepsUI {

    @FindBy(xpath = "//ul[@class='product_list grid row']/li")
    public List<WebElement> products;

    @FindBy(xpath = "//ul[@class='product_list grid row']/li//a[@title='Add to cart']")
    public List<WebElement> productAddToCartButtons;

    @FindBy(xpath = "//ul[@class='product_list grid row']/li//a[@class='product_img_link']")
    public List<WebElement> productLinks;

    @FindBy(xpath = "//div[contains(@class, 'layer_cart_product col-xs-12 col-md-6')]/h2")
    public WebElement productNameModalWindow;

    @FindBy (xpath = "//h1[@itemprop='name']")
    public WebElement quickViewItemNameiFrame;

    @FindBy (xpath = "//iframe[@class='fancybox-iframe']")
    public WebElement itemiFrame;

    public ProductsPage() {
        PageFactory.initElements(driver, this);
    }

    public void selectProduct(int productIndex) {
        try {
            clickElement(productLinks.get(productIndex));
        } catch (IndexOutOfBoundsException e) {
            clickElement(productLinks.get(productLinks.size() - 1));
        }
    }

    public void hoverOverItem(int productIndex) {
        hoverOverElement(products.get(productIndex));
    }

    public void hoverClickAddToCart(int productIndex) {
        hoverOverItem(productIndex);
        clickElement(productAddToCartButtons.get(productIndex));
    }

    public String getItemNameQuickViewiFrame() {
        return getTextFromElement(quickViewItemNameiFrame);
    }
}
