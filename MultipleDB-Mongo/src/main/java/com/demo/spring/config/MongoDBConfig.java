//package com.demo.spring.config;
//import com.mongodb.client.MongoClient;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.mongodb.MongoDatabaseFactory;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
//
//@Configuration
//public class MongoDBConfig {
//
//    @Bean
//    @Qualifier("readMongoTemplate")
//    @Primary
//    public MongoTemplate readMongoTemplate(MongoClient mongoClient) {
//        MongoDatabaseFactory factory = new SimpleMongoClientDatabaseFactory(mongoClient, "userdbread");
//        return new MongoTemplate(factory);
//    }
//
//    @Bean
//    @Qualifier("writeMongoTemplate")
//    public MongoTemplate writeMongoTemplate(MongoClient mongoClient) {
//        MongoDatabaseFactory factory = new SimpleMongoClientDatabaseFactory(mongoClient, "userdbwrite");
//        return new MongoTemplate(factory);
//    }
//}
