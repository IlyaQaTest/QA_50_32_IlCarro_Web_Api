package ui_tests;

import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.SearchPage;
import utils.TestNGListener;

import java.time.LocalDate;
@Listeners(TestNGListener.class)

public class SearchCarTest extends ApplicationManager {
    SoftAssert softAssert = new SoftAssert();
    SearchPage searchPage;

    @BeforeMethod
    public void openSearchPage() {
        searchPage = new SearchPage(getDriver());
    }

    @Test
    public void searchCarPositiveTest() {
        String city = "Rehovot";
        LocalDate startDate = LocalDate.of(2026, 3, 1);
        LocalDate endDate = LocalDate.of(2026, 3, 22);
        searchPage.typeSearchForm(city, startDate, endDate);
        searchPage.clickBtnYalla();
        Assert.assertTrue(searchPage.urlContains("results", 5));
    }

    @Test
    public void searchCarPositiveTestWithCalendar() {
        String city = "Rehovot";
        LocalDate startDate = LocalDate.of(2026, 3, 1);
        LocalDate endDate = LocalDate.of(2026, 3, 22);
        searchPage.typeSearchFormWithCalendar(city, startDate, endDate);
        searchPage.clickBtnYalla();
        Assert.assertTrue(searchPage.urlContains("results", 5));
    }

    @Test(expectedExceptions = org.openqa.selenium.TimeoutException.class)
    public void searchCarNegativeTest_EmptyFieldCity() {
        String city = "";
        LocalDate startDate = LocalDate.of(2026, 3, 1);
        LocalDate endDate = LocalDate.of(2026, 3, 22);
        searchPage.typeSearchFormWOJS(city, startDate, endDate);
        searchPage.clickBtnYalla();
    }

    @Test
    public void searchCarNegativeTest_EmptyFieldCityError() {
        String city = "";
        LocalDate startDate = LocalDate.of(2026, 3, 1);
        LocalDate endDate = LocalDate.of(2026, 3, 22);
        searchPage.typeSearchFormWOJS(city, startDate, endDate);
        Assert.assertTrue(searchPage.isTextInErrorPrecent("City is required"));
    }

    @Test
    public void searchCarNegativeTest_WrongFirstData() {
        String city = "Rehovot";
        LocalDate startDate = LocalDate.of(2027, 3, 1);
        LocalDate endDate = LocalDate.of(2026, 3, 22);
        searchPage.typeSearchForm(city, startDate, endDate);
        searchPage.clickBtnYalla();
        Assert.assertTrue(searchPage.isTextInErrorPrecent("Second date must be after first date"));
    }

    @Test
    public void searchCarNegativeTest_LessCarThenDay() {
        String city = "Rehovot";
        LocalDate startDate = LocalDate.of(2027, 1, 31);
        LocalDate endDate = LocalDate.of(2027, 1, 31);
        searchPage.typeSearchForm(city, startDate, endDate);
        searchPage.clickBtnYalla();
        Assert.assertTrue(searchPage.isTextInErrorPrecent("You can't book car for less than a day"));
    }

    @Test
    public void searchCarNegativeTest_WrongEndDate() {
        String city = "Rehovot";
        LocalDate startDate = LocalDate.of(2027, 1, 31);
        LocalDate endDate = LocalDate.of(2026, 1, 31);
        searchPage.typeSearchForm(city, startDate, endDate);
        searchPage.clickBtnYalla();
        Assert.assertTrue(searchPage.isTextInErrorPrecent("You can't pick date before today"));
    }

    @Test
    public void searchCarNegativeTest_WrongMonthFirstData() {
        String city = "Rehovot";
        LocalDate startDate = LocalDate.of(2027, 1, 31);
        LocalDate endDate = LocalDate.of(2027, 3, 22);
        searchPage.typeSearchNegativeFormDayMonthYear(city, startDate, endDate);
        searchPage.clickBtnYalla();
        Assert.assertTrue(searchPage.isTextInErrorPrecent("Dates are required"));
    }

    @Test
    public void searchCarNegativeTest() {
        String city = "Rehovot";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();
        searchPage.typeSearchForm(city, startDate, endDate);
        searchPage.clickBtnYalla();
        softAssert.assertTrue(searchPage.isTextInErrorPrecent("City is required"), "validate massage");
        softAssert.assertTrue(searchPage.isTextInErrorPrecent("You can't pick date before today"), "validate massage");
    }
}
