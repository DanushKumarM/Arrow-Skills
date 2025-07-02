package com.as.course_service.service;

import com.as.course_service.dto.CategoryRequest;
import com.as.course_service.dto.CategoryResponse;
import com.as.course_service.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    CategoryResponse createCategory(CategoryRequest request);

   List<CategoryResponse> getAllCategory();

   CategoryResponse getCategoryById(Long id);
}
