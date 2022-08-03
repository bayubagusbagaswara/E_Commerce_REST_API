package com.ecommerce.entity;

import com.ecommerce.entity.region.UrbanVillage;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

/**
 * Address ini berelasi One-To-One dengan Supplier
 */
@Entity
@Table(name = "supplier_address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierAddress {

    @Id
    @Column(name = "supplier_id")
    private String id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "supplier_id", foreignKey = @ForeignKey(name = "fk_address_supplier_id"))
    private Supplier supplier;

    @Column(name = "full_address", columnDefinition = "TEXT")
    private String fullAddress;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @FieldNameConstants.Exclude
    @OneToOne
    @JoinColumn(name = "urban_village_id", foreignKey = @ForeignKey(name = "fk_address_urban_village_id"), referencedColumnName = "id")
    private UrbanVillage urbanVillage;
}
