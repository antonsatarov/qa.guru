package demoqapractice.data;

import java.util.ArrayList;
import java.util.List;

public class Subjects {

    private final List<String> subjects;

    public Subjects() {
        this.subjects = List.of(
                "Maths",
                "Accounting",
                "Arts",
                "Biology",
                "Chemistry",
                "Commerce",
                "English",
                "Economics",
                "Hindi",
                "Physics",
                "Computer Science",
                "Social Studies",
                "Civics");
    }

    public List<String> getSubjects() {
        return new ArrayList<>(subjects);
    }
}
