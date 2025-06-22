package ru.yandex.prakticum;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.prakticum.pageobjects.*;
import ru.yandex.prakticum.steps.UserSteps;

@RunWith(Parameterized.class)
public class LogOutButtonTest {
    private String baseUrl = "https://stellarburgers.nomoreparties.site";

    private String email;
    private String password;
    private String name;
    private int passwordLength;
    private int emailLoginLength;
    private int nameLength;

    private UserSteps userSteps;
    private WebDriver driver;
    private LoginStellarBurgersPage loginStellarBurgersPage;
    private MainStellarBurgersPage mainStellarBurgersPage;
    private PersonalAccountStellarBurgerPage personalAccountStellarBurgerPage;
    private DriverFactory driverFactory = new DriverFactory();

    public LogOutButtonTest(int emailLoginLength, int passwordLength, int nameLength){
        this.emailLoginLength = emailLoginLength;
        this.passwordLength = passwordLength;
        this.nameLength = nameLength;
    }

    @Step("Генерируем случайный email для для регистрации")
    public static String getRandomEmail(int n){
        return RandomStringUtils.randomAlphabetic(n).toLowerCase() + "@yandex.ru";
    }

    @Step("Генерируем случайную последовательность символов для пароля или имени для для регистрации")
    public static String getRandomAlphabetic(int n){
        return RandomStringUtils.randomAlphabetic(n);
    }

    @Parameterized.Parameters
    public static Object[][] getText()    {
        return new Object[][]{
                {10, 10, 10}
        };
    }

    @Before
    public void setUp(){
        email = getRandomEmail(emailLoginLength);
        password = getRandomAlphabetic(passwordLength);
        name = getRandomAlphabetic(nameLength);
        userSteps = new UserSteps();
        driverFactory.initDriver();
        driver = driverFactory.getDriver();
        loginStellarBurgersPage = new LoginStellarBurgersPage(driver);
        mainStellarBurgersPage = new MainStellarBurgersPage(driver);
        personalAccountStellarBurgerPage = new PersonalAccountStellarBurgerPage(driver);
        openingBrowser();
    }

    @Step("Открываем браузер и домашнюю страницу Stellar Burger")
    public void openingBrowser(){
        driver.get(baseUrl);
    }

    @Step("Переход с главной страницы Stellar Burger в окно личного кабинета пользователя ")
    public void goToPersonalAccount(){
        checkMainStellarBurgerPageOpen();
        mainStellarBurgersPage.clickPersonalAccountButton();
    }

    @Step("Переход с главной страницы Stellar Burger в окно входа в аккаунт через кнопку Войти в аккаунт")
    public void clickOnLoginButton(){
        checkMainStellarBurgerPageOpen();
        mainStellarBurgersPage.clickLoginAccountButton();
    }

    @Step("Заполнение и логин на форме авторизации")
    public  void fillAndLogin(String email, String password){
        checkLoginStellarBurgerPageOpen();
        loginStellarBurgersPage.loginPersonalAccount(email, password);
    }

    @Step("Переход по кнопке в логотипе из окна авторизации")
    public void goToLogoPageFromLogin(){
        checkLoginStellarBurgerPageOpen();
        loginStellarBurgersPage.clickLogoStellarBurger();
    }

    @Step("Проверка что открылась главная страница Stellar Burger")
    public void checkMainStellarBurgerPageOpen(){
        Assert.assertTrue("Главная страница не открылась: элемент не отображается", mainStellarBurgersPage.checkAssembleBurgerHeaderText());
    }

    @Step("Проверка что открылась страница авторизации Stellar Burger")
    public void checkLoginStellarBurgerPageOpen(){
        Assert.assertTrue("Страница авторизации не открылась: элемент не отображается", loginStellarBurgersPage.checkLoginHeaderText());
    }

    @Step("Проверка что открыта страница с профилем")
    public void checkPersonalAccountStellarBurgerPageOpen(){
        Assert.assertTrue(personalAccountStellarBurgerPage.checkLogOutAccountButtonIsAvailable());
    }

    @Step("Создание пользователя при помощи Api запроса POST /api/auth/register")
    public ValidatableResponse creatingUser(String email, String password, String name){
        return userSteps.createUser(email, password, name);
    }

    @Step("Нажатие на кнопку выход")
    public void clickLogOutButton(){
        checkPersonalAccountStellarBurgerPageOpen();
        personalAccountStellarBurgerPage.clickLogOutAccountButton();
    }

    @Test
    @DisplayName("Проверка выхода по кнопке «Выйти» в личном кабинете")
    public void logOutButtonShouldLogOutPageTest(){
        creatingUser(email, password, name);
        clickOnLoginButton();
        fillAndLogin(email, password);
        goToPersonalAccount();
        clickLogOutButton();
        goToLogoPageFromLogin();
        goToPersonalAccount();
        checkLoginStellarBurgerPageOpen();
    }

    @Step("Получение accessToken")
    public String gettingAccessToken(ValidatableResponse validatableResponse){
        return validatableResponse.extract().path("accessToken");
    }

    @Step("Логин пользователя")
    public ValidatableResponse loginInUser(String email, String password){
        return userSteps.loginUser(email, password);
    }

    @Step("Удаление пользователя")
    public ValidatableResponse deletingUser(String accessToken){
        return userSteps.deleteUser(accessToken);
    }

    @After
    public void tearDown(){
        driver.quit();
        String accessToken = gettingAccessToken(loginInUser(email, password));
        if (accessToken != null) {
            deletingUser(accessToken);
        }
    }

}
