package com.john.coupons.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany
    @JoinTable(name = "purchased_coupons",
            joinColumns = @JoinColumn(name = "purchase_id"),
            inverseJoinColumns = @JoinColumn(name = "coupon_id"))
    private List<CouponEntity> coupons = new ArrayList<>();

    public void addCoupon(CouponEntity couponEntity) {
        coupons.add(couponEntity);
    }


}


