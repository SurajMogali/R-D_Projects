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
//    basePackages = "com.demo.spring.repository2",
//    entityManagerFactoryRef = "entityManagerFactory2",
//    transactionManagerRef = "transactionManager2"
//)
//public class DatabaseConfigWrite {
//
//    @Bean(name = "writeDataSource")
//    @ConfigurationProperties(prefix = "spring.user.write.datasource")
//    public DataSource writeDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//  
//  
//    
//    @Bean(name = "entityManagerFactory2")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory2(
//            @Qualifier("writeDataSource") DataSource writeDataSource) {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(writeDataSource);
//        
//        em.setPackagesToScan("com.demo.spring.entity");
//        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        return em;
//    }
//
//    
//    
//    @Bean(name = "transactionManager2")
//    public PlatformTransactionManager transactionManager2(
//            @Qualifier("entityManagerFactory2") jakarta.persistence.EntityManagerFactory entityManagerFactory2) {
//        return new JpaTransactionManager((jakarta.persistence.EntityManagerFactory) entityManagerFactory2);
//    }
//}
