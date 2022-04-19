package allure.homework;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class IssueWebSteps {

    SelenideElement headerSearch = $(".header-search-input");

    @Step("Открываем главную страницу")
    public IssueWebSteps openMainPage() {
        open("");
        return this;
    }

    @Step("Ищем реопзиторий {REPO_NAME}")
    public IssueWebSteps searchForRepository(String REPO_NAME) {
        headerSearch.click();
        headerSearch.setValue(REPO_NAME);
        headerSearch.submit();
        return this;
    }

    @Step("Переходим по ссылке репозитория {REPO_NAME}")
    public IssueWebSteps clickOnRepositoryLink(String REPO_NAME) {
        $(linkText(REPO_NAME)).click();
        return this;
    }

    @Step("Кликаем на таб Issues")
    public IssueWebSteps openIssuesTab() {
        $(partialLinkText("Issues")).click();
        return this;
    }

    @Step("Проверяем что существует Issue с именем {ISSUE_NAME}")
    public IssueWebSteps shouldSeeIssueWithName(String ISSUE_NAME) {
        $(withText(ISSUE_NAME)).shouldBe(Condition.visible);
        return this;
    }
}
