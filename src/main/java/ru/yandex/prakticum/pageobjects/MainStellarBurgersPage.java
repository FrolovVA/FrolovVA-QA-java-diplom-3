package ru.yandex.prakticum;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainStellarBurgersPage {

    private WebDriver driver;

    public MainStellarBurgersPage(WebDriver driver){
        this.driver = driver;
    }

    //Кнопка Личный кабинет
    private By personalAccountButton  = By.xpath(".//nav[@class = 'AppHeader_header__nav__g5hnF']/a/p");
    //Кнопка Войти
    private By loginAccountButton = By.className("button_button__33qZ0");
    //Логотип Stellar Burger
    private By logoStellarBurger = By.className("AppHeader_header__logo__2D0X2");
    //Кнопка Конструктор
    private By constructorButton = By.xpath(".//nav/ul/li[not(@class)]");
    //Кнопка Лента Заказов
    private By orderFeedButton = By.xpath(".//nav/ul/li[@class]");
    //Кнопка секции Булки
    private By bunSectionButton = By.xpath(".//*[@class = 'BurgerIngredients_ingredients__1N8v2']/div[not(@class)]/div/span[contains(text(), 'Булки')]");
    //Кнопка секции Соусы
    private By sauceSectionButton = By.xpath(".//*[@class = 'BurgerIngredients_ingredients__1N8v2']/div[not(@class)]/div/span[contains(text(), 'Соусы')]");
    //Кнопка секции Начинки
    private By fillingSectionButton = By.xpath(".//*[@class = 'BurgerIngredients_ingredients__1N8v2']/div[not(@class)]/div/span[contains(text(), 'Начинки')]");
    //Заголовок Соберите бургер
    private By assembleBurgerHeader = By.xpath(".//section[@class = 'BurgerIngredients_ingredients__1N8v2']/h1[contains(text(), 'Соберите бургер')]");

    @Step("Нажатие на кнопку Конструктор на главной странице Stellar Burger")
    public void clickConstructorButton(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(constructorButton));
        driver.findElement(constructorButton).click();
    }

    @Step("Нажатие на кнопку Лента Заказов на главной странице Stellar Burger")
    public void clickOrderFeedButton(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(orderFeedButton));
        driver.findElement(orderFeedButton).click();
    }

    @Step("Нажатие на кнопку секции Булки на главной странице Stellar Burger")
    public void clickBunSectionButton(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(bunSectionButton));
        driver.findElement(bunSectionButton).click();
    }

    @Step("Нажатие на кнопку секции Соусы на главной странице Stellar Burger")
    public void clickSauceSectionButton(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(sauceSectionButton));
        driver.findElement(sauceSectionButton).click();
    }

    @Step("Нажатие на кнопку секции Начинки на главной странице Stellar Burger")
    public void clickFillingSectionButton(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(fillingSectionButton));
        driver.findElement(fillingSectionButton).click();
    }

    @Step("Нажатие на лого Stellar Burger на главной странице Stellar Burger")
    public void clickLogoStellarBurger(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(logoStellarBurger));
        driver.findElement(logoStellarBurger).click();
    }

    @Step("Нажатие на кнопку Личный кабинет на главной странице Stellar Burger")
    public void clickPersonalAccountButton(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(personalAccountButton));
        driver.findElement(personalAccountButton).click();
    }

    @Step("Нажатие на кнопку Войти в систему на главной странице Stellar Burger")
    public void clickLoginAccountButton(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(loginAccountButton));
        driver.findElement(loginAccountButton).click();
    }

    @Step("Ожидание элемента Кнопка секции Булки для проверки открытой главной страницы Stellar Burger")
    public WebElement checkBunSectionButtonIsAvailable(){
        return new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(bunSectionButton));
    }

    @Step("Проверка наличия заголовка Соберите бургер для проверки открытой главной страницы Stellar Burger")
    public boolean checkAssembleBurgerHeaderText(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(assembleBurgerHeader));
        return driver.findElement(assembleBurgerHeader).getText().contains("Соберите бургер");
    }

}
