package com.restful.entity.address;

import com.restful.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "kelurahan")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Kelurahan extends BaseEntity {

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_kecamatan", foreignKey = @ForeignKey(name = "fk_kelurahan_kecamatan"))
    private Kecamatan kecamatan;
}
