package ui_tests;

import manager.AppManager;
import org.testng.annotations.Test;
import pages.SearchPage;

public class LoginTests extends AppManager {
    @Test
    public void loginPositiveTest(){
        SearchPage searchPage = new SearchPage(getDriver());
        searchPage.clickBtnLogin();
    }
}
