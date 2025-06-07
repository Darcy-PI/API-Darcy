package com.example.darcy_api.utils;

import lombok.Getter;

import java.security.SecureRandom;

@Getter
public class Accesskey {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateKey(int length){
        StringBuilder key = new StringBuilder(length);
        for (int i=0; i<length; i++){
            key.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return key.toString();
    }
}
