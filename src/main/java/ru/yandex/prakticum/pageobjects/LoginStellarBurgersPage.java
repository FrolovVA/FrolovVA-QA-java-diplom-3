package ru.yandex.prakticum.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginStellarBurgersPage {

    private WebDriver driver;

    public LoginStellarBurgersPage(WebDriver driver){
        this.driver = driver;
    }

    //Кнопка Личный кабинет
    private By personalAccountButton  = By.xpath(".//*[@class = 'AppHeader_header__nav__g5hnF']/a/p");
    //Строка ввода email
    private By emailInput = By.cssSelector("input[type='text'][name='name']");
    //Строка ввода пароля
    private By passwordInput = By.cssSelector("input[type='password']");
    //Кнопка войти
    private By loginButton = By.className("button_button__33qZ0");
    //Логотип Stellar burger
    private By logoStellarBurger = By.className("AppHeader_header__logo__2D0X2");
    //Кнопка Конструктор
    private By constructorButton = By.xpath(".//*[@class = 'AppHeader_header__list__3oKJj']//*[contains(text(), 'Конструктор')]");
    //Кнопка зарегистрироваться
    private By registerButton = By.xpath(".//div/p[@class= 'undefined text text_type_main-default text_color_inactive mb-4']/a[contains(text(), 'Зарегистрироваться')]");
    //Кнопка восстановить пароль
    private By passwordRecoveryButton = By.xpath(".//div/p[@class= 'undefined text text_type_main-default text_color_inactive']/a[contains(text(), 'Восстановить пароль')]");
    //Заголовок Вход
    private By loginHeaderText = By.xpath(".//div[@class = 'Auth_login__3hAey']/h2[contains(text(), 'Вход')]");

    @Step("Нажатие на кнопку Личный кабинет на странице авторизации")
    public void clickPersonalAccountButton(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(personalAccountButton));
        driver.findElement(personalAccountButton).click();
    }

    @Step("Нажатие на кнопку Конструктор на странице авторизации")
    public void clickConstructorButton() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(constructorButton));
        driver.findElement(constructorButton).click();
    }

    @Step("Нажатие на лого Stellar Burger на странице авторизации")
    public void clickLogoStellarBurger(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(logoStellarBurger));
        driver.findElement(logoStellarBurger).click();
    }

    @Step("Ввод в строку email на странице авторизации")
    public void setEmailInput(String email) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(emailInput));
        driver.findElement(emailInput).sendKeys(email);
    }

    @Step("Ввод в строку password на странице авторизации")
    public void setPasswordInput(String password){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(passwordInput));
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Нажатие на кнопку войти в систему на странице авторизации")
    public void clickLoginButton(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
    }

    @Step("Нажатие на кнопку зарегистрироваться на странице авторизации")
    public void clickRegisterButton(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(registerButton));
        driver.findElement(registerButton).click();
    }

    @Step("Вход в систему на странице авторизации")
    public void loginPersonalAccount(String email, String password) {
        setEmailInput(email);
        setPasswordInput(password);
        clickLoginButton();
    }

    @Step("Нажатие на кнопку Восстановить пароль на странице авторизации")
    public void clickPasswordRecoveryButton(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(passwordRecoveryButton));
        driver.findElement(passwordRecoveryButton).click();
    }

    @Step("Проверка наличия заголовка Вход на странице авторизации")
    public boolean checkLoginHeaderText(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(loginHeaderText));
        return driver.findElement(loginHeaderText).getText().contains("Вход");
    }

}
