package web.tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import web.pages.BasePage;

public class UITest extends BaseTest {
    BasePage basePage = new BasePage();

    @Tag("WEB")
    @Tag("SMOKE")
    @DisplayName("Проверка картинок")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void checkLoadingPictureTest(){
        basePage
                .loadingButtonClick()
                .verifyStartTitle()
                .showCompassPicture()
                .showCalendarPicture()
                .showLandscapePicture()
                .showAwardPicture()
                .verifyFinishTitle();
    }
}
