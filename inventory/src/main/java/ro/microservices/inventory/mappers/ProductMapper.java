package ro.microservices.store.mappers;

import ro.microservices.store.entities.Product;
import ro.microservices.store.models.ProductModel;

public class ProductMapper {

    public static ProductModel toModel(final Product product){
        return ProductModel.builder()
                .code(product.getCode())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
    }
}
