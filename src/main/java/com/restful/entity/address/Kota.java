package com.restful.entity.address;

import com.restful.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "kota")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Kota extends BaseEntity {

    private String code;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_provinsi", foreignKey = @ForeignKey(name = "fk_kota_provinsi"))
    private Provinsi provinsi;
}
