package ro.microservices.store.services;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.microservices.store.models.InventoryModel;

import javax.print.attribute.standard.Media;
import javax.validation.constraints.NotNull;

@FeignClient(name = "inventory-service", fallback = InventoryClientFallBack.class)
public interface InventoryClient {

    @RequestMapping(value = "/v1/products/{code}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<InventoryModel> getProductInventory(@PathVariable("code") @NotNull String code);
}
