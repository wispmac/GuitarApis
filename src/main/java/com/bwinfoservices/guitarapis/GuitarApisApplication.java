package com.bwinfoservices.guitarapis;

import com.bwinfoservices.guitarapis.config.FilepathConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({FilepathConfig.class})
public class GuitarApisApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuitarApisApplication.class, args);
    }

}
