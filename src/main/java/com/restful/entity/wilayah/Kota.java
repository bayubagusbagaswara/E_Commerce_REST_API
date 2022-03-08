package com.restful.entity.wilayah;

import com.restful.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "kota", uniqueConstraints = {
        @UniqueConstraint(name = "kota_unique_code", columnNames = "code")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Kota extends BaseEntity {

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_provinsi", foreignKey = @ForeignKey(name = "fk_kota_provinsi"))
    private Provinsi provinsi;
}
