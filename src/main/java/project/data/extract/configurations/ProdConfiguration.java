package project.data.extract.configurations;//package com.example.demo.primary.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import project.data.extract.models.Datasource;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "prodEntityManagerFactory",
        transactionManagerRef = "prodTransactionManager",
        basePackages = { "project.data.extract.repositories" },
        considerNestedRepositories = true
)
public class ProdConfiguration {

    @Autowired private Datasource prod;

    @Primary
    @Bean(name="prodDbDataSource")
    public DataSource prodDataSource() {
        return DataSourceBuilder.create()
                .url(prod.getUrl())
                .username(prod.getUsername())
                .password(prod.getPassword())
                .driverClassName(prod.getDriverClassName())
                .build();
    }

    @Primary
    @Bean(name = "prodEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean prodEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("prodDbDataSource") DataSource prodDataSource) {
        return builder
                .dataSource(prodDataSource)
                .packages("project.data.extract.entities")
                .build();
    }

    @Primary
    @Bean(name = "prodTransactionManager")
    public PlatformTransactionManager prodTransactionManager(
            @Qualifier("prodEntityManagerFactory") EntityManagerFactory prodEntityManagerFactory) {
        return new JpaTransactionManager(prodEntityManagerFactory);
    }

}
