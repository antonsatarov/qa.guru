package github;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ContributorsTest {

    @Test
    void solntsevShouldBEFirstContributor() {
        open("https://github.com/selenide/selenide");

        $(".Layout-sidebar").$(byText("Contributors")).ancestor("div")
                .$$("ul li").first().hover();
        $$(".Popover-message").findBy(visible).shouldHave(text("Andrei Solntsev"));

    }
}
