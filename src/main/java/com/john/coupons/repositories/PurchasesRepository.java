package com.john.coupons.repositories;

import com.john.coupons.entities.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PurchasesRepository extends JpaRepository<PurchaseEntity, Long> {

    @Query("SELECT p FROM PurchaseEntity p" +
            " WHERE p.customer.id = :customerId ")
    List<PurchaseEntity> findAllByCustomerId(@Param("customerId") Long customerId);


    @Query("SELECT p FROM PurchaseEntity p " +
            " WHERE p.coupon.id = :couponId ")
    List<PurchaseEntity> findAllByCouponId(@Param("couponId") Long couponId);


    @Query("select (count(p) > 0) from PurchaseEntity p where p.id = ?1")
    boolean existsById(Long id);

}

