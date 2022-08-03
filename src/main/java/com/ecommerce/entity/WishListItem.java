package com.ecommerce.entity;

import com.ecommerce.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "wish_list_items")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class WishListItem extends BaseEntity {

    @Column(name = "date")
    private LocalDate date;

    // 1 Product bisa memiliki WishList yang banyak
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false, updatable = false)
    private Product product;
}
