package web.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class BasePage {
    SelenideElement
            title = $x("//h1[@class= 'display-4']"),
            loadingImagesButton = $x("//a[text()='Loading images']"),
            loginFormButton = $x("//a[text()='Login form']");

    public void showTitle(){
        title.shouldBe(visible)
        .shouldHave(text("Hands-On Selenium WebDriver with Java"));
    }

    public LoadingImages loadingButtonClick() {
        loadingImagesButton.shouldBe(visible).click();
        return new LoadingImages();
    }

    public void loginFormButtonClick(){
        loginFormButton.shouldBe(visible).click();
    }
}

