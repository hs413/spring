package com.example.jwt.customuser;

public interface CustomUserRepository {
    CustomUser findCustomUserByEmail(String email);
}
