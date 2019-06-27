package com.kshrd.btb.holymomo.service;

import com.kshrd.btb.holymomo.repository.ArticleRepository.CategoryRepository;
import com.kshrd.btb.holymomo.repository.model.Category;
import com.kshrd.btb.holymomo.service.ArticleService.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void add(Category category) {
        categoryRepository.add(category);
    }

    @Override
    public void delete(int id) {
        categoryRepository.delete(id);
    }

    @Override
    public void deleteAll() {
        categoryRepository.deleteAll();
    }

    @Override
    public void update(Category category) {
        categoryRepository.update(category);
    }

    @Override
    public Category findById(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Integer nextCategoryId() {
        if (categoryRepository.nextCategoryId() == null && findAll().isEmpty()){
            return 1;
        }else {
            return categoryRepository.nextCategoryId()+1;
        }
    }
}
