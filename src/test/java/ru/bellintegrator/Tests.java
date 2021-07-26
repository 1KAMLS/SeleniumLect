package ru.bellintegrator;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import ru.bellintegrator.common.utils.AllureRuntime;
import ru.bellintegrator.driver.WebDriverManager;
import ru.bellintegrator.pages.BellPageFactory;
import ru.bellintegrator.steps.Steps;
import ru.bellintegrator.task3.StepsTask3;
import ru.bellintegrator.task4.StepsTask4;
import ru.bellintegrator.task3.TaskPageFactory3;
import ru.bellintegrator.task4.TaskPageFactory4;

import java.util.List;

public class Tests
{
    private static WebDriver driver;
    private static Steps steps;
    private static StepsTask3 stepsTask3;
    private static StepsTask4 stepsTask4;

    @BeforeEach
    private void beforeEach()
    {
        WebDriverManager.initChrome();
        driver = WebDriverManager.getCurrentDriver();
        steps = new Steps(driver);
        stepsTask3=new StepsTask3(driver);
        stepsTask4=new StepsTask4(driver);
    }

    @Feature("Пустой тест")
    @Test
    @DisplayName("Пустой тест")
    public void firstUITest(){
    }

    @Feature("Проверка тайтла")
    @Test
    @DisplayName("Проверка тайтла напрямую")
    public void firstTestTitle(){
        System.out.println("firstTestTitle");
        driver.get("https://bellintegrator.ru/");
        String title = driver.getTitle(); //Bell Integrator
        System.out.println(title);
        Assertions.assertTrue(title.contains("Bell Integrator"),"Тайтл не корректен");
        List<WebElement> digitalServices = driver.findElements(By.xpath("//div[@id='digital-services']//h3[contains(@class,'category_name')]"));
        System.out.println(digitalServices);
        digitalServices.forEach(x-> System.out.println(x.getText()));
        digitalServices.forEach(WebElement::click);
        driver.findElement(By.id("logo_BellIntegrator")).click();
    }

    @Feature("Проверка результатов поиска")
    @Test
    @DisplayName("Проверка результатов поиска c помощью PF")
    public void testPF()
    {
        steps.goToURL("https://bellintegrator.ru/index.php?route=product/search");
        BellPageFactory bell = new BellPageFactory();
        steps.executeFind("RPA", bell);
        System.out.println(bell.getAllElements().size());
        steps.isStringsEquals("111", "111");
        AllureRuntime.print("11111");
        steps.doSmhng();
        steps.isStringsEquals("111", "111");
        steps.doSmhng();
    }

    @Feature("Курсы валют")
    @DisplayName("Проверка результатов поиска c помощью PF")
    @ParameterizedTest(name="{displayName} {arguments}")
    @CsvSource({"Открытие,https://www.google.com/"})
    public void testBank(String name, String url)
    {
        stepsTask3.goToURL(url);//браузер получает адрес страницы
        TaskPageFactory3 task3=PageFactory.initElements(driver, TaskPageFactory3.class);//создание объекта с передачей туда браузера
        stepsTask3.findWord(name, task3);//вставка в поле ввода слова "Открытие" и переход
        stepsTask3.findClick(task3);
        stepsTask3.calc(task3,0,1);
        stepsTask3.calc(task3,2,3);
    }

    @Feature("Сравнение видеокарт")
    @DisplayName("Проверка результатов поиска c помощью PF")
    @ParameterizedTest
    @CsvSource({"GTX 1050 ti,RTX 3070,https://market.yandex.ru/"})
    public void testCard(String nameCard1,String nameCard2, String url)
    {
        stepsTask4.goToURL(url);//браузер получает адрес страницы
        TaskPageFactory4 task4=PageFactory.initElements(driver, TaskPageFactory4.class);//создание объекта с передачей туда браузера
        stepsTask4.clickOnElement("Кнопка Каталога", task4);//Клик по кнопке каталога
        stepsTask4.hoveringTheCursor(task4);//наведение курсора без нажатия
        stepsTask4.clickOnElement("Вкладка Видеокарты", task4);//Клик по кнопке Видеокарты
        stepsTask4.mapSearch(nameCard1, task4);//поиск карты
        stepsTask4.sortingByPrice(task4);//сортировка по ценеп
        int priceGTX = stepsTask4.extractingThePrice(nameCard1, task4);//извлечение цены
        stepsTask4.resetResult(task4);//сброс результатов поиска
        stepsTask4.mapSearch(nameCard2, task4);//поиск карты
        stepsTask4.sortingByPrice(task4);//сортировка по ценеп
        int priceRTX = stepsTask4.extractingThePrice(nameCard2, task4);//извлечение цены
        Assertions.assertTrue(priceGTX<priceRTX);//сравнение цен
    }

    @Test
    public void secondTestFind(){
        driver.get("https://bellintegrator.ru/index.php?route=product/search");
        WebElement searchField = driver.findElement(By.id("input-search"));
        WebElement searchButton = driver.findElement(By.id("button-search"));
        searchField.click();
        searchField.sendKeys("RPA");
        searchButton.click();

        List<WebElement> resaultSearch = driver.findElements(By.xpath("//*[@class='product-layout product-list col-xs-12']//*[@class='short__desc']"));
        System.out.println(resaultSearch.size());
        resaultSearch.stream().forEach(x-> System.out.println(x));
        resaultSearch.stream().forEach(x-> System.out.println(x.getText()));

        Assertions.assertTrue(resaultSearch.stream().anyMatch(x->x.getText().contains("Кирилл Филенков")),"Статьи не найдены");
    }


//    @AfterEach
//    private void afterEach() {
//        WebDriverManager.killCurrentDriver();
//    }
}
