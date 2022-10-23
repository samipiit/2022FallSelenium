package application.page_library;

import base.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RegistrationFormPage extends Base {

    @FindBy (id = "id_gender1")
    public WebElement maleSalutationRadioButton;

    @FindBy (id = "uniform-id_gender2")
    public WebElement femaleSalutationRadioButton;

    @FindBy (id = "customer_firstname")
    public WebElement personalFirstNameInputField;

    @FindBy (id = "customer_lastname")
    public WebElement personalLastNameInputField;

    @FindBy (id = "passwd")
    public WebElement passwordInputField;

    @FindBy (id = "days")
    public WebElement dobDaysComboBox;

    @FindBy (id = "months")
    public WebElement dobMonthsComboBox;

    @FindBy (id = "years")
    public WebElement dobYearsComboBox;

    @FindBy (id = "firstname")
    public WebElement addressFirstNameInputField;

    @FindBy (id = "lastname")
    public WebElement addressLastNameInputField;

    @FindBy (id = "address1")
    public WebElement streetAddressInputField;

    @FindBy (id = "city")
    public WebElement cityInputField;

    @FindBy (id = "id_state")
    public WebElement stateComboBox;

    @FindBy (id = "postcode")
    public WebElement zipcodeInputField;

    @FindBy (id = "id_country")
    public WebElement countryComboBox;

    @FindBy (id = "phone_mobile")
    public WebElement mobilePhoneInputField;

    @FindBy (id = "alias")
    public WebElement addressAliasInputField;

    @FindBy (id = "submitAccount")
    public WebElement submitButton;

    public RegistrationFormPage() {
        PageFactory.initElements(driver, this);
    }

    public AccountPage registerNewAccount(boolean isMale, String firstName, String lastName, String password, int dobDay,
                                   int dobMonth, int dobYear, String streetAddress, String city, String state,
                                   String zipcode, String mobilePhoneNumber, String addressAlias) {
        selectSalutation(isMale);
        inputPersonalFirstName(firstName);
        inputPersonalLastName(lastName);
        inputNewPassword(password);
        selectDateOfBirthDay(dobDay);
        selectDateOfBirthMonth(dobMonth);
        selectDateOfBirthYear(dobYear);
        inputAddressFirstName(firstName);
        inputAddressLastName(lastName);
        inputStreetAddress(streetAddress);
        inputCity(city);
        selectState(state);
        inputZipcode(zipcode);
        inputMobilePhoneNumber(mobilePhoneNumber);
        inputAddressAlias(addressAlias);
        clickRegisterButton();

        return new AccountPage();
    }

    public void selectSalutation(boolean isMale) {
        if (isMale) {
            clickElement(maleSalutationRadioButton);
        } else {
            clickElement(femaleSalutationRadioButton);
        }
    }

    public void inputPersonalFirstName(String firstName) {
        sendKeysToElement(personalFirstNameInputField, firstName);
    }

    public void inputPersonalLastName(String lastName) {
        sendKeysToElement(personalLastNameInputField, lastName);
    }

    public void inputNewPassword(String password) {
        sendKeysToElement(passwordInputField, password);
    }

    public void selectDateOfBirthDay(int day) {
        selectByValue(dobDaysComboBox, String.valueOf(day));
    }

    public void selectDateOfBirthMonth(int month) {
        selectByValue(dobMonthsComboBox, String.valueOf(month));
    }

    public void selectDateOfBirthYear(int year) {
        selectByValue(dobYearsComboBox, String.valueOf(year));
    }

    public void inputAddressFirstName(String firstName) {
        sendKeysToElement(addressFirstNameInputField, firstName);
    }

    public void inputAddressLastName(String lastName) {
        sendKeysToElement(addressLastNameInputField, lastName);
    }

    public void inputStreetAddress(String streetAddress) {
        sendKeysToElement(streetAddressInputField, streetAddress);
    }

    public void inputCity(String city) {
        sendKeysToElement(cityInputField, city);
    }

    public void selectState(String state) {
        selectByVisibleText(stateComboBox, state);
    }

    public void inputZipcode(String zipcode) {
        sendKeysToElement(zipcodeInputField, zipcode);
    }

    public void inputMobilePhoneNumber(String phoneNumber) {
        sendKeysToElement(mobilePhoneInputField, phoneNumber);
    }

    public void inputAddressAlias(String alias) {
        sendKeysToElement(addressAliasInputField, alias);
    }

    public void clickRegisterButton() {
        clickElement(submitButton);
    }


}
