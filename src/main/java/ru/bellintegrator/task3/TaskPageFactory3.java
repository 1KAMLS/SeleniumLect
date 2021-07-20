package ru.bellintegrator.task3;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import ru.bellintegrator.pages.FindPage;
import ru.bellintegrator.pages.Page;

import java.util.ArrayList;
import java.util.List;

public class TaskPageFactory3 implements Page, FindPage
{
    protected WebDriver driver;
    List<String> spanText= new ArrayList<>();
    List<Double> spanDouble=new ArrayList<>();

    public TaskPageFactory3(WebDriver driver) //КОНСТРУКТОР
    {
        this.driver = driver;
        isPageLoaded();//конструктор дожидается кликабельности кнопки
    }

    @FindBy(className = "gLFyf")
    WebElement searchField;//создаётся объект-вебэлемент, который при вызове его в методах класса будет автоматически
    // искать на странице элемент с указанным class

    @FindBy(xpath = "//a[@href='https://www.open.ru/']")
    WebElement resaultSearch;

    @FindAll(@FindBy(xpath = "//*[@class='main-page-exchange__row main-page-exchange__row--with-border']" +
            "//*[@class='main-page-exchange__rate']"))
    List<WebElement> resaulSearch;



    @Override
    public void clickElement()
    {
        resaultSearch.click();//клик по нему
    }

    @Override
    public void find(String findText)
    {
        searchField.click();//клик по полю для активации поля, чтобы вводить буквы
        searchField.sendKeys(findText + Keys.ENTER);//виртуальные нажатия клавиш
    }

    @Override
    public boolean getResults(int a, int b)
    {
        for(WebElement w : resaulSearch)
            spanText.add((w.getText()).replace(",","."));
        //извлечение из списка вебэлеметов текста и передача их в текстовый список с одновременной заменой запятых на точки

        for (String s: spanText)
            spanDouble.add(Double.parseDouble(s));//создание списка числовых значений из текстового списка

        return spanDouble.get(a)<spanDouble.get(b);//передача значений из списка по заданным аргументам
    }

    @Override
    public boolean isPageLoaded()
    {
        return false;
    }

}
