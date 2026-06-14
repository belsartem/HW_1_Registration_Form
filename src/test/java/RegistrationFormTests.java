import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormTests extends TestBase {

    @Test
    void allFieldsPopulatedTest() {

        open("/automation-practice-form");
        executeJavaScript("""
            document.getElementById('fixedban')?.remove();
            document.querySelector('footer')?.remove();
            """);

        $("#firstName").setValue("Test Name");
        $("#lastName").setValue("Test Last Name");
        $("#userEmail").setValue("test@test.com");
        $("#genterWrapper").$$("label").findBy(text("Male")).click();
        $("#userNumber").setValue("0123456789");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("October");
        $(".react-datepicker__year-select").selectOption("1995");
        $(".react-datepicker__year-select").selectOption("1995");
        $(".react-datepicker__day--001:not(.react-datepicker__day--outside-month)").click();
        $("#subjectsInput").setValue("computer").pressEnter();
        $("#hobbiesWrapper").$$("label").findBy(text("Sports")).click();
        $("#hobbiesWrapper").$$("label").findBy(text("Music")).click();
        $("#uploadPicture").uploadFromClasspath("images/scottish_cat.png");
        $("#uploadPicture").shouldHave(value("cat.png"));
        $("#currentAddress").setValue("1st street");
        $("#state").click();
        $$("[id^=react-select-3-option]").findBy(text("Uttar Pradesh")).click();
        $("[id=city]").click();
        $$("[id^=react-select-4-option]").findBy(text("Merrut")).click();

        $("#submit").click();

        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").$(byText("Student Name")).closest("tr").shouldHave(text("Test Name Test Last Name"));
        $(".table-responsive").$(byText("Student Email")).closest("tr").shouldHave(text("test@test.com"));
        $(".table-responsive").$(byText("Gender")).closest("tr").shouldHave(text("Male"));
        $(".table-responsive").$(byText("Mobile")).closest("tr").shouldHave(text("0123456789"));
        $(".table-responsive").$(byText("Date of Birth")).closest("tr").shouldHave(text("01 October,1995"));
        $(".table-responsive").$(byText("Subjects")).closest("tr").shouldHave(text("Computer Science"));
        $(".table-responsive").$(byText("Hobbies")).closest("tr").shouldHave(text("Sports, Music"));
        $(".table-responsive").$(byText("Picture")).closest("tr").shouldHave(text("scottish_cat.png"));
        $(".table-responsive").$(byText("Address")).closest("tr").shouldHave(text("1st street"));
        $(".table-responsive").$(byText("State and City")).closest("tr").shouldHave(text("Uttar Pradesh Merrut"));
    }

    @Test
    public void onlyRequiredFieldsTest(){

        open("/automation-practice-form");
        executeJavaScript("""
            document.getElementById('fixedban')?.remove();
            document.querySelector('footer')?.remove();
            """);

        $("#firstName").setValue("Test Name");
        $("#lastName").setValue("Test Last Name");
        $("#genterWrapper").$$("label").findBy(text("Male")).click();
        $("#userNumber").setValue("0123456789");

        $("#submit").click();

        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").$(byText("Student Name")).closest("tr").shouldHave(text("Test Name Test Last Name"));
        $(".table-responsive").$(byText("Gender")).closest("tr").shouldHave(text("Male"));
        $(".table-responsive").$(byText("Mobile")).closest("tr").shouldHave(text("0123456789"));
    }

    @Test
    void negativeEmptyFormTest() {
        open("/automation-practice-form");
        executeJavaScript("""
            document.getElementById('fixedban')?.remove();
            document.querySelector('footer')?.remove();
            """);

        $("#submit").click();

        $(".modal-body").shouldNot(exist);
        $("#firstName").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("#lastName").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("#userNumber").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    public void negativeNoFirstNameTest() {
        open("/automation-practice-form");
        executeJavaScript("""
            document.getElementById('fixedban')?.remove();
            document.querySelector('footer')?.remove();
            """);

        $("#lastName").setValue("Test Last Name");
        $("#genterWrapper").$$("label").findBy(text("Male")).click();
        $("#userNumber").setValue("0123456789");

        $("#submit").click();

        $(".modal-body").shouldNot(exist);
        $("#firstName").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    public void negativeNoLastNameTest() {
        open("/automation-practice-form");
        executeJavaScript("""
            document.getElementById('fixedban')?.remove();
            document.querySelector('footer')?.remove();
            """);

        $("#firstName").setValue("Test Name");
        $("#genterWrapper").$$("label").findBy(text("Male")).click();
        $("#userNumber").setValue("0123456789");

        $("#submit").click();

        $(".modal-body").shouldNot(exist);
        $("#lastName").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }
}
