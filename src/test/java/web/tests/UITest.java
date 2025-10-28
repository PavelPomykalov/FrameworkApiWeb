package web.tests;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import web.pages.BasePage;
import web.pages.LoadingImages;

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
