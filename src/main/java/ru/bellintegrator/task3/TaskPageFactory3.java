package ru.bellintegrator.task3;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
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
    public double[] refundOfPrices(String currency)
    {
        WebElement purchase=driver.findElement(By.xpath("//td[1]//span[.='"+currency+"']/ancestor::td/following-sibling::td[1]//span"));
        WebElement sale=driver.findElement(By.xpath("//td[1]//span[.='"+currency+"']/ancestor::td/following-sibling::td[3]//span"));

        String str1=purchase.getText().replace(",",".");
        String str2=sale.getText().replace(",",".");

        double [] arrayPrice=new double[2];
        arrayPrice[0]=Double.parseDouble(str1);
        arrayPrice[1]=Double.parseDouble(str2);

        return arrayPrice;
    }

    @Override
    public void getResults()
    {

    }

    @Override
    public boolean isPageLoaded()
    {
        return false;
    }

}
