package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SearchPage;

public class LoginTests extends ApplicationManager {
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

}
