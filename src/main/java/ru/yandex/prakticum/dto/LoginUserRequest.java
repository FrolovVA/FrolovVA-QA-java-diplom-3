package ru.yandex.prakticum.dto;

import lombok.Data;

@Data
public class LoginUserRequest {
    private String email;
    private String password;
}
