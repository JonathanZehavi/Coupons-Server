package com.john.coupons.converters;

import com.john.coupons.dto.Category;
import com.john.coupons.entities.CategoryEntity;

public class CategoryDtoConverter {

    public static Category from(CategoryEntity categoryEntity){
        Category category = new Category();
        category.setId(categoryEntity.getId());
        category.setName(categoryEntity.getName());
        return category;
    }
}
