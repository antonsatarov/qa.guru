package demoqapractice.tests;

import com.codeborne.selenide.Configuration;
import demoqapractice.data.Hobbies;
import demoqapractice.data.StatesAndCities;
import demoqapractice.data.Subjects;
import demoqapractice.pages.RegistrationFormPage;
import demoqapractice.tests.base.TestBase;
import demoqapractice.utils.RandomUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static io.qameta.allure.Allure.step;
import static java.lang.String.join;

@Tag("demoqa")
@DisplayName("Форма регистрации")
public class RegistrationFormTests extends TestBase {

    RegistrationFormPage registrationFormPage = new RegistrationFormPage();
    RandomUtils randomUtils = new RandomUtils();

    @Test
    @DisplayName("Корректное заполнение формы")
    void testFormFilling() {
        //test data
        String firstName = randomUtils.getFirstName();
        String lastName = randomUtils.getLastName();
        String email = randomUtils.getEmail();
        String gender = randomUtils.getGenderFromEnum();
        String mobile = randomUtils.getMobileNumber();

        Date date = randomUtils.getDate();
        String dayOfBirth = new SimpleDateFormat("dd").format(date);
        String monthOfBirth = new SimpleDateFormat("MMMM").format(date);
        String yearOfBirth = new SimpleDateFormat("yyyy").format(date);

        Subjects subjects = new Subjects();
        List<String> subjectList = randomUtils.getSeveralRandomFromCollection(subjects.getSubjects());
        Hobbies hobbies = new Hobbies();
        List<String> hobbyList = randomUtils.getSeveralRandomFromCollection(hobbies.getHobbies());
        String pictureName = "avatar.jpg";
        String currentAddress = randomUtils.getAddress();
        StatesAndCities statesAndCities = new StatesAndCities();
        String state = randomUtils.getRandomFromCollection(statesAndCities.getStates());
        String city = randomUtils.getRandomFromCollection(statesAndCities.getCitiesByState(state));

        //expected results
        String expectedSubjects = join(", ", subjectList);
        String expectedHobbies = join(", ", hobbyList);
        String expectedStateAndCity = join(" ", state, city);
        String expectedFullName = join(" ", firstName, lastName);
        String dateOfBirth = new SimpleDateFormat("dd MMMM,yyyy").format(date);

        //test
        step("Открываем страницу practice form", () -> {
            registrationFormPage.openPage();
        });

        step("Заполняем форму регистрации", () -> {
            registrationFormPage.openPage()
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setUserEmail(email)
                    .setGender(gender)
                    .setMobileNumber(mobile)
                    .setDateOfBirth(monthOfBirth,yearOfBirth,dayOfBirth)
                    .setSubjects(subjectList)
                    .setHobbies(hobbyList)
                    .uploadPicture(pictureName)
                    .setAddress(currentAddress)
                    .selectState(state)
                    .selectCity(city);
        });

        step("Отправляем заполненную форму", () -> {
            registrationFormPage.submitForm();
        });

        //assertions

        step("Проверяем данные в таблице", () -> {
           registrationFormPage.checkModalTitle("Thanks for submitting the form")
                    .checkResult("Student Name", expectedFullName)
                    .checkResult("Student Email", email)
                    .checkResult("Gender", gender)
                    .checkResult("Mobile", mobile)
                    .checkResult("Date of Birth", dateOfBirth)
                    .checkResult("Subjects", expectedSubjects)
                    .checkResult("Hobbies", expectedHobbies)
                    .checkResult("Picture", pictureName)
                    .checkResult("Address", currentAddress)
                    .checkResult("State and City", expectedStateAndCity);
        });

        //close modal
        registrationFormPage.closeModal();
    }
}