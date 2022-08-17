package com.ecommerce.entity.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_detail")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetail {

    @Id
    @Column(name = "id_product")
    private String id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_product", foreignKey = @ForeignKey(name = "fk_product_detail_product_id"))
    private Product product;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}
