package demoqapractice.pages;

import com.codeborne.selenide.SelenideElement;
import demoqapractice.pages.components.CalendarComponent;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormPage {

    CalendarComponent calendarComponent = new CalendarComponent();

    //locators - just examples
    SelenideElement firstNameInput = $("#firstName");
    String lastNameInput = "#lastName";

    //actions
    public RegistrationFormPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        return this;
    }

    public RegistrationFormPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    public RegistrationFormPage setLastName(String lastName) {
        $(lastNameInput).setValue(lastName);
        return this;
    }

    public RegistrationFormPage setUserEmail(String email) {
        $("#userEmail").setValue(email);
        return this;
    }

    public RegistrationFormPage setGender(String gender) {
        $$(".custom-control-label").findBy(text(gender)).click();
        return this;
    }

    public RegistrationFormPage setMobileNumber(String mobileNumber) {
        $("#userNumber").setValue(mobileNumber);
        return this;
    }

    public RegistrationFormPage setDateOfBirth(String month, String year, String day) {
        $("#dateOfBirthInput").click();
        calendarComponent.setDate(month, year, day);
        return this;
    }

    public RegistrationFormPage setAddress(String currentAddress) {
        $("#currentAddress").setValue(currentAddress);
        return this;
    }

    public RegistrationFormPage setSubjects(String ... subjects) {
        for (String subject : subjects) {
            $("#subjectsInput").setValue(subject).pressEnter();
        }
        return this;
    }

    public RegistrationFormPage setHobbies(String hobby) {
        $$(".custom-control-label").findBy(text(hobby)).click();
        return this;
    }

    public RegistrationFormPage uploadPicture(String pictureName) {
        $("#uploadPicture").uploadFile(new File("src/test/resources/" + pictureName));
        return this;
    }

    public RegistrationFormPage selectState(String state) {
        $("#state").click();
        $(byText(state)).click();
        return this;
    }

    public RegistrationFormPage selectCity(String city) {
        $("#city").click();
        $(byText(city)).click();
        return this;
    }

//outdated//
//    public RegistrationFormPage selectStateAndCity() {
//        ElementsCollection listOfStates = getCollectionFromDropdown("#state");
//        SelenideElement stateElement = randomUtils.getRandomFromCollection(listOfStates);
//        state = stateElement.getText();
//        stateElement.click();
//
//        ElementsCollection listOfCities = getCollectionFromDropdown("#city");
//        SelenideElement cityElement = randomUtils.getRandomFromCollection(listOfCities);
//        city = cityElement.getText();
//        cityElement.click();
//        return this;
//    }
//
//    private ElementsCollection getCollectionFromDropdown (String locator) {
//        $(locator).click();
//        return $$("div[id*=option]");
//    }

    public RegistrationFormPage submitForm() {
        $("#submit").click();
        return this;
    }

    public void closeModal() {
        $("#closeLargeModal").click();
    }

    //check data
    public RegistrationFormPage checkModalTitle(String value) {
        $("#example-modal-sizes-title-lg").shouldHave(text(value));
        return this;
    }

    public RegistrationFormPage checkResult(String label, String value) {
        $(".table-responsive").find(byText(label)).sibling(0).shouldHave(text(value));
        return this;
    }
}
