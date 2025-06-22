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
import ru.yandex.prakticum.pageobjects.LoginStellarBurgersPage;
import ru.yandex.prakticum.pageobjects.MainStellarBurgersPage;
import ru.yandex.prakticum.pageobjects.OrderFeedStellarBurgerPage;
import ru.yandex.prakticum.pageobjects.RegistrationStellarBurgerPage;
import ru.yandex.prakticum.steps.UserSteps;

import java.time.Duration;

@RunWith(Parameterized.class)
public class RegistrationTest {

    private WebDriver driver;
    private LoginStellarBurgersPage loginStellarBurgersPage;
    private MainStellarBurgersPage mainStellarBurgersPage;
    private RegistrationStellarBurgerPage registrationStellarBurgerPage;
    private String baseUrl = "https://stellarburgers.nomoreparties.site";
    private UserSteps userSteps;
    private String email;
    private String password;
    private String name;
    private int passwordLength;
    private int emailLoginLength;
    private int nameLength;
    private final DriverFactory driverFactory = new DriverFactory();

    public RegistrationTest(int emailLoginLength, int passwordLength, int nameLength){
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
                {10, 10, 10},
                {10, 7, 10},
                {10, 6, 10},
                {10, 5, 10},
                {10, 4, 10},
                {10, 2, 10}
        };
    }

    @Before
    public void setUp(){
        userSteps = new UserSteps();
        driverFactory.initDriver();
        driver = driverFactory.getDriver();
        loginStellarBurgersPage = new LoginStellarBurgersPage(driver);
        mainStellarBurgersPage = new MainStellarBurgersPage(driver);
        registrationStellarBurgerPage = new RegistrationStellarBurgerPage(driver);
        email = getRandomEmail(emailLoginLength);
        password = getRandomAlphabetic(passwordLength);
        name = getRandomAlphabetic(nameLength);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Step("Открываем браузер и домашнюю страницу Stellar Burger")
    public void openingBrowser(){
        driver.get(baseUrl);
    }

    @Step("Переход в окно личного кабинета пользователя")
    public void goToPersonalAccount(){
        mainStellarBurgersPage.clickPersonalAccountButton();
    }

    @Step("Переход к окну входа в аккаунт через кнопку Войти в аккаунт")
    public void clickOnLoginButton(){
        mainStellarBurgersPage.clickLoginAccountButton();
    }

    @Step("Переход в окно регистрации")
    public void goToRegistration(){
        loginStellarBurgersPage.clickRegisterButton();
    }

    @Step("Регистрация аккаунта")
    public void registerPersonalAccount(String email, String password, String name){
        registrationStellarBurgerPage.registerPersonalAccount(email, password, name);
    }

    @Step("Проверка текста ошибки")
    public void checkTextRegistration(){
        Assert.assertTrue(registrationStellarBurgerPage.checkErrorTest());
    }

    @Step("Заполнение полей и авторизация на странице авторизации")
    public void loginPersonalAccount(String email, String password){
        loginStellarBurgersPage.loginPersonalAccount(email, password);
    }

    @Step("Проверка что открылась главная страница Stellar Burger")
    public void checkMainStellarBurgerPageOpen(){
        Assert.assertTrue("Главня страница не открылась: элемент не отображается", mainStellarBurgersPage.checkAssembleBurgerHeaderText());
    }

    @Step("Проверка что открылась страница авторизации Stellar Burger")
    public void checkLoginStellarBurgerPageOpen(){
        Assert.assertTrue("Страница авторизации не открылась: элемент не отображается", loginStellarBurgersPage.checkLoginHeaderText());
    }

    @Step("Проверка что открылась страница регистрации Stellar Burger")
    public void checkRegistrationStellarBurgerPageOpen(){
        Assert.assertTrue("Страница регистрации не открылась: элемент не отображается", registrationStellarBurgerPage.checkRegistrationHeaderText());
    }

    @Test
    @DisplayName("Проверка регистрации через кнопку Личный кабинет при валидном значении пароля и при невалидном")
    public void registrationViaPersonalAccountButtonTest(){
        openingBrowser();
        checkMainStellarBurgerPageOpen();
        goToPersonalAccount();
        checkLoginStellarBurgerPageOpen();
        goToRegistration();
        checkRegistrationStellarBurgerPageOpen();
        registerPersonalAccount(email, password, name);
        if(password.length()<6){
            checkRegistrationStellarBurgerPageOpen();
            checkTextRegistration();
        }else {
            checkLoginStellarBurgerPageOpen();
            loginPersonalAccount(email,password);
            checkMainStellarBurgerPageOpen();
        }
    }

    @Test
    @DisplayName("Проверка регистрации через кнопку Войти в аккаунт при валидном значении пароля и при невалидном")
    public void registrationViaLoginButtonTest(){
        openingBrowser();
        checkMainStellarBurgerPageOpen();
        clickOnLoginButton();
        checkLoginStellarBurgerPageOpen();
        goToRegistration();
        checkRegistrationStellarBurgerPageOpen();
        registerPersonalAccount(email, password, name);
        if(password.length()<6){
            checkRegistrationStellarBurgerPageOpen();
            checkTextRegistration();
        }else {
            checkLoginStellarBurgerPageOpen();
            loginPersonalAccount(email,password);
            checkMainStellarBurgerPageOpen();
        }
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
