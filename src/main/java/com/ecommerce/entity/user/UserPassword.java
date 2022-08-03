package com.ecommerce.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_password")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPassword {

    @Id
    @Column(name = "user_id")
    private String id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_password_user_id"))
    private User user;

    @Column(name = "password")
    private String password;
}
