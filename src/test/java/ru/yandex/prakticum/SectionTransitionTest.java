package ru.yandex.prakticum;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.prakticum.pageobjects.*;
import java.time.Duration;

public class SectionTransitionTest {

    private String baseUrl = "https://stellarburgers.nomoreparties.site";
    private WebDriver driver;
    private MainStellarBurgersPage mainStellarBurgersPage;
    private DriverFactory driverFactory = new DriverFactory();

    @Before
    public void setUp(){
        driverFactory.initDriver();
        driver = driverFactory.getDriver();
        mainStellarBurgersPage = new MainStellarBurgersPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        openingBrowser();
    }

    @Step("Открываем браузер и домашнюю страницу Stellar Burger")
    public void openingBrowser(){
        driver.get(baseUrl);
    }

    @Step("Проверка что открылась главная страница Stellar Burger")
    public void checkMainStellarBurgerPageOpen(){
        Assert.assertTrue("Главня страница не открылась: элемент не отображается", mainStellarBurgersPage.checkAssembleBurgerHeaderText());
    }

    @Step("Проверка наличия класса")
    public void checkBunSectionButton(){
        Assert.assertTrue("класс tab_tab_type_current__2BEPc не найден", mainStellarBurgersPage.checkBunSectionButtonSelected());
    }

    @Step("Проверка отображения секции ")
    public void checkBunSectionIsDisplayed(){
        Assert.assertTrue(mainStellarBurgersPage.checkBunSectionIsDisplayed());
    }

    @Step("Нажатие кнопки bunSectionButton")
    public void clickSauceSectionButton(){
        mainStellarBurgersPage.clickSauceSectionButton();
    }

    @Step("Проверка наличия класса tab_tab_type_current__2BEPc у элемента sauceSectionButton")
    public void checkSauceSectionButton(){
        Assert.assertTrue(mainStellarBurgersPage.checkSauceSectionButtonSelected());
    }

    @Step("Проверка отображения секции bunSection")
    public void checkSauceSectionIsDisplayed(){
        Assert.assertTrue(mainStellarBurgersPage.checkSauceSectionIsDisplayed());
    }

    @Step("Нажатие кнопки fillingSectionButton")
    public void clickFillingSectionButton(){
        checkMainStellarBurgerPageOpen();
        mainStellarBurgersPage.clickFillingSectionButton();
    }

    @Step("Проверка наличия класса tab_tab_type_current__2BEPc у элемента fillingSectionButton")
    public void checkFillingSectionButton(){
        Assert.assertTrue(mainStellarBurgersPage.checkFillingSectionButtonSelected());
    }

    @Step("Проверка отображения секции ")
    public void checkFillingSectionIsDisplayed(){
        Assert.assertTrue(mainStellarBurgersPage.checkFillingSectionIsDisplayed());
    }

    @Test
    @DisplayName("Проверка перехода к секции Булки")
    public void bunSectionTest(){
        checkBunSectionButton();
        checkBunSectionIsDisplayed();
    }

    @Test
    @DisplayName("Проверка перехода к секции Соусы")
    public void sauceSectionTest(){
        clickSauceSectionButton();
        checkSauceSectionButton();
        checkSauceSectionIsDisplayed();
    }

    @Test
    @DisplayName("Проверка перехода к секции Начинки")
    public void fillingSectionTest(){
        clickFillingSectionButton();
        checkFillingSectionIsDisplayed();
        checkFillingSectionButton();
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
