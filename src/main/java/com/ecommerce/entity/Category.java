package com.ecommerce.entity;

import com.ecommerce.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE categories SET status_record = 'INACTIVE' WHERE id = ?")
@Where(clause = "status_record = 'ACTIVE'")
public class Category extends BaseEntity {

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();
}
