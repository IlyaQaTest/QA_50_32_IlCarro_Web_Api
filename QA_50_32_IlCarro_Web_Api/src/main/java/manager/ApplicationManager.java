package manager;
//Этот класс — сердце твоего тестового фреймворка, отвечающее за жизненный цикл браузера
// и логирование. В паттерне проектирования тестов это часто называют Base Test или Manager,
// который берет на себя всю «грязную работу» по настройке окружения.

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.LocalDate;
import java.time.LocalTime;

public class ApplicationManager {
    public final static Logger logger =
            LoggerFactory.getLogger(ApplicationManager.class);
    private WebDriver driver; //Инкапсулированная переменная для управления браузером.

    public WebDriver getDriver() {
        return driver;//Публичный метод, который позволяет другим классам (например, Page Objects)
        // получить доступ к текущему окну браузера, не меняя его настройки напрямую.
    }

    // Аннотации TestNG (Жизненный цикл)
//Это механизмы, которые управляют запуском браузера до и после каждого теста:
    @BeforeMethod //Выполняется перед каждым тестовым методом.
    public void setup() { // библиотекa, SLF4J с LoggerFactory
        logger.info("Start testing " + LocalDate.now() + " : " + LocalTime.now());//logger.info(...): Записывает в консоль (или файл) точное время начала и окончания теста.
        driver = new ChromeDriver(); //Инициализирует и открывает новое окно Chrome
        driver.manage().window().maximize(); // разворачивает окно на весь экран, чтобы элементы не перекрывались и были кликабельны.
    }
    // В setup и tearDown добавлены вывод даты (LocalDate) и времени (LocalTime),
    // что помогает при анализе отчетов: сразу видно, когда упал тест.

    @AfterMethod(enabled = false) // Выполняется после каждого теста.
    public void tearDown() {
        logger.info("Stop testing " + LocalDate.now() + " : " + LocalTime.now());
        if (driver != null)
            driver.quit(); //полностью закрывает браузер и завершает процесс драйвера, чтобы не забивать оперативную память.
    }
    //Структурный итог
    //Этот класс делает твои тесты "чистыми":
    //Не нужно в каждом тесте писать код открытия браузера.
    //Гарантируется, что каждый тест начинается с "чистого листа" (нового окна).
    //Ведется история запусков через логи.

}
