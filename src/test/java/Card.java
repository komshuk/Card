import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Card {
    private String generateData(int addDays) {
        return LocalDate.now().plusDays(9).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    public void orderFormTest() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");
        String currentDate = generateData(9);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(currentDate);
        $("[data-test-id='name'] input").setValue("Илья");
        $("[data-test-id='phone'] input").setValue("+79846256511");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + currentDate));
    }
}
