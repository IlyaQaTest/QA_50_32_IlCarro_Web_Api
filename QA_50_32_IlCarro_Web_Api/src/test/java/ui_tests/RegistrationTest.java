package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.RegistrationPage;
import pages.SearchPage;

import java.util.Random;

public class RegistrationTest extends ApplicationManager {
    User registredUser;
    LoginPage loginPage;
    // Выносим переменные на уровень класса, чтобы они были доступны во всех методах
    RegistrationPage registrationPage;
    SearchPage searchPage;

    @BeforeMethod
    public void setupSteps() {
        searchPage = new SearchPage(getDriver());
        registrationPage = new RegistrationPage(getDriver());
        // Логика перехода: кликаем SignUp перед каждым тестом регистрации
        searchPage.clickBtnSingUp();
    }

    @Test
    public void registrationPositiveTest() {
        int i = new Random().nextInt(10000);
        User user = User.builder()
                .firstname("Ilya")
                .lastname("Lapidus")
                .email("test_" + i + "@gmail.com") // Уникальный email
                .password("Password123!")
                .build();
        registrationPage.fillRegistrationForm(user); // Используем наш метод из POM
        //registrationPage.clickCheckBoxWithActions();
        registrationPage.setCheckBoxAgree(true);
        //registrationPage.clickCheckBoxJS();        // Используем надежный клик через JS
        registrationPage.clickBtnYalla();
        Assert.assertTrue(registrationPage.isLoggedInDisplayed(), "Сообщение об успехе не отобразилось!");
        registrationPage.clickBtnPopUpOk();
    }

    @Test
    public void registerPositiveTest() {
        User user = User.builder()
                .firstname("first")
                .lastname("last")
                .email("test" + new Random().nextInt(1000) + "@mail.com")
                .password("Password777$")
                .build();
        searchPage.clickBtnSingUp();
        registrationPage.fillRegistrationFormWithActions(user);
        registrationPage.clickCheckBoxWithActions();
        registrationPage.submitFormWithActions();
        Assert.assertTrue(registrationPage.isLoggedInDisplayed());
        registrationPage.clickBtnPopUpOk();
        searchPage.clickBtnLogout();
    }

}
