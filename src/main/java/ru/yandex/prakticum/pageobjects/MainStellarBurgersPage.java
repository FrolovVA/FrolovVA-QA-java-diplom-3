package ru.yandex.prakticum.pageobjects;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
    private By constructorButton = By.xpath(".//*[@class = 'AppHeader_header__list__3oKJj']//*[contains(text(), 'Конструктор')]");
    //Кнопка Лента Заказов
    private By orderFeedButton = By.xpath(".//nav/ul/li[@class]");
    //Кнопка секции Булки
    private By bunSectionButton = By.xpath(".//*[@class = 'BurgerIngredients_ingredients__1N8v2']/div[not(@class)]/div[1]");
    //Кнопка секции Соусы
    private By sauceSectionButton = By.xpath(".//*[@class = 'BurgerIngredients_ingredients__1N8v2']/div[not(@class)]/div[2]");
    //Кнопка секции Начинки
    private By fillingSectionButton = By.xpath(".//*[@class = 'BurgerIngredients_ingredients__1N8v2']/div[not(@class)]/div[3]");
    //Заголовок Соберите бургер
    private By assembleBurgerHeader = By.xpath(".//section[@class = 'BurgerIngredients_ingredients__1N8v2']/h1[contains(text(), 'Соберите бургер')]");
    //Секция булки
    private By bunSection = By.xpath(".//div[@class = 'BurgerIngredients_ingredients__menuContainer__Xu3Mo']/ul[1]");
    //Секция соусы
    private By sauceSection = By.xpath(".//div[@class = 'BurgerIngredients_ingredients__menuContainer__Xu3Mo']/ul[2]");
    //Секция начинки
    private By fillingSection = By.xpath(".//div[@class = 'BurgerIngredients_ingredients__menuContainer__Xu3Mo']/ul[3]");

    @Step("Проверка отображения bunSection")
    public boolean checkBunSectionIsDisplayed(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(bunSection));
        return driver.findElement(bunSection).isDisplayed();
    }

    @Step("Проверка отображения sauceSection")
    public boolean checkSauceSectionIsDisplayed(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(sauceSection));
        return driver.findElement(sauceSection).isDisplayed();
    }

    @Step("Проверка отображения fillingSection")
    public boolean checkFillingSectionIsDisplayed(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(fillingSection));
        return driver.findElement(fillingSection).isDisplayed();
    }

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

    @Step("Нажатие на кнопку секции Соусы на главной странице Stellar Burger")
    public void clickSauceSectionButton(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(sauceSectionButton));
        driver.findElement(sauceSectionButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.attributeContains(sauceSectionButton, "class", "tab_tab_type_current__2BEPc"));
    }

    @Step("Нажатие на кнопку секции Начинки на главной странице Stellar Burger")
    public void clickFillingSectionButton(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(fillingSectionButton));
        driver.findElement(fillingSectionButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.attributeContains(fillingSectionButton, "class", "tab_tab_type_current__2BEPc"));
    }

    @Step("Проверка что элемент bunSectionButton содержит класс tab_tab_type_current__2BEPc ")
    public boolean checkBunSectionButtonSelected(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.attributeContains(bunSectionButton, "class", "tab_tab_type_current__2BEPc"));
        String attributeClass = driver.findElement(bunSectionButton).getAttribute("class");
        return attributeClass.contains("current");
    }

    @Step("Проверка что элемент sauceSectionButton содержит класс tab_tab_type_current__2BEPc ")
    public boolean checkSauceSectionButtonSelected(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.attributeContains(sauceSectionButton, "class", "tab_tab_type_current__2BEPc"));
        String attributeClass = driver.findElement(sauceSectionButton).getAttribute("class");
        return attributeClass.contains("current");
    }

    @Step("Проверка что элемент fillingSectionButton содержит класс tab_tab_type_current__2BEPc ")
    public boolean checkFillingSectionButtonSelected(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.attributeContains(fillingSectionButton, "class", "tab_tab_type_current__2BEPc"));
        String attributeClass = driver.findElement(fillingSectionButton).getAttribute("class");
        return attributeClass.contains("current");
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

    @Step("Проверка наличия заголовка Соберите бургер для проверки открытой главной страницы Stellar Burger")
    public boolean checkAssembleBurgerHeaderText(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(assembleBurgerHeader));
        return driver.findElement(assembleBurgerHeader).getText().contains("Соберите бургер");
    }

}
