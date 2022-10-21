package com.john.coupons.repositories;

import com.john.coupons.entities.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CouponsRepository extends JpaRepository<CouponEntity, Long> {


    @Query("select c from CouponEntity c where c.company.id = ?1")
    List<CouponEntity> getCouponByCompanyId(@Param("id") Long companyId);


    @Query("SELECT c FROM CouponEntity c")
    List<CouponEntity> getAllCoupons();

    @Query("select c from CouponEntity c where c.category = ?1")
    List<CouponEntity> getCouponsByCategory(String categoryName);

    @Query("select c from CouponEntity c where c.price <= ?1")
    List<CouponEntity> findByPriceLessThanEqual(float price);


    @Modifying
    @Query("select c from CouponEntity c where c.endDate < now()")
    List<CouponEntity> findAllByEndDateBefore();
}

