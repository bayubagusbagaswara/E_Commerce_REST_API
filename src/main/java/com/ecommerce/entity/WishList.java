package com.ecommerce.entity;

import com.ecommerce.entity.base.BaseEntity;
import com.ecommerce.entity.enumerator.StatusRecord;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "wish_lists")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class WishList extends BaseEntity {

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "session_token")
    private String sessionToken;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<WishListItem> items = new HashSet<>();

    @JsonIgnore
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
    public StatusRecord getStatusRecord() {
        return super.getStatusRecord();
    }
}
