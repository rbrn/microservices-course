package ro.microservices.store.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.microservices.store.repositories.ProductRepository;
import ro.microservices.store.mappers.ProductMapper;
import ro.microservices.store.models.ProductModel;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InventoryClient inventoryClient;

    public Optional<ProductModel> getByCode(final String code) {
        return productRepository.findByCode(code).stream().findFirst().map(p ->
            ProductMapper.toModel(
                    p,
                    inventoryClient.getProductInventory(p.getCode())
            )
        );
    }

}
