package ro.microservices.store.services;

import kafka.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;
import ro.microservices.store.models.InventoryModel;
import ro.microservices.store.repositories.ProductRepository;
import ro.microservices.store.mappers.ProductMapper;
import ro.microservices.store.models.ProductModel;

import java.util.Optional;

@Service
public class ProductService {

    private static Logger LOG = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InventoryClient inventoryClient;

    public Optional<ProductModel> getByCode(final String code) {
        return productRepository.findByCode(code).stream().findFirst().map(p ->
            ProductMapper.toModel(
                    p,
                    inventoryClient.getProductInventory(p.getCode()).getBody()
            )
        );
    }

    @StreamListener("stockChannel")
    public void onReceiving(final org.springframework.messaging.Message<InventoryModel> message) {
        InventoryModel inventoryModel = message.getPayload();
        LOG.info(inventoryModel.toString());
    }

}
