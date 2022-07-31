package com.restful.entity;

import com.restful.entity.base.BaseEntity;
import com.restful.entity.region.UrbanVillage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

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

    @Column(name = "street", length = 100)
    private String street;

    // harusnya postal code kita taruh di kelurahan saja
    // karena bisa saja user salah memasukkan postal code nya
    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @OneToOne
    @JoinColumn(name = "id_kelurahan", foreignKey = @ForeignKey(name = "fk_address_kelurahan"))
    private UrbanVillage urbanVillage;
}
