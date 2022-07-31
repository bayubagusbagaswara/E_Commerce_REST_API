package com.ecommerce.entity;

import com.ecommerce.entity.base.BaseEntity;
import com.ecommerce.entity.region.UrbanVillage;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "address", uniqueConstraints = {
        @UniqueConstraint(name = "address_unique_postal_code", columnNames = "postal_code")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE address SET status_record = 'INACTIVE' WHERE id = ?")
@Where(clause = "status_record = 'ACTIVE'")
public class Address extends BaseEntity {

    @Column(name = "full_address", columnDefinition = "TEXT")
    private String fullAddress;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @FieldNameConstants.Exclude
    @OneToOne
    @JoinColumn(name = "urban_village_id", foreignKey = @ForeignKey(name = "fk_address_urban_village_id"), referencedColumnName = "id")
    private UrbanVillage urbanVillage;
}
