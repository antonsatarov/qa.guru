package demoqa_tests.data;

import java.util.*;

public class StatesAndCities {

    private final Map<String, List<String>> statesAndCities;

    public StatesAndCities() {
        this.statesAndCities = Map.of(
                "NCR",List.of("Delhi","Gurgaon","Noida"),
                "Rajasthan",List.of("Jaipur","Jaiselmer"),
                "Haryana",List.of("Karnal","Panipat"),
                "Uttar Pradesh",List.of("Agra","Lucknow","Merrut")
        );
    }

    public List<String> getStates() {
        return new ArrayList<>(statesAndCities.keySet());
    }

    public List<String> getCitiesByState(String state) {
        return statesAndCities.get(state);
    }

}
