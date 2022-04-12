package demoqa_tests.tests;

import com.codeborne.selenide.Configuration;
import demoqa_tests.data.Hobbies;
import demoqa_tests.data.StatesAndCities;
import demoqa_tests.data.Subjects;
import demoqa_tests.pages.RegistrationFormPage;
import demoqa_tests.utils.RandomUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.String.join;


public class RegistrationFormTests {

    RegistrationFormPage registrationFormPage = new RegistrationFormPage();
    RandomUtils randomUtils = new RandomUtils();

    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void testFormFilling() {
        //test data
        String firstName = randomUtils.getFirstName();
        String lastName = randomUtils.getLastName();
        String email = randomUtils.getEmail();
        String gender = randomUtils.getGenderFromEnum();
        String mobile = randomUtils.getMobileNumber();

        Date date =  randomUtils.getDate();
        String dayOfBirth = new SimpleDateFormat("dd").format(date);
        String monthOfBirth = new SimpleDateFormat("MMMM").format(date);
        String yearOfBirth = new SimpleDateFormat("yyyy").format(date);

        Subjects subjects = new Subjects();
        String subject = randomUtils.getRandomFromCollection(subjects.getSubjects());
        Hobbies hobbies = new Hobbies();
        String hobby = randomUtils.getRandomFromCollection(hobbies.getHobbies());
        String pictureName = "avatar.jpg";
        String currentAddress = randomUtils.getAddress();
        StatesAndCities statesAndCities = new StatesAndCities();
        String state = randomUtils.getRandomFromCollection(statesAndCities.getStates());
        String city = randomUtils.getRandomFromCollection(statesAndCities.getCitiesByState(state));

        //expected results
        String expectedSubjects = join(", ", subject);
        String expectedStateAndCity = join(" ", state, city);
        String expectedFullName = join(" ", firstName, lastName);

        DateFormat dateFormat = new SimpleDateFormat("dd MMMM,yyyy");
        String dateOfBirth = dateFormat.format(date);

        registrationFormPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(email)
                .setGender(gender)
                .setMobileNumber(mobile)
                .setDateOfBirth(monthOfBirth,yearOfBirth,dayOfBirth)
                .setSubjects(subject)
                .setHobbies(hobby)
                .uploadPicture(pictureName)
                .setAddress(currentAddress)
                .selectState(state)
                .selectCity(city)
                .submitForm()
                //assertions
                .checkModalTitle("Thanks for submitting the form")
                .checkResult("Student Name", expectedFullName)
                .checkResult("Student Email", email)
                .checkResult("Gender", gender)
                .checkResult("Mobile", mobile)
                .checkResult("Date of Birth", dateOfBirth)
                .checkResult("Subjects", expectedSubjects)
                .checkResult("Hobbies", hobby)
                .checkResult("Picture", pictureName)
                .checkResult("Address", currentAddress)
                .checkResult("State and City", expectedStateAndCity);

        //close modal
        //$("#closeLargeModal").click();
    }
}