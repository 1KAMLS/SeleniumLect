package ru.bellintegrator.common.exceptions;

import io.qameta.allure.Allure;
import ru.bellintegrator.common.utils.AllureRuntime;

public class AllureRuntimeError extends AssertionError
{
    public AllureRuntimeError(String errorText, boolean screenshot)
    {
        Allure.attachment("Текст ошибки:", errorText);
        if (screenshot)
            AllureRuntime.captureScreenshot();
        throw new AssertionError(errorText);
    }

    public AllureRuntimeError(String errorText)
    {
        this(errorText, false);
    }
}