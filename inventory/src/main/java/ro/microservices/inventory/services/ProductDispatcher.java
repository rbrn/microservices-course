package ro.microservices.inventory.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.microservices.inventory.config.KafkaGateway;
import ro.microservices.inventory.models.ProductModel;

@Service
public class ProductDispatcher {

    private static Logger logger = LoggerFactory.getLogger(ProductDispatcher.class);

    @Autowired
    private KafkaGateway kafkaGateway;

    public void dispatch(final ProductModel productModel){
        logger.info("[dispatch]"+ productModel.toString());
        kafkaGateway.write(productModel);
    }

}
