package com.john.coupons.repositories;

import com.john.coupons.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomersRepository extends JpaRepository<CustomerEntity, Long> {


//    CustomerEntity findById(Long id);

    @Query(value = "SELECT cus.address, cus.birthday, cus.amount_of_children, cus.phone_number, u.id, u.first_name, u.last_name, u.username " +
            "FROM customers cus, users u " +
            "where cus.id = u.id", nativeQuery = true)
    List<CustomerEntity> getAllCustomers();


}

