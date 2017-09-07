package ro.microservices.store.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;
import ro.microservices.store.entities.Product;
import ro.microservices.store.models.InventoryModel;
import ro.microservices.store.repositories.ProductRepository;
import org.springframework.messaging.Message;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class InventoryReceiver {

    private ProductRepository productRepository;

    private static Logger logger = LoggerFactory.getLogger(InventoryReceiver.class);

    @StreamListener("stockChannel")
    public void onReceiving(final Message<InventoryModel> messages){
        InventoryModel inventoryModel = messages.getPayload();

        logger.info("Ack received: " + inventoryModel.toString());

        Collection<Product>  products = productRepository.findByCode(inventoryModel.getCode())
                    .stream().map(p-> {
                        p.setIsPublished(inventoryModel.getStock() > 0);
                        return p;
                }).collect(Collectors.toList());
        productRepository.save(products);
    }

}
