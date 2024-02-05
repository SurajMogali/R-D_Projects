package com.demo.spring;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demo.spring.rsa.RSA;

@SpringBootApplication
public class RsaApplication {

	public static void main(String[] args) {
		RSA rsa = new RSA();
		try {
			String encryptedMessage = rsa.encrypt("Hello World");
			String decryptedMessage = rsa.decrypt(encryptedMessage);

			System.out.println("Encrypted: " + encryptedMessage);
			System.out.println("Decrypted: " + decryptedMessage);
			//rsa.showKeys();

		} catch (Exception ignored) {
		}

	}

}
