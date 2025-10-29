package allureutils;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

/**
 * Утилитный класс для добавления скриншотов и HTML-кода страницы
 * в отчёт Allure при выполнении UI-тестов на Selenide.
 */

public class AllureSteps {

    @Step("Сделать скриншот (спойлер)")
    public static void screenshotSpoiler() {
        Allure.addAttachment("Screenshot",
                new ByteArrayInputStream(((TakesScreenshot)
                        WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES)));
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] screenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver())
                .getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "HTML", type = "text/html")
    public static String sourceCode() {
        return WebDriverRunner.source();
    }
}
