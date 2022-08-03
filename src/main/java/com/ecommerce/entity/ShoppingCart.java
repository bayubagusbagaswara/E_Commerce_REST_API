package com.ecommerce.entity;

import com.ecommerce.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * ShoppingCart adalah table yang berisi daftar belanjaan consumer beserta jumlah yang harus dibayarkan
 * daftar belanja dan harga masing-masing item dapat diambil dari table CartItem
 * harga pada CartItem akan dijumlah lalu dimasukkan ke data totalPrice
 */
@Entity
@Table(name = "shopping_carts")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart extends BaseEntity {

    @Column(name = "data")
    private LocalDate date;

    @Transient
    private Double totalPrice;

    @Transient
    private int itemsNumber;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CartItem> items = new HashSet<>();

    @Column(name = "session_token")
    private String sessionToken;

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
