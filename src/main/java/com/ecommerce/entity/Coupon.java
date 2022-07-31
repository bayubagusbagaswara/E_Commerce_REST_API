package com.ecommerce.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int discount;

    @Transient
    private boolean isDiscount;
}
