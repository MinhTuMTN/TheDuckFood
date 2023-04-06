package com.theduckfood.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncodingUtil {
    public static String encoding(String text) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            byte[] text_hashed_bytes = messageDigest.digest(text.getBytes());

            return Base64.getEncoder().encodeToString(text_hashed_bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isValid(String plainText, String hashedText) {
        return hashedText.equals(encoding(plainText));
    }
}
