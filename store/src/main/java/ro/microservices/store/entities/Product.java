package ro.microservices.store.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {


    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String code;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Integer stock;

    @ManyToOne
    private Category category;

}
