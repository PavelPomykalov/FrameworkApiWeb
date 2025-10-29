package web.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class BasePage {
    SelenideElement
            title = $x("//h1[@class= 'display-4']"),
            loadingImagesButton = $x("//a[text()='Loading images']"),
            loginFormButton = $x("//a[text()='Login form']");

    @Step("Проверка Наименования сайта")
    public BasePage showTitle(){
        title.shouldBe(visible)
        .shouldHave(text("Hands-On Selenium WebDriver with Java"));
        return this;
    }
    @Step("Клик по кнопке ")
    public LoadingImages loadingButtonClick() {
        loadingImagesButton.shouldBe(visible).click();
        return new LoadingImages();
    }

    public void loginFormButtonClick(){
        loginFormButton.shouldBe(visible).click();
    }
}

