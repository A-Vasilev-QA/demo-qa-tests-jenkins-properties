package guru.qa.pages;

import com.codeborne.selenide.SelenideElement;
import guru.qa.pages.components.CalendarComponent;

import java.util.Date;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class AutomationPracticeFormPage {
    private static final String FORM_TITLE = "Student Registration Form",
            TABLE_TITLE = "Thanks for submitting the form";
    private static String genderLocator = "label[for='gender-radio-%d']",
            hobbyLocator = "label[for='hobbies-checkbox-%d']",
            stateLocator = "#react-select-3-option-%d",
            cityLocator = "#react-select-4-option-%d";
    private static SelenideElement
            formTitle = $(".practice-form-wrapper"),
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            userNumberInput = $("#userNumber"),
            subjectInput = $("#subjectsInput"),
            subjectTab = $("#react-select-2-option-0"),
            uploadFileInput = $("#uploadPicture"),
            currentAddressInput = $("#currentAddress"),
            stateInput = $("#state"),
            cityInput = $("#city"),
            submitButton = $("#submit"),
            tableHeader = $("#example-modal-sizes-title-lg");
    private static CalendarComponent calendar = new CalendarComponent();

    public static void openPage() {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        formTitle.shouldHave(text(FORM_TITLE));
    }

    public static void typeFirstName(String value) {
        firstNameInput.setValue(value);
    }

    public static void typeLastName(String value) {
        lastNameInput.setValue(value);
    }

    public static void typeUserEmail(String value) {
        userEmailInput.setValue(value);
    }

    public static String selectGender(int option) {
        $(format(genderLocator, option)).click();
        return $(format(genderLocator, option)).text();
    }

    public static void typeUserNumber(String value) {
        userNumberInput.setValue(value);
    }

    public static String selectDate(Date birthDate) {
        String fullDate;
        calendar.setDate(format("%td", birthDate), format("%tB", birthDate), format("%tY", birthDate));
        fullDate = format("%td %<tB,%<tY", birthDate);
        return fullDate;
    }

    public static String inputSubject(String value) {
        String subject;
        subjectInput.setValue(value);
        subject = subjectTab.text();
        subjectTab.click();
        return subject;
    }

    public static String selectHobbies(boolean[] isHobby) {
        if (isHobby.length != 3) {
            throw new RuntimeException("Quantity of hobbies in testdata is wrong");
        }
        String hobbies = "";

        for (int i = 0; i < isHobby.length; i++) {
            if (isHobby[i]) {
                $(format(hobbyLocator, i + 1)).click();
                hobbies += $(format(hobbyLocator, i + 1)).text() + ", ";
            }
        }
        if (hobbies.endsWith(", ")) {
            hobbies = hobbies.substring(0, hobbies.length() - 2);
        }
        return hobbies;
    }

    public static void uploadPicture(String fileName) {
        uploadFileInput.uploadFromClasspath("img/" + fileName);
    }

    public static void typeCurrentAddress(String value) {
        currentAddressInput.shouldBe(enabled).setValue(value);
    }

    public static String selectStateAndCity(int stateNumber, int cityNumber) {
        String StateAndCity;
        stateInput.click();
        StateAndCity = $(format(stateLocator, stateNumber)).text() + " ";
        $(format(stateLocator, stateNumber)).scrollIntoView(false).click();
        cityInput.click();
        StateAndCity += $(format(cityLocator, cityNumber)).text();
        $(format(cityLocator, cityNumber)).click();
        return StateAndCity;
    }

    public static void clickSubmit() {
        submitButton.click();
        tableHeader.shouldBe(visible).shouldHave(text(TABLE_TITLE));
    }

    public static void checkResultsValue(String key, String value) {
        if (value.equals("")) {
            $(".table-responsive").$(byText(key)).sibling(0).shouldBe(empty);
        } else {
            $(".table-responsive").$(byText(key)).sibling(0).shouldHave(exactText(value));
        }
    }

}
