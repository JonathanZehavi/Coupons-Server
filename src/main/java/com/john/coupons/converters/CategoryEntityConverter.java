package com.john.coupons.converters;

import com.john.coupons.dto.Category;
import com.john.coupons.entities.CategoryEntity;

public class CategoryEntityConverter {

    public static CategoryEntity from(Category category){
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(category.getName());
        return categoryEntity;
    }
}
