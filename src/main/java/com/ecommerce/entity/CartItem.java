package com.ecommerce.entity;

import com.ecommerce.entity.base.BaseEntity;
import com.ecommerce.entity.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

/**
 * Cart item berisi semua data product/item yang akan kita masukkan kedalam keranjang belanja consumer
 * Misal Product MacBook Pro, kita mau beli sebanyak 5
 * Maka di table CartItem akan ada data sebagai berikut:
 * product_id = MacBook Pro
 * quantity = 5
 * date = tanggal cart item nya dibuat
 * Jadi cart item ini belum masuk ke menu keranjang, tetapi masih memilih product beserta jumlahnya
 */
@Entity
@Table(name = "cart_items")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class CartItem extends BaseEntity {

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "date")
    private LocalDate date;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @FieldNameConstants.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "fk_cart_item_product_id"), nullable = false, updatable = false)
    private Product product;

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
