package com.john.coupons.service;

import com.john.coupons.converters.CustomerDtoConverter;
import com.john.coupons.converters.CustomerEntityConverter;
import com.john.coupons.dto.Customer;
import com.john.coupons.entities.CustomerEntity;
import com.john.coupons.enums.ErrorType;
import com.john.coupons.enums.Role;
import com.john.coupons.exceptions.ApplicationException;
import com.john.coupons.repositories.CustomersRepository;
import com.john.coupons.validations.CustomerValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomersService {

    private final CustomersRepository customersRepository;
    private final UsersService usersService;
    private final CustomerValidations customerValidations;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomersService(CustomersRepository customersRepository, UsersService usersService, CustomerValidations customerValidations, PasswordEncoder passwordEncoder) {
        this.customersRepository = customersRepository;
        this.usersService = usersService;
        this.customerValidations = customerValidations;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Customer createCustomer(Customer customer) throws ApplicationException {
        customerValidations.validateCustomer(customer);
        CustomerEntity customerEntity = CustomerEntityConverter.from(customer);
        customerEntity.getUser().setPassword(passwordEncoder.encode(customer.getUser().getPassword()));
        customerEntity.getUser().setRole(Role.Customer);
        customerEntity = customersRepository.save(customerEntity);
        return CustomerDtoConverter.from(customerEntity);
    }

    @Transactional
    public Customer updateCustomer(Long id, Customer customer) throws ApplicationException {
        customerValidations.validateCustomer(customer);
        customer = getCustomerById(id);
        CustomerEntity customerEntity = CustomerEntityConverter.from(customer);
        customerEntity.getUser().setPassword(passwordEncoder.encode(customer.getUser().getPassword()));
        customerEntity.getUser().setRole(Role.Customer);
        customerEntity = customersRepository.save(customerEntity);
        return CustomerDtoConverter.from(customerEntity);
    }

    public Customer getCustomerById(Long id) throws ApplicationException {
        CustomerEntity customerEntity = customersRepository.findById(id).orElseThrow(() ->
                new ApplicationException(ErrorType.ID_DOES_NOT_EXIST));
        return CustomerDtoConverter.from(customerEntity);
    }

    public void deleteCustomer(Long id) throws ApplicationException {
        customersRepository.deleteById(id);
    }

    public List<Customer> getAllCustomers() throws ApplicationException {
        List<CustomerEntity> customersList = customersRepository.getAllCustomers();
        if (customersList.isEmpty()) {
            throw new ApplicationException(ErrorType.GENERAL_ERROR);
        }
        return customersList.stream().map(CustomerDtoConverter::from).collect(Collectors.toList());
    }

    public List<Customer> findCustomersWithSortingAscending(String parameterToSortBy) {
        List<CustomerEntity> customerEntityList = customersRepository.findAll(Sort.by(Sort.Direction.ASC, parameterToSortBy));
        return customerEntityList.stream().map(CustomerDtoConverter::from).collect(Collectors.toList());
    }

    public List<Customer> findCustomersWithSortingDescending(String parameterToSortBy) {
        List<CustomerEntity> customerEntityList = customersRepository.findAll(Sort.by(Sort.Direction.DESC, parameterToSortBy));
        return customerEntityList.stream().map(CustomerDtoConverter::from).collect(Collectors.toList());
    }

    public List<Customer> findCustomersWithPagination(int offset, int pageSize) {
        Page<CustomerEntity> customerEntityPage = customersRepository.findAll(PageRequest.of(offset, pageSize));
        return customerEntityPage.stream().map(CustomerDtoConverter::from).collect(Collectors.toList());
    }

    public List<Customer> findCustomersWithPaginationAndSortingAscending(int offset, int pageSize, String parameterToSortBy) {
        Page<CustomerEntity> customerEntityPage = customersRepository.findAll(PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.ASC, parameterToSortBy)));
        return customerEntityPage.stream().map(CustomerDtoConverter::from).collect(Collectors.toList());
    }

    public List<Customer> findCustomersWithPaginationAndSortingDescending(int offset, int pageSize, String parameterToSortBy) {
        Page<CustomerEntity> customerEntityPage = customersRepository.findAll(PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.DESC, parameterToSortBy)));
        return customerEntityPage.stream().map(CustomerDtoConverter::from).collect(Collectors.toList());
    }

}

