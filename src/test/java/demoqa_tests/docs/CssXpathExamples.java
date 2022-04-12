package demoqa_tests.docs;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CssXpathExamples {
    void cssXpathExamples() {
        // <input type="email" class="inputtext login_form_input_box" name="email" id="email" data-testid="email">
        $("[data-testid=email]").setValue("123");
        $(by("data-testid", "email")).setValue("123");

        // <input type="email" class="inputtext login_form_input_box" name="email" id="email">
        $("[id=email]").setValue("123");
        $("#email").setValue("123");
        $(byId("email")).setValue("123");
        $(By.id("email")).setValue("123");
        $x("//*[@id='email']").setValue("123");
        $(byXpath("//*[@id='email']")).setValue("123");

        // <input type="email" class="inputtext login_form_input_box" name="email">
        $("[name=email]").setValue("123");
        $(byName("email")).setValue("123");

        // <input type="email" class="inputtext login_form_input_box" name="email">
        $("[class=login_form_input_box]").setValue("123");
        $(".login_form_input_box").setValue("123");
        $(".inputtext.login_form_input_box").setValue("123");
        $("input.inputtext.login_form_input_box").setValue("123");
        $x("//input[@class='login_form_input_box']").setValue("123");
        $x("//input[@class='inputtext'][@class='login_form_input_box']").setValue("123");

        // <div class="inputtext">
        //      <input type="email" class="login_form_input_box" name="email">
        // </div>
        $(".inputtext .login_form_input_box").setValue("123");

        // <input type="email" class="inputtext login_form_input_box" name="email" id="email" data-testid="email">
        $("input.inputtext.login_form_input_box#email[name=email][data-testid=email]").setValue("123");

        // <div>Hello qa.guru</div>
        $x("//div[text()='Hello qa.guru']");
        $x("//div[contains(text(), 'ello qa.gur')]");
        $x("//div[text()[contains(.,'ello qa.gur')]]");
        $(byText("Hello qa.guru"));
        $(withText("ello qa.gur"));
    }
}
