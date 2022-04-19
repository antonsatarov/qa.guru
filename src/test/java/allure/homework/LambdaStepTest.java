package allure.homework;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

@DisplayName("Лямбда шаги через step")
public class LambdaStepTest {

    SelenideElement headerSearch = $(".header-search-input");

    private static final String REPO_NAME = "antonsatarov/qa.guru";
    private static final String ISSUE_NAME = "Allure Test";

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://github.com/";
    }

    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Поиск Issue по названию")
    @Test
    public void issueNameTest() {

        step("Открываем главную страницу", () -> {
            open("");
        });
        step("Ищем репозиторий " + REPO_NAME, () -> {
            headerSearch.click();
            headerSearch.setValue(REPO_NAME);
            headerSearch.submit();
        });
        step("Переходим по ссылке репозитория " + REPO_NAME, () -> {
            $(linkText(REPO_NAME)).click();
        });
        step("Кликаем на таб Issues", () -> {
            $(partialLinkText("Issues")).click();
        });
        step("Ищем Issue с именем " + ISSUE_NAME, () -> {
            $(withText(ISSUE_NAME)).shouldBe(Condition.visible);
            Allure.getLifecycle().addAttachment(
                    "Исходники страницы",
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
            );
        });
    }
}
