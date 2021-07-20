package ru.bellintegrator.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import ru.bellintegrator.common.utils.PageUtils;
import ru.bellintegrator.driver.WebDriverManager;

import java.util.List;

public class BellPageFactory implements Page, FindPage

{
    protected WebDriver driver;

    public BellPageFactory() //КОНСТРУКТОР
    {
        initPage();//работа метода initPage интерфейса Page, проверяющего инициализацию вебдрайвера
        driver = WebDriverManager.getCurrentDriver();
        isPageLoaded();//конструктор дожидается кликабельности кнопки
    }
    @FindBy(id = "input-search")//аннотированный поиск: известен id вебэлемента
    WebElement searchField;//создаётся объект-вебэлемент, который при вызове его в методах класса будет автоматически
    // искать на странице элемент с указанным id

    @FindBy(id = "button-search")//аннотированный поиск: известен id вебэлемента
    WebElement searchButton;//создаётся объект-вебэлемент, который при вызове его в методах класса будет автоматически
    // искать на странице элемент с указанным id

    @FindAll(@FindBy(xpath = "//*[@class=\"product-layout product-list col-xs-12\"]//*[@class=\"short__desc\"]"))
    //@FindBy(how= How.XPATH, using = "//*[@class=\"product-layout product-list col-xs-12\"]")
    //Ищутся элементы, ибо FindAll, по xpath. И укладываются в список вепбэлементов. Далее в методе достаточно использовать
    //этот список-объект при его вызове.
    List<WebElement> allElements;


    @Override//метод интерфейса Page
    public boolean isPageLoaded()
    {
        return PageUtils.isClickable(searchButton);//через класс PageUtils статисеский метод isClickable
        // дожидается кликабельности searchButton
    }


    @Override
    public void clickElement() {

    }

    @Override
    public boolean getResults(int a, int b) {
        return false;
    }

    @Override//метод интерфейса FindPage
    @Step("Произвести запрос {strokFind}")//Используется аннотация Step.
    // В параметр "strokFind" аллюр-отчета будет записана переданная в метод find переменная
    public void find (String strokFind)
    {
        searchField.click();
        searchField.sendKeys(strokFind);
        searchButton.click();
    }

    public List<WebElement> getAllElements()
    {
        return allElements;
    }
}
