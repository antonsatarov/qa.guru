package allure.homework;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

@DisplayName("Чистый Selenide (с Listener)")
public class SelenideWithListenerTest {

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
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("");

        headerSearch.click();
        headerSearch.setValue(REPO_NAME);
        headerSearch.submit();

        $(linkText(REPO_NAME)).click();
        $(partialLinkText("Issues")).click();
        $(withText(ISSUE_NAME)).shouldBe(Condition.visible);
    }

}
