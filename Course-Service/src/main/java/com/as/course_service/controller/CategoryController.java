package com.as.course_service.controller;

import com.as.course_service.dto.CategoryRequest;
import com.as.course_service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostMapping()
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequest request){
        service.createCategory(request);
        return ResponseEntity.ok("Created successfully");
    }

}
