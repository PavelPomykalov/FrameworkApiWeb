package web.pages;

import com.codeborne.selenide.SelenideElement;
import org.assertj.core.api.SoftAssertions;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class LoadingImages {
    SelenideElement
            compassPicture = $x("//img[@id='compass']"),
            calendarPicture = $x("//img[@id='calendar']"),
            awardPicture = $x("//img[@id='award']"),
            landscapePicture = $x("//img[@id='landscape']"),
            loadingText = $x("//p[@id='text']"); // исправил на текстовый элемент

    public LoadingImages showCompassPicture(){
        compassPicture.shouldBe(visible);
        return this;
    }

    public LoadingImages showCalendarPicture(){
        calendarPicture.shouldBe(visible);
        return this;
    }

    public LoadingImages showAwardPicture(){
        awardPicture.shouldBe(visible);
        return this;
    }

    public LoadingImages showLandscapePicture(){
        landscapePicture.shouldBe(visible);
        return this;
    }

    public LoadingImages verifyStartTitle() {
        loadingText.shouldBe(visible)
                .shouldHave(text("Please wait until the images are loaded..."));
        return this;
    }

    public LoadingImages verifyFinishTitle() {
        loadingText.shouldBe(visible)
                .shouldHave(text("Done!"));
        return this;
    }
}
