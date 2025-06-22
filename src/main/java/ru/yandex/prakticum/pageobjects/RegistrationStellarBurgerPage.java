package ru.yandex.prakticum.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationStellarBurgerPage {

    private WebDriver driver;

    private String expectedErrorText = "Некорректный пароль";

    public RegistrationStellarBurgerPage(WebDriver driver){
        this.driver = driver;
    }
    //Кнопка Личный кабинет
    private By personalAccountButton  = By.xpath(".//*[@class = 'AppHeader_header__nav__g5hnF']/a/p");
    //Строка ввода Имя
    private By nameInput = By.xpath(".//form[@class ='Auth_form__3qKeq mb-20' ]/fieldset[1]/div/div/input");
    //Строка ввода Email
    private By emailInput = By.xpath(".//form[@class ='Auth_form__3qKeq mb-20' ]/fieldset[2]/div/div/input");
    //Строка ввода Пароль
    private By passwordInput = By.xpath(".//input[@type= 'password']");
    //Кнопка зарегистрироваться
    private By registrationButton = By.className("button_button__33qZ0");
    //Текст ошибки "Некорректный пароль"
    private By errorText = By.className("input__error");
    //Кнопка Войти
    private By alreadyRegLoginButton = By.className("Auth_link__1fOlj");
    //Логотип Stellar Burger
    private By logoStellarBurger = By.className("AppHeader_header__logo__2D0X2");
    //Кнопка Конструктор
    private By constructorButton = By.xpath(".//*[@class = 'AppHeader_header__list__3oKJj']//*[contains(text(), 'Конструктор')]");
    //Заголовок Регистрация
    private By registrationHeader = By.xpath(".//div[@class = 'Auth_login__3hAey']/h2[contains(text(), 'Регистрация')]");

    @Step("Проверка наличия заголовка Регистрация на странице регистрации")
    public boolean checkRegistrationHeaderText(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(registrationHeader));
        return driver.findElement(registrationHeader).getText().contains("Регистрация");
    }

    @Step("Нажатие на кнопку Личный кабинет на странице регистрации")
    public void clickPersonalAccountButton(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(personalAccountButton));
        driver.findElement(personalAccountButton).click();
    }

    @Step("Нажатие на лого Stellar Burger на странице регистрации")
    public void clickLogoStellarBurger(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.presenceOfElementLocated(logoStellarBurger));
        driver.findElement(logoStellarBurger).click();
    }

    @Step("Нажатие на кнопку Конструктор на странице регистрации")
    public void clickConstructorButton() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(constructorButton));
        driver.findElement(constructorButton).click();
    }

    @Step("Ввод в строку имя на странице регистрации")
    public void setNameInput(String name){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.presenceOfElementLocated(nameInput));
        driver.findElement(nameInput).sendKeys(name);
    }

    @Step("Ввод в строку email на странице регистрации")
    public void setEmailInput(String email){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.presenceOfElementLocated(emailInput));
        driver.findElement(emailInput).sendKeys(email);
    }

    @Step("Ввод в строку пароль на странице регистрации")
    public void setPasswordInput(String password){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.presenceOfElementLocated(passwordInput));
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Нажатие на кнопку Зарегистрироваться на странице регистрации")
    public void clickRegistrationButton(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(registrationButton));
        driver.findElement(registrationButton).click();
    }

    @Step("Регистрация личного аккаунта пользователя на странице регистрации")
    public void registerPersonalAccount(String email, String password, String name){
        setNameInput(name);
        setEmailInput(email);
        setPasswordInput(password);
        clickRegistrationButton();
    }

    @Step("Проверка текста ошибки на странице регистрации")
    public boolean checkErrorTest(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.presenceOfElementLocated(errorText));
        return driver.findElement(errorText).getText().contains(expectedErrorText);
    }

    @Step("Нажатие на кнопку Войти на странице регистрации")
    public void clickAlreadyRegLoginButton(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(alreadyRegLoginButton));
        driver.findElement(alreadyRegLoginButton).click();
    }




}
