package com.example.userdetail;

public interface CustomUserRepository {
    CustomUser findCustomUserByEmail(String email);
}
