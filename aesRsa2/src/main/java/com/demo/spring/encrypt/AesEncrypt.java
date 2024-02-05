package com.demo.spring.encrypt;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class AesEncrypt {
	  public static String encrypt(byte[] plaintext, SecretKey key) throws Exception{
	        
	        
	        Cipher cipher = Cipher.getInstance("AES");
	        
	        cipher.init(Cipher.ENCRYPT_MODE, key);
	        
	        
	        byte[] encryptedByte= cipher.doFinal(plaintext);
	        String str= Base64.getEncoder().encodeToString(encryptedByte);
	        return str;
	        
	        
	        
	    }

}
