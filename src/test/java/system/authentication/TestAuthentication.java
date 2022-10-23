package system.authentication;

import application.page_library.AccountPage;
import application.page_library.HomePage;
import application.page_library.RegistrationFormPage;
import application.page_library.SignInPage;
import base.Base;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.DataFaker;

import java.awt.*;
import java.util.Random;

public class TestAuthentication extends Base {

    @Test
    public void testNavigationToLoginPage() {
        HomePage homePage = new HomePage();

        Assert.assertTrue(isElementDisplayed(homePage.clickSignInButton().signInButton));
    }

    @Test (groups = {"authentication", "smoke"})
    public void testLogin() {
        HomePage homePage = new HomePage();

        AccountPage accountPage = homePage.clickSignInButton()
                .login(prop.getProperty("username"), prop.getProperty("password"));

        String expectedAccountName = "QA Engineer";

        Assert.assertEquals(accountPage.getAccountName(), expectedAccountName);
    }

    /*
    Register for an account
    1. Navigate to URL
    2. Click sign in button from the homepage
    3. Under "Create an Account", enter email address
    4. Click "Create an Account" button
    5. Select a salutation
    6. Enter first name in First Name field
    7. Enter last name
    8. Enter password
    9. Select DOB day
    10. Select DOB month
    11. Select DOB year
    12. Under "Your Address", enter first name
    13. Enter last name
    14. Enter street address
    15. Enter city
    16. Select state
    17. Enter zipcode
    18. Enter mobile phone number
    19. Click "Register" button
     */
    @Test
    public void testRegisterNewAccount() throws AWTException {
        HomePage homePage = new HomePage();
        SignInPage signInPage = homePage.clickSignInButton();

        DataFaker faker = new DataFaker();
        String emailAddress = faker.email();
        signInPage.inputRegistrationEmailAddress(emailAddress);

        RegistrationFormPage registration = signInPage.clickCreateAnAccountButton();

        String firstName = faker.firstName();
        String lastName = faker.lastName();
        String password = faker.password();
        int dobDay = new Random().nextInt(28);
        int dobMonth = 2;
        int dobYear = 1994;
        String streetAddress = faker.streetAddress();
        String city = faker.city();
        String state = faker.state();
        String zipcode = faker.zipcode();
        String phoneNumber = faker.mobilePhone();
        String addressAlias = "Home Address";

        AccountPage accountPage = registration.registerNewAccount(false, firstName, lastName, password, dobDay,
                dobMonth, dobYear, streetAddress, city, state, zipcode, phoneNumber, addressAlias);

        Assert.assertEquals(accountPage.getAccountName(), firstName + " " + lastName);
    }
}
