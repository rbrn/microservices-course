package ro.microservices.inventory.config;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ro.microservices.inventory.models.ProductModel;

@MessagingGateway
public interface KafkaGateway {

    @Gateway(replyChannel = "stockChannel")
    void write(ProductModel client);

}
