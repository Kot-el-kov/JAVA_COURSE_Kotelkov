package com.github.kotelkov.pms.configuration;

import lombok.SneakyThrows;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Map;

@EnableTransactionManagement(proxyTargetClass = true)
@Configuration
@PropertySource("classpath:/database.properties")
public class DatabaseConfiguration {

    @Value("${database.url}")
    private String databaseUrl;
    @Value("${database.username}")
    private String databaseUserName;
    @Value("${database.password}")
    private String databasePassword;
    @Value("#{${database.hibernate}}")
    private Map<String, String> hibernateProperties;

    @SneakyThrows
    @Bean
    public DataSource dataSource(){
        return new DriverManagerDataSource(databaseUrl,databaseUserName,databasePassword);
    }

    @Bean
    public TransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public FactoryBean<EntityManagerFactory> entityManagerFactory(DataSource dataSource){
        final LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setPackagesToScan("com.github.kotelkov.pms.entity");
        localContainerEntityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        localContainerEntityManagerFactoryBean.setDataSource(dataSource);
        localContainerEntityManagerFactoryBean.setJpaPropertyMap(hibernateProperties);
        return localContainerEntityManagerFactoryBean;
    }
}
