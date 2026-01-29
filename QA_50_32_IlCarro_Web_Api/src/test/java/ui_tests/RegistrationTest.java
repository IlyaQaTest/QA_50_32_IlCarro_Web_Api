package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PopUpPage;
import pages.RegistrationPage;
import pages.SearchPage;

import static utils.UserFactory.*;

import java.util.Random;


public class RegistrationTest extends ApplicationManager {
    RegistrationPage registrationPage;

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
}