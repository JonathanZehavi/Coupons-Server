package com.john.coupons.controllers;

import com.john.coupons.dto.Coupon;
import com.john.coupons.exceptions.ApplicationException;
import com.john.coupons.service.CouponsService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CouponsControllerTest {

    @Mock
    private CouponsService couponsService;
    private CouponsController underTest;

    @BeforeEach
    void setUp() {
        underTest = new CouponsController(couponsService);
    }

    @Test(expected = NullPointerException.class)
    public void testAddCoupon() throws ApplicationException {
        Coupon coupon = new Coupon(
                1L,
                "Get the best value for your money",
                "with a very great price",
                LocalDate.now(),
                LocalDate.of(2022, 12, 17),
                "Travel",
                1L,
                50,
                100,
                "image"
        );
        underTest.addCoupon(coupon);

        ArgumentCaptor<Coupon> couponArgumentCaptor = ArgumentCaptor.forClass(Coupon.class);
        Coupon couponValue = couponArgumentCaptor.getValue();

        assertThat(couponValue).isEqualTo(coupon);

    }

    @Test(expected = NullPointerException.class)
    public void testGetCoupon() throws ApplicationException {
        underTest.getCoupon(1L);
        verify(couponsService).getCouponById(1L);
    }

    @Test(expected = NullPointerException.class)
    public void testGetAllCoupons() throws ApplicationException {
        underTest.getAllCoupons();
        verify(couponsService).getAllCoupons();
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateCoupon() throws ApplicationException {
        Coupon coupon = couponsService.getCouponById(1L);
        coupon.setTitle("Get the best value");
        coupon.setDescription("without effort");
        coupon.setStartDate(LocalDate.now());
        coupon.setEndDate(LocalDate.of(2022, 12, 18));
        coupon.setCategory("Education");
        coupon.setCompanyId(1L);
        coupon.setAmount(60);
        coupon.setPrice(150);
        coupon.setImage("imageUrl");

        underTest.updateCoupon(coupon.getId(), coupon);

        ArgumentCaptor<Coupon> couponArgumentCaptor = ArgumentCaptor.forClass(Coupon.class);
        Coupon couponValue = couponArgumentCaptor.getValue();

        assertThat(couponValue).isEqualTo(coupon);
    }

    @Test(expected = NullPointerException.class)
    public void testDeleteCoupon() throws ApplicationException {
        underTest.deleteCoupon(1L);
        verify(couponsService).deleteCoupon(1L);
    }



}