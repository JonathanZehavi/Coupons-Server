package com.john.coupons.controllers;

import com.john.coupons.dto.Coupon;
import com.john.coupons.service.CouponsService;
import com.john.coupons.exceptions.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coupons")
public class CouponsController {

    private final CouponsService couponsService;

    @Autowired
    CouponsController(CouponsService couponsService) {
        this.couponsService = couponsService;
    }

    @PostMapping
    public ResponseEntity<Coupon> addCoupon(@RequestBody Coupon coupon) throws ApplicationException {
        return new ResponseEntity<>(couponsService.createCoupon(coupon), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coupon> updateCoupon(@PathVariable("id") Long id, @RequestBody Coupon coupon) throws ApplicationException {
        return new ResponseEntity<>(couponsService.updateCoupon(id, coupon), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coupon> getCoupon(@PathVariable("id") Long id) throws ApplicationException {
        return new ResponseEntity<>(couponsService.getCouponById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoupon(@PathVariable("id") Long id) throws ApplicationException {
        couponsService.deleteCoupon(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Coupon>> getAllCoupons() throws ApplicationException {
        return new ResponseEntity<>(couponsService.getAllCoupons(), HttpStatus.OK);
    }

    @GetMapping("/byCategory")
    public ResponseEntity<List<Coupon>> getCouponByCategory(@RequestParam("category") String category) throws ApplicationException {
        return new ResponseEntity<>(couponsService.findByCategory(category), HttpStatus.OK);
    }

    @GetMapping("/byCompanyId/{companyId}")
    public ResponseEntity<List<Coupon>> getCouponByCompanyId(@PathVariable("companyId") Long companyId) throws ApplicationException {
        return new ResponseEntity<>(couponsService.getCouponsByCompanyId(companyId), HttpStatus.OK);
    }

    @GetMapping("/findByMaxPrice/{price}")
    public ResponseEntity<List<Coupon>> getCouponByMaxPrice(@PathVariable("price") float price) throws ApplicationException {
        return new ResponseEntity<>(couponsService.getCouponsByMaxPrice(price), HttpStatus.OK);
    }

    @GetMapping("/isCouponExist/{id}")
    public ResponseEntity<Boolean> isCouponExist(@PathVariable("id") Long id) throws ApplicationException {
        return new ResponseEntity<>(couponsService.existById(id), HttpStatus.OK);
    }

    @GetMapping("/parameterToSortByAscending")
    public ResponseEntity<List<Coupon>> findCouponsWithSortingAsc(@RequestParam("sortAscending") String parameterToSortBy) throws ApplicationException {
        return new ResponseEntity<>(couponsService.findCouponsWithSortingAscending(parameterToSortBy), HttpStatus.OK);
    }
    @GetMapping("/parameterToSortByDescending")
    public ResponseEntity<List<Coupon>> findCouponsWithSortingDesc(@RequestParam("sortDescending") String parameterToSortBy)throws ApplicationException {
        return new ResponseEntity<>(couponsService.findCouponsWithSortingDescending(parameterToSortBy), HttpStatus.OK);
    }

    @GetMapping("/pages/{pageNumber}/{pageSize}")
        public ResponseEntity<List<Coupon>> findCouponsWithPagination(@PathVariable("pageNumber") int offset, @PathVariable("pageSize") int pageSize) throws ApplicationException {
        return new ResponseEntity<>(couponsService.findCouponsWithPagination(offset, pageSize), HttpStatus.OK);
    }

    @GetMapping("/pageAndSortAscending/{pageNumber}/{pageSize}")
    public ResponseEntity<List<Coupon>> getAllCouponsWithPaginationAndSortingAsc(@PathVariable("pageNumber") int offset, @PathVariable("pageSize") int pageSize, @RequestParam("parameterToSortBy") String parameterToSortBy) throws ApplicationException {
        return new ResponseEntity<>(couponsService.findCouponsWithPaginationAndSortingAscending(offset, pageSize, parameterToSortBy), HttpStatus.OK);
    }

    @GetMapping("/pageAndSortDescending/{pageNumber}/{pageSize}")
    public ResponseEntity<List<Coupon>> getAllCouponsWithPaginationAndSortingDesc(@PathVariable("pageNumber") int offset, @PathVariable("pageSize") int pageSize, @RequestParam("sortDescending") String parameterToSortBy) throws ApplicationException {
        return new ResponseEntity<>(couponsService.findCouponsWithPaginationAndSortingDescending(offset, pageSize, parameterToSortBy), HttpStatus.OK);
    }
}

