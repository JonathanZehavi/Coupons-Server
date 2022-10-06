package com.john.coupons.service;

import com.john.coupons.converters.*;
import com.john.coupons.dto.Coupon;
import com.john.coupons.dto.Customer;
import com.john.coupons.dto.Purchase;
import com.john.coupons.dto.PurchaseDetails;
import com.john.coupons.entities.PurchaseEntity;
import com.john.coupons.enums.ErrorType;
import com.john.coupons.exceptions.ApplicationException;
import com.john.coupons.repositories.PurchasesRepository;
import com.john.coupons.validations.PurchaseValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchasesService {

    private final PurchasesRepository purchasesRepository;
    private final PurchaseValidations purchaseValidations;
    private final CouponsService couponsService;
    private final CustomersService customersService;

    @Autowired
    public PurchasesService(PurchasesRepository purchasesRepository, PurchaseValidations purchaseValidations, CouponsService couponsService, CustomersService customersService) {
        this.purchasesRepository = purchasesRepository;
        this.purchaseValidations = purchaseValidations;
        this.couponsService = couponsService;
        this.customersService = customersService;
    }

    public Purchase createPurchase(Purchase purchase) throws ApplicationException {
        Coupon coupon = couponsService.getCouponById(purchase.getCouponId());
        Customer customer = customersService.getCustomerById(purchase.getCustomerId());
        purchaseValidations.validatePurchase(purchase);
        purchase.setTotalPrice(coupon.getPrice() * purchase.getAmount());
        PurchaseEntity purchaseEntity = PurchaseEntityConverter.from(purchase);
        purchaseEntity.setCoupon(CouponEntityConverter.from(coupon));
        coupon.setAmount(coupon.getAmount() - purchase.getAmount());
        purchaseEntity.setCustomer(CustomerEntityConverter.from(customer));
        couponsService.updateCoupon(coupon.getId(), coupon);
        purchaseEntity = purchasesRepository.save(purchaseEntity);
        return PurchaseDtoConverter.from(purchaseEntity);
    }


    public Purchase updatePurchase(Long id, Purchase purchase) throws ApplicationException {
        purchaseValidations.validatePurchase(purchase);
        Coupon coupon = couponsService.getCouponById(purchase.getCouponId());
        Customer customer = customersService.getCustomerById(purchase.getId());
        purchase = getPurchaseById(id);
        PurchaseEntity purchaseEntity = PurchaseEntityConverter.from(purchase);
        purchasesRepository.save(purchaseEntity);
        purchaseEntity.setCoupon(CouponEntityConverter.from(coupon));
        purchaseEntity.setCustomer(CustomerEntityConverter.from(customer));
        couponsService.updateCoupon(coupon.getId(), coupon);
        return PurchaseDtoConverter.from(purchaseEntity);
    }

    public Purchase getPurchaseById(Long id) throws ApplicationException {
        PurchaseEntity purchaseEntity = purchasesRepository.findById(id).orElseThrow(() ->
                new ApplicationException(ErrorType.ID_DOES_NOT_EXIST));
        return PurchaseDtoConverter.from(purchaseEntity);
    }

    public void deletePurchase(Long id) throws ApplicationException {
        if (id == null || id <=0) {
            throw new ApplicationException(ErrorType.ID_DOES_NOT_EXIST);
        }
        purchasesRepository.deleteById(id);
    }

    public List<PurchaseDetails> getPurchasesByCustomerId(Long customerId) throws ApplicationException {
        List<PurchaseEntity> purchaseEntities = purchasesRepository.findAllByCustomerId(customerId);
        return purchaseEntities.stream().map(PurchaseDetailsConverter::from).collect(Collectors.toList());
    }

    public List<PurchaseDetails> getPurchasesByCouponId(Long couponId) throws ApplicationException {
        List<PurchaseEntity> purchaseEntities = purchasesRepository.findAllByCouponId(couponId);
        return purchaseEntities.stream().map(PurchaseDetailsConverter::from).collect(Collectors.toList());
    }

    public List<PurchaseDetails> getPurchasesDetails() {
        List<PurchaseEntity> purchaseEntities = purchasesRepository.findAll();
        return purchaseEntities.stream().map(PurchaseDetailsConverter::from).collect(Collectors.toList());
    }

    public List<Purchase> getAllPurchases() throws ApplicationException {
        List<PurchaseEntity> purchaseEntities = purchasesRepository.findAll();
        if (purchaseEntities.isEmpty()) {
            throw new ApplicationException(ErrorType.GENERAL_ERROR);
        }
        return purchaseEntities.stream().map(PurchaseDtoConverter::from).collect(Collectors.toList());
    }


    public boolean existById(Long id) {
        return purchasesRepository.existsById(id);
    }

    public List<Purchase> findPurchasesWithSortingAscending(String parameterToSortBy) {
        List<PurchaseEntity> purchaseEntities = purchasesRepository.findAll(Sort.by(Sort.Direction.ASC, parameterToSortBy));
        return purchaseEntities.stream().map(PurchaseDtoConverter::from).collect(Collectors.toList());
    }
    public List<Purchase> findPurchasesWithSortingDescending(String parameterToSortBy) {
        List<PurchaseEntity> purchaseEntities = purchasesRepository.findAll(Sort.by(Sort.Direction.DESC, parameterToSortBy));
        return purchaseEntities.stream().map(PurchaseDtoConverter::from).collect(Collectors.toList());
    }


    public List<Purchase> findPurchasesWithPagination(int offset, int pageSize) {
        Page<PurchaseEntity> purchasePagination =  purchasesRepository.findAll(PageRequest.of(offset, pageSize));
        return purchasePagination.map(PurchaseDtoConverter::from).stream().collect(Collectors.toList());
    }

    public List<Purchase> findPurchasesWithPaginationAndSortingAscending(int offset, int pageSize, String parameterToSortBy) {
        Page<PurchaseEntity> purchases = purchasesRepository.findAll(PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.ASC, parameterToSortBy)));
        return purchases.stream().map(PurchaseDtoConverter::from).collect(Collectors.toList());
    }

    public List<Purchase> findPurchasesWithPaginationAndSortingDescending(int offset, int pageSize, String parameterToSortBy) {
        Page<PurchaseEntity> purchases = purchasesRepository.findAll(PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.DESC, parameterToSortBy)));
        return purchases.stream().map(PurchaseDtoConverter::from).collect(Collectors.toList());
    }
}

