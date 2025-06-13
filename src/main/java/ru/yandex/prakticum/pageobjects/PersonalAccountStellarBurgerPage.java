package ru.yandex.prakticum;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountStellarBurgerPage {

    private WebDriver driver;

    public PersonalAccountStellarBurgerPage(WebDriver driver){
        this.driver = driver;
    }
    //Кнопка Личный кабинет
    private By personalAccountButton  = By.xpath(".//*[@class = 'AppHeader_header__nav__g5hnF']/a/p");
    //Логотип Stellar Burger
    private By logoStellarBurger = By.className("AppHeader_header__logo__2D0X2");
    //Кнопка Выйти
    private By logOutAccountButton = By.xpath(".//ul[@class = 'Account_list__3KQQf mb-20']/li/button[contains(text(), 'Выход')]");


    @Step("Нажатие на лого Stellar Burger на странице личного кабинета")
    public void clickLogoStellarBurger(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.presenceOfElementLocated(logoStellarBurger));
        driver.findElement(logoStellarBurger).click();
    }

    @Step("Нажатие на кнопку выхода из аккаунта на странице личного кабинета")
    public void clickLogOutAccountButton(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.presenceOfElementLocated(logOutAccountButton));
        driver.findElement(logOutAccountButton).click();
    }

    @Step("Нажатие на кнопку Личный кабинет на странице личного кабинета")
    public void clickPersonalAccountButton(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(personalAccountButton));
        driver.findElement(personalAccountButton).click();
    }

    @Step("Проверка наличия кнопки выйти на страницы личного кабинета")
    public boolean checkLogOutAccountButtonIsAvailable(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(logOutAccountButton));
        return driver.findElement(logOutAccountButton).getText().contains("Выход");
    }


}
