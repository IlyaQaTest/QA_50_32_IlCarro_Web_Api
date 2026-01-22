package pages;

import dto.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage extends BasePage{
    public RegistrationPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }
    @FindBy(xpath = "//input[@id='name']")
    WebElement inputName;
    @FindBy(xpath = "//input[@id='lastName']")
    WebElement lastName;
    @FindBy(xpath = "//input[@id='email']")
    WebElement inputEmail;
    @FindBy(xpath = "//input[@id='password']")
    WebElement inputPassword;
    @FindBy(xpath = "//input[@id='terms-of-use']")
    WebElement checkBox;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYalla;
    @FindBy(xpath = "//h2[contains(text(),'You are logged in success')]") // Замените текст на тот, что на сайте
    WebElement popUpSuccessfulLogin;
    @FindBy(xpath = "//button[@class='positive-button ng-star-inserted']")
    WebElement btnPUpOk;// название кнопки получилось веселое

    public void RegistrationForm(User user){
        inputName.sendKeys(user.getFirstname());
        lastName.sendKeys(user.getLastname());
        inputEmail.sendKeys(user.getEmail());
        inputPassword.sendKeys(user.getPassword());
    }

    public void clickCheckBox() {
        // 1. Ждем, пока чекбокс появится в DOM (но он может быть невидимым)
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='terms-of-use']")));

        // 2. Кликаем через JavaScript, так как стандартный click() часто не срабатывает
        // на элементах типа checkbox из-за перекрытия другими слоями
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", checkBox);
    }

    public void clickBtnYalla(){
        btnYalla.click();
    }
    public boolean isLoggedInDisplayed(){
        return isElementDisplayed(popUpSuccessfulLogin);
    }
    public void clickBtnPopUpOk(){
        btnPUpOk.click();
    }

}
