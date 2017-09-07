package ro.microservices.inventory.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.microservices.inventory.mappers.ProductMapper;
import ro.microservices.inventory.models.ProductModel;
import ro.microservices.inventory.services.ProductService;

@RestController
@RequestMapping("v1/products")
public class ProductResource {

    @Autowired
    private ProductService productService;


    @GetMapping(value = "/{code}")
    public ResponseEntity<ProductModel> getProductsByCode(@PathVariable("code") final String code){
        return productService.getByCode(code)
                .map(e-> ResponseEntity.ok( ProductMapper.toModel(e)))
                .orElseGet(() -> new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/")
    public ResponseEntity<ProductModel> addProducts(@RequestBody final ProductModel productModel){
        return ResponseEntity.ok(productService.save(productModel));
    }

}
