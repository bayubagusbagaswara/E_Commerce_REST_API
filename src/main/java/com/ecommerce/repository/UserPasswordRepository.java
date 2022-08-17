package com.ecommerce.repository;

import com.ecommerce.entity.user.UserPassword;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPasswordRepository extends CrudRepository<UserPassword, String> {
}
