package com.ecommerce.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ecommerce.entity.base.BaseEntity;
import com.ecommerce.entity.enumerator.StatusRecord;
import com.ecommerce.entity.role.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    @Column(name = "first_name", length = 40, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 40, nullable = false)
    private String lastName;

    @Email
    @Column(name = "email", length = 40)
    private String email;

    @Column(name = "username", length = 20, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_id"), referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "fk_role_id"), referencedColumnName = "id")
    )
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void removeRole(Role role) {
        this.getRoles().remove(role);
    }

    public void removeRoles() {
        for (Role role : new HashSet<>(roles)) {
            removeRole(role);
        }
    }

    @Override
    public Instant getCreatedAt() {
        return super.getCreatedAt();
    }

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

    @Override
    public String getId() {
        return super.getId();
    }

    @JsonIgnore
    @Override
    public StatusRecord getStatusRecord() {
        return super.getStatusRecord();
    }
}
