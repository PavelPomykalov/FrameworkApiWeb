package web.tests;

import org.junit.jupiter.api.Test;
import web.pages.BasePage;

public class UITest extends BaseTest {
    BasePage basePage = new BasePage();

    @Test
    public void checkLoadingPictureTest(){
        basePage.loadingButtonClick()
                .verifyStartTitle()
                .showCompassPicture()
                .showCalendarPicture()
                .showLandscapePicture()
                .showAwardPicture()
                .verifyFinishTitle();
    }
}
