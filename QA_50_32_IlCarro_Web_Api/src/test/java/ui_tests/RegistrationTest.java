package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import pages.SearchPage;

public class RegistrationTest extends AppManager {
    @Test
    public void registrationPositiveTest() {
        User user = User.builder().firstname("Ilya").lastname("Lapidus").email("1test123test@gmail.com")
                .password("Password123!").build();
        SearchPage searchPage = new SearchPage(getDriver());
        searchPage.clickBtnSingUp();
        RegistrationPage registrationPage = new RegistrationPage(getDriver());
        registrationPage.RegistrationForm(user);
        registrationPage.clickCheckBox();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(registrationPage.isLoggedInDisplayed());
        registrationPage.clickBtnPopUpOk();
    }
}
