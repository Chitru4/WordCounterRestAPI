package com.airtel.filereader;

import com.airtel.filereader.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class FileReaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileReaderApplication.class, args);
    }

}
