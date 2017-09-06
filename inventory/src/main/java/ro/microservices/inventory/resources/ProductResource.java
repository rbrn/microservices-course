package ro.microservices.store.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.microservices.store.ProductRepository;
import ro.microservices.store.mappers.ProductMapper;
import ro.microservices.store.models.ProductModel;

import java.util.Objects;

@RestController
@RequestMapping("v1/products")
public class ProductResource {

    private final ProductRepository productRepository;

    @Autowired
    public ProductResource(final ProductRepository productRepository){
        this.productRepository = Objects.requireNonNull(productRepository, "");
    }

    @GetMapping(value = "/{code}")
    public ResponseEntity<ProductModel> getProductsByCode(@PathVariable("code") final String code){
        return productRepository.findByCode(code).stream().findFirst()
                .map(e-> ResponseEntity.ok( ProductMapper.toModel(e)))
                .orElseGet(() -> new ResponseEntity(HttpStatus.NOT_FOUND));
    }

}
