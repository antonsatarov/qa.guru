package demoqapractice.tests.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import demoqapractice.config.CredentialsConfig;
import demoqapractice.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {


    @BeforeAll
    @Tag("owner")
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        CredentialsConfig config  = ConfigFactory.create(CredentialsConfig.class);

        Configuration.holdBrowserOpen = Boolean.parseBoolean(System.getProperty("holdBrowserOpen"));
        Configuration.baseUrl = System.getProperty("baseUrl");
        Configuration.browser = System.getProperty("browser");
        Configuration.browserSize = System.getProperty("browserSize");
        Configuration.browserVersion = System.getProperty("browserVersion");

        String login = config.login();
        String password = config.password();

        Configuration.remote = "https://" + login + ":" + password + "@" + System.getProperty("selenoidHub");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}
