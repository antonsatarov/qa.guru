package allure.homework;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Шаги с аннотацией @Step")
public class AnnotatedStepsTest {

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
        IssueWebSteps issueWebSteps = new IssueWebSteps();

        issueWebSteps.openMainPage()
                .searchForRepository(REPO_NAME)
                .clickOnRepositoryLink(REPO_NAME)
                .openIssuesTab()
                .shouldSeeIssueWithName(ISSUE_NAME);
    }
}
