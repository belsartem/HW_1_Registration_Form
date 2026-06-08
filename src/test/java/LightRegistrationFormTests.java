import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class LightRegistrationFormTests extends TestBase {

    @Test
    void allFieldsPopulatedTest() {

        open("/text-box");
        executeJavaScript("""
            document.getElementById('fixedban')?.remove();
            document.querySelector('footer')?.remove();
            """);

        $("#userName").setValue("Test Name");
        $("#userEmail").setValue("test@test.com");
        $("#currentAddress").setValue("1st street");
        $("#permanentAddress").setValue("2nd street");

        $("#submit").click();

        $("#output #name").shouldHave(text("Test Name"));
        $("#output #email").shouldHave(text("test@test.com"));
        $("#output #currentAddress").shouldHave(text("1st street"));
        $("#output #permanentAddress").shouldHave(text("2nd street"));
    }

    @Test
    public void negativeInvalidEmailTest() {

        open("/text-box");
        executeJavaScript("""
            document.getElementById('fixedban')?.remove();
            document.querySelector('footer')?.remove();
            """);

        $("#userName").setValue("Test Name");
        $("#userEmail").setValue("test");

        $("#submit").click();

        $("#userEmail").shouldHave(cssClass("field-error"));
    }
}
