package ru.bellintegrator.task3;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ru.bellintegrator.common.utils.AllureRuntime;
import ru.bellintegrator.pages.FindPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StepsTask3
{
    WebDriver driver;

    public StepsTask3(WebDriver driver)
    {
        this.driver = driver;
    }

    @Step("Перейти по ссылке {url}")
    public void goToURL(String url)
    {
        driver.get(url);
    }

    @Step("Вставка названия банка и клик поиска{findword}")
    public void findWord(String findWord, FindPage page)
    {
        page.find(findWord);
        AllureRuntime.print("Поиск прошёл успешно. Найдено x элементов" );
        AllureRuntime.captureScreenshot();
        AllureRuntime.printTextAttachment(page.getClass().getSimpleName());
    }
    @Step("Поиск ссылки https://www.open.ru и переход по ней")
    public void findClick(FindPage page)
    {
        page.clickElement();
        AllureRuntime.print("Поиск прошёл успешно. Найдено x элементов" );
        AllureRuntime.captureScreenshot();
        AllureRuntime.printTextAttachment(page.getClass().getSimpleName());
    }

    @Step("Сравнение цен покупки и продажи по заданной влюте {currency}")
    public void comparisonPrice(FindPage page, String currency)
    {
        page.getResults(currency);
    }
}
