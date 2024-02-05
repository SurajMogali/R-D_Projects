//package com.ratelimit.rateLimit.config;
//
//import com.ulisesbocchio.jasyptspringboot.properties.JasyptEncryptorConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class PasswordEncrypt {
//
//    @Bean
//    public JasyptEncryptorConfigurationProperties jasyptEncryptorConfigurationProperties(){
//        JasyptEncryptorConfigurationProperties properties
//                = new JasyptEncryptorConfigurationProperties();
//        properties.setAlgorithm("AES");
//        properties.setPassword("secretKey");
//        properties.setIvGeneratorClassname("org.jasypt.iv.NoIvGenerator");
//        System.out.println("properties.getAlgorithm() = " + properties.getAlgorithm());
//        System.out.println("properties = " + properties.getIvGeneratorClassname());
//        System.out.println("properties = " + properties);
//        System.out.println("properties = " + properties.getBean());
//        return properties;
//
//    }
//}
