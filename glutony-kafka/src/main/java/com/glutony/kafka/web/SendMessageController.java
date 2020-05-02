package com.glutony.kafka.web;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * @author 拓仲 on 2020/5/2
 */
@RestController
public class SendMessageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SendMessageController.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @GetMapping("/send")
    public String send(String params) throws ExecutionException, InterruptedException {
        LOGGER.info("发送消息至KAFKA,{}", params);
        ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send("test-topic", params);
        SendResult<String, String> sendResult = listenableFuture.get();
        listenableFuture.addCallback(success -> {
            LOGGER.info("kafka Producer发送消息成功！topic=" + sendResult.getRecordMetadata().topic() + ",partition" + sendResult.getRecordMetadata().partition() + ",offset=" + sendResult.getRecordMetadata().offset());
        }, failure -> {
            LOGGER.error("kafka Producer发送消息失败！sendResult=" + JSONObject.toJSONString(sendResult.getProducerRecord()));
        });

        return "success";
    }
}
