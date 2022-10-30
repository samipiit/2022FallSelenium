package application.page_library;

import application.shared.SharedStepsUI;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage extends SharedStepsUI {

    @FindBy(xpath = "//ul[@class='product_list grid row']/li//a[@class='product_img_link']")
    public List<WebElement> products;

    @FindBy (xpath = "//h1[@itemprop='name']")
    public WebElement quickViewItemName;

    @FindBy (xpath = "//iframe[@class='fancybox-iframe']")
    public WebElement itemIFrame;

    public ProductsPage() {
        PageFactory.initElements(driver, this);
    }

    public void selectProduct(int productIndex) {
        try {
            clickElement(products.get(productIndex));
        } catch (IndexOutOfBoundsException e) {
            clickElement(products.get(products.size() - 1));
        }
    }

    public String getItemName() {
        return getTextFromElement(quickViewItemName);
    }
}
