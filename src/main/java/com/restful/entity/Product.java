package com.restful.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @OneToOne
    @JoinColumn(name = "id_product_detail", foreignKey = @ForeignKey(name = "fk_product_product_detail"))
    private ProductDetail productDetail;

    @ManyToOne
    @JoinColumn(name = "id_category", foreignKey = @ForeignKey(name = "fk_product_category"))
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "product_supplier",
            joinColumns = @JoinColumn(name = "id_product"),
            foreignKey = @ForeignKey(name = "fk_product"),
            inverseJoinColumns = @JoinColumn(name = "id_supplier"),
            inverseForeignKey = @ForeignKey(name = "fk_supplier")
    )
    private Set<Supplier> suppliers = new HashSet<>();

    public void addSupplier(Supplier supplier) {
        this.suppliers.add(supplier);
        supplier.getProducts().add(this);
    }

    public void removeSupplier(Supplier supplier) {
        this.getSuppliers().remove(supplier);
        supplier.getProducts().remove(this);
    }

    public void removeSuppliers() {
        for (Supplier supplier : new HashSet<>(suppliers)) {
            removeSupplier(supplier);
        }
    }
}
