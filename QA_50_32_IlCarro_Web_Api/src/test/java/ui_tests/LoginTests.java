package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.SearchPage;
import pages.LoginPage;
import pages.PopUpPage;
import pages.SearchPage;
import utils.RetryAnalyser;

import static utils.PropertiesReader.*;

public class LoginTests extends ApplicationManager {
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void loginPositiveTest() {
//        User user = User.builder()
//                .email("family@mail.ru")
//                .password("Family123!")
//                .build();
        User user = User.builder()
                .email(getProperty("base.properties","login")).
                password(getProperty("base.properties","password")).
                build();

        SearchPage homePage = new SearchPage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(loginPage.isLoggedInDisplayed());
    }

    @Test(retryAnalyzer = RetryAnalyser.class)
    public void loginPositiveTest_WithPopUpPage() {
        User user = User.builder()
                .email(getProperty("base.properties","login"))
                .password(getProperty("base.properties","password"))
                .build();
        SearchPage homePage = new SearchPage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("Logged in success"));
    }

    @Test
    public void loginNegativeTest_WrongPassword_WOSpecSymbol() {
        User user = User.builder()
                .email(getProperty("pos_email_neg_pass.properties","login"))
                .password(getProperty("pos_email_neg_pass.properties" ,"password"))
                .build();
        SearchPage homePage = new SearchPage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("Login or Password incorrect"));
    }

    @Test
    public void loginNegativeTest_WrongEmail_Empty() {
        User user = User.builder()
                .email("")
                .password("Family123!")
                .build();
        SearchPage homePage = new SearchPage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        softAssert.assertTrue(loginPage.isTextInError
                ("It'snot look like email"), "validate field email");
        System.out.println("wrong text!!");
        softAssert.assertTrue(loginPage.isTextInError
                ("Password is required"), "validate field password");
        System.out.println("right text!!");
        softAssert.assertAll();
    }
}