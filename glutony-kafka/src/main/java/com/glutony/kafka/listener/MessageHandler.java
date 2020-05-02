package com.glutony.kafka.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author 拓仲 on 2020/5/2
 */
@Component
public class MessageHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageHandler.class);

    @KafkaListener(topics = {"test-topic"})
    public void handle(String message) {
        LOGGER.info("开始处理消息1：{}", message);
    }

    @KafkaListener(topics = {"test-topic"})
    public void handle(ConsumerRecord<String, String> record) {
        LOGGER.info("开始处理消息2：{}", record);
    }
}
