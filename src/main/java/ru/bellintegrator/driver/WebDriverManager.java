package ru.bellintegrator.driver;

import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.bellintegrator.common.Constants;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebDriverManager
{
    private static WebDriver currentDriver;//создаётся объект вебдрайвера ждя браузера

    public static WebDriver getCurrentDriver()//этот метод возвращает драйвер
    {
        return currentDriver;
    }

    public static void initChrome()//этот метод запускапет драйвер с параметрами
    {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");//получпение драйвера от системы
        ChromeOptions options = new ChromeOptions();//создание объекта хром-драйвера
        options.addArguments(List.of("start-maximized", "disable-infobars", "--no-sandbox"));//передача в него параметров
        try {
            currentDriver = new ChromeDriver(options);//передача его самого в переменную currentDriver
        } catch (SessionNotCreatedException e) {
            System.out.println("Данный драйвер не совместим с текущим браузером. Используйте другой драйвер");
        }
        setDriverDefaultSettings();//назначение переменной-драйвера парметров ожидания и удаления Cookies
    }

    private static void setDriverDefaultSettings() //установка неявных ожиданий
    {
        currentDriver.manage().timeouts().implicitlyWait(Constants.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        currentDriver.manage().deleteAllCookies();
    }

    public static void killCurrentDriver() {
        if (currentDriver != null) {
           currentDriver.quit();
           currentDriver = null;
        }
    }
}
