package com.restful.entity;

import com.restful.entity.address.Kelurahan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseEntity {

    @Column(name = "street", length = 100)
    private String street;

    @OneToOne
    @JoinColumn(name = "id_kelurahan", foreignKey = @ForeignKey(name = "fk_kelurahan"))
    private Kelurahan kelurahan;
}
