package ru.sergo.challenge.sovcombank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    private Integer code;
    private String name;
    private String phone;

    public UserResponse(Integer code) {
        this.code = code;
    }
}
