package lesson_3;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimpleTest {

    @BeforeEach
    void openGooglePage() {
        Selenide.open("https://google.com");
    }

    @AfterEach
    void closePage() {
        WebDriverRunner.closeWindow();
    }

    @Test
    void assetTest() {

    }

}
