package web.tests;

import allureutils.AfterTestExtension;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import config.TestPropertiesConfig;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import web.pages.BasePage;

import static api.constans.Constans.BASE_URI_WEB;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

@ExtendWith(AfterTestExtension.class)
public class BaseTest {
    TestPropertiesConfig configProperties = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());

    @BeforeEach
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10_000;
        Configuration.headless = Boolean.parseBoolean(configProperties.getHeadless());  // Запуск без браузера
        Selenide.open(configProperties.getUiBaseUrl());
    }

    @Step("Браузер закрыт")
    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }

    @Test
    public void checkTest() {
        // Проверка, что URL действительно содержит нужный фрагмент
        Assertions.assertTrue(url().contains("bonigarcia.dev/selenium-webdriver-java!"));
    }
}

