import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SimpleJUniteTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 5000;
    }

    @AfterAll
    static void afterAll() {
        closeWebDriver();
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");

        // Удаляем мешающие элементы
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        // Заполнение формы
        $("#firstName").setValue("Basil");
        $("#lastName").setValue("Pupkin");
        $("#userEmail").setValue("pupkin@basil.com");
        $$("#genterWrapper label").filterBy(text("Male")).first().click();
        $("#userNumber").setValue("0441234567");

        // Дата рождения
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("February");
        $(".react-datepicker__year-select").selectOption("1982");
        $(".react-datepicker__day--006:not(.react-datepicker__day--outside-month)").click();

        // Subjects
        $("#subjectsInput").setValue("Bio");
        $$(".subjects-auto-complete__option").findBy(text("Biology")).click();

        // Hobbies
        $$("#hobbiesWrapper label").filterBy(text("Sports")).first().click();
        $$("#hobbiesWrapper label").filterBy(text("Reading")).first().click();
        $$("#hobbiesWrapper label").filterBy(text("Music")).first().click();

        // Upload picture
        $("#uploadPicture").uploadFromClasspath("Foto 07.2024.jpg");

        // Address
        $("#currentAddress").setValue("Площадь Пушкина, пр-д Калатушкина, 6");

        // State и City
        $("#state").scrollTo().shouldBe(interactable).click();
        $$("div[class*='-option']").findBy(text("Haryana")).click();
        $("#city").scrollTo().shouldBe(interactable).click();
        $$("div[class*='-option']").findBy(text("Karnal")).click();

        // Submit
        $("#submit").scrollTo().click();

        // Проверки (раздельные для каждого поля)
        $(".modal-content").shouldBe(visible);

        //  Проверка по таблице
        $(".table-responsive")
                .$(byText("Student Name")).parent().shouldHave(text("Basil Pupkin"));
        $(".table-responsive")
                .$(byText("Student Email")).parent().shouldHave(text("pupkin@basil.com"));
        $(".table-responsive")
                .$(byText("Gender")).parent().shouldHave(text("Male"));
        $(".table-responsive")
                .$(byText("Mobile")).parent().shouldHave(text("0441234567"));
        $(".table-responsive")
                .$(byText("Date of Birth")).parent().shouldHave(text("06 February,1982"));
        $(".table-responsive")
                .$(byText("Subjects")).parent().shouldHave(text("Biology"));
        $(".table-responsive")
                .$(byText("Hobbies")).parent().shouldHave(text("Sports, Reading, Music"));
        $(".table-responsive")
                .$(byText("Picture")).parent().shouldHave(text("Foto 07.2024.jpg"));
        $(".table-responsive")
                .$(byText("Address")).parent().shouldHave(text("Площадь Пушкина, пр-д Калатушкина, 6"));
        $(".table-responsive")
                .$(byText("State and City")).parent().shouldHave(text("Haryana Karnal"));

        // Закрытие модального окна
        $("#closeLargeModal").click();
    }
}