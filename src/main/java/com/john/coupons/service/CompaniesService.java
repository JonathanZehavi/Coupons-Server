package com.john.coupons.service;

import com.john.coupons.converters.CompanyDtoConverter;
import com.john.coupons.converters.CompanyEntityConverter;
import com.john.coupons.dto.Company;
import com.john.coupons.entities.CompanyEntity;
import com.john.coupons.enums.ErrorType;
import com.john.coupons.exceptions.ApplicationException;
import com.john.coupons.repositories.CompaniesRepository;
import com.john.coupons.validations.CompanyValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompaniesService {

    private final CompaniesRepository companiesRepository;
    private final CompanyValidations companyValidations;

    @Autowired
    public CompaniesService(CompaniesRepository companiesRepository, @Lazy CompanyValidations companyValidations) {
        this.companiesRepository = companiesRepository;
        this.companyValidations = companyValidations;
    }


    public Company createCompany(Company company) throws ApplicationException {
        companyValidations.validateCompany(company);
        CompanyEntity companyEntity = CompanyEntityConverter.from(company);
        companyEntity = companiesRepository.save(companyEntity);
        return CompanyDtoConverter.from(companyEntity);
    }

    public Company updateCompany(Long id, Company company) throws ApplicationException {
        companyValidations.validateCompany(company);
        company = getCompany(id);
        CompanyEntity companyEntity = CompanyEntityConverter.from(company);
        companyEntity = companiesRepository.save(companyEntity);
        return CompanyDtoConverter.from(companyEntity);
    }

    public Company getCompany(Long id) throws ApplicationException {
        CompanyEntity companyEntity = companiesRepository.findById(id).orElseThrow(() ->
                new ApplicationException(ErrorType.ID_DOES_NOT_EXIST));
        return CompanyDtoConverter.from(companyEntity);
    }


    public void deleteCompany(Long id) throws ApplicationException {
        companiesRepository.deleteById(id);
    }

    public List<Company> getAllCompanies() throws ApplicationException {
        List<CompanyEntity> companyEntityList = companiesRepository.getAllCompanies();
        if (companyEntityList.isEmpty()) {
            throw new ApplicationException(ErrorType.GENERAL_ERROR);
        }
        return companyEntityList.stream().map(CompanyDtoConverter::from).collect(Collectors.toList());
    }

    public boolean existById(Long id) throws ApplicationException{
        if (id == null || id<=0){
            throw new ApplicationException(ErrorType.INVALID_ID);
        }
        return companiesRepository.existsById(id);
    }

    public boolean isCompanyNameExist(String companyName) throws ApplicationException {
        if (companyName == null) {
            throw new ApplicationException(ErrorType.INVALID_NAME);
        }
        return companiesRepository.existByName(companyName);
    }

    public List<Company> findCompaniesWithSortingAscending(String parameterToSortBy) {
        List<CompanyEntity> companyEntityList = companiesRepository.findAll(Sort.by(Sort.Direction.ASC, parameterToSortBy));
        return companyEntityList.stream().map(CompanyDtoConverter::from).collect(Collectors.toList());
    }

    public List<Company> findCompaniesWithSortingDescending(String parameterToSortBy) {
        List<CompanyEntity> companyEntityList = companiesRepository.findAll(Sort.by(Sort.Direction.DESC, parameterToSortBy));
        return companyEntityList.stream().map(CompanyDtoConverter::from).collect(Collectors.toList());
    }

    public List<Company> findCompaniesWithPagination(int offset, int pageSize) {
        Page<CompanyEntity> companiesPagination = companiesRepository.findAll(PageRequest.of(offset, pageSize));
        return companiesPagination.stream().map(CompanyDtoConverter::from).collect(Collectors.toList());
    }

    public List<Company> findCompaniesWithPaginationAndSortingAscending(int offset, int pageSize, String parameterToSortBy) {
        Page<CompanyEntity> companies = companiesRepository.findAll(PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.ASC, parameterToSortBy)));
        return companies.stream().map(CompanyDtoConverter::from).collect(Collectors.toList());
    }

    public List<Company> findCompaniesWithPaginationAndSortingDescending(int offset, int pageSize, String parameterToSortBy) {
        Page<CompanyEntity> companies = companiesRepository.findAll(PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.DESC, parameterToSortBy)));
        return companies.stream().map(CompanyDtoConverter::from).collect(Collectors.toList());
    }

}

