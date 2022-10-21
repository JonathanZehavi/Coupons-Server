package com.john.coupons.controllers;

import com.john.coupons.dto.Company;
import com.john.coupons.exceptions.ApplicationException;
import com.john.coupons.service.CompaniesService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class CompaniesControllerTest {

    @Mock
    private CompaniesService companiesService;
    private CompaniesController underTest;

    @BeforeEach
    void setUp() {
        underTest = new CompaniesController(companiesService);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateCompany() throws ApplicationException {
        Company company = new Company(
                1L,
                "TravelCO",
                "0547479929",
                "Israel"
        );

        underTest.createCompany(company);

        ArgumentCaptor<Company> companyArgumentCaptor = ArgumentCaptor.forClass(Company.class);
        verify(companiesService).createCompany(companyArgumentCaptor.capture());

        Company companyValue = companyArgumentCaptor.getValue();

        assertThat(companyValue).isEqualTo(company);
    }
    @Test(expected = NullPointerException.class)
    public void testGetCompany() throws ApplicationException {
        underTest.getCompany(1L);
        verify(companiesService).getCompany(1L);
    }

    @Test(expected = NullPointerException.class)
    public void testGetAllCompanies() throws ApplicationException {
        underTest.getAllCompanies();
        verify(companiesService).getAllCompanies();
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateCompany() throws ApplicationException {
        Company company = companiesService.getCompany(1L);

        company.setCompanyName("Travel");
        company.setAddress("Israel");
        company.setPhoneNumber("054747799");

        underTest.updateCompany(company.getId(), company);

        ArgumentCaptor<Company> companyArgumentCaptor = ArgumentCaptor.forClass(Company.class);
        Company companyValue = companyArgumentCaptor.getValue();

        assertThat(companyValue).isEqualTo(company);

    }

    @Test(expected = NullPointerException.class)
    public void testDeleteCompany() throws ApplicationException {
        underTest.deleteCompany(1L);
        verify(companiesService).deleteCompany(1L);
    }

}