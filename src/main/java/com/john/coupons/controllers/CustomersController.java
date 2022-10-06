package com.john.coupons.controllers;

import com.john.coupons.dto.Customer;
import com.john.coupons.exceptions.ApplicationException;
import com.john.coupons.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    private final CustomersService customersService;

    @Autowired
    public CustomersController(CustomersService customersService) {
        this.customersService = customersService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) throws ApplicationException {
        return new ResponseEntity<>(customersService.createCustomer(customer), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) throws ApplicationException {
        return new ResponseEntity<>(customersService.updateCustomer(id, customer), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id) throws ApplicationException {
        return new ResponseEntity<>(customersService.getCustomerById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long id) throws ApplicationException {
        customersService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() throws ApplicationException {
        return new ResponseEntity<>(customersService.getAllCustomers(), HttpStatus.OK);
    }


    @GetMapping("/parameterToSortByAscending")
    public ResponseEntity<List<Customer>> findCustomersWithSortingAsc(@RequestParam("sortAscending") String parameterToSortBy) {
        return new ResponseEntity<>(customersService.findCustomersWithSortingAscending(parameterToSortBy), HttpStatus.OK);
    }

    @GetMapping("/parameterToSortByDescending")
    public ResponseEntity<List<Customer>> findCustomersWithSortingDesc(@RequestParam("sortDescending") String parameterToSortBy) {
        return new ResponseEntity<>(customersService.findCustomersWithSortingDescending(parameterToSortBy), HttpStatus.OK);
    }

    @GetMapping("/pages")
    public ResponseEntity<List<Customer>> findCustomersWithPagination(@PathVariable("pageNumber") int offset, @PathVariable("pageSize") int pageSize) {
        return new ResponseEntity<>(customersService.findCustomersWithPagination(offset, pageSize), HttpStatus.OK);
    }

    @GetMapping("/pageAndSortAscending")
    public ResponseEntity<List<Customer>> getAllCustomersWithPaginationAndSortingAsc(@PathVariable("pageNumber") int offset, @PathVariable("pageSize") int pageSize, @RequestParam("parameterToSortBy") String parameterToSortBy) {
        return new ResponseEntity<>(customersService.findCustomersWithPaginationAndSortingAscending(offset, pageSize, parameterToSortBy), HttpStatus.OK);
    }
    @GetMapping("/pageAndSortDescending")
    public ResponseEntity<List<Customer>> getAllCustomersWithPaginationAndSortingDesc(@PathVariable("pageNumber") int offset, @PathVariable("pageSize") int pageSize, @RequestParam("parameterToSortBy") String parameterToSortBy) {
        return new ResponseEntity<>(customersService.findCustomersWithPaginationAndSortingDescending(offset, pageSize, parameterToSortBy), HttpStatus.OK);
    }

}

