package com.productcatalog.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.productcatalog.entity.Product;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.protocol.Message;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
public class ProductConsumer {

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @KafkaListener(topics = "productsTopic",containerFactory = "getCustomListenerContainerFactory" ,groupId = "products-group-1")
    public void processMessage(GenericMessage<String> msg) {
log.info("Product consumer processed msg : {}",msg.getPayload());

        ObjectMapper mapper =new ObjectMapper();
        try {
            Product  product  =  mapper.readValue(msg.getPayload(),Product.class);
            log.info(product.getName());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

}
