package ru.yandex.prakticum;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderFeedStellarBurgerPage {

    private WebDriver driver;

    public  OrderFeedStellarBurgerPage(WebDriver driver){
        this.driver = driver;
    }


    //Кнопка Личный кабинет
    private By personalAccountButton = By.xpath(".//*[@class = 'AppHeader_header__nav__g5hnF']/a/p");
    //Заголовок лента заказов
    private By orderFeedHeader = By.xpath(".//div[@class = 'OrderFeed_orderFeed__2RO_j']/h1[contains(text(), 'Лента заказов')]");


    @Step("Нажатие кнопки Личный кабинет на странице Ленты Заказов")
    public void clickPersonalAccountButton(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(personalAccountButton));
        driver.findElement(personalAccountButton).click();
    }

    @Step("Проверка текста заголовка Лента заказов на странице Ленты заказов")
    public boolean checkOrderFeedHeaderText(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(orderFeedHeader));
        return driver.findElement(orderFeedHeader).getText().contains("Лента заказов");
    }

}
