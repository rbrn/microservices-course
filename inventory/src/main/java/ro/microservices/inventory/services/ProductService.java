package ro.microservices.inventory.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.microservices.inventory.ProductRepository;
import ro.microservices.inventory.entities.Product;
import ro.microservices.inventory.mappers.ProductMapper;
import ro.microservices.inventory.models.ProductModel;

import java.util.Optional;
import java.util.function.Function;

@Service
public class ProductService {

    private static Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductDispatcher productDispatcher;

    public ProductModel save(final ProductModel productModel){
        Product product = getByCode(productModel.getCode())
                .map(updateStock(productModel))
                .map(p->{
                     p.setPrice(productModel.getPrice());
                    return p;
                }).orElseGet(()-> ProductMapper.toEntity(productModel));
        return ProductMapper.toModel(productRepository.save(product));
    }

    private Function<Product, Product> updateStock(ProductModel productModel) {
        return p->{
            Integer initStock = p.getStock();
            if(initStock != productModel.getStock()) {
                p.setStock(productModel.getStock());
                if(initStock == 0 || productModel.getStock() == 0) {
                    logger.info("dispatching");
                    productDispatcher.dispatch(productModel);
                }
            }
            return p;
        };
    }

    public Optional<Product> getByCode(final String code) {
        return productRepository.findByCode(code).stream().findFirst();
    }

}
