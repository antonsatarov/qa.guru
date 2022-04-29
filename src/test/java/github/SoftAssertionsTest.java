package github;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SoftAssertionsTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void softAssertionsContainsJunit5Code() {

        // открыть страницу selenide github.com
        open("https://github.com/selenide/selenide");
        // перейти в раздел wiki
        $("#wiki-tab").click();
        // проверка наличия страницы SoftAssertions
        $(".wiki-more-pages-link").$("button").click();
        $(".wiki-rightbar").shouldHave(text("SoftAssertions"));
        // перейти на страницу SoftAssertions
        $(".wiki-rightbar").$(byText("SoftAssertions")).click();
        // проверка наличия кода для Junit
        $("#wiki-content")
                .shouldHave(text("JUnit5 extension - com.codeborne.selenide.junit5.SoftAssertsExtension")
                        ,text("Using JUnit5 extend test class")
                        ,text("@ExtendWith({SoftAssertsExtension.class})\n" +
                                "class Tests {\n" +
                                "  @Test\n" +
                                "  void test() {\n" +
                                "    Configuration.assertionMode = SOFT;\n" +
                                "    open(\"page.html\");\n" +
                                "\n" +
                                "    $(\"#first\").should(visible).click();\n" +
                                "    $(\"#second\").should(visible).click();\n" +
                                "  }\n" +
                                "}"));
    }
}
