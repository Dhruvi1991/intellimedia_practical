package com.intellimedia.practical.utils;

import android.content.Context;

import com.intellimedia.practical.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utilities {

    public static String getBlankErrorMessage(Context context, String fieldName) {
        return fieldName + " " + context.getString(R.string.errorBlank);
    }

    public static String getSpaceErrorMessage(Context context, String fieldName) {
        return fieldName + " " + context.getString(R.string.errorSpace);
    }

    public static String getInvalidErrorMessage(Context context, String fieldName) {
        return fieldName + " " + context.getString(R.string.errorInvalid);
    }

    public static String getAlreadyPresenetErrorMessage(Context context, String fieldName) {
        return fieldName + " " + context.getString(R.string.erroAlreadyPresent);
    }

    public static String md5(final String s) {
        final String MD5 = "MD5";
        try {
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte[] messageDigest = digest.digest();

            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                StringBuilder h = new StringBuilder(Integer.toHexString(0xFF & aMessageDigest));
                while (h.length() < 2)
                    h.insert(0, "0");
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
