package ru.netology;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserModel {
    private final String userFullName;
    private final String userPhone;
    private final String cityPreInput;
}

