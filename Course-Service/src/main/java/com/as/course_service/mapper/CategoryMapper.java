package com.as.course_service.mapper;

import com.as.course_service.dto.CategoryRequest;
import com.as.course_service.dto.CategoryResponse;
import com.as.course_service.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toEntity(CategoryRequest request){
        return Category.builder()
                .name(request.getName())
                .build();
    }

    public CategoryResponse getResponse(Category category){
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
