package com.demo.bancobisa.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import java.util.logging.Level;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Log
@Configuration
public class Config {

    public static final Level LOG_LEVEL = Level.INFO;

    @Bean
    public OpenAPI customOpenAPI(
            @Value("${application-description}") String appDesciption,
            @Value("${application-version}") String appVersion
    ) {
        return new OpenAPI()
                .info(new Info()
                        .title("MSA Consulta Demo")
                        .version(appVersion)
                        .description(appDesciption)
                        .contact(new Contact()
                                .email("soporte@gmail.com")
                                .name("Soporte DEMO")
                                .url("http://pruebas.com"))
                        .termsOfService("")
                        .license(new License().name("").url(""))
                );
    }

}