package ro.microservices.store.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ro.microservices.store.models.InventoryModel;

import java.math.BigDecimal;

@Component
public class InventoryClientFallBack implements InventoryClient {

    @Override
    public ResponseEntity<InventoryModel> getProductInventory(final String code) {
        return ResponseEntity.ok(InventoryModel.builder().price(BigDecimal.ZERO).stock(0).build());
    }
}
