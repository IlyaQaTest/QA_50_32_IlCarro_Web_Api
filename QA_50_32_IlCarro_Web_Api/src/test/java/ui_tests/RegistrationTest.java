package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.PopUpPage;
import pages.RegistrationPage;
import pages.SearchPage;

import static utils.UserFactory.*;

import java.util.Random;


public class RegistrationTest extends ApplicationManager {
    RegistrationPage registrationPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToRegistrationPage() {
        new SearchPage(getDriver()).clickBtnSingUp();
        registrationPage = new RegistrationPage(getDriver());
    }

    @Test
    public void registrationPositiveTest() {
        int i = new Random().nextInt(1000);
        User user = User.builder()
                .firstname("UUU")
                .lastname("PPP")
                .email("qwerty" + i + "@gmail.com")
                .password("Qwerty453$")
                .build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxWithActions();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("You are logged in success"));
    }

    @Test
    public void registrationPositiveTest_WithFaker() {
        User user = positiveUser();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxWithActions();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("You are logged in success"));
    }

    @Test
    public void registrationNegativeTest_UserAlreadyExist() {
        User user = User.builder()
                .firstname("qwerty")
                .lastname("qwerty")
                .email("family@mail.ru")
                .password("Family123!")
                .build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.setCheckBoxAgreeTermsOfUse();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("User already exists"));
    }

    @Test
    public void registrationNegativeTest_WithSpaceInFirstName() {
        User user = User.builder()
                .firstname(" ")
                .lastname("qwerty")
                .email("family@mail.ru")
                .password("Family123!")
                .build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.setCheckBoxAgreeTermsOfUse();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("must not be blank"));
    }

    @Test
    public void registrationNegativeTest_WithAllEmptyFields() {
        User user = User.builder()
                .firstname("")
                .lastname("")
                .email("")
                .password("")
                .build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.setCheckBoxAgreeTermsOfUse();
        registrationPage.clickBtnYalla();
        softAssert.assertTrue(registrationPage.isTextInError("Name is required")
                ,"validate error message Name is required");
        softAssert.assertTrue(registrationPage.isTextInError("Last name is required")
                ,"validate error message Last Name is required");
        softAssert.assertTrue(registrationPage.isTextInError("Email is required")
                ,"validate error message Email is required");
        softAssert.assertTrue(registrationPage.isTextInError("Password is required")
                ,"validate error message Password is required");
        softAssert.assertAll();

    }
}