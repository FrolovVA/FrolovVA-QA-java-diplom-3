package ru.yandex.prakticum.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.prakticum.dto.CreateUserRequest;
import ru.yandex.prakticum.dto.LoginUserRequest;

import static io.restassured.RestAssured.given;

public class UserSteps {

private String createUserHandle = "api/auth/register";
private String loginUserHandle = "/api/auth/login";
private String userInfoHandle = "/api/auth/user";

    @Step("Формируем json тело для запроса POST /api/auth/register для создания пользователя")
    public CreateUserRequest createUserRequestBody(String email, String password, String name){
        CreateUserRequest request =new CreateUserRequest();
        request.setEmail(email);
        request.setPassword(password);
        request.setName(name);
        return request;
    }

    @Step("Отравляем Api запрос POST /api/auth/register для создания пользователя")
    public ValidatableResponse postCreateUserRequest(CreateUserRequest createUserRequestBody, String createUserHandle){
        return given()
                .spec(RequestSpec.baseSpec())
                .body(createUserRequestBody)
                .when()
                .post(createUserHandle)
                .then();
    }

    @Step("Оправляем запрос на Api запрос POST /api/auth/register для создания пользователя и получаем ответ")
    public ValidatableResponse createUser(String email, String password, String name) {
        CreateUserRequest createUserRequestBody = createUserRequestBody(email, password, name);
        return postCreateUserRequest(createUserRequestBody, createUserHandle);
    }


    @Step("Формируем json тело для запроса POST /api/auth/login для логина пользователя")
    public LoginUserRequest loginUserRequestBody(String email, String password){
        LoginUserRequest request = new LoginUserRequest();
        request.setEmail(email);
        request.setPassword(password);
        return request;
    }

    @Step("Отправляем Api запрос POST /api/auth/login для логина пользователя")
    public ValidatableResponse postLoginUserRequest(LoginUserRequest loginUserRequestBody, String loginUserHandle){
        return given()
                .spec(RequestSpec.baseSpec())
                .body(loginUserRequestBody)
                .when()
                .post(loginUserHandle)
                .then();
    }
    @Step("Оправляем запрос на Api запрос POST /api/auth/login для логина пользователя и получаем ответ")
    public ValidatableResponse loginUser(String email, String password){
        LoginUserRequest loginUserRequestBody = loginUserRequestBody(email, password);
        return postLoginUserRequest(loginUserRequestBody, loginUserHandle);
    }


    @Step("Формируем Api запрос DELETE /api/auth/user для удаления пользователя")
    public  ValidatableResponse deleteDeleteUserRequest(String accessToken){
        var requestSpec = given()
                .spec(RequestSpec.baseSpec());
        if (accessToken != null) {
            requestSpec.header("Authorization", accessToken);
        }
        return requestSpec
                .when()
                .delete(userInfoHandle)
                .then();
    }

    @Step("Отправляем Api запрос DELETE /api/auth/user для удаления пользователя и получаем ответ")
    public ValidatableResponse deleteUser(String accessToken){
        return deleteDeleteUserRequest(accessToken);
    }


}
