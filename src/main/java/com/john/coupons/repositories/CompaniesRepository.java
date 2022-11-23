package com.john.coupons.repositories;

import com.john.coupons.entities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompaniesRepository extends JpaRepository<CompanyEntity, Long> {

    @Query("SELECT c FROM CompanyEntity c")
    List<CompanyEntity> getAllCompanies();

    @Query("SELECT CASE WHEN COUNT(c.companyName) = 1 THEN true ELSE false END from CompanyEntity c where c.companyName = :companyName ")
    boolean existByName(@Param("companyName") String companyName);

    @Query("select (count(c) > 0) from CompanyEntity c where c.phoneNumber = :phoneNumber")
    boolean existsByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}

