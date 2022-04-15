package junit5.tests;

import com.codeborne.selenide.*;
import junit5.data.Currency;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Map;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.*;

public class JUnit5HomeWorkTests {

    @BeforeAll
    static void setUp() {
        //Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://cash.rbc.ru/cash";
    }

    @ValueSource(strings = {
            "USD",
            "RUR"
    })
    @ParameterizedTest(name = "Top line search from cash redirect for {0}")
    void topLineSearchMenuRedirectTest(String testData) {
        Selenide.open("/converter.html");
        $(".topline__search__menu").click();
        $(".topline__search__input").setValue(testData);
        $(".topline__search__button").click();

        webdriver().shouldHave(url("https://www.rbc.ru/search/?project=rbcnews&project=cash&query="+testData));
    }

    @CsvSource({
            "USD, 1 USD = 1 USD",
            "RUR, 1 RUR = 1 RUR",
            "EUR, 1 EUR = 1 EUR",
            "GBP, 1 GBP = 1 GBP"
    })
    @ParameterizedTest(name = "Currency rate for {0} = {0} equals 1")
    void currencyRateForSameCurrencyTest(String testData, String expected) {
//        Предусловия:
        Selenide.open("/converter.html");
//        Шаги:
        $$("[data-id=from] .calc__switcher__item").findBy(text(testData)).click();
        $$("[data-id=to] .calc__switcher__item").findBy(text(testData)).click();
//        Ожидаемый результат:
        $(".js-converter-rate-from").shouldHave(text(expected));
        $(".js-converter-rate-to").shouldHave(text(expected));
    }

    @EnumSource(Currency.class)
    @ParameterizedTest(name = "Button is active after click for {0}")
    void currencyButtonIsActiveTest(Currency testData) {
//        Предусловия:
        Selenide.open("/converter.html");
//        Шаги:
        $$("[data-id=from] .calc__switcher__item").findBy(text(testData.toString())).click();
//        Ожидаемый результат:
        $$("[data-id=from] .calc__switcher__item")
                .findBy(text(testData.toString()))
                .shouldBe(attribute("class","calc__switcher__item js-item active"));
    }

    static Stream<Arguments> methodSourceExampleTest() {
        return Stream.of(
                Arguments.of("RUR", Map.of("USD","0.0123","EUR","0.0114","GBP","0.0094","RUR","1")),
                Arguments.of("USD", Map.of("USD","1","EUR","0.9237","GBP","0.767","RUR","81.288")),
                Arguments.of("EUR", Map.of("USD","1.0826","EUR","1","GBP","0.8303","RUR","88.0024"))
        );
    }
/* так как курс валют динамично меняется, тест быстро перестает быть актуальным. в реальности он бесполезен, но для
* решения асбтрактной задачи лучше не придумал. */

    @MethodSource("methodSourceExampleTest")
    @ParameterizedTest(name = "Rates for {0}")
    void checkAllCurrenciesRateTest(String s, Map<String, String> map) {
//        Предусловия:
        Selenide.open("/converter.html");
//        Шаги:
        $$("[data-id=from] .calc__switcher__item").findBy(text(s)).click();
        $(".calc__input_box__input").clear();
        $(".calc__input_box__input").setValue("1");

        ElementsCollection elements = $$("[data-id=to] .calc__switcher__item:not([data-list])");
//        Ожидаемый результат:
        for (SelenideElement element : elements) {
            element.click();
            $(".js-converter-rate-from").shouldHave(text(map.get(element.getText())));
        }

    }

    @AfterEach
    void close() {
        Selenide.closeWebDriver();
    }

}
