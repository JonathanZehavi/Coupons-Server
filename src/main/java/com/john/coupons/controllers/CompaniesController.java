package com.john.coupons.controllers;

import com.john.coupons.dto.Company;
import com.john.coupons.exceptions.ApplicationException;
import com.john.coupons.service.CompaniesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompaniesController {

    private final CompaniesService companiesService;

    @Autowired
    CompaniesController(CompaniesService companiesService) {
        this.companiesService = companiesService;
    }

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company) throws ApplicationException {
        return new ResponseEntity<>(companiesService.createCompany(company), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable("id") Long id, @RequestBody Company company) throws ApplicationException {
        return new ResponseEntity<>(companiesService.updateCompany(id, company), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable("id") Long id) throws ApplicationException {
        return new ResponseEntity<>(companiesService.getCompany(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable("id") Long id) throws ApplicationException {
        companiesService.deleteCompany(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() throws ApplicationException {
        return new ResponseEntity<>(companiesService.getAllCompanies(), HttpStatus.OK);
    }

    @GetMapping("/isCompanyExist/{id}")
    public ResponseEntity<Boolean> isCompanyExist(@PathVariable("id") Long id) throws ApplicationException {
        return new ResponseEntity<>(companiesService.isExistById(id), HttpStatus.OK);
    }

    @GetMapping("/isCompanyNameExist")
    public ResponseEntity<Boolean> isCompanyNameExist(@RequestParam("companyName") String companyName) throws ApplicationException {
        return new ResponseEntity<>(companiesService.isCompanyNameExist(companyName), HttpStatus.OK);
    }

    @GetMapping("/isPhoneNumberExist")
    public ResponseEntity<Boolean> isPhoneNumberExist(@RequestParam("phoneNumber") String phoneNumber) throws ApplicationException {
        return new ResponseEntity<>(companiesService.isPhoneNumberExist(phoneNumber), HttpStatus.OK);
    }

    @GetMapping("/parameterToSortByAscending")
    public ResponseEntity<List<Company>> findCompaniesWithSortingAsc(@RequestParam("sortAscending") String parameterToSortBy) throws ApplicationException {
        return new ResponseEntity<>(companiesService.findCompaniesWithSortingAscending(parameterToSortBy), HttpStatus.OK);
    }

    @GetMapping("/parameterToSortByDescending")
    public ResponseEntity<List<Company>> findCompaniesWithSortingDesc(@RequestParam("sortDescending") String parameterToSortBy) throws ApplicationException {
        return new ResponseEntity<>(companiesService.findCompaniesWithSortingDescending(parameterToSortBy), HttpStatus.OK);
    }

    @GetMapping("/pages/{pageNumber}/{pageSize}")
    public ResponseEntity<List<Company>> findCompaniesWithPagination(@PathVariable("pageNumber") int offset, @PathVariable("pageSize") int pageSize) throws ApplicationException {
        return new ResponseEntity<>(companiesService.findCompaniesWithPagination(offset, pageSize), HttpStatus.OK);
    }

    @GetMapping("/pageAndSortAscending/{pageNumber}/{pageSize}")
    public ResponseEntity<List<Company>> getAllCompaniesWithPaginationAndSortingAsc(@PathVariable("pageNumber") int offset, @PathVariable("pageSize") int pageSize, @RequestParam("parameterToSortBy") String parameterToSortBy) throws ApplicationException {
        return new ResponseEntity<>(companiesService.findCompaniesWithPaginationAndSortingAscending(offset, pageSize, parameterToSortBy), HttpStatus.OK);
    }

    @GetMapping("/pageAndSortDescending/{pageNumber}/{pageSize}")
    public ResponseEntity<List<Company>> getAllCompaniesWithPaginationAndSortingDesc(@PathVariable("pageNumber") int offset, @PathVariable("pageSize") int pageSize, @RequestParam("parameterToSortBy") String parameterToSortBy) throws ApplicationException {
        return new ResponseEntity<>(companiesService.findCompaniesWithPaginationAndSortingDescending(offset, pageSize, parameterToSortBy), HttpStatus.OK);
    }
}

