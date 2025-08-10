import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SimpleJUniteTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        //Configuration.holdBrowserOpen = true;
        Configuration.timeout = 5000; // default 4000
    }

    @AfterAll
    static void afterAll() {
        closeWebDriver(); // Закрывает браузер и очищает WebDriver
    }


    @Test
    void fillFormTest() {


        open("/automation-practice-form");
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

// State и City с прокруткой и ожиданием
        $("#state").scrollTo().shouldBe(interactable).click();
        $$("div[class*='-option']").findBy(text("Haryana")).click();

        $("#city").scrollTo().shouldBe(interactable).click();
        $$("div[class*='-option']").findBy(text("Karnal")).click();

// Submit
        $("#submit").scrollTo().click();


        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        executeJavaScript("document.querySelector('.modal-content').style.width='100%';");
        executeJavaScript("document.querySelector('.modal-content').style.height='auto';");
        $("#closeLargeModal").scrollIntoView(true).click();
/*2) Я бы советовал переделать выбор пола и хобби, чтобы избавиться от использования селекторов
 с порядковыми номерами. В дальнейшем при развитии проекта с текущим вариантом будет сложнее,
  ну и в целом к порядковым номерам стоит привязываться только в том случае,
   если другого способа нет, т.к. порядок элементов на странице может поменяться
    в любой момент, и тесты упадут. Лучше сделать поиск по тексту в конкретном месте страницы,
     в данном случае в #genterWrapper и в #hobbiesWrapper  */
    }
}
