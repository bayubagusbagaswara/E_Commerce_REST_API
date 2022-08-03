package com.ecommerce.repository;

import com.ecommerce.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, String> {

    @Query("SELECT DISTINCT c FROM Coupon c where c.discount=(SELECT MAX(discount) FROM Coupon)")
    Coupon findMax();
}
