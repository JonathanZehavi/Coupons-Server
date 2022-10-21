package com.john.coupons.service;

import com.john.coupons.converters.CategoryDtoConverter;
import com.john.coupons.converters.CategoryEntityConverter;
import com.john.coupons.dto.Category;
import com.john.coupons.entities.CategoryEntity;
import com.john.coupons.enums.ErrorType;
import com.john.coupons.exceptions.ApplicationException;
import com.john.coupons.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;

    @Autowired
    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }


    public Category createCategory(Category category){
        CategoryEntity categoryEntity = CategoryEntityConverter.from(category);
        categoryEntity = categoriesRepository.save(categoryEntity);
        return CategoryDtoConverter.from(categoryEntity);
    }

    public Category getCategory(Long id) throws ApplicationException {
        CategoryEntity categoryEntity = categoriesRepository.findById(id).orElseThrow(() ->
                new ApplicationException(ErrorType.ID_DOES_NOT_EXIST));
        return CategoryDtoConverter.from(categoryEntity);
    }

    public Category getCategoryByName(String name) {
        CategoryEntity categoryEntity = categoriesRepository.findByName(name);
        return CategoryDtoConverter.from(categoryEntity);
    }

    public Category updateCategory(Long id, Category category) {
        CategoryEntity categoryEntity = CategoryEntityConverter.from(category);
        categoryEntity.setId(id);
        categoryEntity = categoriesRepository.save(categoryEntity);
        return CategoryDtoConverter.from(categoryEntity);
    }

    public List<Category> getAllCategories() {
        List<CategoryEntity> categoryEntityList = categoriesRepository.findAll();
        return categoryEntityList.stream().map(CategoryDtoConverter::from).collect(Collectors.toList());
    }

    public void deleteCategory(Long id) {
        categoriesRepository.deleteById(id);
    }

    public Boolean isCategoryExistByName(String name) {
        return categoriesRepository.existsByName(name);
    }
}
