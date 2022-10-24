package com.john.coupons.controllers;

import com.john.coupons.dto.Category;
import com.john.coupons.exceptions.ApplicationException;
import com.john.coupons.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {


    private final CategoriesService categoriesService;

    @Autowired
    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) throws ApplicationException {
        return new ResponseEntity<>(categoriesService.createCategory(category), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") Long id, @RequestBody Category category) throws ApplicationException {
        return new ResponseEntity<>(categoriesService.updateCategory(id, category), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable("id") Long id) throws ApplicationException {
        return new ResponseEntity<>(categoriesService.getCategory(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() throws ApplicationException {
        return new ResponseEntity<>(categoriesService.getAllCategories(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id) throws ApplicationException {
        categoriesService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/isExistByName")
    public ResponseEntity<Boolean> isCategoryExistByName(@RequestParam("name") String name) throws ApplicationException {
        return new ResponseEntity<>(categoriesService.isCategoryExistByName(name), HttpStatus.OK);
    }

    @GetMapping("/parameterToSortByAscending")
    public ResponseEntity<List<Category>> findCategoriesWithSortingAsc(@RequestParam("sortAscending") String parameterToSortBy) throws ApplicationException {
        return new ResponseEntity<>(categoriesService.findCategoriesWithSortingAscending(parameterToSortBy), HttpStatus.OK);
    }

    @GetMapping("/parameterToSortByDescending")
    public ResponseEntity<List<Category>> findCategoriesWithSortingDesc(@RequestParam("sortDescending") String parameterToSortBy) throws ApplicationException {
        return new ResponseEntity<>(categoriesService.findCategoriesWithSortingDescending(parameterToSortBy), HttpStatus.OK);
    }

    @GetMapping("/pages/{pageNumber}/{pageSize}")
    public ResponseEntity<List<Category>> findCategoriesWithPagination(@PathVariable("pageNumber") int offset, @PathVariable("pageSize") int pageSize) throws ApplicationException {
        return new ResponseEntity<>(categoriesService.findCategoriesWithPagination(offset, pageSize), HttpStatus.OK);
    }

    @GetMapping("/pageAndSortAscending/{pageNumber}/{pageSize}")
    public ResponseEntity<List<Category>> getAllCategoriesWithPaginationAndSortingAsc(@PathVariable("pageNumber") int offset, @PathVariable("pageSize") int pageSize, @RequestParam("parameterToSortBy") String parameterToSortBy) throws ApplicationException {
        return new ResponseEntity<>(categoriesService.findCategoriesWithPaginationAndSortingAscending(offset, pageSize, parameterToSortBy), HttpStatus.OK);
    }

    @GetMapping("/pageAndSortDescending/{pageNumber}/{pageSize}")
    public ResponseEntity<List<Category>> getAllCategoriesWithPaginationAndSortingDesc(@PathVariable("pageNumber") int offset, @PathVariable("pageSize") int pageSize, @RequestParam("parameterToSortBy") String parameterToSortBy) throws ApplicationException {
        return new ResponseEntity<>(categoriesService.findCategoriesWithPaginationAndSortingDescending(offset, pageSize, parameterToSortBy), HttpStatus.OK);
    }
}
