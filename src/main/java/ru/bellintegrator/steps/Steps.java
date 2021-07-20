package ru.bellintegrator.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ru.bellintegrator.common.exceptions.AllureRuntimeError;
import ru.bellintegrator.common.utils.AllureRuntime;
import ru.bellintegrator.pages.FindPage;

public class Steps //Этот класс содержит методы с аннотацией Step, которые создают для Allure отчеты о своём срабатывании
{
    WebDriver driver;

    public Steps(WebDriver driver)
    {
        this.driver = driver;
    }

    @Step("do something...")
    public void doSmhng()
    {
        AllureRuntime.printError("Я ничего не делаю");
    }

    @Step("Перейти по ссылке {url}")
    public void goToURL(String url)
    {
        driver.get(url);
    }

    @Step("Произвести поиск по тексту {findText}")
    public void executeFind(String findText, FindPage page)
    {
        page.find(findText);
        AllureRuntime.print("Поиск прошёл успешно. Найдено x элементов" );
        AllureRuntime.captureScreenshot();
        AllureRuntime.printTextAttachment(page.getClass().getSimpleName());
        System.out.println("//Коммент для коммита");
    }

    @Step("Проверка равности строк '{actual}' и '{expected}'")
    public void isStringsEquals(String actual, String expected)
    {
            if (!actual.equals(expected))
            {
                throw new AllureRuntimeError("Ошибка! Строки 1 и 2 не равны");
            }
    }
}
