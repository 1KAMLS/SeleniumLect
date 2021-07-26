package ru.bellintegrator.task4;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.bellintegrator.pages.FieldName;
import ru.bellintegrator.pages.FindPage;
import ru.bellintegrator.pages.Page;
import org.openqa.selenium.interactions.Actions;

public class TaskPageFactory4 implements Page, FindPage
{
    protected WebDriver driver;

    public TaskPageFactory4(WebDriver driver) //КОНСТРУКТОР
    {
        this.driver = driver;
        isPageLoaded();//конструктор дожидается кликабельности кнопки
    }

    @FieldName("Кнопка Каталога")
    @FindBy(xpath = "//div[@class='_10dWCjaZug']")
    WebElement resaultSearchKat;//КАТАЛОГ

    @FieldName("Курсор на вкладку")
    @FindBy(xpath = "//img[@src='//avatars.mds.yandex.net/get-marketcms/879900/img-265ee528-3e72-4769-91ab-ae15cf38da8e.svg/svg']")
    WebElement resaultSearchKomp;//Вкладка КОМПЬЮТЕРЫ

    @FieldName("Вкладка Видеокарты")
    @FindBy(xpath = "//a[@href='/catalog--videokarty/55314/list?hid=91031']")
    WebElement resaultSearchVid;//Вкладка ВИДЕОКАРТЫ

    @FieldName("Поле поиска")
    @FindBy(id = "header-search")//аннотированный поиск: известен id вебэлемента
    WebElement searchField;//создаётся объект-вебэлемент, который при вызове его в методах класса будет автоматически
    // искать на странице элемент с указанным id

    @FieldName("Кнопка сортировки")
    @FindBy(xpath = "//button[@class='_2zH77vazcW _3tIaiy1WMf']")
    WebElement resaultSearchSORT;//Кнопка сортировки

    @FieldName("GTX 1050 ti")
    @FindBy(xpath =  "//div[@class='_1OAvzJPfIW']//*[contains(@title, 'GTX 1050 Ti')]/parent::h3/parent::div/parent::div/following-sibling::div//span/span")
    WebElement resaulSearch1;

    @FieldName("RTX 3070")
    @FindBy(xpath =  "//div[@class='_35AHlgIzSZ']//*[contains(@title, 'RTX 3070')]/parent::a/parent::div//" +
            "following-sibling::div/following-sibling::div/div/div/div/a/" +
            "div/span/span")
    WebElement resaulSearch2;

    @FieldName("Кнопка сброса запроса")
    @FindBy(className="ImikwIWLKB")
    WebElement searchButton;


    @Override
    public void find(String findText){

    }

    @Override
    public void getResults(String str) {

    }

    @Override
    public void clickElement() {
    }

    @Override
    public boolean isPageLoaded()
    {
        return false;
    }

}
