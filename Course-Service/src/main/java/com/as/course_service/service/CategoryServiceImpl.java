package com.as.course_service.service;

import com.as.course_service.dto.CategoryRequest;
import com.as.course_service.dto.CategoryResponse;
import com.as.course_service.dto.CourseRequest;
import com.as.course_service.dto.CourseResponse;
import com.as.course_service.mapper.CategoryMapper;
import com.as.course_service.model.Category;
import com.as.course_service.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        Category category = categoryMapper.toEntity(request);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.getResponse(savedCategory);
    }

    @Override
    public List<CategoryResponse> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponse> responses = new ArrayList<>();

        for(Category category: categories){
            responses.add(categoryMapper.getResponse(category));
        }
        return responses;
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()->
                new RuntimeException("Invalid  CategoryId"));
        return categoryMapper.getResponse(category);
    }
}
