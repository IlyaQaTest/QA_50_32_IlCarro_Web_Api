package pages;

import dto.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        setDriver(driver);
        // AjaxElementLocatorFactory будет ждать появления элементов до 10 секунд
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(id = "name")
    WebElement inputName;

    @FindBy(id = "lastName")
    WebElement lastName;

    @FindBy(id = "email")
    WebElement inputEmail;

    @FindBy(id = "password")
    WebElement inputPassword;

    // Сам чекбокс (скрытый input)
    @FindBy(id = "terms-of-use")
    WebElement checkBoxInput;

    // Метка чекбокса (видимый элемент)
    @FindBy(xpath = "//label[@for='terms-of-use']")
    WebElement checkBoxLabel;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYalla;

    @FindBy(xpath = "//h2[@class='message']")
    WebElement popUpSuccessfulLogin;

    @FindBy(xpath = "//button[@class='positive-button ng-star-inserted']")
    WebElement btnPUpOk;

    @FindBy(xpath = "//label[@for='terms-of-use']")
    WebElement checkBoxAgree;

    public void fillRegistrationForm(User user) {
        inputName.sendKeys(user.getFirstname());
        lastName.sendKeys(user.getLastname());
        inputEmail.sendKeys(user.getEmail());
        inputPassword.sendKeys(user.getPassword());
    }

    // Рекомендуемый способ: клик по Label (имитирует поведение пользователя)
    public void clickCheckBox() {
        checkBoxLabel.click();
    }
    // Резервный способ через JS: если Label перекрыт другими элементами
    public void clickCheckBoxJS() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", checkBoxInput);
    }

    public void clickBtnYalla() {
        btnYalla.click();
    }

    public boolean isLoggedInDisplayed() {
        // Метод isElementDisplayed должен быть в BasePage
        return isElementDisplayed(popUpSuccessfulLogin);
    }

    public void clickBtnPopUpOk() {
        btnPUpOk.click();
    }
    public void setCheckBoxAgree(boolean value) {
        if (checkBoxAgree.isSelected() != value)
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();",
                            checkBoxAgree);
    }
    public void fillRegistrationFormWithActions(User user){
        Actions actions = new  Actions(driver);
        actions.sendKeys(inputName, user.getFirstname()).perform();
        actions.sendKeys(lastName, user.getLastname()).perform();
        actions.sendKeys(inputEmail, user.getEmail()).perform();
        actions.sendKeys(inputPassword, user.getPassword()).perform();
    }
    public void clickCheckBoxWithActions(){
        Actions actions = new Actions(driver);
        actions.moveToElement(checkBoxAgree,1,1).click().perform();
    }
    public void submitFormWithActions(){
        Actions actions = new Actions(driver);
        actions.moveToElement(btnYalla).click().perform();
    }

}