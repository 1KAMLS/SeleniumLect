package ru.bellintegrator.pages;

import org.openqa.selenium.WebElement;

public interface FindPage
{
    void find(String findText);

    void clickElement();

    boolean getResults(int a, int b);
}
