package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.PopUpPage;
import pages.SearchPage;

public class LoginTests extends ApplicationManager {
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void loginPositiveTest() {
        User user = User.builder()
                .email("testmail@gmail.ru").password("Test#000").build();
        SearchPage searchPage = new SearchPage(getDriver());
        searchPage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(loginPage.isLoggedInDisplayed());

    }

    @Test
    public void loginPositiveTest_WithPopUpPage() {
        User user = User.builder()
                .email("testmail@gmail.ru").password("Test#000").build();
        SearchPage searchPage = new SearchPage(getDriver());
        searchPage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("Logged in success"));

    }

    @Test
    public void loginNegativeTest_WrongPassword_WOSpecSimbol() {
        User user = User.builder()
                .email("testmail@gmail.ru").password("Test#000").build();
        SearchPage searchPage = new SearchPage(getDriver());
        searchPage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("Login or Password incorrect"));

    }

    @Test
    public void loginNegativeTest_WrongEmail_Empty() {
        User user = User.builder()
                .email("testmailgmail.ru").password("").build();//password Test#000
        SearchPage searchPage = new SearchPage(getDriver());
        searchPage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        softAssert.assertTrue(loginPage.isTextInError("It'snot look like email")
                ,"validate field email");
        System.out.println("wrong test!!");
        softAssert.assertTrue(loginPage.isTextInError("Password is required")
        ,"validate field password");
        System.out.println("right text!!");
        softAssert.assertAll();
    }

}
