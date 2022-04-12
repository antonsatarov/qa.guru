package demoqa_tests.utils;

import com.github.javafaker.Faker;
import demoqa_tests.data.Genders;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class RandomUtils {

    Faker faker = new Faker();

//    public SelenideElement getRandomFromCollection(ElementsCollection listOfElements) {
//        Random rand = new Random();
//        return listOfElements.get(rand.nextInt(listOfElements.size()));
//    }

    public <T> T getRandomFromCollection(List<T> list) {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    public String getFirstName() {
        return faker.name().firstName();
    }

    public String getLastName() {
        return faker.name().lastName();
    }

    public String getEmail() {
        return faker.internet().emailAddress();
    }

    public String getGenderFromEnum() {
        return Genders.values()[new Random().nextInt(Genders.values().length)].toString();
    }

    public String getMobileNumber() {
        return faker.numerify("##########");
    }

    public Date getDate() {
        return faker.date().birthday();
    }

    public String getAddress() {
        return faker.address().fullAddress();
    }
}
