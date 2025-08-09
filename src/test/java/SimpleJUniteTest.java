import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


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
            $("label[for='gender-radio-1']").click();
            $("#userNumber").setValue("0441234567");
            $("#subjectsInput").setValue("Bio");
            $$(".subjects-auto-complete__option").findBy(Condition.text("Biology")).click();
            $("label[for='hobbies-checkbox-1']").click();
            $("label[for='hobbies-checkbox-2']").click();
            $("label[for='hobbies-checkbox-3']").click();
            $("#uploadPicture").uploadFromClasspath("Foto 07.2024.jpg");
            $("#currentAddress").setValue("Площадь Пушкина, пр-д Калатушкина, 6");
            $("#state").click();
            $$("div[class*='-option']").findBy(text("Haryana")).click();
            $("#city").click();
            $$("div[class*='-option']").findBy(text("Karnal")).click();

            $("#submit").scrollTo().click();



        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        executeJavaScript("document.querySelector('.modal-content').style.width='100%';");
        executeJavaScript("document.querySelector('.modal-content').style.height='auto';");
        $("#closeLargeModal").scrollIntoView(true).click();

    }
}