package com.john.coupons.repositories;

import com.john.coupons.dto.PurchaseDetails;
import com.john.coupons.entities.PurchaseEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

public interface PurchasesRepository extends JpaRepository<PurchaseEntity, Long> {

    @Query("SELECT p FROM PurchaseEntity p" +
            " WHERE p.customer.id = :customerId ")
    List<PurchaseEntity> findAllByCustomerId(@Param("customerId") Long customerId);

    @Query("SELECT (count(p) > 0) from PurchaseEntity p where p.id = ?1")
    boolean existsById(@NotNull Long id);

    @Query(value = "SELECT COUNT(p.purchase_id) " +
            "FROM purchased_coupons p " +
            "where p.coupon_id = ?", nativeQuery = true)
    Integer amountOfPurchasesByCouponId(Long couponId);

}

