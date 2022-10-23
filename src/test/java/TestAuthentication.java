import application.page_library.AccountPage;
import application.page_library.HomePage;
import base.Base;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAuthentication extends Base {

    @Test
    public void testLogin() {
        HomePage homePage = new HomePage();

        AccountPage accountPage = homePage.clickSignInButton()
                .login(prop.getProperty("username"), prop.getProperty("password"));

        String expectedAccountName = "QA Engineer";
        Assert.assertEquals(accountPage.getAccountName(), expectedAccountName);
    }

}
