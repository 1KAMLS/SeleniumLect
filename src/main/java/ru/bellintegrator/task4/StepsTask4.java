package ru.bellintegrator.task4;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import ru.bellintegrator.common.utils.AllureRuntime;
import ru.bellintegrator.pages.Page;

import java.util.ArrayList;
import java.util.List;

public class StepsTask4
{
    WebDriver driver;
    List<Integer> spanText= new ArrayList<>();

    public StepsTask4(WebDriver driver)
    {
        this.driver = driver;
    }

    @Step("Перейти по ссылке {url}")
    public void goToURL(String url)
    {
        driver.get(url);
    }

    @Step("Кликнуть на элемент {elementName}")
    public void clickOnElement(String elementName, Page page)
    {
        page.getElement(elementName).click();
    }

    @Step("Переход на вкладку Видеокарты")
    public void hoveringTheCursor(Page page)
    {
        Actions actions = new Actions(driver);
        actions.moveToElement(page.getElement("Курсор на вкладку")).build().perform();
        AllureRuntime.captureScreenshot();
    }

    @Step("Очистка результатов поиска")
    public void resetResult(Page page)
    {
        boolean flag=page.getElement("Поле поиска").getAttribute("value").equals("GTX 1050 ti");
        if(flag==true)
            clickOnElement("Кнопка сброса запроса", page);//Клик по кнопке сброса поискового запроса
    }

    @Step("Поиск видеокарты")
    public void mapSearch(String text, Page page)
    {
        clickOnElement("Поле поиска", page);//Клик по полю поиска для активации поля
        page.getElement("Поле поиска").sendKeys(text + Keys.ENTER);//вставка в поле названия видеокарты
        AllureRuntime.captureScreenshot();
    }

    @Step("Сортировка по цене")
    public void sortingByPrice(Page page)
    {
        clickOnElement("Кнопка сортировки", page);//Клик по кнопке сортировки
    }

    @Step("Извлечение цены видеокарты")
    public int extractingThePrice(String card, Page page)
    {
        String str2 = (page.getElement(card)).getText();
        str2 = str2.replaceAll("\\s+", "");
        int price = Integer.parseInt(str2);
        return price;
    }
}
