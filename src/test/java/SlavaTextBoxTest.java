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
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        Configuration.browserCapabilities = options;
        Configuration.timeout = 30000;
        Configuration.pageLoadTimeout = 60000;
    }

    @Test
    void fillFormTest() {
        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue("1");
        $("#lastName").setValue("2");
        $("#userEmail").setValue("3");
        $("label[for='gender-radio-1']").click();
        $("#userNumber").setValue("4");
        $("label[for='hobbies-checkbox-3']").click();
        $("#subjectsContainer").setValue("5");
        $("#submit").click();
        closeWindow();
    }
}


//$("label[for='hobbies-checkbox-3']").shouldHave(text("ru.selenide.org"));