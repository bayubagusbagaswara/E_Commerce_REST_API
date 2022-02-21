package com.restful.entity.address;

import com.restful.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "kecamatan")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Kecamatan extends BaseEntity {

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_kota", foreignKey = @ForeignKey(name = "fk_kecamatan_kota"))
    private Kota kota;
}
