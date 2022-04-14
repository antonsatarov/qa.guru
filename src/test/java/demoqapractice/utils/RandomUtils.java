package demoqapractice.utils;

import com.github.javafaker.Faker;
import demoqapractice.data.Genders;

import java.util.*;

public class RandomUtils {

    Faker faker = new Faker();

    public <T> T getRandomFromCollection(List<T> list) {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    public <T> List<T> getSeveralRandomFromCollection(List<T> list) {
        Random rand = new Random();
        list = new ArrayList<>(list);
        Collections.shuffle(list);
        return list.subList(0, rand.nextInt(list.size() - 1) + 1);
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
