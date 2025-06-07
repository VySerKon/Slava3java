
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;


@Tag("SIMPLE")
public class SlavaTextBoxTest {

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 10000;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        Configuration.browserCapabilities = capabilities;
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        String browserName = System.getProperty("browserName", "chrome");
        String browserVersion = System.getProperty("browserVersion", "128.0");
        String windowSize = System.getProperty("windowSize", "1920x1080");

        Configuration.browser = browserName;
        Configuration.browserVersion = browserVersion;
        Configuration.browserSize = windowSize;

        String selenoid = System.getProperty("selenoid", "selenoid.autotests.cloud");
        String login = System.getProperty("login", "user1");
        String password = System.getProperty("password", "1234");
        Configuration.remote = String.format("https://%s:%s@%s/wd/hub", login, password, selenoid);
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @Test
    void fillFormTest() {
        step("Open", () -> {
                    open("/automation-practice-form");
                    $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
                    executeJavaScript("$('#fixedban').remove()");
                    executeJavaScript("$('footer').remove()");
                });
        step("Fill form", () -> {
                    $("#firstName").setValue("Kotik");
                    $("#lastName").setValue("Krasiviy");
                    $("#userEmail").setValue("kotik@kotik.catq");
                    $("#genterWrapper").$(byText("Male")).click();
                    $("#userNumber").setValue("9998887766");
                    $("#dateOfBirthInput").click();
                    $(".react-datepicker__month-select").selectOption("January");
                    $(".react-datepicker__year-select").selectOption("1999");
                    $(".react-datepicker__month").$(byText("26")).click();
                    $("#hobbiesWrapper").$(byText("Music")).click();
                    $("#subjectsInput").click();
                    $("#subjectsInput").setValue("Math").pressEnter();
                    $("#uploadPicture").uploadFromClasspath("TestBox1.JPG");
                    $("#currentAddress").setValue("address null");
                    $("#state").scrollIntoView(true).click();
                    //$("#state").click();
                    $("#react-select-3-input").setValue("NCR").pressEnter();
                    $("#city").click();
                    $("#react-select-4-input").setValue("Delhi").pressEnter();
                    $("#submit").click();
                });

        step("Show results", () -> {
            $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text("Kotik Krasiviy"));
            $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text("kotik@kotik.catq"));
            $(".table-responsive").$(byText("Gender")).parent().shouldHave(text("Male"));
            $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text("9998887766"));
            $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text("26 January,1999"));
            $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text("Math"));
            $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text("Music"));
            $(".table-responsive").$(byText("Picture")).parent().shouldHave(text("TestBox1.JPG"));
            $(".table-responsive").$(byText("Address")).parent().shouldHave(text("address null"));
            $(".table-responsive").$(byText("State and City")).parent().shouldHave(text("NCR Delhi"));
        });
    }

    @AfterEach
    void addAttachments() {
        helpers.Attach.screenshotAs("Last screenshot");
        helpers.Attach.pageSource();
        helpers.Attach.browserConsoleLogs();
        helpers.Attach.addVideo();
    }
}
