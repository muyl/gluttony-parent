package com.glutony.kafka.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
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

    @KafkaListener(topics = {"test-topic"}, containerFactory = "ackContainerFactory")
    public void handle(ConsumerRecord record, Acknowledgment acknowledgment) {
        try {
            String message = (String) record.value();
            LOGGER.info("收到消息: {}", message);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            // 手动提交 offset
            acknowledgment.acknowledge();
        }
    }
}
