package com.ecommerce.entity.product;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "product_details", uniqueConstraints = {
        @UniqueConstraint(name = "product_detail_unique_sku", columnNames = "sku")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetail {

    @Id
    @Column(name = "product_id")
    private String id;

    @Column(name = "sku", nullable = false, length = 50)
    private String sku;

    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @OneToOne
    @MapsId
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "fk_product_detail_product_id"))
    private Product product;

    public ProductDetail(String sku, String description) {
        this.sku = sku;
        this.description = description;
    }
}
