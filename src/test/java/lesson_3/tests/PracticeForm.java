package lesson_3.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
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

        findElementInTable("Student Name").shouldHave(text(firstName + " " + lastName));
        findElementInTable("Student Email").shouldHave(text(email));
        findElementInTable("Gender").shouldHave(text(gender));
        findElementInTable("Mobile").shouldHave(text(mobile));
        findElementInTable("Date of Birth").shouldHave(text(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth));
        findElementInTable("Subjects").shouldHave(text(subject));
        findElementInTable("Hobbies").shouldHave(text(hobby));
        findElementInTable("Picture").shouldHave(text(pictureName));
        findElementInTable("Address").shouldHave(text(currentAddress));
        findElementInTable("State and City").shouldHave(text(state + " " + city));

        //close modal
        //$("#closeLargeModal").click();
    }

    private SelenideElement findElementInTable(String fieldName) {
        return $(".table-responsive").find(byText(fieldName)).sibling(0);
    }
}