package com.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Table Kupon ini berisi tentang nilai diskon dari sebuah produk
 * Jadi diskon ini akan berelasi dengan produk
 * Biasanya relasinya adalah one to one, karena 1 product hanya memiliki 1 kupon diskon
 */
@Entity
@Table(name = "coupons")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {

    @Column(name = "discount")
    private int discount;

    // ini hanya untuk cek apakah diskon tersedia atau tidak
    @Transient
    private boolean isDiscount;
}
