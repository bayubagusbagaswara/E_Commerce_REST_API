package com.restful.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "product_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetail extends BaseEntity {

    @Column(name = "SKU", nullable = false, length = 50)
    private String sku;

    @Column(name = "description", nullable = false, length = 500)
    private String description;
}
