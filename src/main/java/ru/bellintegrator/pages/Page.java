package ru.bellintegrator.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import ru.bellintegrator.driver.WebDriverManager;

import java.lang.reflect.Field;
public interface Page
{

    boolean isPageLoaded();

    default void initPage()
    {
        WebDriver driver = WebDriverManager.getCurrentDriver();
        try {
            PageFactory.initElements(driver, this);
            if (!isPageLoaded()) {
                throw new RuntimeException("Страница" + this.getClass().getSimpleName() + "не загружена");
            }
        } catch (WebDriverException e) {
            throw new RuntimeException("Ошибка при загрузки страницы " + this.getClass().getSimpleName() + "\n" + e.toString());
        }
    }

    default WebElement getElement(String name)
    {
        WebElement element = null;
        Class<?> validationClass = this.getClass();//рефлексирование класса через объект, вызвавший данный метод
        do
        {
            Field[] fields = validationClass.getDeclaredFields();//массив полей объекта рефлексированного класса
            for (Field field : fields)//проход по массиву полей
            {
                if (field.getType() == WebElement.class)//если поле является вебэлементом
                {
                    field.setAccessible(true);//допуск к работе над полем через флаг со значением true
                    if (field.getAnnotation(FieldName.class) != null)//если с данного field передача аннотации FieldName не ноль
                    {
                        if (field.getAnnotation(FieldName.class).value().equals(name))//тогда содержание переданной аннотации
                        // стрингуется и сравнивается с переданным name
                        {
                            try//при совпадении
                            {
                                element = (WebElement) field.get(this);//всё содержание передаётся в element
                            }
                            catch (IllegalAccessException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
            validationClass = validationClass.getSuperclass();//Возвращается: прямой суперкласс класса, представленного этим объектом
        }
        while (element == null && validationClass == null);//условие цикла do обеспечивающее его неповторяемость (???)
        return element;//возвращение элемента с содержимым
    }
}
