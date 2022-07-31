package com.ecommerce.entity.region;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ecommerce.entity.audit.DateAudit;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.Instant;

@Entity
@Table(name = "districts", uniqueConstraints = {
        @UniqueConstraint(name = "district_unique_code", columnNames = "code")
})
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class District extends DateAudit {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "id", length = 64)
    private String id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @FieldNameConstants.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "province_id", foreignKey = @ForeignKey(name = "fk_region_province_id"), referencedColumnName = "id", nullable = false)
    private Province province;

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
}
