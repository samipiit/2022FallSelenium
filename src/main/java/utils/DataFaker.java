package utils;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.util.Locale;
import java.util.Random;

public class DataFaker {

    private static Faker faker = new Faker();
    static FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-US"), new RandomService());

    public String firstName() {
        return faker.name().firstName();
    }

    public String lastName() {
        return faker.name().lastName();
    }

    public String email() {
        String[] domains = {"gmail.com", "yahoo.com", "me.com", "live.com", "hotmail.com"};
        Random random = new Random();
        int randomNum = random.nextInt(5);
        String domain = domains[randomNum];

        return fakeValuesService.bothify("test_???????####@" + domain);
    }

    public String password() {
        return fakeValuesService.bothify("??##?#?##??##???##");
    }

    public String streetAddress() {
        return faker.address().streetAddress();
    }

    public String city() {
        return faker.address().city();
    }

    public String state() {
        return faker.address().state();
    }

    public String zipcode() {
        return faker.address().zipCode().substring(0, 5);
    }

    public String mobilePhone() {
        return faker.phoneNumber().cellPhone().replace("-", "").replace(".", "");
    }

    public static void main(String[] args) {
        DataFaker obj = new DataFaker();

        System.out.println(obj.mobilePhone());
    }

}
