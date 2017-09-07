package ro.microservices.inventory.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class ProductModel {

    private String code;

    private BigDecimal price;

    private Integer stock;
}
