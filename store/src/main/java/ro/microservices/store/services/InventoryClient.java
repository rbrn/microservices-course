package ro.microservices.store.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.microservices.store.models.InventoryModel;

import java.math.BigDecimal;

@Service
public class InventoryClient {

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${inventory.api.url}")
    private  String apiUrl;

    public InventoryModel getProductInventory(final String code){
        final String finalApi = apiUrl+"/v1/products/"+code;
        try {
            return restTemplate.getForEntity(finalApi, InventoryModel.class).getBody();
        }catch (Exception e) {
            return InventoryModel.builder().price(BigDecimal.ZERO).stock(0).build();
        }

    }
}
