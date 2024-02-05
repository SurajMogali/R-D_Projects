package com.demo.spring;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demo.spring.decrypt.AesDecrypt;
import com.demo.spring.encrypt.AesEncrypt;

@SpringBootApplication
public class AesRsa2Application {

	public static void main(String[] args) throws NoSuchAlgorithmException, Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES"); //AES alogotithm

		keyGenerator.init(128, new SecureRandom());  //128 bits key generation(only 128,192,256 bit key can be generated)

		SecretKey key = keyGenerator.generateKey();   //generate the key

		byte[] plaintext = "This is a secret Message".getBytes();

		String ciphertext = AesEncrypt.encrypt(plaintext, key);
		

		String decryptedText = AesDecrypt.decrypt(ciphertext, key);

		System.out.println("\nPlaintext: " + new String(plaintext));

		System.out.println("\nCiphertext: " + new String(ciphertext));

		System.out.println("\nDecrypted text: " + new String(decryptedText));

		
	}

}
