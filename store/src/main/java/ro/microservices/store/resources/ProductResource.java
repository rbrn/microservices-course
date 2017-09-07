package ro.microservices.store.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.microservices.store.models.ProductModel;
import ro.microservices.store.services.ProductService;

@RestController
@RequestMapping("v1/products")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/{code}")
    public ResponseEntity<ProductModel> getProductsByCode(@PathVariable("code") final String code){
        return productService.getByCode(code).map(p-> ResponseEntity.ok(p))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
