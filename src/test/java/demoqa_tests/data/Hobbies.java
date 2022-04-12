package demoqa_tests.data;

import java.util.ArrayList;
import java.util.List;

public class Hobbies {

    private final List<String> hobbies;

    public Hobbies() {
        this.hobbies = List.of("Sports", "Reading", "Music");
    }

    public List<String> getHobbies() {
        return new ArrayList<>(hobbies);
    }
}
