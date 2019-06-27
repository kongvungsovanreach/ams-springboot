package com.kshrd.btb.holymomo.service.ArticleService;

import com.kshrd.btb.holymomo.repository.model.Category;
import java.util.List;

public interface CategoryService {
    void add(Category category);
    void delete(int id);
    void deleteAll();
    void update(Category category);
    Category findById(int id);
    List<Category> findAll();
    Integer nextCategoryId();
}