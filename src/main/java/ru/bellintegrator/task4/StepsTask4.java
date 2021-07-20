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
    public void executeFind1(Page page)
    {
        Actions actions = new Actions(driver);
        clickOnElement("Кнопка Каталога", page);//Клик по кнопке каталога
        actions.moveToElement(page.getElement("Курсор на вкладку")).build().perform();
        clickOnElement("Вкладка Видеокарты", page);//Клик по кнопке Видеокарты
        AllureRuntime.captureScreenshot();
    }

    @Step("Поиск видеокарты")
    public void executeFind2(String text, Page page)
    {
        boolean flag=page.getElement("Поле поиска").getAttribute("value").equals("GTX 1050 ti");
        if(flag==true)
            clickOnElement("Кнопка сброса запроса", page);//Клик по кнопке сброса поискового запроса
        page.getElement("Поле поиска").clear();
        clickOnElement("Поле поиска", page);//Клик по полю поиска для активации поля
        page.getElement("Поле поиска").sendKeys(text + Keys.ENTER);//вставка в поле названия видеокарты
        clickOnElement("Кнопка сортировки", page);//Клик по кнопке сортировки
        AllureRuntime.captureScreenshot();
    }

    @Step("Вычисление неравности цен на видеокарты")
    public void executeFind3(Page page)
    {
        try {
                if (spanText.size() == 1)
                {
                String str2 = (page.getElement("Вторая цена")).getText();
                str2 = str2.replaceAll("\\s+", "");
                int i = Integer.parseInt(str2);
                spanText.add(i);
                Assertions.assertTrue(spanText.get(0) < spanText.get(1));
                return;
                }

                if (spanText.size() == 0)
                {
                String str1 = (page.getElement("Первая цена")).getText();
                str1 = str1.replaceAll("\\s+", "");
                int i = Integer.parseInt(str1);
                spanText.add(i);
                }
        }
        catch (NumberFormatException nfe)
        {
            System.out.println("NumberFormatException: " + nfe.getMessage());
        }
    }
}
