package ro.microservices.store.mappers;

import ro.microservices.store.entities.Product;
import ro.microservices.store.models.InventoryModel;
import ro.microservices.store.models.ProductModel;

public class ProductMapper {

    public static ProductModel toModel(final Product product, final InventoryModel inventoryModel){
        return ProductModel.builder()
                .code(product.getCode())
                .category(product.getCategory())

                .price(inventoryModel.getPrice())
                .stock(inventoryModel.getStock())

                .build();
    }
}
