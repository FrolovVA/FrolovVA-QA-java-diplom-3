package ru.yandex.prakticum.dto;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String email;
    private String password;
    private String name;
}
