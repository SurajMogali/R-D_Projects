package com.ratelimit.rateLimit.controller;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/security/rate-limit")
public class RateLimitController {

    @Value("${my.test}")
    private String myValue;

    @Value("${my.param}")
    private String myParam;

    @Value("${jasypt.encryptor.password}")
    private String mySecretKey;

    @GetMapping
    public ResponseEntity<?> rateLimitTest1(){
        return ResponseEntity.ok().body("RUNNING SUCCESSFULLY");
    }

    @GetMapping("/two")
    public ResponseEntity<?> rateLimitTest2(){
        return ResponseEntity.ok().body("RUNNING SUCCESSFULLY");
    }

    @GetMapping("/disable")
    public ResponseEntity<?> rateLimitRemovedTest(){
        return ResponseEntity.ok().body("RUNNING SUCCESSFULLY");
    }

    @GetMapping("/encrypt")
    public ResponseEntity<?> encryptProperties(@RequestHeader(required = false) String string){
        Map map = new HashMap<>();
        if (string != null){
            StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
            AES256TextEncryptor aes256TextEncryptor = new AES256TextEncryptor();
            aes256TextEncryptor.setPassword(mySecretKey);


            String aesString = aes256TextEncryptor.encrypt(string);
            map.put("AES256TextEncryptor",aesString);

            encryptor.setPassword(mySecretKey);
            encryptor.setIvGenerator(new RandomIvGenerator());

            String decryptedValue = encryptor.encrypt(string);
            map.put("myParam",myParam);
            map.put("StandardPBEStringEncryptor",decryptedValue);

        }
        map.put("valueAfterDecryption",myValue != null ? myValue : null);
        return ResponseEntity.ok().body(map);
    }

    public static void main(String[] args) {

        String encryptedValue = "RAM";
        String encryptedValue2 = "SHIVA";
        String password = "secretKey";

        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        AES256TextEncryptor aes256TextEncryptor = new AES256TextEncryptor();
        aes256TextEncryptor.setPassword("secretKey");
        String aesString = aes256TextEncryptor.encrypt(encryptedValue);
        System.out.println("aesString = " + aesString);
        String aesString2 = aes256TextEncryptor.encrypt(encryptedValue2);
        System.out.println("aesString2 = " + aesString2);
        System.out.println("aes256TextEncryptor = " + aes256TextEncryptor.decrypt("UKt4x0FAFc5wT4j4sjbRvItVVGqAGcpHfiHPkrKphsVvFoRuAssReZf8Rc+mTfWs"));

        encryptor.setPassword(password);

        String decryptedValue = encryptor.encrypt(encryptedValue);
        System.out.println("Decrypted Value: " + decryptedValue);
    }
}
