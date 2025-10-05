package com.productcatalog.configuration;

import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
public class ProductConsumer {

    @Transactional
    @KafkaListener(topics = "productsTopic")
    public void processMessage(String msg) {
log.info("Product consumer processed msg : {}",msg);
    }

}
