package demoqa_tests.utils;

import com.github.javafaker.Faker;
import demoqa_tests.data.Genders;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class RandomUtils {

    Faker faker = new Faker();

    public <T> T getRandomFromCollection(List<T> list) {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

//TODO
//    public <T> T[] getSeveralRandomFromCollection(List<T> list) {
//        Random rand = new Random();
//
//        int numberOfElements = rand.nextInt(list.size());
//
//        for (int i = 0; i < numberOfElements; i++) {
//            int randomIndex = rand.nextInt(list.size());
//            result.add(list.get(randomIndex));
//            list.remove(randomIndex);
//        }
//        return result;
//    }

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
