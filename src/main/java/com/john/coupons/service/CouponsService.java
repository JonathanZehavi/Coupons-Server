package com.john.coupons.service;

import com.john.coupons.converters.*;
import com.john.coupons.dto.Category;
import com.john.coupons.dto.Company;
import com.john.coupons.dto.Coupon;
import com.john.coupons.entities.CouponEntity;
import com.john.coupons.enums.ErrorType;
import com.john.coupons.exceptions.ApplicationException;
import com.john.coupons.repositories.CouponsRepository;
import com.john.coupons.validations.CouponValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CouponsService {

    private final CouponsRepository couponsRepository;
    private final CompaniesService companiesService;
    private final CouponValidations couponValidations;
    private final CategoriesService categoriesService;


    @Autowired
    public CouponsService(CouponsRepository couponsRepository, CompaniesService companiesService, CouponValidations couponValidations, CategoriesService categoriesService) {
        this.couponsRepository = couponsRepository;
        this.companiesService = companiesService;
        this.couponValidations = couponValidations;
        this.categoriesService = categoriesService;
    }

    public Coupon createCoupon(Coupon coupon) throws ApplicationException {
        Company company = companiesService.getCompany(coupon.getCompanyId());

        couponValidations.validateCoupon(coupon);
        CouponEntity couponEntity = CouponEntityConverter.from(coupon);
        if (coupon.getStartDate().isBefore(LocalDate.now())) {
            throw new ApplicationException(ErrorType.COUPON_START_DATE_MUST_BE_IN_THE_FUTURE);
        }
        couponEntity.setCompany(CompanyEntityConverter.from(company));

        if (categoriesService.isCategoryExistByName(coupon.getCategory())) {
            Category category = categoriesService.getCategoryByName(coupon.getCategory());
            couponEntity.setCategory(category.getName());
        } else {
            Category category = new Category();
            category.setName(coupon.getCategory());
            categoriesService.createCategory(category);
            couponEntity.setCategory(category.getName());
        }
        couponEntity = couponsRepository.save(couponEntity);
        return CouponDtoConverter.from(couponEntity);
    }

    public Coupon updateCoupon(Long id, Coupon coupon) throws ApplicationException {
        couponValidations.validateCoupon(coupon);
        CouponEntity couponEntity = CouponEntityConverter.from(coupon);
        couponEntity.setId(id);
        couponEntity.setAmount(coupon.getAmount());
        Company company = companiesService.getCompany(coupon.getCompanyId());
        couponEntity.setCompany(CompanyEntityConverter.from(company));
        if (categoriesService.isCategoryExistByName(coupon.getCategory())) {
            Category category = categoriesService.getCategoryByName(coupon.getCategory());
            couponEntity.setCategory(category.getName());
        } else {
            Category category = new Category();
            category.setName(coupon.getCategory());
            categoriesService.createCategory(category);
            couponEntity.setCategory(category.getName());
        }
        couponEntity = couponsRepository.save(couponEntity);
        return CouponDtoConverter.from(couponEntity);
    }

    public Coupon getCouponById(Long id) throws ApplicationException {
        if (id == null) {
            throw new ApplicationException(ErrorType.INVALID_ID);
        }
        CouponEntity couponEntity = couponsRepository.findById(id).orElseThrow(() ->
                new ApplicationException(ErrorType.ID_DOES_NOT_EXIST));
        return CouponDtoConverter.from(couponEntity);
    }

    public void deleteCoupon(Long id) throws ApplicationException {
        couponsRepository.deleteById(id);
    }

    public List<Coupon> getAllExpiredCoupons() throws ApplicationException {
        List<CouponEntity> couponEntityList = couponsRepository.findAllByEndDateBefore();
        return couponEntityList.stream().map(CouponDtoConverter::from).collect(Collectors.toList());
    }

    public List<Coupon> getAllCoupons() throws ApplicationException {
        List<CouponEntity> couponEntityList = couponsRepository.getAllCoupons();
        return couponEntityList.stream().map(CouponDtoConverter::from).collect(Collectors.toList());
    }

    public List<Coupon> getCouponsByCompanyId(Long companyId) throws ApplicationException {
        if (companyId == null || companyId <= 0) {
            throw new ApplicationException(ErrorType.INVALID_ID);
        }
        List<CouponEntity> couponEntityList = couponsRepository.getCouponByCompanyId(companyId);
        return couponEntityList.stream().map(CouponDtoConverter::from).collect(Collectors.toList());
    }

    public List<Coupon> findByCategory(String category) throws ApplicationException {
        if (category == null) {
            throw new ApplicationException(ErrorType.INVALID_CATEGORY);
        }
        List<CouponEntity> couponEntityList = couponsRepository.getCouponsByCategory(category);
        return couponEntityList.stream().map(CouponDtoConverter::from).collect(Collectors.toList());
    }

    public Boolean existById(Long id) {
        return couponsRepository.existsById(id);
    }

    public List<Coupon> getCouponsByMaxPrice(float price) throws ApplicationException {
        List<CouponEntity> couponsList = couponsRepository.findByPriceLessThanEqual(price);
        if (couponsList.isEmpty()) {
            throw new ApplicationException(ErrorType.EMPTY_COUPONS_LIST);
        }
        return couponsList.stream().map(CouponDtoConverter::from).collect(Collectors.toList());
    }

    public List<Coupon> findCouponsWithSortingAscending(String parameterToSortBy) {
        List<CouponEntity> couponsEntity = couponsRepository.findAll(Sort.by(Sort.Direction.ASC, parameterToSortBy));
        return couponsEntity.stream().map(CouponDtoConverter::from).collect(Collectors.toList());
    }

    public List<Coupon> findCouponsWithSortingDescending(String parameterToSortBy) {
        List<CouponEntity> couponEntityList = couponsRepository.findAll(Sort.by(Sort.Direction.DESC, parameterToSortBy));
        return couponEntityList.stream().map(CouponDtoConverter::from).collect(Collectors.toList());
    }

    public List<Coupon> findCouponsWithPagination(int offset, int pageSize) {
        Page<CouponEntity> couponsPagination = couponsRepository.findAll(PageRequest.of(offset, pageSize));
        return couponsPagination.stream().map(CouponDtoConverter::from).collect(Collectors.toList());
    }

    public List<Coupon> findCouponsWithPaginationAndSortingAscending(int offset, int pageSize, String parameterToSortBy) {
        Page<CouponEntity> coupons = couponsRepository.findAll(PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.ASC, parameterToSortBy)));
        return coupons.stream().map(CouponDtoConverter::from).collect(Collectors.toList());
    }

    public List<Coupon> findCouponsWithPaginationAndSortingDescending(int offset, int pageSize, String parameterToSortBy) {
        Page<CouponEntity> coupons = couponsRepository.findAll(PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.DESC, parameterToSortBy)));
        return coupons.stream().map(CouponDtoConverter::from).collect(Collectors.toList());
    }
}

