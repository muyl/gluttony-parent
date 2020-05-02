package com.glutony.kafka.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 拓仲 on 2020/5/2
 */
@RestController
public class SendMessageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SendMessageController.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @GetMapping("/send")
    public String send(String params) {
        LOGGER.info("发送消息至KAFKA,{}", params);
        kafkaTemplate.send("test-topic", params);
        return "success";
    }
}
