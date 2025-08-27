package project.data.extract.configurations;//package com.example.demo.primary.configurations;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
import project.data.extract.util.AppUtil;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.Console;
import java.sql.Connection;
import java.sql.SQLException;
import project.data.extract.util.Error;


/**
 * Configuration for DSS Database.
 * Manually configured to allow nested repository by declaring considerNestedRepositories = true inside @EnableJpaRepositories annotation
 *
 * c/o: Lenie Gonzales
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "prodEntityManagerFactory",
        transactionManagerRef = "prodTransactionManager",
        basePackages = { "project.data.extract.repositories" },
        considerNestedRepositories = true
)
@Getter
@Setter
@ConfigurationProperties(prefix = "database")
public class DSS_DatabaseConfiguration {

    private String driver_class_name,password, username, url, errorMessage;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired private Datasource prod;
    @Autowired private AppUtil appUtil;
    private int loginCounter=0;
    @Primary
    @Bean(name="prodDbDataSource")
    public DataSource getDatasource() {
        Console console;
        if ((console = System.console())!=null){
            setUsername(console.readLine("Username: "));
            setPassword(console.readLine("Password: "));
            console.flush();
        }else {
            logger.error(this.getClass().getName()+" ("+Thread.currentThread()
                    .getStackTrace()[1]
                    .getMethodName()+") method -> No console found");
            System.exit(0);
        }

        DataSource dataSource = DataSourceBuilder
                .create()
                .driverClassName(getDriver_class_name())
                .url(getUrl())
                .username(getUsername())
                .password(getPassword())
                .build();

        appUtil.clearConsole();
        System.out.println("Logging in...");

        if (!isValid(dataSource)){
            System.err.println(errorMessage+"\n");
            return getDatasource();
        }

        System.out.println("Successfully connected");
        return dataSource;

//        return DataSourceBuilder
//                .create()
//                .driverClassName("oracle.jdbc.OracleDriver")
//                .url(prod.getUrl())
//                .username(prod.getUsername())
//                .password(prod.getPassword())
//                .build();
    }

    public Boolean isValid(DataSource dataSource)   {
        try {
            if (getUsername().equals("") || getPassword().equals("") || getUrl().equals("")||
                    getUsername().equals(null) || getPassword().equals(null) || getUrl().equals(null))
                throw new SQLException("invalid username/password; logon denied");

            Connection connection = dataSource.getConnection();
            connection.close();
            System.out.println("Login Successful");
            return true;

        } catch (Exception e) {
            logger.error("[Exception Thrown] in class "+this.getClass().getName()+" ("+Thread.currentThread()
                    .getStackTrace()[1]
                    .getMethodName()+") method -> exception message: "+e.getMessage() +" ["+e.getClass().getName()+"]", e);

            if (e.getMessage().contains("ORA-01017: invalid username/password; logon denied")) {
                errorMessage = Error.DATABASE_AUTH_FAIL.getUser_msg();
                loginCounter++;
            }
            else if (e.getMessage().contains("The Network Adapter could not establish the connection"))
                errorMessage = Error.CONNECTION_ERROR.getUser_msg();

            else if(e.getMessage().contains("ORA-28000: The account is locked")) {
                System.out.println(Error.DATABASE_LOCKED.getUser_msg());
                loginCounter=2;
            }
            else errorMessage=e.getMessage();

            if (loginCounter==2)
                errorMessage = Error.LOCKING_ACCOUNT_WARNING.getUser_msg();

            return false;
        }
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
