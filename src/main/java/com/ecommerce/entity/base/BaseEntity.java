package com.ecommerce.entity.base;

import com.ecommerce.entity.audit.UserDateAudit;
import com.ecommerce.entity.enumerator.StatusRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseEntity extends UserDateAudit {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false, length = 64)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_record", nullable = false)
    private StatusRecord statusRecord = StatusRecord.ACTIVE;
}
