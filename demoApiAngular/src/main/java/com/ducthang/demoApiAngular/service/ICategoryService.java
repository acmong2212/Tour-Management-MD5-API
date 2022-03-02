package com.ducthang.demoApiAngular.service;

import com.ducthang.demoApiAngular.model.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAllCategories();
}
