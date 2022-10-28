package com.john.coupons.service;

import com.john.coupons.converters.*;
import com.john.coupons.dto.*;
import com.john.coupons.entities.CouponEntity;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchasesService {

    private final PurchasesRepository purchasesRepository;
    private final PurchaseValidations purchaseValidations;
    private final CouponsService couponsService;
    private final CustomersService customersService;
    private final CompaniesService companiesService;


    @Autowired
    public PurchasesService(PurchasesRepository purchasesRepository, PurchaseValidations purchaseValidations, CouponsService couponsService, CustomersService customersService, CompaniesService companiesService) {
        this.purchasesRepository = purchasesRepository;
        this.purchaseValidations = purchaseValidations;
        this.couponsService = couponsService;
        this.customersService = customersService;
        this.companiesService = companiesService;
    }

    public Purchase createPurchase(Purchase purchase) throws ApplicationException {
        Customer customer = customersService.getCustomerById(purchase.getCustomerId());
        purchase.setAmount(purchase.getCoupons().size());
        purchaseValidations.validatePurchase(purchase);
        PurchaseEntity purchaseEntity = PurchaseEntityConverter.from(purchase);
        List<Coupon> coupons = purchase.getCoupons();
        HashMap<Long, List<Coupon>> map = new HashMap<>();
        for (Coupon coupon : coupons) {
            Company company = companiesService.getCompany(coupon.getCompanyId());
            CouponEntity couponEntity = CouponEntityConverter.from(coupon);
            List<Coupon> couponsWithTheSameId = map.computeIfAbsent(coupon.getId(), k -> new ArrayList<>());
            couponsWithTheSameId.add(coupon);
            couponEntity.setCompany(CompanyEntityConverter.from(company));
            int amount = map.get(coupon.getId()).size();
            couponEntity.setAmount(coupon.getAmount() - amount);
            couponsService.updateCoupon(coupon.getId(), CouponDtoConverter.from(couponEntity));
            purchaseEntity.setCoupons(purchaseEntity.getCoupons().stream().filter(couponToFilter -> couponToFilter.getCompany() != null).collect(Collectors.toList()));
            purchaseEntity.addCoupon(couponEntity);
        }
        purchaseEntity.setCustomer(CustomerEntityConverter.from(customer));
        purchaseEntity = purchasesRepository.save(purchaseEntity);
        return PurchaseDtoConverter.from(purchaseEntity);
    }


    public Purchase updatePurchase(Long id, Purchase purchase) throws ApplicationException {
        purchaseValidations.validatePurchase(purchase);
        Customer customer = customersService.getCustomerById(purchase.getId());
        purchase.setId(id);
        PurchaseEntity purchaseEntity = PurchaseEntityConverter.from(purchase);
        purchasesRepository.save(purchaseEntity);
        purchaseEntity.setCustomer(CustomerEntityConverter.from(customer));
        return PurchaseDtoConverter.from(purchaseEntity);
    }

    public Purchase getPurchaseById(Long id) throws ApplicationException {
        PurchaseEntity purchaseEntity = purchasesRepository.findById(id).orElseThrow(() ->
                new ApplicationException(ErrorType.ID_DOES_NOT_EXIST));
        return PurchaseDtoConverter.from(purchaseEntity);
    }

    public void deletePurchase(Long id) throws ApplicationException {
        if (id == null || id <= 0) {
            throw new ApplicationException(ErrorType.ID_DOES_NOT_EXIST);
        }
        purchasesRepository.deleteById(id);
    }

    public List<PurchaseDetails> getPurchasesByCustomerId(Long customerId) throws ApplicationException {
        List<PurchaseEntity> purchaseEntities = purchasesRepository.findAllByCustomerId(customerId);
        if (purchaseEntities.isEmpty()) {
            throw new ApplicationException(ErrorType.EMPTY_LIST);
        }
        return purchaseEntities.stream().map(PurchaseDetailsConverter::from).collect(Collectors.toList());
    }


    public List<PurchaseDetails> getPurchasesDetails() throws ApplicationException {
        List<PurchaseEntity> purchaseEntities = purchasesRepository.findAll();
        if (purchaseEntities.isEmpty()) {
            throw new ApplicationException(ErrorType.EMPTY_LIST);
        }
        return purchaseEntities.stream().map(PurchaseDetailsConverter::from).collect(Collectors.toList());
    }

    public PurchaseDetails getPurchasesDetailsById(Long purchaseId) throws ApplicationException {
        PurchaseEntity purchaseEntities = purchasesRepository.findById(purchaseId).orElseThrow(() ->
                new ApplicationException(ErrorType.ID_DOES_NOT_EXIST));
        return PurchaseDetailsConverter.from(purchaseEntities);
    }

    public List<Purchase> getAllPurchases() throws ApplicationException {
        List<PurchaseEntity> purchaseEntities = purchasesRepository.findAll();
        if (purchaseEntities.isEmpty()) {
            throw new ApplicationException(ErrorType.GENERAL_ERROR);
        }
        return purchaseEntities.stream().map(PurchaseDtoConverter::from).collect(Collectors.toList());
    }


    public boolean existById(Long id) throws ApplicationException {
        if (id == null) {
            throw new ApplicationException(ErrorType.INVALID_ID);
        }
        return purchasesRepository.existsById(id);
    }

    public List<Purchase> findPurchasesWithSortingAscending(String parameterToSortBy) throws ApplicationException {
        List<PurchaseEntity> purchaseEntities = purchasesRepository.findAll(Sort.by(Sort.Direction.ASC, parameterToSortBy));
        if (purchaseEntities.isEmpty()){
            throw new ApplicationException(ErrorType.EMPTY_LIST);
        }
        return purchaseEntities.stream().map(PurchaseDtoConverter::from).collect(Collectors.toList());
    }

    public List<Purchase> findPurchasesWithSortingDescending(String parameterToSortBy) throws ApplicationException {
        List<PurchaseEntity> purchaseEntities = purchasesRepository.findAll(Sort.by(Sort.Direction.DESC, parameterToSortBy));
        if (purchaseEntities.isEmpty()){
            throw new ApplicationException(ErrorType.EMPTY_LIST);
        }
        return purchaseEntities.stream().map(PurchaseDtoConverter::from).collect(Collectors.toList());
    }


    public List<Purchase> findPurchasesWithPagination(int offset, int pageSize) throws ApplicationException {
        Page<PurchaseEntity> purchasePagination = purchasesRepository.findAll(PageRequest.of(offset, pageSize));
        if (purchasePagination.isEmpty()){
            throw new ApplicationException(ErrorType.EMPTY_LIST);
        }
        return purchasePagination.map(PurchaseDtoConverter::from).stream().collect(Collectors.toList());
    }

    public List<Purchase> findPurchasesWithPaginationAndSortingAscending(int offset, int pageSize, String parameterToSortBy) throws ApplicationException {
        Page<PurchaseEntity> purchases = purchasesRepository.findAll(PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.ASC, parameterToSortBy)));
        if (purchases.isEmpty()){
            throw new ApplicationException(ErrorType.EMPTY_LIST);
        }
        return purchases.stream().map(PurchaseDtoConverter::from).collect(Collectors.toList());
    }

    public List<Purchase> findPurchasesWithPaginationAndSortingDescending(int offset, int pageSize, String parameterToSortBy) throws ApplicationException {
        Page<PurchaseEntity> purchases = purchasesRepository.findAll(PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.DESC, parameterToSortBy)));
        if (purchases.isEmpty()){
            throw new ApplicationException(ErrorType.EMPTY_LIST);
        }
        return purchases.stream().map(PurchaseDtoConverter::from).collect(Collectors.toList());
    }
}

