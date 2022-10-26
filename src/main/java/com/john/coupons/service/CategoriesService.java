package com.john.coupons.service;

import com.john.coupons.converters.CategoryDtoConverter;
import com.john.coupons.converters.CategoryEntityConverter;
import com.john.coupons.dto.Category;
import com.john.coupons.entities.CategoryEntity;
import com.john.coupons.enums.ErrorType;
import com.john.coupons.exceptions.ApplicationException;
import com.john.coupons.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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


    public Category createCategory(Category category) throws ApplicationException {
        if (category.getName() == null){
            throw new ApplicationException(ErrorType.INVALID_NAME);
        }
        CategoryEntity categoryEntity = CategoryEntityConverter.from(category);
        categoryEntity = categoriesRepository.save(categoryEntity);
        return CategoryDtoConverter.from(categoryEntity);
    }

    public Category getCategory(Long id) throws ApplicationException {
        CategoryEntity categoryEntity = categoriesRepository.findById(id).orElseThrow(() ->
                new ApplicationException(ErrorType.ID_DOES_NOT_EXIST));
        return CategoryDtoConverter.from(categoryEntity);
    }

    public Category getCategoryByName(String name) throws ApplicationException {
        if (name == null){
            throw new ApplicationException(ErrorType.INVALID_NAME);
        }
        CategoryEntity categoryEntity = categoriesRepository.findByName(name);
        return CategoryDtoConverter.from(categoryEntity);
    }

    public Category updateCategory(Long id, Category category) throws ApplicationException  {
        if (category.getName() == null){
            throw new ApplicationException(ErrorType.INVALID_NAME);
        }
        CategoryEntity categoryEntity = CategoryEntityConverter.from(category);
        categoryEntity.setId(id);
        categoryEntity = categoriesRepository.save(categoryEntity);
        return CategoryDtoConverter.from(categoryEntity);
    }

    public List<Category> getAllCategories() throws ApplicationException  {
        List<CategoryEntity> categoryEntityList = categoriesRepository.findAll();
        if (categoryEntityList.isEmpty()){
            throw new ApplicationException(ErrorType.EMPTY_LIST);
        }
        return categoryEntityList.stream().map(CategoryDtoConverter::from).collect(Collectors.toList());
    }

    public void deleteCategory(Long id) throws ApplicationException {
        if (id == null){
            throw new ApplicationException(ErrorType.INVALID_ID);
        }
        categoriesRepository.deleteById(id);
    }

    public Boolean isCategoryExistByName(String name) throws ApplicationException {
        if (name == null){
            throw new ApplicationException(ErrorType.INVALID_NAME);
        }
        return categoriesRepository.existsByName(name);
    }

    public List<Category> findCategoriesWithSortingAscending(String parameterToSortBy) throws ApplicationException {
        List<CategoryEntity> categoryEntityList = categoriesRepository.findAll(Sort.by(Sort.Direction.ASC, parameterToSortBy));
        if (categoryEntityList.isEmpty()){
            throw new ApplicationException(ErrorType.EMPTY_LIST);
        }
        return categoryEntityList.stream().map(CategoryDtoConverter::from).collect(Collectors.toList());
    }

    public List<Category> findCategoriesWithSortingDescending(String parameterToSortBy) throws ApplicationException {
        List<CategoryEntity> categoryEntityList = categoriesRepository.findAll(Sort.by(Sort.Direction.DESC, parameterToSortBy));
        if (categoryEntityList.isEmpty()){
            throw new ApplicationException(ErrorType.EMPTY_LIST);
        }
        return categoryEntityList.stream().map(CategoryDtoConverter::from).collect(Collectors.toList());
    }

    public List<Category> findCategoriesWithPagination(int offset, int pageSize) throws ApplicationException {
        Page<CategoryEntity> categoryPagination = categoriesRepository.findAll(PageRequest.of(offset, pageSize));
        if (categoryPagination.isEmpty()){
            throw new ApplicationException(ErrorType.EMPTY_LIST);
        }
        return categoryPagination.stream().map(CategoryDtoConverter::from).collect(Collectors.toList());
    }

    public List<Category> findCategoriesWithPaginationAndSortingAscending(int offset, int pageSize, String parameterToSortBy) throws ApplicationException {
        Page<CategoryEntity> categoryEntities = categoriesRepository.findAll(PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.ASC, parameterToSortBy)));
        if (categoryEntities.isEmpty()){
            throw new ApplicationException(ErrorType.EMPTY_LIST);
        }
        return categoryEntities.stream().map(CategoryDtoConverter::from).collect(Collectors.toList());
    }

    public List<Category> findCategoriesWithPaginationAndSortingDescending(int offset, int pageSize, String parameterToSortBy) throws ApplicationException {
        Page<CategoryEntity> categoryEntities = categoriesRepository.findAll(PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.DESC, parameterToSortBy)));
        if (categoryEntities.isEmpty()){
            throw new ApplicationException(ErrorType.EMPTY_LIST);
        }
        return categoryEntities.stream().map(CategoryDtoConverter::from).collect(Collectors.toList());
    }
}
