package com.paradise.eventnotificator.service;

import com.paradise.eventnotificator.dto.EventChangeKafkaMessage;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaEventConsumerService {

    private final static Logger logger = LoggerFactory.getLogger(KafkaEventConsumerService.class);
    private final NotificationService notificationService;

    @KafkaListener(topics = "change-event-topic", containerFactory = "containerFactory")
    public void listenerChangeEvent(
            ConsumerRecord<Long, EventChangeKafkaMessage> record
    ){
        logger.info("Received change event from Kafka topic: {}", record.topic());
        System.out.println(record.value());
        notificationService.save(record.value());
    }


}
