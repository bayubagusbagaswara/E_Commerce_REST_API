package com.ecommerce.entity;

import com.ecommerce.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

/**
 * Table Kupon ini berisi tentang nilai diskon dari sebuah produk
 * Jadi diskon ini akan berelasi dengan produk
 * Biasanya relasinya adalah one to one, karena 1 product hanya memiliki 1 kupon diskon
 */
@Entity
@Table(name = "coupons")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Coupon extends BaseEntity {

    @Column(name = "discount")
    private int discount;

    // ini hanya untuk cek apakah diskon tersedia atau tidak
    @Transient
    private boolean isDiscount;

    @JsonIgnore
    @Override
    public Instant getCreatedAt() {
        return super.getCreatedAt();
    }

    @JsonIgnore
    @Override
    public Instant getUpdatedAt() {
        return super.getUpdatedAt();
    }

    @JsonIgnore
    @Override
    public String getCreatedBy() {
        return super.getCreatedBy();
    }

    @JsonIgnore
    @Override
    public String getUpdatedBy() {
        return super.getUpdatedBy();
    }
}
