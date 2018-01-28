package com.github.rawsanj.kubekafka;

import com.github.rawsanj.kubekafka.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class BootKubelessKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootKubelessKafkaApplication.class, args);
	}
}
