package com.restful.entity;

import com.restful.entity.enumerator.Gender;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "suppliers", uniqueConstraints = {
        @UniqueConstraint(name = "suppliers_unique_email", columnNames = "email"),
        @UniqueConstraint(name = "suppliers_unique_mobile_phone", columnNames = "mobile_phone")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Supplier extends BaseEntity {
    
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Email
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "mobile_phone", length = 30)
    private String mobilePhone;

    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.NONE;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_address", foreignKey = @ForeignKey(name = "fk_supplier_address"))
    private Address address;

    @ManyToMany(mappedBy = "suppliers", fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();

    public void addProduct(Product product) {
        this.products.add(product);
        product.getSuppliers().add(this);
    }

    public void removeProduct(Product product) {
        this.getProducts().remove(product);
        product.getSuppliers().remove(this);
    }

    public void removeProducts() {
        for (Product product : new HashSet<>(products)) {
            removeProduct(product);
        }
    }
}
