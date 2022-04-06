package lesson_3.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeForm {

    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void testFormFilling() {
        //test data
        String firstName = "John";
        String lastName = "Doe";
        String email = "jd@email.com";
        String gender = "Male";
        String mobile = "0123456789";
        String dayOfBirth = "14";
        String monthOfBirth = "March";
        String yearOfBirth = "1985";
        String subject = "English";
        String hobby = "Music";
        String pictureName = "avatar.jpg";
        String currentAddress = "Some address 1";
        String state = "Haryana";
        String city = "Karnal";

        open("/automation-practice-form");

        //fill form
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $$(".custom-control-label").findBy(text(gender)).click();
        $("#userNumber").setValue(mobile);
        $("#currentAddress").setValue(currentAddress);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $(".react-datepicker__day.react-datepicker__day--0" + dayOfBirth).click();
        $("#subjectsInput").setValue(subject).pressEnter();
        $$(".custom-control-label").findBy(text(hobby)).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/" + pictureName));
        $("#state").click();
        $(byText(state)).click();
        $("#city").click();
        $(byText(city)).click();

        //submit
        $("#submit").click();

        //check data
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

        $(".table-responsive")
                .find(byText("Student Name")).sibling(0).shouldHave(text(firstName + " " + lastName));
        $(".table-responsive")
                .find(byText("Student Email")).sibling(0).shouldHave(text(email));
        $(".table-responsive")
                .find(byText("Gender")).sibling(0).shouldHave(text(gender));
        $(".table-responsive")
                .find(byText("Mobile")).sibling(0).shouldHave(text(mobile));
        $(".table-responsive")
                .find(byText("Date of Birth")).sibling(0).shouldHave(text(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth));
        $(".table-responsive")
                .find(byText("Subjects")).sibling(0).shouldHave(text(subject));
        $(".table-responsive")
                .find(byText("Hobbies")).sibling(0).shouldHave(text(hobby));
        $(".table-responsive")
                .find(byText("Picture")).sibling(0).shouldHave(text(pictureName));
        $(".table-responsive")
                .find(byText("Address")).sibling(0).shouldHave(text(currentAddress));
        $(".table-responsive")
                .find(byText("State and City")).sibling(0).shouldHave(text(state + " " + city));

        //close modal
        $("#closeLargeModal").click();
    }
}
