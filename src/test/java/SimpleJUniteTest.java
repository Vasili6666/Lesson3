import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SimpleJUniteTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
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
        $("#submit").scrollTo().click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        //$("#output #email").shouldHave(text("alex@egorov.com"));
        //$("#output #currentAddress").shouldHave(text("Some street 1"));
        //$("#output #permanentAddress").shouldHave(text("Another street 1"));
    }
}