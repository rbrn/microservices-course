package ro.microservices.store.config;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ro.microservices.store.models.ProductModel;

@MessagingGateway
public interface KafkaGateway {

    @Gateway(replyChannel = "stockChannel")
    void write(ProductModel client);

}
