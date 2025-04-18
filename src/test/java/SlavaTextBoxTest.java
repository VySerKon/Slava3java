import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;


public class SlavaTextBoxTest {

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 30000; //без задержек тест падает по таймауту, браузер долго открывается
        Configuration.pageLoadTimeout = 60000;
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("Kotik");
        $("#lastName").setValue("Krasiviy");
        $("#userEmail").setValue("kotik@kotik.catq");
        $("label[for='gender-radio-1']").click();
        $("#userNumber").setValue("9998887766");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("January");
        $(".react-datepicker__year-select").selectOption("1999");
        $(".react-datepicker__month .react-datepicker__week:nth-child(5)").$$("div").findBy(text("26")).click();
        $("label[for='hobbies-checkbox-3']").click();
        $("#subjectsInput").click();
        $("#subjectsInput").setValue("Math");
        $(".subjects-auto-complete__menu").$$("div").findBy(text("Math")).click();
        $("#uploadPicture").uploadFromClasspath("TestBox1.JPG");
        $("#currentAddress-wrapper #currentAddress").setValue("address null");
        $("#state").click();
        $("#react-select-3-input").setValue("NCR").pressEnter();
        $("#city").click();
        $("#react-select-4-input").setValue("Delhi").pressEnter();
        $("#submit").click();
    }
    @AfterEach
    void afterEach() {
        Selenide.closeWebDriver();
    }
}


//css-2b097c-container
//css-2613qy-menu


//id="dateOfBirthInput"
//react-datepicker__month-select
//react-datepicker__year-select
//react-datepicker__week
//$("label[for='hobbies-checkbox-3']").shouldHave(text("ru.selenide.org"));

//ChromeOptions options = new ChromeOptions();
//        options.addArguments("--disable-gpu");
//        options.addArguments("--no-sandbox");
//        options.addArguments("--disable-dev-shm-usage");
//Configuration.browserCapabilities = options;

//$("#subjectsInput").click();
//$("#subjectsInput").setValue("Math");
//$(".subjects-auto-complete__menu").$$("div").findBy(text("Math")).click();

//$("#subjectsInput").click();
//$("#subjectsInput").setValue("Math");
//$(".subjects-auto-complete__menu").$("div=Math").click();

//$(".react-datepicker__month-select").$$("option").findBy(text("January")).click();
//$(".react-datepicker__year-select").$$("option").findBy(text("1999")).click();
//$(".react-datepicker__month .react-datepicker__week:nth-child(5)").$$("div").findBy(text("26")).click();
