import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SimpleJUniteTest {

    @BeforeAll
    static void setupBrowser() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 5000;
    }

    @Test
    void fillFormTest() {
        openForm();
        fillPersonalInfo();
        setDateOfBirth(6, "February", 1982);
        fillSubjectsAndHobbies();
        uploadFile();
        fillAddress();
        selectLocation("Haryana", "Karnal");
        submitForm();
        verifyAndCloseModal();
    }

    private void openForm() {
        open("/automation-practice-form");
    }

    private void fillPersonalInfo() {
        $("#firstName").setValue("Basil");
        $("#lastName").setValue("Pupkin");
        $("#userEmail").setValue("pupkin@basil.com");
        selectGender("Male");
        $("#userNumber").setValue("0441234567");
    }

    private void selectGender(String gender) {
        $("label[for='gender-radio-1']").click();
    }

    private void setDateOfBirth(int day, String month, int year) {
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(String.valueOf(year));
        $(String.format(".react-datepicker__day--%03d:not(.react-datepicker__day--outside-month)", day))
                .shouldBe(visible)
                .click();
    }

    private void fillSubjectsAndHobbies() {
        $("#subjectsInput").setValue("Bio");
        selectSubject("Biology");

        selectHobby("Sports");
        selectHobby("Reading");
        selectHobby("Music");
    }

    private void selectSubject(String subject) {
        $$(".subjects-auto-complete__option")
                .findBy(text(subject))
                .click();
    }

    private void selectHobby(String hobby) {
        $x(String.format("//label[contains(.,'%s')]", hobby)).click();
    }

    private void uploadFile() {
        $("#uploadPicture").uploadFromClasspath("Foto 07.2024.jpg");
    }

    private void fillAddress() {
        $("#currentAddress").setValue("Площадь Пушкина, пр-д Калатушкина, 6");
    }

    private void selectLocation(String state, String city) {
        selectDropdownOption("#state", state);
        selectDropdownOption("#city", city);
    }

    private void selectDropdownOption(String dropdownLocator, String option) {
        $(dropdownLocator).click();
        $$("div[class*='-option']")
                .findBy(text(option))
                .shouldBe(visible)
                .click();
    }

    private void submitForm() {
        $("#submit").scrollTo().click();
    }

    private void verifyAndCloseModal() {
        $("#example-modal-sizes-title-lg")
                .shouldHave(text("Thanks for submitting the form"));

        adjustModalSize();
        $("#closeLargeModal")
                .scrollIntoView(true)
                .shouldBe(visible, enabled)
                .click();
    }

    private void adjustModalSize() {
        executeJavaScript(
                "document.querySelector('.modal-content').style.cssText='width:100%;height:auto;'"
        );
    }

    @AfterAll
    static void closeBrowser() {
        closeWebDriver();
    }
}



/*
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
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("February");
        $(".react-datepicker__year-select").selectOption("1982");
        $(".react-datepicker__day--006:not(.react-datepicker__day--outside-month)").click();
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
}*/