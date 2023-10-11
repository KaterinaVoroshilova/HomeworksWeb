import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class GetCardTest {
    @Test
    void shouldTest() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Екатерина Ворошилова");
        $("[data-test-id=phone] input").setValue("+79048631049");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
    @Test
    void shouldNotTestName() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Ekaterina");
        $("[data-test-id=phone] input").setValue("+79048631049");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name] span.input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotTestPhone() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Екатерина Ворошилова");
        $("[data-test-id=phone] input").setValue("+7904863104");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone] span.input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotTestAgreement() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Екатерина Ворошилова");
        $("[data-test-id=phone] input").setValue("+79048631049");
        $("button").click();
        $("[data-test-id=agreement]").shouldHave(cssClass("input_invalid"));
    }
}
