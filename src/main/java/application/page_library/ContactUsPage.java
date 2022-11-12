package application.page_library;

import application.shared.SharedStepsUI;
import org.openqa.selenium.support.PageFactory;

public class ContactUsPage extends SharedStepsUI {

    public ContactUsPage() {
        PageFactory.initElements(driver, this);
    }
}
