package com.john.coupons.controllers;

import com.john.coupons.dto.Purchase;
import com.john.coupons.dto.PurchaseDetails;
import com.john.coupons.exceptions.ApplicationException;
import com.john.coupons.service.PurchasesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchasesController {

    private final PurchasesService purchasesService;

    @Autowired
    public PurchasesController(PurchasesService purchasesService) {
        this.purchasesService = purchasesService;
    }

    @PostMapping
    public ResponseEntity<Purchase> createPurchase(@RequestBody Purchase purchase) throws ApplicationException {
        return new ResponseEntity<>(purchasesService.createPurchase(purchase), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Purchase> updatePurchase(@PathVariable("id") Long id, @RequestBody Purchase purchase) throws ApplicationException {
        return new ResponseEntity<>(purchasesService.updatePurchase(id, purchase), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Purchase> getPurchaseById(@PathVariable("id") Long id) throws ApplicationException {
        return new ResponseEntity<>(purchasesService.getPurchaseById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchase(@PathVariable("id") Long id) throws ApplicationException {
        purchasesService.deletePurchase(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/ByCustomerId/{id}")
    public ResponseEntity<List<PurchaseDetails>> getPurchasesByCustomerId(@PathVariable("id") Long customerId) throws ApplicationException {
        return new ResponseEntity<>(purchasesService.getPurchasesByCustomerId(customerId), HttpStatus.OK);
    }

    @GetMapping("/ByCouponId/{id}")
    public ResponseEntity<Integer> getPurchasesByCouponId(@PathVariable("id") Long couponId) throws ApplicationException {
        return new ResponseEntity<>(purchasesService.amountOfPurchasesByCouponId(couponId), HttpStatus.OK);
    }


    @GetMapping("/getAllPurchasesDetails")
    public ResponseEntity<List<PurchaseDetails>> getPurchasesDetails() throws ApplicationException {
        return new ResponseEntity<>(purchasesService.getPurchasesDetails(), HttpStatus.OK);
    }

    @GetMapping("/getPurchaseDetails/{id}")
    public ResponseEntity<PurchaseDetails> getPurchasesDetailsById(@PathVariable("id") Long purchaseId) throws ApplicationException {
        return new ResponseEntity<>(purchasesService.getPurchasesDetailsById(purchaseId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Purchase>> getAllPurchases() throws ApplicationException {
        return new ResponseEntity<>(purchasesService.getAllPurchases(), HttpStatus.OK);
    }

    @GetMapping("/isPurchaseExist/{id}")
    public ResponseEntity<Boolean> isPurchaseExist(@PathVariable("id") Long id) throws ApplicationException {
        return new ResponseEntity<>(purchasesService.existById(id), HttpStatus.OK);
    }

    @GetMapping("/parameterToSortByAscending")
    public ResponseEntity<List<Purchase>> findPurchasesWithSortingAsc(@RequestParam("sortAscending") String parameterToSortBy) throws ApplicationException {
        return new ResponseEntity<>(purchasesService.findPurchasesWithSortingAscending(parameterToSortBy), HttpStatus.OK);
    }

    @GetMapping("/parameterToSortByDescending")
    public ResponseEntity<List<Purchase>> findPurchasesWithSortingDesc(@RequestParam("sortDescending") String parameterToSortBy) throws ApplicationException {
        return new ResponseEntity<>(purchasesService.findPurchasesWithSortingDescending(parameterToSortBy), HttpStatus.OK);
    }

    @GetMapping("/pages/{pageNumber}/{pageSize}")
    public ResponseEntity<List<Purchase>> findPurchasesWithPagination(@PathVariable("pageNumber") int offset, @PathVariable("pageSize") int pageSize) throws ApplicationException {
        return new ResponseEntity<>(purchasesService.findPurchasesWithPagination(offset, pageSize), HttpStatus.OK);
    }

    @GetMapping("/pagesOfPurchaseDetails/ByCustomerId/{id}/{pageNumber}/{pageSize}")
    public ResponseEntity<List<PurchaseDetails>> getPurchasesByCustomerId(@PathVariable("id") Long customerId, @PathVariable("pageNumber") int offset, @PathVariable("pageSize") int pageSize) throws ApplicationException {
        return new ResponseEntity<>(purchasesService.findPurchasesDetailsByCustomerIdWithPagination(customerId, offset, pageSize), HttpStatus.OK);
    }

    @GetMapping("/pagesOfPurchaseDetails/{pageNumber}/{pageSize}")
    public ResponseEntity<List<PurchaseDetails>> findPurchasesDetailsWithPagination(@PathVariable("pageNumber") int offset, @PathVariable("pageSize") int pageSize) throws ApplicationException {
        return new ResponseEntity<>(purchasesService.findPurchasesDetailsWithPagination(offset, pageSize), HttpStatus.OK);
    }

    @GetMapping("/pageAndSortAscending{pageNumber}/{pageSize}")
    public ResponseEntity<List<Purchase>> getAllPurchasesWithPaginationAndSortingAsc(@PathVariable("pageNumber") int offset, @PathVariable("pageSize") int pageSize, @RequestParam("parameterToSortBy") String parameterToSortBy) throws ApplicationException {
        return new ResponseEntity<>(purchasesService.findPurchasesWithPaginationAndSortingAscending(offset, pageSize, parameterToSortBy), HttpStatus.OK);
    }

    @GetMapping("/pageAndSortDescending{pageNumber}/{pageSize}")
    public ResponseEntity<List<Purchase>> getAllPurchasesWithPaginationAndSortingDESC(@PathVariable("pageNumber") int offset, @PathVariable("pageSize") int pageSize, @RequestParam("parameterToSortBy") String parameterToSortBy) throws ApplicationException {
        return new ResponseEntity<>(purchasesService.findPurchasesWithPaginationAndSortingDescending(offset, pageSize, parameterToSortBy), HttpStatus.OK);
    }

}

