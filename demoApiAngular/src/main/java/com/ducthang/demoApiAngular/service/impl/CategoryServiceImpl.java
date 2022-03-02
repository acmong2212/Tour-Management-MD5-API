package com.ducthang.demoApiAngular.service.impl;

import com.ducthang.demoApiAngular.model.Category;
import com.ducthang.demoApiAngular.repository.ICategoryRepository;
import com.ducthang.demoApiAngular.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    ICategoryRepository categoryRepository;

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }
}
