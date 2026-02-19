package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class PopUpPage extends BasePage {
    public PopUpPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//mat-dialog-container//h2")
    WebElement message;

    public boolean isTextInPopUpMessagePresent(String text) {
        return isTextInElementPresentWait(message, text);
    }
}
//Этот класс PopUpPage — отличный пример того, как в автоматизации выделяют компоненты.
// Хотя это называется "Page", по сути это описание модального (всплывающего) окна,
// которое появляется поверх основной страницы.
//Вот разбор того, что здесь реализовано:
//1. Локатор для Material Design
//@FindBy(xpath = "//mat-dialog-container//h2"):
//Судя по тегу mat-dialog-container, твой проект использует библиотеку Angular Material.
//Это очень грамотный локатор. Ты ищешь заголовок h2 внутри контейнера диалогового окна.
// Это гарантирует, что ты проверяешь текст именно в поп-апе,
// а не случайный заголовок на заднем плане страницы.
//2. Динамическая проверка текста
//isTextInPopUpMessagePresent(String text):
//Вместо того чтобы создавать разные методы для каждого сообщения
// (например, isSuccessPresent, isErrorPresent), ты сделал универсальный метод.
//Ты передаешь ожидаемый текст (text) как аргумент.
// Это позволяет использовать один и тот же метод для проверки и успешного входа
// («Logged in success»), и ошибок («Wrong password»), и уведомлений.
//3. Использование Explicit Wait (Явных ожиданий)
//Метод вызывает isTextInElementPresentWait(message, text)
// из родительского класса BasePage.
//Почему это критично здесь: Всплывающие окна часто имеют анимацию появления (fade-in).
// Если попытаться прочитать текст в ту же миллисекунду,
// когда поп-ап только начал отрисовываться, тест может упасть.
// Твой метод будет терпеливо ждать (до 10 секунд),
// пока текст внутри элемента не совпадет с ожидаемым.

