package ru.yandex.prakticum;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.prakticum.pageobjects.*;
import ru.yandex.prakticum.steps.UserSteps;


@RunWith(Parameterized.class)
public class LoginTest {

    private String baseUrl = "https://stellarburgers.nomoreparties.site";
    private String email;
    private String password;
    private String name;
    private UserSteps userSteps;
    private WebDriver driver;
    private LoginStellarBurgersPage loginStellarBurgersPage;
    private MainStellarBurgersPage mainStellarBurgersPage;
    private RegistrationStellarBurgerPage registrationStellarBurgerPage;
    private FirstStageRecoveryPasswordPage firstStageRecoveryPasswordPage;
    private SecondStageRecoveryPasswordPage secondStageRecoveryPasswordPage;
    private DriverFactory driverFactory = new DriverFactory();

    private int passwordLength;
    private int emailLoginLength;
    private int nameLength;

    public LoginTest(int emailLoginLength, int passwordLength, int nameLength){
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
        creatingUser(email, password, name);
        openingBrowser();
    }

    @Step("Создание пользователя при помощи Api запроса POST /api/auth/register")
    public ValidatableResponse creatingUser(String email, String password, String name){
        return userSteps.createUser(email, password, name);
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

    @Step("Переход из окна регистрации в окно логина")
    public void goToLoginFromRegPage(){
        checkRegistrationStellarBurgerPageOpen();
        registrationStellarBurgerPage.clickAlreadyRegLoginButton();
    }

    @Step("Переход из окна логина в окно восстановления пароля")
    public void goToRecoveryFromLoginPage(){
        checkLoginStellarBurgerPageOpen();
        loginStellarBurgersPage.clickPasswordRecoveryButton();
    }

    @Step("Переход из окна восстановления пароля в окно логина")
    public void goToLoginFromRecoveryPage(){
        checkFirstStageRecoveryPasswordPageOpen();
        firstStageRecoveryPasswordPage.clickRememberLoginButton();
    }

    @Step("Заполняем строку ввода email и переходим на второй этап восстановления пароля")
    public void goToSecondStageRecovery(String email){
        checkFirstStageRecoveryPasswordPageOpen();
        firstStageRecoveryPasswordPage.goToSecondStageRecover(email);
    }

    @Step("Переход со второго этапа восстановления пароля в окно логина")
    public void goToLoginFromSecondStageRecovery(){
        checkSecondStageRecoveryPasswordPageOpen();
        secondStageRecoveryPasswordPage.clickRememberLoginButton();
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

    @Test
    @DisplayName("Проверка входа по кнопке Войти в аккаунт на главной странице Stellar Burger")
    public void loginViaLoginButtonTest(){
        clickOnLoginButton();
        fillAndLogin(email, password);
        checkMainStellarBurgerPageOpen();
    }

    @Test
    @DisplayName("Проверка вход через кнопку Личный кабинет на главной странице Stellar Burger")
    public void loginViaPersonalAccountButtonTest(){
        goToPersonalAccount();
        fillAndLogin(email, password);
        checkMainStellarBurgerPageOpen();
    }

    @Test
    @DisplayName("Проверка входа через кнопку Войти в форме регистрации Stellar Burger")
    public void loginViaRegistrationFormTest(){
        goToPersonalAccount();
        goToRegistrationFromLogin();
        goToLoginFromRegPage();
        fillAndLogin(email, password);
        checkMainStellarBurgerPageOpen();
    }

    @Test
    @DisplayName("Проверка входа через кнопку Войти в форме первого этапа восстановления пароля Stellar Burger")
    public void loginViaFirstStagePasswordRecoveryFormTest(){
        clickOnLoginButton();
        goToRecoveryFromLoginPage();
        goToLoginFromRecoveryPage();
        fillAndLogin(email, password);
        checkMainStellarBurgerPageOpen();
    }

    @Test
    @DisplayName("Проверка входа через кнопку Войти в форме второй стадии восстановления пароля Stellar Burger")
    public void loginViaSecondStagePasswordRecoveryFormTest(){
        clickOnLoginButton();
        goToRecoveryFromLoginPage();
        goToSecondStageRecovery(email);
        goToLoginFromSecondStageRecovery();
        fillAndLogin(email, password);
        checkMainStellarBurgerPageOpen();
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
