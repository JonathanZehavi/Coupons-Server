package com.john.coupons.repositories;

import com.john.coupons.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoriesRepository extends JpaRepository<CategoryEntity, Long> {


    @Query("select (count(c) > 0) from CategoryEntity c where c.name = ?1")
    Boolean existsByName(@Param("name") String name);

    @Query("select c from CategoryEntity c where c.name = ?1")
    CategoryEntity findByName(@Param("name") String name);
}
