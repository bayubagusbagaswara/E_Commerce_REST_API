package com.ecommerce.entity;

import com.ecommerce.entity.base.BaseEntity;
import com.ecommerce.entity.user.User;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "refresh_tokens")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken extends BaseEntity {

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @FieldNameConstants.Exclude
    @OneToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_refresh_token_user_id"), referencedColumnName = "id")
    private User user;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    @Column(name = "expired_date", nullable = false)
    private Instant expiredDate;

    public RefreshToken(User user, String refreshToken, Instant expiredDate, Instant createdAt) {
        this.user = user;
        this.refreshToken = refreshToken;
        this.expiredDate = expiredDate;
        this.setCreatedAt(createdAt);
    }

    @Override
    public void setCreatedAt(Instant createdAt) {
        super.setCreatedAt(createdAt);
    }

    @Override
    public void setUpdatedAt(Instant updatedAt) {
        super.setUpdatedAt(updatedAt);
    }
}
