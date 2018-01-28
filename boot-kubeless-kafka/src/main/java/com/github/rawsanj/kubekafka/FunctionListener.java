package com.github.rawsanj.kubekafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class FunctionListener {

    private final Logger logger = LoggerFactory.getLogger(FunctionListener.class);

    @KafkaListener(topics = "${kubeless.kafka.topic}", group = "${kubeless.kafka.group}")
    public void listenGreeting(String message) {

        // Process Message here.
        logger.info("Message Received: {}", message);

    }

}
