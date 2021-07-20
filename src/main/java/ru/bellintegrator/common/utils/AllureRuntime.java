package ru.bellintegrator.common.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.model.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.bellintegrator.driver.WebDriverManager;

import java.nio.charset.StandardCharsets;

public class AllureRuntime
{
    public static void print(String text)
    {
        Allure.step(text);
        System.out.println(text);
    }

    public static void printError(String text)
    {
        Allure.step(text, Status.FAILED);
        System.out.println(text);
    }

    @Attachment(value = "Скриншот", type = "image/png")
    public static byte[] captureScreenshot()
    {
        byte[] screenshot = null;
        screenshot = ((TakesScreenshot) WebDriverManager.getCurrentDriver()).getScreenshotAs(OutputType.BYTES);
        return screenshot;
    }

    @Attachment(value = "text", fileExtension = ".txt")
    public static byte[] printTextAttachment(String text)
    {
        return text.getBytes(StandardCharsets.UTF_8);
    }
}
