package guru.qa.tests;

import com.github.javafaker.Faker;

import java.util.Date;

import static java.lang.String.format;

public class TestData {
    public static Faker faker;
    public static String firstName,
            lastName,
            userEmail,
            userNumber,
            subjectLetter,
            fileName,
            currentAddress;
    public static int genderNumber,
            stateNumber,
            cityNumber;
    public static Date birthDate;
    public static boolean[] isHobby;

    public static void generateTestData() {
        faker = new Faker();
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        userEmail = format("%s%s@yopmail.com", firstName, lastName).toLowerCase();
        userNumber = faker.phoneNumber().subscriberNumber(10);
        subjectLetter = faker.regexify("[a-eg-hl-pr-v]");
        fileName = "1.jpg";
        currentAddress = faker.address().fullAddress();
        genderNumber = faker.number().numberBetween(1, 3);
        stateNumber = faker.number().numberBetween(0, 2);
        cityNumber = faker.number().numberBetween(0, 1);
        birthDate = faker.date().birthday();
        isHobby = new boolean[3];
        for (int i = 0; i < isHobby.length; i++) {
            isHobby[i] = faker.bool().bool();
        }
    }
}
