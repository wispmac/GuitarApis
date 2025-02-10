package com.bwinfoservices.guitarapis.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "guitarapis")
public class FilepathConfig {
    private String fileLocation;
    private String audioPath;
    private String pdfPath;
}