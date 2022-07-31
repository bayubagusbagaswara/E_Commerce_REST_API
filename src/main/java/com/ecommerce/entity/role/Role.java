package com.ecommerce.entity.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ecommerce.entity.base.BaseEntity;
import com.ecommerce.entity.enumerator.RoleName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "roles", uniqueConstraints = @UniqueConstraint(name = "roles_name_unique", columnNames = "name"))
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(name = "name")
    private RoleName name;

    public Role(RoleName name) {
        this.name = name;
    }

    @Override
    public Instant getCreatedAt() {
        return super.getCreatedAt();
    }

    @JsonIgnore
    @Override
    public Instant getUpdatedAt() {
        return super.getUpdatedAt();
    }

    @JsonIgnore
    @Override
    public String getCreatedBy() {
        return super.getCreatedBy();
    }

    @JsonIgnore
    @Override
    public String getUpdatedBy() {
        return super.getUpdatedBy();
    }

    @JsonIgnore
    @Override
    public String getId() {
        return super.getId();
    }
}
