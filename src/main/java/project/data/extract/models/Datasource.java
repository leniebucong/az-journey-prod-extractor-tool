package project.data.extract.models;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * Datasource Properties
 *  Add properties/variable as needed for the configuration setup of your database.
 *  Values should be properly defined in application.properties with prefix defined in @ConfigurationProperties annotation
 * @author Lenie.Gonzales
 */

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix="spring.datasource")
@ToString
public class Datasource{

    private String url,
            username,
            password,
            driverClassName;

}