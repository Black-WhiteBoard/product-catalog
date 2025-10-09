package com.productcatalog.configuration;

import com.productcatalog.entity.Product;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.apache.kafka.common.serialization. ByteArrayDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

/**
 * this configuration is needed only when multiple consumers are there with each having difference  kafka configuration
 *  i.e each consumer are consuming messages from different kafka servers
 */
@Configuration
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
    public ConcurrentKafkaListenerContainerFactory<String,Product> getCustomListenerContainerFactory(){

     ConcurrentKafkaListenerContainerFactory<String,Product> listenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        listenerContainerFactory.setConsumerFactory(customConsumerFactory());
        return  listenerContainerFactory;
    }

    @Bean
    public ConsumerFactory<String,Product> customConsumerFactory() {
        Map<String,Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootStrapServerUrl);
        config.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG,"read_committed");
        //config.put(ConsumerConfig.ISOLATION_LEVEL_DOC,"read_committed");
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,valueSerializer);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,keySerializer);

        JsonDeserializer<Product> jsonDeserializer = new JsonDeserializer<>(Product.class);
        jsonDeserializer.addTrustedPackages("com.productcatalog.entity");
        return new DefaultKafkaConsumerFactory<>(config,new StringDeserializer(),jsonDeserializer);
    }

}
