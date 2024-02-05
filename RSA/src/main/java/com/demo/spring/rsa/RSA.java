package com.demo.spring.rsa;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;

public class RSA {

	private PrivateKey privateKey;
	private PublicKey publicKey;

	public RSA() {
		try {
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(1024);
			KeyPair pair = generator.generateKeyPair();
			privateKey = pair.getPrivate();
			publicKey = pair.getPublic();

		} catch (Exception ignored) {

		}

	}

	public String encrypt(String message) throws Exception {
		byte[] messageToBytes = message.getBytes();
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] encryptedBytes = cipher.doFinal(messageToBytes);
		return encode(encryptedBytes);

	}

	public String encode(byte[] data) {
		return Base64.getEncoder().encodeToString(data);  //binarydata(byte array) to string
	}

	public String decrypt(String encryptedMessage) throws Exception {
		byte[] encryptedBytes = decode(encryptedMessage);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] decryptedMessage = cipher.doFinal(encryptedBytes);
		return new String(decryptedMessage, "UTF8");   //Each byte will be encoded to character

	}

	public byte[] decode(String data) {
		return Base64.getDecoder().decode(data);
	}
	
//	public void showKeys()
//	{
//		System.out.println("PrivateKey"+ privateKey );
//		System.out.println("PublicKey"+ publicKey );
//		
//	}

}
