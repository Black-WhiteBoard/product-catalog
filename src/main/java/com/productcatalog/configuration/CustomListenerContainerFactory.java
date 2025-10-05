package com.productcatalog.configuration;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * this configuration is needed only when multiple consumers are there with each having difference  kafka configuration
 *  i.e each consumer are consuming messages from different kafka servers
 */
//@Configuration
public class CustomListenerContainerFactory {



    @Value("${spring.kafka.bootstrap-servers}")
    private  String bootStrapServerUrl;

    @Value("${spring.kafka.consumer.group-id}")
    private  String consumerGroupId;

    @Value("${spring.kafka.consumer.value-deserializer}")
    private  String valueSerializer;

    @Value("${spring.kafka.consumer.key-deserializer}")
    private  String keySerializer;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,String> getCustomListenerContainerFactory(){

     ConcurrentKafkaListenerContainerFactory<String,String> listenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        listenerContainerFactory.setConsumerFactory(customConsumerFactory());
        return  listenerContainerFactory;
    }

    @Bean
    public ConsumerFactory<? super String,? super String> customConsumerFactory() {
        Map<String,Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootStrapServerUrl);
       // config.put(ConsumerConfig.GROUP_ID_CONFIG,consumerGroupId);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,valueSerializer);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,keySerializer);
        return new DefaultKafkaConsumerFactory<>(config);
    }

}
