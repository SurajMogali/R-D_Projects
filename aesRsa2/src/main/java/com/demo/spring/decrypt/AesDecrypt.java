package com.demo.spring.decrypt;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class AesDecrypt {
	 public static String decrypt(String ciphertext, SecretKey key) throws Exception
	    {
	        
	        Cipher cipher = Cipher.getInstance("AES");
	        
	        cipher.init(Cipher.DECRYPT_MODE, key);
	        
	       return new  String(cipher.doFinal( Base64.getDecoder().decode(ciphertext)));
	       
	        
	        
	    }

}
