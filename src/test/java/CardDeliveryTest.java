import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

import ru.netology.UserModel;

class CardDeliveryTest {
    private LocalDate currentDate;
    private FakeValuesService fakeValuesService = new FakeValuesService(new Locale("ru"), new RandomService());
    private UserModel userModel;
    private int plusDays;

    @BeforeEach
    void setUp () {
        open ("http://localhost:9999");
        userModel = GenerationUser.Registration.generateByDelivery("ru");
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldRegisterDeliveryTest() {
        $("[placeholder=Город]").setValue(userModel.getCityPreInput());
        $("span.menu-item__control").click();
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE) );
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        $("[placeholder='Дата встречи']").setValue(date);
        $("[name=name]").setValue(userModel.getUserFullName());
        $$("[type='tel']").last().setValue(userModel.getUserPhone());
        $("span.checkbox__box").click();
        $$("button").find(exactText("Запланировать")).click();
        $$("button").find(exactText("Запланировать")).click();
        $$("button").find(exactText("Перепланировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 15000);
    }
}