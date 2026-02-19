package pages;

import dto.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(id = "email")
    WebElement inputEmail;
    @FindBy(id = "password")
    WebElement inputPassword;
    @FindBy(xpath = "//button[text()='Y’alla!']")
    WebElement btnYalla;
    @FindBy(xpath = "//h2[text()='Logged in success']")
    WebElement popUpSuccessfulLogin;

    public void typeLoginForm(User user) {
        inputEmail.sendKeys(user.getEmail());
        inputPassword.sendKeys(user.getPassword());

    }

    public void clickBtnYalla() {
        btnYalla.click();
    }

    public boolean isLoggedInDisplayed() {
        return isElementDisplayed(popUpSuccessfulLogin);
    }
}
//Это класс LoginPage, который завершает цепочку Page Object Model
// для страницы авторизации. Здесь объединяется использование DTO (User),
// Selenium WebElements и Ajax-локаторов.
//Вот подробный разбор того, как это работает «под капотом»:
//1. Поиск элементов (@FindBy) используется разные стратегии поиска
//id: Для email и password. Это самый быстрый и надежный способ поиска в Selenium.
//xpath: Для кнопки и всплывающего окна.
//Совет: Локатор //button[text()='Y’alla!'] очень чувствителен к тексту.
// Если разработчики изменят текст на кнопке (например, уберут восклицательный знак),
// тест упадет. Но для учебного проекта это допустимо.
//2. Использование AjaxElementLocatorFactory
//Зачем это здесь: Когда ты вводишь данные и нажимаешь "Y'alla!", серверу нужно время,
// чтобы ответить. Окно popUpSuccessfulLogin появится не мгновенно.
// Благодаря этому локатору, метод isLoggedInDisplayed() будет автоматически ждать появления окна
// до 10 секунд, прежде чем вернуть false или выкинуть ошибку.
//3. Методы бизнес-логики
//typeLoginForm(User user) Этот метод — отличный пример интеграции UI и данных.
//Вместо того чтобы передавать две строки (String email, String password),
//передается целый объект User.
//Это делает код чище: если завтра в форму добавят третье поле,
// нужно будет изменить только этот метод, а не все тесты, где он вызывается.
//clickBtnYalla() Простое действие клика. Разделение ввода данных и клика — хорошая практика,
// так как иногда в тестах нужно проверить,
// что кнопка заблокирована после ввода данных, прежде чем на неё нажать.
//isLoggedInDisplayed()
//Этот метод используется для Assertions (проверок) в тестах.
// Он обращается к родительскому методу isElementDisplayed из BasePage.
//Благодаря тому, что в BasePage у тебя есть метод clickButtonHeader,
// тест на логин может выглядеть очень лаконично:
//Маленькое уточнение:
//В методе isLoggedInDisplayed() может возникнуть исключение,
// если элемент вообще не появится в DOM.
// Чтобы сделать его "пуленепробиваемым", иногда полезно обернуть вызов в try-catch
// или использовать явное ожидание WebDriverWait, которое ты уже настроил в BasePage.
