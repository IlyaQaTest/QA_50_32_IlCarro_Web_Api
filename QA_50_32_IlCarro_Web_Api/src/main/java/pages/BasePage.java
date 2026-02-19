package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.enums.HeaderMenuItem;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {
    protected static WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        PageFactory.initElements(driver, this);
    }

    public static void setDriver(WebDriver wd) {
        driver = wd;
    }

    @FindBy(xpath = "//div[@class='error']")
    List<WebElement> listErrors;

    public boolean isTextInErrorPrecent(String text) {
        if (listErrors == null || listErrors.isEmpty())
            return false;
        for (WebElement element : listErrors) {
            if (element.getText().contains(text))
                return true;
        }
        return false;
    }

    public void pause(int time) {
        try {
            Thread.sleep(time * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isElementDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public boolean isTextInElementPresentWait(WebElement element, String text) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public <T extends BasePage> T clickButtonHeader(HeaderMenuItem item) {
        WebElement button = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(item.getLocator())));
        button.click();
        switch (item) {
            case LOGIN -> {
                return (T) new LoginPage(driver);
            }
            case SIGN_UP -> {
                return (T) new RegistrationPage(driver);
            }
            case SEARCH -> {
                return (T) new SearchPage(driver);
            }
            case TERMS_OF_USE -> {
                return (T) new TermOfUsePage(driver);
            }
            case LET_THE_CAR_WORK -> {
                return (T) new LetTheCarWorkPage(driver);
            }
            case LOGOUT -> {
                return (T) new SearchPage(driver);
            }
            case DELETE_ACCOUNT -> {
                return (T) new SearchPage(driver);
            }
            default -> throw new IllegalArgumentException("Invalid parameter");

        }
    }

    public void clickWait(WebElement element, int time) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public boolean urlContains(String pratOfUrl, int time) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(time))
                    .until(ExpectedConditions.urlContains(pratOfUrl));
        }catch (TimeoutException e){
            e.printStackTrace();
            return false;
        }
    }
}
