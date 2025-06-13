package ru.yandex.prakticum;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SecondStageRecoveryPasswordPage {

    private WebDriver driver;

    public SecondStageRecoveryPasswordPage(WebDriver driver){
        this.driver = driver;
    }

    //Кнопка Личный кабинет
    private By personalAccountButton  = By.xpath(".//*[@class = 'AppHeader_header__nav__g5hnF']//*[contains(text(), 'Личный Кабинет')]");
    //кнопка Войти
    private By rememberLoginButton = By.className("Auth_link__1fOlj");
    //Плейсхолдер строки ввода кода из письма
    private By emailCodePlaceHolder = By.xpath(".//fieldset[@class = 'Auth_fieldset__1QzWN mb-6']//label[contains(text(), 'Введите код из письма')]");

    @Step("Нажатие на кнопку Личный кабинет на странице второй стадии восстановления пароля")
    public void clickPersonalAccountButton(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(personalAccountButton));
        driver.findElement(personalAccountButton).click();
    }

    @Step("Нажатие на кнопку на странице второй стадии восстановления пароля")
    public void clickRememberLoginButton() throws InterruptedException {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(rememberLoginButton));
        driver.findElement(rememberLoginButton).click();
    }

    @Step("Проверка текста плейсхолдера на странице второй стадии восстановления пароля")
    public boolean checkEmailCodePlaceHolderText(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(emailCodePlaceHolder));
        return driver.findElement(emailCodePlaceHolder).getText().contains("Введите код из письма");
    }


}
