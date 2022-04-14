package demoqapractice.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import demoqapractice.pages.components.CalendarComponent;

import java.io.File;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormPage {

    CalendarComponent calendarComponent = new CalendarComponent();

    //locators - just examples
    SelenideElement firstNameInput = $("#firstName");
    SelenideElement lastNameInput = $("#lastName");
    SelenideElement emailInput = $("#userEmail");
    ElementsCollection genderOptions = $$(".custom-control-label");
    SelenideElement mobileInput = $("#userNumber");
    SelenideElement dateOfBirthPicker = $("#dateOfBirthInput");
    SelenideElement currentAddressInput = $("#currentAddress");
    SelenideElement subjectsInput = $("#subjectsInput");
    ElementsCollection hobbyOptions = $$(".custom-control-label");
    SelenideElement uploadPictureButton = $("#uploadPicture");
    SelenideElement stateSelect = $("#state");
    SelenideElement citySelect = $("#city");
    SelenideElement closeModalButton = $("#closeLargeModal");
    SelenideElement submitButton = $("#submit");
    SelenideElement table = $(".table-responsive");
    SelenideElement headerText = $(".practice-form-wrapper");
    SelenideElement modalHeaderText = $("#example-modal-sizes-title-lg");

    //actions
    public RegistrationFormPage openPage() {
        open("/automation-practice-form");
        headerText.shouldHave(text("Student Registration Form"));
        return this;
    }

    public RegistrationFormPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    public RegistrationFormPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    public RegistrationFormPage setUserEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public RegistrationFormPage setGender(String gender) {
        genderOptions.findBy(text(gender)).click();
        return this;
    }

    public RegistrationFormPage setMobileNumber(String mobileNumber) {
        mobileInput.setValue(mobileNumber);
        return this;
    }

    public RegistrationFormPage setDateOfBirth(String month, String year, String day) {
        dateOfBirthPicker.click();
        calendarComponent.setDate(month, year, day);
        return this;
    }

    public RegistrationFormPage setAddress(String currentAddress) {
        currentAddressInput.setValue(currentAddress);
        return this;
    }

    public RegistrationFormPage setSubjects(List<String> subjects) {
        for (String subject : subjects) {
            subjectsInput.setValue(subject).pressEnter();
        }
        return this;
    }

    public RegistrationFormPage setHobbies(List<String> hobbies) {
        for (String hobby : hobbies) {
            hobbyOptions.findBy(text(hobby)).click();
        }
        return this;
    }

    public RegistrationFormPage uploadPicture(String pictureName) {
        uploadPictureButton.uploadFile(new File("src/test/resources/" + pictureName));
        return this;
    }

    public RegistrationFormPage selectState(String state) {
        stateSelect.click();
        $(byText(state)).click();
        return this;
    }

    public RegistrationFormPage selectCity(String city) {
        citySelect.click();
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
        submitButton.click();
        return this;
    }

    public void closeModal() {
        closeModalButton.click();
    }

    //check data
    public RegistrationFormPage checkModalTitle(String value) {
        modalHeaderText.shouldHave(text(value));
        return this;
    }

    public RegistrationFormPage checkResult(String label, String value) {
       table.find(byText(label)).sibling(0).shouldHave(text(value));
        return this;
    }
}
