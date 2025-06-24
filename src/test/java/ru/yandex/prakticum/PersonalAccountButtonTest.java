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
import ru.yandex.prakticum.steps.RequestSpec;
import ru.yandex.prakticum.steps.UserSteps;

@RunWith(Parameterized.class)
public class PersonalAccountButtonTest {

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
    private RegistrationStellarBurgerPage registrationStellarBurgerPage;
    private FirstStageRecoveryPasswordPage firstStageRecoveryPasswordPage;
    private SecondStageRecoveryPasswordPage secondStageRecoveryPasswordPage;
    private OrderFeedStellarBurgerPage orderFeedStellarBurgerPage;
    private PersonalAccountStellarBurgerPage personalAccountStellarBurgerPage;
    private DriverFactory driverFactory = new DriverFactory();

    public PersonalAccountButtonTest(int emailLoginLength, int passwordLength, int nameLength){
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
        registrationStellarBurgerPage = new RegistrationStellarBurgerPage(driver);
        firstStageRecoveryPasswordPage = new FirstStageRecoveryPasswordPage(driver);
        secondStageRecoveryPasswordPage = new SecondStageRecoveryPasswordPage(driver);
        orderFeedStellarBurgerPage = new OrderFeedStellarBurgerPage(driver);
        personalAccountStellarBurgerPage = new PersonalAccountStellarBurgerPage(driver);
        openingBrowser();
    }

    @Step("Открываем браузер и домашнюю страницу Stellar Burger")
    public void openingBrowser(){
        driver.get(StellarBurgerPageConfig.getBaseUrl());
    }

    @Step("Переход с главной страницы Stellar Burger в окно личного кабинета пользователя ")
    public void goToPersonalAccount(){
        checkMainStellarBurgerPageOpen();
        mainStellarBurgersPage.clickPersonalAccountButton();
    }

    @Step("Переход с главной страницы Stellar Burger в ленту заказов")
    public void goToOrderFeed(){
        checkMainStellarBurgerPageOpen();
        mainStellarBurgersPage.clickOrderFeedButton();
    }

    @Step("Переход с главной страницы Stellar Burger в окно входа в аккаунт через кнопку Войти в аккаунт")
    public void clickOnLoginButton(){
        checkMainStellarBurgerPageOpen();
        mainStellarBurgersPage.clickLoginAccountButton();
    }

    @Step("Переход с окна авторизации в окно регистрации")
    public void goToRegistrationFromLogin(){
        checkLoginStellarBurgerPageOpen();
        loginStellarBurgersPage.clickRegisterButton();
    }

    @Step("Заполнение и логин на форме авторизации")
    public  void fillAndLogin(String email, String password){
        checkLoginStellarBurgerPageOpen();
        loginStellarBurgersPage.loginPersonalAccount(email, password);
    }

    @Step("Переход из окна логина в окно первого этапа восстановления пароля")
    public void goToFirstStageRecoveryFromLoginPage(){
        checkLoginStellarBurgerPageOpen();
        loginStellarBurgersPage.clickPasswordRecoveryButton();
    }

    @Step("Заполняем строку ввода email и переходим на второй этап восстановления пароля")
    public void goToSecondStageRecovery(String email){
        checkFirstStageRecoveryPasswordPageOpen();
        firstStageRecoveryPasswordPage.goToSecondStageRecover(email);
    }

    @Step("Переход в личный кабинет из окна авторизации")
    public void goToPersonalAccountFromLogin(){
        checkLoginStellarBurgerPageOpen();
        loginStellarBurgersPage.clickPersonalAccountButton();
    }

    @Step("Переход в личный кабинет из окна регистрации")
    public void goToPersonalAccountFromReg(){
        checkRegistrationStellarBurgerPageOpen();
        registrationStellarBurgerPage.clickPersonalAccountButton();
    }

    @Step("Переход в личный кабинет из окна первого этапа восстановления пароля")
    public void goToPersonalAccountFromFirstStageRecovery(){
        checkFirstStageRecoveryPasswordPageOpen();
        firstStageRecoveryPasswordPage.clickPersonalAccountButton();
    }

    @Step("Переход в личный кабинет из окна второй стадии восстановления пароля")
    public void goToPersonalAccountFromSecondStageRecovery(){
        checkSecondStageRecoveryPasswordPageOpen();
        secondStageRecoveryPasswordPage.clickPersonalAccountButton();
    }

    @Step("Переход в личный кабинет из окна ленты заказов")
    public void goToPersonalAccountFromOrderFeed(){
        checkOrderFeedStellarBurgerPageOpen();
        orderFeedStellarBurgerPage.clickPersonalAccountButton();
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

    @Step("Проверка что открылась страница первого этапа восстановления пароля Stellar Burger")
    public void checkFirstStageRecoveryPasswordPageOpen(){
        Assert.assertTrue("Страница первого этапа восстановления пароля не открылась: элемент не отображается", firstStageRecoveryPasswordPage.checkFirstStageRecoveryHeaderText());
    }

    @Step("Проверка что открылась страница второй стадии восстановления пароля Stellar Burger")
    public void checkSecondStageRecoveryPasswordPageOpen(){
        Assert.assertTrue("Страница второй стадии восстановления пароля не открылась: элемент не отображается", secondStageRecoveryPasswordPage.checkEmailCodePlaceHolderText());
    }

    @Step("Проверка что открылась страница ленты заказов Stellar Burger")
    public  void checkOrderFeedStellarBurgerPageOpen(){
        Assert.assertTrue("Страница ленты заказов не открылась: элемент не отображается", orderFeedStellarBurgerPage.checkOrderFeedHeaderText());
    }

    @Test
    @DisplayName("Проверка: нажатие на кнопку Личный кабинет на странице Stellar Burger без авторизации ведет на страницу авторизации Stellar Burger")
    public void personalAccountButtonMainPageWithoutLoginShouldOpenLoginPageTest(){
        goToPersonalAccount();
        checkLoginStellarBurgerPageOpen();
    }

    @Test
    @DisplayName("Проверка: нажатие на кнопку Личный кабинет на авторизации без авторизации ведет на страницу авторизации Stellar Burger")
    public void personalAccountButtonLoginPageWithoutLoginShouldOpenLoginPageTest(){
        clickOnLoginButton();
        goToPersonalAccountFromLogin();
        checkLoginStellarBurgerPageOpen();
    }

    @Test
    @DisplayName("Проверка: нажатие на кнопку Личный кабинет на странице регистрации без авторизации ведет на страницу авторизации Stellar Burger")
    public void personalAccountButtonRegisterPageWithoutLoginShouldOpenLoginPageTest(){
        clickOnLoginButton();
        goToRegistrationFromLogin();
        goToPersonalAccountFromReg();
        checkLoginStellarBurgerPageOpen();
    }

    @Test
    @DisplayName("Проверка: нажатие на кнопку Личный кабинет на странице первого этапа восстановления пароля без авторизации ведет на страницу авторизации Stellar Burger")
    public void personalAccountButtonFirstRecoveryPageWithoutLoginShouldOpenLoginPageTest(){
        clickOnLoginButton();
        goToFirstStageRecoveryFromLoginPage();
        goToPersonalAccountFromFirstStageRecovery();
        checkLoginStellarBurgerPageOpen();
    }

    @Test
    @DisplayName("Проверка: нажатие на кнопку Личный кабинет на странице второй стадии восстановления пароля без авторизации ведет на страницу авторизации Stellar Burger")
    public void personalAccountButtonSecondRecoveryPageWithoutLoginShouldOpenLoginPageTest(){
        clickOnLoginButton();
        goToFirstStageRecoveryFromLoginPage();
        goToSecondStageRecovery(email);
        goToPersonalAccountFromSecondStageRecovery();
        checkLoginStellarBurgerPageOpen();
    }

    @Test
    @DisplayName("Проверка: нажатие на кнопку Личный кабинет на странице ленты заказов без авторизации ведет на страницу авторизации Stellar Burger")
    public void personalAccountButtonOrderFeedPageWithoutLoginShouldOpenLoginPageTest(){
        goToOrderFeed();
        goToPersonalAccountFromOrderFeed();
        checkLoginStellarBurgerPageOpen();
    }

    @Step("Проверка что открыта страница с профилем")
    public void checkPersonalAccountStellarBurgerPageOpen(){
        Assert.assertTrue(personalAccountStellarBurgerPage.checkLogOutAccountButtonIsAvailable());
    }

    @Step("Переход в личный кабинет из личного кабинета")
    public void goToPersonalAccountFromPersonalAccount(){
        checkPersonalAccountStellarBurgerPageOpen();
        personalAccountStellarBurgerPage.clickPersonalAccountButton();
    }

    @Step("Создание пользователя при помощи Api запроса POST /api/auth/register")
    public ValidatableResponse creatingUser(String email, String password, String name){
        return userSteps.createUser(email, password, name);
    }

    @Test
    @DisplayName("Проверка: нажатие на кнопку Личный кабинет на главной странице Stellar Burger с авторизацией ведет в личный кабинет Stellar Burger")
    public void personalAccountButtonMainPageWithLoginTest(){
        creatingUser(email, password, name);
        clickOnLoginButton();
        fillAndLogin(email, password);
        checkMainStellarBurgerPageOpen();
        goToPersonalAccount();
        checkPersonalAccountStellarBurgerPageOpen();
    }

    @Test
    @DisplayName("Проверка: нажатие на кнопку Личный кабинет в личном кабинете с авторизацией ведет в личный кабинет Stellar Burger")
    public void personalAccountButtonPersonalAccountPageWithLoginTest(){
        creatingUser(email, password, name);
        clickOnLoginButton();
        fillAndLogin(email, password);
        goToPersonalAccount();
        goToPersonalAccountFromPersonalAccount();
        checkPersonalAccountStellarBurgerPageOpen();
    }

    @Test
    @DisplayName("Проверка: нажатие на кнопку Личный кабинет на странице ленты заказов с авторизацией ведет в личный кабинет Stellar Burger")
    public void personalAccountButtonOrderFeedPageWithLoginTest(){
        creatingUser(email, password, name);
        clickOnLoginButton();
        fillAndLogin(email, password);
        goToOrderFeed();
        goToPersonalAccountFromOrderFeed();
        checkPersonalAccountStellarBurgerPageOpen();
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
