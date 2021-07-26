package ru.bellintegrator.pages;

import org.openqa.selenium.WebElement;

public interface FindPage
{
    void find(String findText);

    void clickElement();

    void getResults(String str);

}
