package com.john.coupons.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "purchases")
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "date_of_purchase", nullable = false)
    @CreationTimestamp
    private LocalDateTime dateOfPurchase;

    @Column(name = "total_price", nullable = false)
    private float totalPrice;

    @ManyToOne
    private CustomerEntity customer;

    @ManyToOne
    private CouponEntity coupon;

}


