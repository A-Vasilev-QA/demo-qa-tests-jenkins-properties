package guru.qa.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static guru.qa.pages.AutomationPracticeFormPage.*;
import static guru.qa.tests.TestData.*;
import static io.qameta.allure.Allure.step;

public class PracticeFormWithPageObjectsTests extends TestBase {

    @Test
    @DisplayName("Successful registration form fill")
    public void fillFormTest() {
        step("Generate test data", () -> {
            generateTestData();
        });
        step("Open registration page", () -> {
            openPage();
        });
        // Actually a step's needed here, but the returned values will become local, which will break the test
        typeFirstName(TestData.firstName);
        typeLastName(lastName);
        typeUserEmail(userEmail);
        String genderValue = selectGender(genderNumber);
        typeUserNumber(userNumber);
        String fullDate = selectDate(birthDate);
        String subjectName = inputSubject(subjectLetter);
        String hobbies = selectHobbies(isHobby);
        uploadPicture(fileName);
        typeCurrentAddress(currentAddress);
        String StateAndCity = selectStateAndCity(stateNumber, cityNumber);
        step("Submit the form", () -> {
            clickSubmit();
        });

        step("Check the form data", () -> {
            checkResultsValue("Student Name", firstName + " " + lastName);
            checkResultsValue("Student Email", userEmail);
            checkResultsValue("Gender", genderValue);
            checkResultsValue("Mobile", userNumber);
            checkResultsValue("Date of Birth", fullDate);
            checkResultsValue("Subjects", subjectName);
            checkResultsValue("Hobbies", hobbies);
            checkResultsValue("Picture", fileName);
            checkResultsValue("Address", currentAddress);
            checkResultsValue("State and City", StateAndCity);
        });
    }
}
