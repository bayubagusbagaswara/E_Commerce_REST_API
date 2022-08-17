package com.ecommerce.entity;

import com.ecommerce.entity.base.BaseEntity;
import com.ecommerce.entity.enumerator.Gender;
import com.ecommerce.entity.product.Product;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "suppliers", uniqueConstraints = {
        @UniqueConstraint(name = "supplier_unique_email", columnNames = "email"),
        @UniqueConstraint(name = "supplier_unique_mobile_phone", columnNames = "mobile_phone")
})
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE suppliers SET status_record = 'INACTIVE' WHERE id = ?")
@Where(clause = "status_record = 'ACTIVE'")
public class Supplier extends BaseEntity {
    
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Email
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "mobile_phone", length = 30)
    private String mobilePhone;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @FieldNameConstants.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", foreignKey = @ForeignKey(name = "fk_supplier_address_id"))
    private SupplierAddress supplierAddress;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @FieldNameConstants.Exclude
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
