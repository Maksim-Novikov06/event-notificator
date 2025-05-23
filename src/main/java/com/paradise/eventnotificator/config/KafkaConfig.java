package com.paradise.eventnotificator.config;

import com.paradise.eventnotificator.dto.EventChangeKafkaMessage;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Bean
    public ConsumerFactory<Long, EventChangeKafkaMessage> consumerFactory() {

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "notificator-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);

        var factory = new DefaultKafkaConsumerFactory<Long, EventChangeKafkaMessage>(props);

        factory.setValueDeserializer(new JsonDeserializer<>(EventChangeKafkaMessage.class, false));


        return factory;
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<Long, EventChangeKafkaMessage> containerFactory(
            ConsumerFactory<Long, EventChangeKafkaMessage> consumerFactory
    ) {
        var factory = new  ConcurrentKafkaListenerContainerFactory<Long, EventChangeKafkaMessage>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}
