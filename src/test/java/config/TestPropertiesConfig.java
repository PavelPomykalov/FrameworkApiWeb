package config;

import org.aeonbits.owner.Config;

/**
 * Конфигурационный интерфейс для тестовых свойств приложения.
 * Загружает настройки из properties-файлов в зависимости от окружения.
 */

@Config.Sources({
        "classpath:${env}.properties",
        "classpath:default.properties"
})
public interface TestPropertiesConfig extends org.aeonbits.owner.Config {
    @Key("apiBaseUrl")
    String getApiBaseUrl();


    @Key("uiBaseUrl")
    String getUiBaseUrl();


    @Key("login")
    String getLogin();


    @Key("password")
    String getPassword();

    @Key("headless")
    @DefaultValue("true")
    String getHeadless();
}

