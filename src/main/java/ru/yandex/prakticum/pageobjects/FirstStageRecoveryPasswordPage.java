package ru.yandex.prakticum.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FirstStageRecoveryPasswordPage {

    private WebDriver driver;

    public FirstStageRecoveryPasswordPage(WebDriver driver){
        this.driver = driver;
    }

    //Кнопка Личный кабинет
    private By personalAccountButton  = By.xpath(".//*[@class = 'AppHeader_header__nav__g5hnF']/a/p");
    //кнопка Войти
    private By rememberLoginButton = By.className("Auth_link__1fOlj");
    //логотип Stellar Burger
    private By logoStellarBurger = By.className("AppHeader_header__logo__2D0X2");
    //Кнопка Конструктор
    private By constructorButton = By.xpath(".//*[@class = 'AppHeader_header__list__3oKJj']//*[contains(text(), 'Конструктор')]");
    //Строка ввода email
    private By emailInput = By.className("input__textfield");
    //Кнопка Восстановить
    private By resetPasswordButton = By.className("button_button__33qZ0");
    //Заголовок Восстановление пароля на странице первого этапа восстановления пароля
    private By firstStageRecoveryHeader = By.xpath(".//div[@class = 'Auth_login__3hAey']/h2[contains(text(), 'Восстановление пароля')]");

    @Step("Проверка наличия заголовка Восстановление пароля на странице первого этапа восстановления пароля")
    public boolean checkFirstStageRecoveryHeaderText(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(firstStageRecoveryHeader));
        return driver.findElement(firstStageRecoveryHeader).getText().contains("Восстановление пароля");
    }

    @Step("Нажатие на кнопку Конструктор на странице первого этапа восстановления пароля")
    public void clickConstructorButton(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(constructorButton));
        driver.findElement(constructorButton).click();
    }

    @Step("Нажатие на кнопку Личный кабинет на странице первого этапа восстановления пароля")
    public void clickPersonalAccountButton(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(personalAccountButton));
        driver.findElement(personalAccountButton).click();
    }

    @Step("Нажатие на кнопку Войти на странице первого этапа восстановления пароля")
    public void clickRememberLoginButton(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(rememberLoginButton));
        driver.findElement(rememberLoginButton).click();
    }

    @Step("Нажатие на лого Stellar Burger на странице первого этапа восстановления пароля")
    public void clickLogoStellarBurger(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.presenceOfElementLocated(logoStellarBurger));
        driver.findElement(logoStellarBurger).click();
    }

    @Step("Ввод в строку email на странице первого этапа восстановления пароля")
    public void setEmailInput(String email){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.presenceOfElementLocated(emailInput));
        driver.findElement(emailInput).sendKeys(email);
    }

    @Step("Клик по кнопке Восстановить на странице первого этапа восстановления пароля")
    public void clickResetPasswordButton(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(resetPasswordButton));
        driver.findElement(resetPasswordButton).click();
    }

    @Step("Переход ко второму этапу восстановления пароля на странице первого этапа восстановления пароля")
    public void goToSecondStageRecover(String email){
        setEmailInput(email);
        clickResetPasswordButton();
    }

}
