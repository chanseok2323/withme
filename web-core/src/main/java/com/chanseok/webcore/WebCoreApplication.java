package com.chanseok.webcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class WebCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebCoreApplication.class, args);
    }

}
