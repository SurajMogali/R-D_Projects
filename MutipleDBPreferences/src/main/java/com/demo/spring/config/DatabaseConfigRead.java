//package com.demo.spring.config;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//    basePackages = "com.demo.spring.repository",
//    entityManagerFactoryRef = "entityManagerFactory",
//    transactionManagerRef = "transactionManager"
//)
//public class DatabaseConfigRead {
//
//
//    @Bean(name = "readDataSource")
//    @ConfigurationProperties(prefix = "spring.user.read.datasource")
//    public DataSource readDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "entityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
//            @Qualifier("readDataSource") DataSource readDataSource) {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        
//        em.setDataSource(readDataSource);
//        em.setPackagesToScan("com.demo.spring.entity");
//        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        return em;
//    }
//    
//   
//
//    @Bean(name = "transactionManager")
//    public PlatformTransactionManager transactionManager(
//            @Qualifier("entityManagerFactory") jakarta.persistence.EntityManagerFactory entityManagerFactory) {
//        return new JpaTransactionManager((jakarta.persistence.EntityManagerFactory) entityManagerFactory);
//    }
//    
//    
//}
