package ru.bellintegrator.common.utils;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.bellintegrator.common.Constants;
import ru.bellintegrator.driver.WebDriverManager;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class PageUtils//этот класс содержит методы обслуживающие класс BellPageFactory
{
    public static boolean isVisible(WebElement element)
    {
        return element.isDisplayed();//возвращает true если елемент стал видимым
    }

    private static void waitUntilElementBeVisible(WebElement element) //пока элемент станет видимым
    {
        if (isVisible(element))
            return;
        new WebDriverWait(WebDriverManager.getCurrentDriver(), Constants.DEFAULT_TIMEOUT)
                .until(visibilityOf(element));
    }

    private static void waitUntilElementBeClickable(WebElement element)//пока элемент станет кликабельным
    {
        new WebDriverWait(WebDriverManager.getCurrentDriver(), Constants.DEFAULT_TIMEOUT)
                //передаётся драйвер из класса WebDriverManager, время таймаута из класса Constants
                .until(ExpectedConditions.elementToBeClickable(element));//дождаться, пока элемент станет кликабельным
    }

    public static boolean isClickable(WebElement element)
    {
        try
        {
            waitUntilElementBeClickable(element);//если элемент стал кликабельным
        }
        catch (TimeoutException e)
        {
            return false;
        }
        return true;
    }
}
