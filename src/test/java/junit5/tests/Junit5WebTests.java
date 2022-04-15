package junit5.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Junit5WebTests {

    @ValueSource(strings = {
            "Selenide",
            "JUnit"
    })
    @ParameterizedTest(name = "Проверка поиска в Яндексе по слову {0}")
    void yaSearchTest(String testData) {

        //Предусловия
        Selenide.open("https://ya.ru");
        //Шаги
        $("#text").setValue(testData);
        $("button[type='submit']").click();
        //Ожидаемый результат
        $$(".serp-item")
                .find(Condition.text(testData))
                .shouldBe(Condition.visible);
    }

    @CsvSource({
            "Selenide, is an open source library",
            "JUnit, Support JUnit"
    })
    @ParameterizedTest(name = "Проверка поиска в Яндексе по слову {0}, ожидаем результат {1}")
    void yaSearchComplexTest(String testData, String expected) {

        //Предусловия
        Selenide.open("https://ya.ru");
        //Шаги
        $("#text").setValue(testData);
        $("button[type='submit']").click();
        //Ожидаемый результат
        $$(".serp-item")
                .find(Condition.text(expected))
                .shouldBe(Condition.visible);
    }

    static Stream<Arguments> methodSourceExampleTest() {
        return Stream.of(
                Arguments.of("first str", List.of(12,23)),
                Arguments.of("second str", List.of(23,43))
        );
    }

    @MethodSource("methodSourceExampleTest")
    @ParameterizedTest
    void methodSourceExampleTest(String s, List<Integer> list) {
        System.out.println(s + "and list" + list);
    }

}
