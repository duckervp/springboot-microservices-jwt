package com.mst.user.util;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

public class Jasoup {
    public static void main(String[] args) {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("micros3rVice!");
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.NoIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);

        String originalStr = "thisIsSecret!";
        String encryptedStr = encryptor.encrypt(originalStr);
        String decryptedStr = encryptor.decrypt(encryptedStr);
        System.out.println("originalStr: " + originalStr);
        System.out.println("encryptedStr: " + encryptedStr);
        System.out.println("decryptedStr: " + decryptedStr);
    }
}
